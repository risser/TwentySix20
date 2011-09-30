package com.twentysix20.pegs;

import junit.framework.TestCase;

public class testBoardTriangle extends TestCase {

	public static void main(String args[]) {
		junit.textui.TestRunner.run(testBoardTriangle.class);
	}

/*
	protected void setUp() {
	}
*/

	public void testBoardTriangleInit()
	{
		BoardTriangle b = new BoardTriangle();
		b.removePeg(0);
		assertFalse(b.hasPeg(0));
		b.jump(3,3);
		assertEquals(b.boardState(),"101011111111111");
		b.jump(8,4);
		assertEquals(b.boardState(),"111001110111111");
		b.jump(0,0);
		assertEquals(b.boardState(),"001101110111111");
		b.unjump(0,0);
		assertEquals(b.boardState(),"111001110111111");
		b.unjump(8,4);
		assertEquals(b.boardState(),"101011111111111");
		b.unjump(3,3);
		assertEquals(b.boardState(),"011111111111111");
	}
}