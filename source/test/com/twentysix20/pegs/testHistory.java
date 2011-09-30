package com.twentysix20.pegs;

import junit.framework.*;

public class testHistory extends TestCase {

	public static void main(String args[]) {
		junit.textui.TestRunner.run(testHistory.class);
	}

	protected void setUp() {
	}

	public void testMoveCount()
	{
		History h = new History(4);
		assertEquals(0,h.moveCount());
		h.addMove(1,2);
		assertEquals(1,h.moveCount());
		h.addMove(2,3);
		assertEquals(2,h.moveCount());
		h.undoMove();
		assertEquals(1,h.moveCount());
		h.undoMove();
		assertEquals(0,h.moveCount());
	}

	public void testOutput()
	{
		History h = new History(4);
		h.addMove(1,2);
		h.addMove(2,3);
		h.addMove(3,4);
		assertEquals("3: 2-3; 3-4; 4-5; ",h.toString());
	}

	public void testStoreSolution()
	{
			History h = new History(4);
			h.addMove(1,2);
			h.addMove(2,3);
			h.addMove(3,4);
			h.storeSolution();
			assertEquals(1,h.getSolutionCount());
			h.storeSolution();
			assertEquals(2,h.getSolutionCount());
	}

	public void testStoreFailure()
	{
			History h = new History(4);
			h.addMove(1,2);
			h.addMove(2,3);
			h.addMove(3,4);
			h.storeFailure();
			assertEquals(1,h.getFailureCount());
			h.storeFailure();
			assertEquals(2,h.getFailureCount());
	}

	public void testAttempts()
	{
			History h = new History(4);
			h.addMove(1,2);
			h.addMove(2,3);
			h.addMove(3,4);
			h.storeFailure();
			h.storeFailure();
			h.storeSolution();
			assertEquals(3,h.getAttemptCount());
	}

	public void testBadMoves()
	{
		try{
			new History(0);
			fail();
		} catch(java.lang.IllegalArgumentException iae) {}
		try{
			new History(-1);
			fail();
		} catch(java.lang.IllegalArgumentException iae) {}
		History h = new History(3);
		try{
			h.undoMove();
			fail();
		} catch(java.lang.IllegalArgumentException iae) {}
		try{
			h.addMove(1,2);
			h.addMove(2,3);
			h.addMove(4,5);
			h.addMove(6,7);
			fail();
		} catch(java.lang.IllegalArgumentException iae) {}
	}
}