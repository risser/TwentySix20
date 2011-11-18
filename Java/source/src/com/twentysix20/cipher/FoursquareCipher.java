package com.twentysix20.cipher;

public class FoursquareCipher implements Cipher {
	private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private final String plainBox;
	private final String upperRightBox;
	private final String lowerLeftBox;

	public FoursquareCipher(String key1, String key2) {
		this(key1, key2, 'Q');
	}

	public FoursquareCipher(String key1, String key2, char c) {
		plainBox = ALPHABET.replaceFirst(""+c, "");
		upperRightBox = generateBox(key1.replaceFirst(""+c, ""));
		lowerLeftBox = generateBox(key2.replaceFirst(""+c, ""));
	}

	@Override
	public String encode(String plaintext) {
		String preEncodedMessage = "";
		for (char c : plaintext.toUpperCase().toCharArray())
			if (plainBox.indexOf(c) >= 0)
				preEncodedMessage += c;
		if (preEncodedMessage.length() % 2 == 1)
			preEncodedMessage += 'X';

		String encodedMessage = "";
		for (int i = 0; i < preEncodedMessage.length(); i += 2) {
			String digraph = preEncodedMessage.substring(i, i+2);
			encodedMessage += encodeDigraph(digraph);
		}
			
		return encodedMessage;
	}

	@Override
	public String decode(String encodedMessage) {
		String encodedLetters = encodedMessage.toUpperCase().replaceAll("[^A-Z]", "");
		String decodedLetters = "";
		for (int i = 0; i < encodedLetters.length(); i += 2) {
			String digraph = encodedLetters.substring(i, i+2);
			decodedLetters += decodeDigraph(digraph);
		}

		String decodedMessage = encodedMessage.toLowerCase();
		for (char c : decodedLetters.toCharArray())
			decodedMessage = decodedMessage.replaceFirst("[a-z]", ""+c);

		return decodedMessage;
	}

	private String decodeDigraph(String digraph) {
		RowColumn pos1 = findPosition(digraph.charAt(0), upperRightBox);
		RowColumn pos2 = findPosition(digraph.charAt(1), lowerLeftBox);
		char c1 = findLetter(pos1.row, pos2.column, plainBox);
		char c2 = findLetter(pos2.row, pos1.column, plainBox);
		return "" + c1 + c2;
	}

	private String encodeDigraph(String digraph) {
		RowColumn pos1 = findPosition(digraph.charAt(0), plainBox);
		RowColumn pos2 = findPosition(digraph.charAt(1), plainBox);
		char c1 = findLetter(pos1.row, pos2.column, upperRightBox);
		char c2 = findLetter(pos2.row, pos1.column, lowerLeftBox);
		return "" + c1 + c2;
	}

	private char findLetter(int row, int column, String box) {
		int pos = row * 5 + column;
		return box.charAt(pos);
	}

	private RowColumn findPosition(char c, String box) {
		int pos = box.indexOf(c);
		RowColumn rc = new RowColumn();
		rc.row = pos / 5;
		rc.column = pos % 5;
		return  rc;
	}

	private String generateBox(String keyword) {
		String box = "";
		for (char c : keyword.toUpperCase().toCharArray()) {
			if (box.indexOf(c) < 0)
				box += c;
		}
		for (char c : plainBox.toCharArray()) {
			if (box.indexOf(c) < 0)
				box += c;
		}
		return box;
	}
}
