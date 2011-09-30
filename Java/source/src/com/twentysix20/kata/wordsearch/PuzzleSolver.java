package com.twentysix20.kata.wordsearch;

public class PuzzleSolver {

	public static PuzzleSolution solve(Puzzle puzzle) {
		PuzzleSolution solution = new PuzzleSolution(puzzle);
		for (String word : puzzle.getWords())
			for (PuzzleLocation loc : WordFinder.find(word, puzzle))
				solution.apply(word, loc);
			
		return solution;
	}

}
