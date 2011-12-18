package com.twentysix20.cipher;

public class PlayfairCipher implements Cipher {
	private final Box25 box;
	private final PlayfairAlphabet whichAlphabet;

	public PlayfairCipher(String keyword, PlayfairAlphabet whichAlphabet) {
		this.whichAlphabet = whichAlphabet;
		char removedCharacter = (whichAlphabet == PlayfairAlphabet.REMOVE_Q) ? 'Q' : 'J';
		box = generateBox(keyword, removedCharacter);
		System.out.println(box.toString());
	}

	@Override
	public String decode(String encodedMessage) {
		StringBuilder plainText = new StringBuilder("");
		String messageToDecode = encodedMessage.toUpperCase().replaceAll("[^A-Z]", "");
		while(!messageToDecode.isEmpty()) {
			char firstChar = messageToDecode.charAt(0);
			char secondChar = messageToDecode.charAt(1);
			messageToDecode = messageToDecode.substring(2);

			RowColumn pos1 = box.positionOf(firstChar);
			RowColumn pos2 = box.positionOf(secondChar);

			int row1 = pos1.getRow();
			int col1 = pos2.getColumn();
			int row2 = pos2.getRow();
			int col2 = pos1.getColumn();
			if (col1 == col2) {
				row1 = (row1+5 - 1) % 5;
				row2 = (row2+5 - 1) % 5;
			}
			if (row1 == row2) {
				col1 = (pos1.getColumn()+5 - 1) % 5;
				col2 = (pos2.getColumn()+5 - 1) % 5;
			}

			char c1 = box.charAt(row1, col1);
			char c2 = box.charAt(row2, col2);
			plainText.append(c1).append(c2);
		}
		return plainText.toString();
	}

	@Override
	public String encode(String plaintext) {
		StringBuilder encodedMessage = new StringBuilder("");
		String messageToEncode = plaintext.toUpperCase().replaceAll("[^A-Z]", "");
		if (needToReplaceJ())
			messageToEncode = messageToEncode.replace('J','I');
		while(!messageToEncode.isEmpty()) {
			boolean oneLetterLeft = messageToEncode.length() == 1;
			char firstChar = messageToEncode.charAt(0);
			char secondChar = oneLetterLeft ? 'X' : messageToEncode.charAt(1);
			if (firstChar == secondChar) {
				secondChar = 'X';
				messageToEncode = messageToEncode.substring(1);
			} else {
				messageToEncode = oneLetterLeft ? "" : messageToEncode.substring(2);
			}
			RowColumn pos1 = box.positionOf(firstChar);
			RowColumn pos2 = box.positionOf(secondChar);

			int row1 = pos1.getRow();
			int col1 = pos2.getColumn();
			int row2 = pos2.getRow();
			int col2 = pos1.getColumn();
			if (col1 == col2) {
				row1 = (row1 + 1) % 5;
				row2 = (row2 + 1) % 5;
			}
			if (row1 == row2) {
				col1 = (pos1.getColumn() + 1) % 5;
				col2 = (pos2.getColumn() + 1) % 5;
			}

			char c1 = box.charAt(row1, col1);
			char c2 = box.charAt(row2, col2);
			encodedMessage.append(c1).append(c2);
		}
		return encodedMessage.toString();
	}

	private boolean needToReplaceJ() {
		return whichAlphabet == PlayfairAlphabet.REPLACE_J_WITH_I;
	}

	private Box25 generateBox(String keyword, char removedChar) {
		String boxStr = "";
		String keywordToUse = keyword;
		if (needToReplaceJ())
			keywordToUse = keywordToUse.replace('J', 'I');
		for (char c : keywordToUse.toUpperCase().toCharArray()) {
			if (boxStr.indexOf(c) < 0)
				boxStr += c;
		}
		for (char c : Box25.ALPHABET.replaceAll(""+removedChar,"").toCharArray()) {
			if (boxStr.indexOf(c) < 0)
				boxStr += c;
		}
		return new Box25(boxStr);
	}

	public String outputBox() {
		return box.toString();
	}

	static public void main(String[] args) {
		String keyword = "SNEAKINESS";
		String message1 = "GDJCENARUSDGGATDRWRNOEPSTA";
		String message2 = "GAGRTDRANAJUTOCSDEEDRPNGWS";
		PlayfairCipher noQ = new PlayfairCipher(keyword, PlayfairAlphabet.REMOVE_Q);
		System.out.println(noQ.decode(message1));
		System.out.println(noQ.decode(message2));
	}
}
