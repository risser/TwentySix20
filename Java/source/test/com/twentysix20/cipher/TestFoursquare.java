package com.twentysix20.cipher;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestFoursquare {
	@Test public void encoding() {
		Cipher cipher = new FoursquareCipher("EXAMPLE", "KEYWORD");
		assertEquals("FYGMKYHOBXMFKKKIMD", cipher.encode("Help me Obi Wan Kenobi"));
	}

	@Test public void aDifferentEncoding() {
		Cipher cipher = new FoursquareCipher("FOURSUARE", "CIPHERS");
		assertEquals("OOVTPBNMGPPOOU", cipher.encode("Cryptips Master"));
	}

	@Test public void encodeKeywordContainsDroppedLetter() {
		Cipher cipher = new FoursquareCipher("FOURSQUARE", "CIPHERS");
		assertEquals("OOVTPBNMGPPOOU", cipher.encode("Cryptips Master"));
	}

	@Test public void encodingPlainTextHasDroppedLetter() {
		Cipher cipher = new FoursquareCipher("EXAMPLE", "KEYWORD");
		assertEquals("FYGMKYHOBXMFKKKIMD", cipher.encode("Help me Qobi Wan Kenobi"));
	}

	@Test public void encodingOddNumber() {
		Cipher cipher = new FoursquareCipher("EXAMPLE", "KEYWORD");
		assertEquals("FYGMKYHOBXMFKKKIAU", cipher.encode("Help me Obi Wan Kenob"));
	}

	@Test public void encodingIgnoreJNotQ() {
		Cipher cipher = new FoursquareCipher("EXAMPLE", "KEYWORD",'J');
		assertEquals("FYNFNEHWBXAFFOKHMD", cipher.encode("Help me Obi Wan Kenobi"));
	}

	@Test public void decoding() {
		Cipher cipher = new FoursquareCipher("EXAMPLE", "KEYWORD");
		assertEquals("HELPMEOBIWANKENOBI", cipher.decode("FYGMKYHOBXMFKKKIMD"));
	}

	@Test public void decodingOddNumber() {
		Cipher cipher = new FoursquareCipher("EXAMPLE", "KEYWORD");
		assertEquals("HELPMEOBIWANKENOBX", cipher.decode("FYGMKYHOBXMFKKKIAU"));
	}

	@Test public void decodingWithSpaces() {
		Cipher cipher = new FoursquareCipher("FOURSUARE", "CIPHERS");
		assertEquals("CRYPTIPS MASTER.", cipher.decode("OOVTPBNM GPPOOU."));
	}

	@Test public void decodingKeywordWithDroppedLetter() {
		Cipher cipher = new FoursquareCipher("FOURSQUARE", "CIPHERS");
		assertEquals("CRYPTIPSMASTER", cipher.decode("OOVTPBNMGPPOOU"));
	}

	@Test public void decodingIgnoreJNotQ() {
		Cipher cipher = new FoursquareCipher("EXAMPLE", "KEYWORD",'J');
		assertEquals("HELPMEOBIWANKENOBI", cipher.decode("FYNFNEHWBXAFFOKHMD"));
	}
}
