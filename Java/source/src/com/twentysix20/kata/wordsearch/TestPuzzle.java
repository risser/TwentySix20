package com.twentysix20.kata.wordsearch;

import com.twentysix20.testutil.TestCase2620;

public class TestPuzzle extends TestCase2620 {
	public void test3LetterPuzzle() throws Exception {
		Puzzle puz = new Puzzle("","ABC","DEF","GHI");
		assertEquals('A', puz.getGrid()[0][0]);
		assertEquals('B', puz.getGrid()[0][1]);
		assertEquals('C', puz.getGrid()[0][2]);
		assertEquals('D', puz.getGrid()[1][0]);
		assertEquals('E', puz.getGrid()[1][1]);
		assertEquals('F', puz.getGrid()[1][2]);
		assertEquals('G', puz.getGrid()[2][0]);
		assertEquals('H', puz.getGrid()[2][1]);
		assertEquals('I', puz.getGrid()[2][2]);
		try {
			char c = puz.getGrid()[3][0];
			fail("Should have thrown exception.");
		} catch(ArrayIndexOutOfBoundsException e) {
			// yay
		}
		try {
			char c = puz.getGrid()[0][3];
			fail("Should have thrown exception.");
		} catch(ArrayIndexOutOfBoundsException e) {
			// yay
		}
	}

	public void testWordList() throws Exception {
		Puzzle puz = new Puzzle("one, TWO,THree","ABC","DEF","GHI");
		assertEquals("ONE",puz.getWords()[0]);
		assertEquals("TWO",puz.getWords()[1]);
		assertEquals("THREE",puz.getWords()[2]);
	}

	public void testChangeChar() throws Exception {
		Puzzle puz = new Puzzle("","ABC","DEF","GHI");
		puz.setChar(0,1,'Z');
		assertEquals('Z',puz.getGrid()[0][1]);
		assertEquals('A',puz.getGrid()[0][0]);
		assertEquals('C',puz.getGrid()[0][2]);
		assertEquals('D',puz.getGrid()[1][0]);
	}

	public void testSize() throws Exception {
		Puzzle puz = new Puzzle("","ABC","DEF","GHI","JKL");
		assertEquals(4,puz.sizeY());
		assertEquals(3,puz.sizeX());
	}
}