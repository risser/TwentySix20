package com.wf.jdf.checkers;

import com.twentysix20.testutil.TestCase2620;

public class BoardTest extends TestCase2620 {
	// X,Y; 0,0 is bottom left; 7,7 is top-right

	public void testAlwaysEmptySquareIsEmpty() {
		assertTrue(new CheckerBoard().emptySquare(1,0));
	}

	public void testUseableSquareIsEmpty() {
		assertTrue(new CheckerBoard().emptySquare(1,3));
	}

	public void testSquareIsNotEmpty1() {
		assertFalse(new CheckerBoard().emptySquare(0,0));
	}

	public void testSquareIsNotEmpty2() {
		assertFalse(new CheckerBoard().emptySquare(2,2));
	}

	public void testMovePieceForwardLeft_Valid1() {
		CheckerBoard board = new CheckerBoard();
		board.movePieceForwardLeft(2,2);
		assertTrue(board.emptySquare(2,2));
		assertFalse(board.emptySquare(1,3));
	}

	public void testMovePieceForwardLeft_Valid2() {
		CheckerBoard board = new CheckerBoard();
		board.movePieceForwardLeft(4,2);
		assertTrue(board.emptySquare(4,2));
		assertFalse(board.emptySquare(3,3));
	}

	public void testMovePieceForwardLeft_Occupied() {
		CheckerBoard board = new CheckerBoard();
		assertFalse(board.movePieceForwardLeft(3,1));
		assertFalse(board.emptySquare(3,1));
		assertFalse(board.emptySquare(2,2));
	}

	public void testMovePieceForwardLeft_NoPiece() {
		CheckerBoard board = new CheckerBoard();
		assertFalse(board.movePieceForwardLeft(1,3));
		assertTrue(board.emptySquare(1,3));
	}
}
