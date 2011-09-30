package com.twentysix20.pegs;

import junit.framework.TestCase;

public class testBoard extends TestCase {

	public static void main(String args[]) {
		junit.textui.TestRunner.run(testBoard.class);
	}

/*
	protected void setUp() {
	}

	public void testBagMultiply() {
		IMoney expected= MoneyBag.create(new Money(24, "CHF"), new Money(14, "USD"));
		assertEquals(expected, fMB1.multiply(2));
		assertEquals(fMB1, fMB1.multiply(1));
		assertTrue(fMB1.multiply(0).isZero());
	}
*/

	public void testNewBoard()
	{
		Board b = new Board(6,6);
		assertEquals(6,b.getSize());
		assertEquals(6,b.getNeighborCount());
		assertTrue(b.hasPeg(0));
		assertEquals(b.getNeighbor(0,0),-1);
		assertFalse(b.hasNeighbor(0,0));
		assertFalse(b.legalJump(0,0));
		assertEquals(b.getNumberOfPegs(),6);
	}

	public void testPegNoPeg()
	{
		Board b = new Board(6,6);
		assertTrue(b.hasPeg(0));
		b.removePeg(0);
		assertFalse(b.hasPeg(0));
		assertEquals(b.getNumberOfPegs(),5);
		b.insertPeg(0);
		assertTrue(b.hasPeg(0));
		assertEquals(b.getNumberOfPegs(),6);
	}

	public void testBoardState()
	{
		Board b = new Board(6,6);
		assertEquals(b.boardState(),"111111");
		b.removePeg(0);
		b.removePeg(2);
		b.removePeg(4);
		assertEquals(b.boardState(),"010101");
		assertEquals(b.getNumberOfPegs(),3);
		b.removePeg(1);
		b.removePeg(3);
		b.removePeg(5);
		assertEquals(b.boardState(),"000000");
		assertEquals(b.getNumberOfPegs(),0);
	}

	public void testBogusCalls()
	{
		try{
			Board b = new Board(2,6);
			fail();
		} catch(java.lang.IllegalArgumentException iae) {}
		try{
			Board b = new Board(15,1);
			fail();
		} catch(java.lang.IllegalArgumentException iae) {}
		Board b2 = new Board(6,6);
		try
		{
			boolean x = b2.hasPeg(6);
			fail();
		} catch(java.lang.ArrayIndexOutOfBoundsException aie) {}
		try
		{
			boolean y = b2.hasPeg(-1);
			fail();
		} catch(java.lang.ArrayIndexOutOfBoundsException aie) {}
		try
		{
			int a = b2.getNeighbor(0,6);
			fail();
		} catch(java.lang.ArrayIndexOutOfBoundsException aie) {}
		try
		{
			int b = b2.getNeighbor(2,-1);
			fail();
		} catch(java.lang.ArrayIndexOutOfBoundsException aie) {}
		try
		{
			b2.insertPeg(-1);
			fail();
		} catch(java.lang.ArrayIndexOutOfBoundsException aie) {}
		try
		{
			b2.insertPeg(7);
			fail();
		} catch(java.lang.ArrayIndexOutOfBoundsException aie) {}
		try
		{
			b2.insertPeg(0);
			fail();
		} catch(java.lang.IllegalArgumentException iae) {}
		b2.removePeg(0);
		try
		{
			b2.removePeg(0);
			fail();
		} catch(java.lang.IllegalArgumentException iae) {}
	}
}