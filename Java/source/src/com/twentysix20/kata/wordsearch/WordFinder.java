package com.twentysix20.kata.wordsearch;

import java.util.ArrayList;
import java.util.List;

public class WordFinder {

	public static List<PuzzleLocation> find(String wordToFind, Puzzle puzzle) {
		List<PuzzleLocation> locs = new ArrayList<PuzzleLocation>();
		char firstChar = wordToFind.charAt(0);
		for (int y = 0; y < puzzle.getGrid().length; y++)
			for (int x = 0; x < puzzle.getGrid()[y].length; x++)
				if (puzzle.getGrid()[y][x] == firstChar) {
					locs.addAll(checkWord(y,x,puzzle,wordToFind));
				}
		return locs;
	}

	private static List<PuzzleLocation> checkWord(int y, int x, Puzzle puz, String wordToFind) {
		List<PuzzleLocation> locs = new ArrayList<PuzzleLocation>();
		for (int[] direction : PuzzleLocation.DIRECTIONS) {
			boolean directionFailed = false;
			for (int i = 1; !directionFailed && i < wordToFind.length(); i++) {
				int newX = x + direction[1]*i;
				int newY = y + direction[0]*i;
				if (newX < 0 || newX >= puz.sizeX()) {
					directionFailed = true;
					break;
				}
				if (newY < 0 || newY >= puz.sizeY()) {
					directionFailed = true;
					break;
				}
				if (puz.getGrid()[newY][newX] != wordToFind.charAt(i))
					directionFailed = true;

			}
			if (!directionFailed)
				locs.add(new PuzzleLocation(y,x,direction));
		}

		return locs;
	}
}