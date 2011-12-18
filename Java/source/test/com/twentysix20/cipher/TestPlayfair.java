package com.twentysix20.cipher;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestPlayfair {
//	@Test public void encoding() {
//	Cipher cipher = new PlayfairCipher("KEYWORD", PlayfairAlphabet.REMOVED_Q);
//	assertEquals("GYMSUDWCPBHVEYSYIP", cipher.encode("Help me Obi Wan Kenobi"));
//}
	@Test public void basicEncodingWithNoQ() {
		Cipher cipher = new PlayfairCipher("KEYWORD", PlayfairAlphabet.REMOVE_Q);
		assertEquals("EL", cipher.encode("KM"));
	}
	@Test public void basicEncodingWithLowercase() {
		Cipher cipher = new PlayfairCipher("KEYWORD", PlayfairAlphabet.REMOVE_Q);
		assertEquals("EL", cipher.encode("km"));
	}
	@Test public void basicEncodingWithNoJ() {
		Cipher cipher = new PlayfairCipher("KEYWORD", PlayfairAlphabet.REPLACE_J_WITH_I);
		assertEquals("EM", cipher.encode("KN"));
	}
	@Test public void encodingJWordWithNoJ() {
		Cipher cipher = new PlayfairCipher("KEYWORD", PlayfairAlphabet.REPLACE_J_WITH_I);
		assertEquals("QWLFIZ", cipher.encode("JILL"));
	}
	@Test public void encodingJWordAndJKeywordWithNoJ() {
		Cipher cipher = new PlayfairCipher("JAMESBOND", PlayfairAlphabet.REPLACE_J_WITH_I);
		assertEquals("MVSFHZ", cipher.encode("JILL"));
	}
	@Test public void encodingWithSameColumn() {
		Cipher cipher = new PlayfairCipher("KEYWORD", PlayfairAlphabet.REPLACE_J_WITH_I);
		assertEquals("RT", cipher.encode("KM"));
		assertEquals("TR", cipher.encode("MK"));
	}
	@Test public void encodingWithSameColumnAndWraparound() {
		Cipher cipher = new PlayfairCipher("KEYWORD", PlayfairAlphabet.REPLACE_J_WITH_I);
		assertEquals("MK", cipher.encode("FT"));
	}
	@Test public void encodingWithSameRow() {
		Cipher cipher = new PlayfairCipher("KEYWORD", PlayfairAlphabet.REPLACE_J_WITH_I);
		assertEquals("GL", cipher.encode("FI"));
		assertEquals("LG", cipher.encode("IF"));
	}
	@Test public void encodingWithSameRowAndWraparound() {
		Cipher cipher = new PlayfairCipher("KEYWORD", PlayfairAlphabet.REPLACE_J_WITH_I);
		assertEquals("PM", cipher.encode("NS"));
	}
	@Test public void encodingOddLetters() {
		Cipher cipher = new PlayfairCipher("KEYWORD", PlayfairAlphabet.REPLACE_J_WITH_I);
		assertEquals("ESWU", cipher.encode("ONE"));
	}
	@Test public void encodingSpaces() {
		Cipher cipher = new PlayfairCipher("KEYWORD", PlayfairAlphabet.REPLACE_J_WITH_I);
		assertEquals("ESYKSEUD", cipher.encode("ONE ON ONE"));
		assertEquals("ESKUOKESWU", cipher.encode("ONE TWO ONE"));
	}
	@Test public void encodingDoubleLetters() {
		Cipher cipher = new PlayfairCipher("KEYWORD", PlayfairAlphabet.REPLACE_J_WITH_I);
		assertEquals("MQWZES", cipher.encode("SPOON"));
		assertEquals("MQWZESQZ", cipher.encode("SPOONS"));
		assertEquals("SEES", cipher.encode("NOON"));
	}
//	@Test public void encodingOddWithXOnEnd() {
//		Cipher cipher = new PlayfairCipher("KEYWORD", PlayfairAlphabet.REPLACE_J_WITH_I);
//		assertEquals("CWZZ", cipher.encode("BOX"));
//	}
	@Test public void basicDecodingWithNoQ() {
		Cipher cipher = new PlayfairCipher("KEYWORD", PlayfairAlphabet.REMOVE_Q);
		assertEquals("KM", cipher.decode("EL"));
	}
	@Test public void basicDecodingWithLowercase() {
		Cipher cipher = new PlayfairCipher("KEYWORD", PlayfairAlphabet.REMOVE_Q);
		assertEquals("KM", cipher.decode("el"));
	}
	@Test public void basicDecodingWithNoJ() {
		Cipher cipher = new PlayfairCipher("KEYWORD", PlayfairAlphabet.REPLACE_J_WITH_I);
		assertEquals("KN", cipher.decode("EM"));
	}
	@Test public void decodingWithSameColumn() {
		Cipher cipher = new PlayfairCipher("KEYWORD", PlayfairAlphabet.REPLACE_J_WITH_I);
		assertEquals("KM", cipher.decode("RT"));
		assertEquals("MK", cipher.decode("TR"));
	}
	@Test public void decodingWithSameColumnAndWraparound() {
		Cipher cipher = new PlayfairCipher("KEYWORD", PlayfairAlphabet.REPLACE_J_WITH_I);
		assertEquals("FT", cipher.decode("MK"));
	}
	@Test public void decodingWithSameRow() {
		Cipher cipher = new PlayfairCipher("KEYWORD", PlayfairAlphabet.REPLACE_J_WITH_I);
		assertEquals("FI", cipher.decode("GL"));
		assertEquals("IF", cipher.decode("LG"));
	}
	@Test public void decodingWithSameRowAndWraparound() {
		Cipher cipher = new PlayfairCipher("KEYWORD", PlayfairAlphabet.REPLACE_J_WITH_I);
		assertEquals("NS", cipher.decode("PM"));
	}
	@Test public void decodingOddLetters() {
		Cipher cipher = new PlayfairCipher("KEYWORD", PlayfairAlphabet.REPLACE_J_WITH_I);
		assertEquals("ONEX", cipher.decode("ESWU"));
	}
	@Test public void decodingSpaces() {
		Cipher cipher = new PlayfairCipher("KEYWORD", PlayfairAlphabet.REMOVE_Q);
		assertEquals("ONETWOONEX", cipher.decode("YSK UOKY SWU"));
		assertEquals("ONEONONE", cipher.decode("YSY KS-YMY"));
	}
	@Test public void decodingDoubleLetters() {
		Cipher cipher = new PlayfairCipher("KEYWORD", PlayfairAlphabet.REPLACE_J_WITH_I);
		assertEquals("SPOXON", cipher.decode("MQWZES"));
		assertEquals("SPOXONSX", cipher.decode("MQWZESQZ"));
		assertEquals("NOON", cipher.decode("SEES"));
	}
}
