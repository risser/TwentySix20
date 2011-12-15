package com.twentysix20.cipher;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestPlayfair {
	@Test public void encoding() {
		Cipher cipher = new PlayfairCipher("KEYWORD", PlayfairAlphabet.REMOVED_Q);
//		assertEquals("gymsudwcpbhveysyip", cipher.encode("Help me Obi Wan Kenobi"));
	}
}
