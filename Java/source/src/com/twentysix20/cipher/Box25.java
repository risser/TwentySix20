package com.twentysix20.cipher;

public class Box25 {
	public static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	private String box;

	public Box25(String box) {
		box = box.toUpperCase();
		if (box.length() != 25) throw new IllegalArgumentException("Alphabet must be exactly 25 characters.");
		if (alphabetHasDuplicateLetters(box)) throw new IllegalArgumentException("Alphabet may not contain duplciate characters.");
		this.box = box;
	}

	public Box25(char c) {
		this.box = ALPHABET.replaceAll(charAsUppercaseString(c), "");
	}

	public String toFlatString() {
		return box;
	}
	

	private boolean alphabetHasDuplicateLetters(String alphabet) {
		for (char c : alphabet.toCharArray()) {
			String charAsString = ""+c;
			String testString = alphabet.replaceFirst(charAsString, "");
			if (testString.contains(charAsString))
				return true;
		}
		return false;
	}

	public RowColumn positionOf(char c) {
		String upperC = charAsUppercaseString(c);
		int pos = box.indexOf(upperC);
		if (pos < 0) throw new IndexOutOfBoundsException("Character '"+upperC+"' isn't in the box.");
		RowColumn rc = new RowColumn(pos / 5, pos % 5);
		return  rc;
	}

	public boolean contains(char c) {
		String upperC = charAsUppercaseString(c);
		return (box.indexOf(upperC) >= 0);
	}

	private String charAsUppercaseString(char c) {
		return (""+c).toUpperCase();
	}

	public char charAt(int row, int column) {
		if ((row < 0 || row > 4) || (column < 0 || column > 4)) 
			throw new IndexOutOfBoundsException("Row and Column must be between 0 and 4, inclusive. ("+row+", "+column+").");
		int pos = row * 5 + column;
		return box.charAt(pos);
	}

	public char charAt(RowColumn rowColumn) {
		return charAt(rowColumn.getRow(), rowColumn.getColumn());
	}

	@Override public String toString() {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < box.length(); i++) {
			s.append(box.charAt(i) + " ");
			if ((i + 1) % 5 == 0)
				s.append("\n");
		}
		return s.toString();
	}
}
