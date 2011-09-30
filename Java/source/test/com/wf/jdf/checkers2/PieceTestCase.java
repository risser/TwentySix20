package com.wf.jdf.checkers2;

import com.twentysix20.testutil.TestCase2620;

public class PieceTestCase extends TestCase2620 {
	public void testPieceCanMove() {
		Piece p = new Piece(1,1);
		assertTrue(p.canMove());
	}

	public void testPieceCreationWithPosition() {
		Piece p = new Piece(1,1);
		assertEquals(1, p.getX());
		assertEquals(1, p.getY());
	}

	public void testPieceCreationWithBadPosition() {
		Piece p = new Piece(9,9);
	}

	public void testCreationTwoPiecesSamePosition() {
		Piece p1 = new Piece(1,1);
		Piece p2 = new Piece(1,1);
	}
}