package com.twentysix20.kata.wordsearch;

import com.twentysix20.testutil.TestCase2620;

public class TestFullPuzzle extends TestCase2620 {
	public void testCrazyCat() throws Exception {
		Puzzle puzzle = new Puzzle("CAT","TZCAT","AAZAZ","CXCXC","ZAEAA","TACQT");
		PuzzleSolution solution = PuzzleSolver.solve(puzzle);
		String expected = "T + C A T\nA A + A +\nC + C + C\n+ A + A A\nT A C + T";
		assertEquals(expected,solution.toString());
		System.out.println(solution.toString());
	}

	public void testActualCase() throws Exception {
		Puzzle puzzle = new Puzzle("Ruby, rocks, DAN, matZ","UEWRTRBHCD", "CXGZUWRYER", "ROCKSBAUCU", "SFKFMTYSGE", "YSOOUNMZIM", "TCGPRTIDAN", "HZGHQGWTUV", "HQMNDXZBST", "NTCLATNBCE", "YBURPZUXMS");
		PuzzleSolution solution = PuzzleSolver.solve(puzzle);
		String expected = "+ + + R + + + + + +\n+ + + + U + + + + +\nR O C K S B + + + +\n+ + + + + + Y + + +\n+ + + + + + + + + M\n+ + + + + + + D A N\n+ + + + + + + T + +\n+ + + + + + Z + + +\n+ + + + + + + + + +\nY B U R + + + + + +";
		assertEquals(expected,solution.toString());
		System.out.println(solution.toString());
	}
}
