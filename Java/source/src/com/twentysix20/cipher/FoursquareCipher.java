package com.twentysix20.cipher;

public class FoursquareCipher implements Cipher {
	private final Box25 plainBox;
	private final Box25 upperRightBox;
	private final Box25 lowerLeftBox;

	public FoursquareCipher(String key1, String key2) {
		this(key1, key2, 'Q');
	}

	public FoursquareCipher(String key1, String key2, char c) {
		plainBox = new Box25(c);
		upperRightBox = generateBox(key1.replaceFirst(""+c, ""));
		lowerLeftBox = generateBox(key2.replaceFirst(""+c, ""));
	}

	@Override
	public String encode(String plaintext) {
		String preEncodedMessage = "";
		for (char c : plaintext.toUpperCase().toCharArray())
			if (plainBox.contains(c))
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
		RowColumn pos1 = upperRightBox.positionOf(digraph.charAt(0));
		RowColumn pos2 = lowerLeftBox.positionOf(digraph.charAt(1));
		char c1 = plainBox.charAt(pos1.getRow(), pos2.getColumn());
		char c2 = plainBox.charAt(pos2.getRow(), pos1.getColumn());
		return "" + c1 + c2;
	}

	private String encodeDigraph(String digraph) {
		RowColumn pos1 = plainBox.positionOf(digraph.charAt(0));
		RowColumn pos2 = plainBox.positionOf(digraph.charAt(1));
		char c1 = upperRightBox.charAt(pos1.getRow(), pos2.getColumn());
		char c2 = lowerLeftBox.charAt(pos2.getRow(), pos1.getColumn());
		return "" + c1 + c2;
	}

	private Box25 generateBox(String keyword) {
		String box = "";
		for (char c : keyword.toUpperCase().toCharArray()) {
			if (box.indexOf(c) < 0)
				box += c;
		}
		for (char c : plainBox.toFlatString().toCharArray()) {
			if (box.indexOf(c) < 0)
				box += c;
		}
		return new Box25(box);
	}
}
