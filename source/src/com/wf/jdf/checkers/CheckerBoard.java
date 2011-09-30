package com.wf.jdf.checkers;

public class CheckerBoard {
	private boolean[][] squares = new boolean[8][8];

	public CheckerBoard() {
		for (int x = 0; x < squares.length; x++)
			for (int y = 0; y < squares.length; y++) {
				if ((y == 3) || (y == 4)) continue;
				squares[x][y] = (x % 2 == y % 2);
			}
//		squares[0][0] = true;
//		squares[2][2] = true;
//		squares[3][1] = true;
//		squares[1][3] = false;
	}

	public boolean emptySquare(int x, int y) {
		return !squares[x][y];
	}

	public boolean movePieceForwardLeft(int x, int y) {
		if (!squares[x][y]) return false;
		if (squares[x-1][y+1]) return false;

		squares[x][y] = false;
		squares[x-1][y+1] = true;
		return true;
	}
}