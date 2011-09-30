package com.wf.jdf.checkers2;

public class Player {
	int numPieces = 12;

	public boolean hasPiecesLeft() {
		return numPieces > 0;
	}

	public void removePieces(int i) {
		if (numPieces < i) throw new CheckerException();
		numPieces -= i;
	}

	public boolean hasAvailableMove() {
		return true;
	}

}