package com.twentysix20.kata.wordsearch;

public class PuzzleSolution {
	private Puzzle puzzle;
	private char[][]grid;

	public PuzzleSolution(Puzzle puz) {
		this.puzzle = puz;
		grid = new char[puz.sizeY()][puz.sizeX()];
		for (int y = 0; y < grid.length; y++)
			for (int x = 0; x < grid[y].length; x++)
				grid[y][x] = '+';
	}

	public char[][] getGrid() {
		return grid;
	}

	public void apply(String word, PuzzleLocation loc) {
		for (int i = 0; i < word.length(); i++) {
			int x = loc.getX() + i * loc.getDirection()[1];
			int y = loc.getY() + i * loc.getDirection()[0];
			grid[y][x] = word.charAt(i);
		}
	}

	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		for (int y = 0; y < grid.length; y++) {
			for (int x = 0; x < grid[y].length; x++)
				buf.append(grid[y][x]).append(' ');
			buf.deleteCharAt(buf.length()-1).append('\n');
		}
		return buf.deleteCharAt(buf.length()-1).toString();
	}
}
