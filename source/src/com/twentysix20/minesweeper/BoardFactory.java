package com.twentysix20.minesweeper;

public class BoardFactory {
	static public Board manufacture(int x, int y, int bombs) {
		Board board = new Board(x,y);
		while (board.getBombCount() < bombs) {
			int bombX = randomInteger(x);
			int bombY = randomInteger(y);
			board.setBomb(new Position(bombX,bombY));
		}
		return board;
	}

	private static int randomInteger(int x) {
		return (int)Math.round(Math.floor(Math.random() * x));
	}

	public static void main(String[] args) {
		int x = 10;
		int y = 10;
		Board testBoard = manufacture(x,y,10);
		System.out.println(testBoard.bombLayout());
		System.out.println();
		while (!testBoard.finished()) {
			int testX = randomInteger(x);
			int testY = randomInteger(y);
			testBoard.clear(new Position(testX,testY));
		}
		System.out.println(testBoard.clearedSoFar());
	}
	
}
