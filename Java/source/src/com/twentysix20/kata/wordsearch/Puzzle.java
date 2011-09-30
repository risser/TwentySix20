package com.twentysix20.kata.wordsearch;

import java.util.ArrayList;
import java.util.List;

public class Puzzle {
	private char[][]grid;
	private String[] words;

	public Puzzle(String wordlist, String... lines) {
		grid = new char[lines.length][lines[0].length()];
		for (int i = 0; i < lines.length; i++)
			grid[i] = lines[i].toUpperCase().toCharArray();
		words = wordlist.toUpperCase().split("\\s*,\\s*");
	}

	public char[][] getGrid() {
		return grid;
	}

	public String[] getWords() {
		return words;
	}

	public void setChar(int y, int x, char c) {
		grid[y][x] = c;
	}

	public int sizeY() {
		return grid.length;
	}

	public int sizeX() {
		return grid[0].length;
	}
}
