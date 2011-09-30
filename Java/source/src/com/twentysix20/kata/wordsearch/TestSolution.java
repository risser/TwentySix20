package com.twentysix20.kata.wordsearch;

import com.twentysix20.testutil.TestCase2620;

public class TestSolution extends TestCase2620 {
	public void testSolutionGrid() throws Exception {
		Puzzle puz = new Puzzle("","ABC","DEF","GHI","JKL");
		PuzzleSolution soln = new PuzzleSolution(puz);
		assertEquals('+', soln.getGrid()[0][0]);
		assertEquals('+', soln.getGrid()[0][1]);
		assertEquals('+', soln.getGrid()[0][2]);
		assertEquals('+', soln.getGrid()[1][0]);
		assertEquals('+', soln.getGrid()[1][1]);
		assertEquals('+', soln.getGrid()[1][2]);
		assertEquals('+', soln.getGrid()[2][0]);
		assertEquals('+', soln.getGrid()[2][1]);
		assertEquals('+', soln.getGrid()[2][2]);
		assertEquals('+', soln.getGrid()[3][0]);
		assertEquals('+', soln.getGrid()[3][1]);
		assertEquals('+', soln.getGrid()[3][2]);
	}
	public void testAddingSolution() throws Exception {
		Puzzle puz = new Puzzle("","ABCAB","DEFAB","GHIAB","JKLAB","ABCDE");
		PuzzleSolution soln = new PuzzleSolution(puz);
		soln.apply("CAT",new PuzzleLocation(2,2,PuzzleLocation.DIRECTION_NE));
		soln.apply("CAT",new PuzzleLocation(2,2,PuzzleLocation.DIRECTION_SE));
		soln.apply("CAT",new PuzzleLocation(2,2,PuzzleLocation.DIRECTION_NW));
		soln.apply("CAT",new PuzzleLocation(2,2,PuzzleLocation.DIRECTION_SW));
		soln.apply("CAT",new PuzzleLocation(0,2,PuzzleLocation.DIRECTION_EAST));
		soln.apply("CAT",new PuzzleLocation(2,4,PuzzleLocation.DIRECTION_SOUTH));
		soln.apply("CAT",new PuzzleLocation(4,2,PuzzleLocation.DIRECTION_WEST));
		soln.apply("CAT",new PuzzleLocation(2,0,PuzzleLocation.DIRECTION_NORTH));
		assertEquals('T', soln.getGrid()[0][0]);
		assertEquals('+', soln.getGrid()[0][1]);
		assertEquals('C', soln.getGrid()[0][2]);
		assertEquals('A', soln.getGrid()[0][3]);
		assertEquals('T', soln.getGrid()[0][4]);
		assertEquals('A', soln.getGrid()[1][0]);
		assertEquals('A', soln.getGrid()[1][1]);
		assertEquals('+', soln.getGrid()[1][2]);
		assertEquals('A', soln.getGrid()[1][3]);
		assertEquals('+', soln.getGrid()[1][4]);
		assertEquals('C', soln.getGrid()[2][0]);
		assertEquals('+', soln.getGrid()[2][1]);
		assertEquals('C', soln.getGrid()[2][2]);
		assertEquals('+', soln.getGrid()[2][3]);
		assertEquals('C', soln.getGrid()[2][4]);
		assertEquals('+', soln.getGrid()[3][0]);
		assertEquals('A', soln.getGrid()[3][1]);
		assertEquals('+', soln.getGrid()[3][2]);
		assertEquals('A', soln.getGrid()[3][3]);
		assertEquals('A', soln.getGrid()[3][4]);
		assertEquals('T', soln.getGrid()[4][0]);
		assertEquals('A', soln.getGrid()[4][1]);
		assertEquals('C', soln.getGrid()[4][2]);
		assertEquals('+', soln.getGrid()[4][3]);
		assertEquals('T', soln.getGrid()[4][4]);

		System.out.println(soln.toString());
	}
}