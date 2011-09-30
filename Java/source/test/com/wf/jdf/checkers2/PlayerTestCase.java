package com.wf.jdf.checkers2;

import com.twentysix20.testutil.TestCase2620;

public class PlayerTestCase extends TestCase2620 {
	public void testPlayerHasPieces() {
		Player p = new Player();
		assertTrue(p.hasPiecesLeft());
	}

	public void testPlayerHasNoPieces() {
		Player p = new Player();
		p.removePieces(12);
		assertFalse(p.hasPiecesLeft());
	}

	public void testPlayerHasPiecesAfterRemoval() {
		Player p = new Player();
		p.removePieces(1);
		assertTrue(p.hasPiecesLeft());
	}

	public void testPlayerRemoveTooManyPieces() {
		Player p = new Player();
		try {
			p.removePieces(100);
			fail("Should have thrown exception.");
		} catch(CheckerException ce) {
			// YAY!
		}
	}

	public void testPlayerHasAvailableMove() {
		Player p = new Player();
		assertTrue(p.hasAvailableMove());
	}
}