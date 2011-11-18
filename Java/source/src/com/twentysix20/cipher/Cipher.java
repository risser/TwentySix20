package com.twentysix20.cipher;

public interface Cipher {
	String encode(String plaintext);
	String decode(String encodedMessage);
}
