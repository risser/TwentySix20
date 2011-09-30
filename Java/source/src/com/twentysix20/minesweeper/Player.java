package com.twentysix20.minesweeper;

public class Player {
	public static void main(String[] args) {
		Board board = BoardFactory.manufacture(10,10,10);
		MineSolver mineSolver = new MineSolver(board);
		while(!board.finished()) {
			mineSolver.clearSomeSquares();
		}

		if (board.uncoveredBomb())
			System.out.println("You BLEW UP!");
		else
			System.out.println("YOU WIN!!");
		System.out.println("\n"+board.clearedSoFar());
	}
}
