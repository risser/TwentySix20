package com.wf.jdf.life;

public class Grid {
	private boolean[][] grid;
	public Grid(int x, int y) {
		grid = new boolean[y][x];
	}

	public boolean isAlive(int x, int y) {
		return grid[y][x];
	}

	public void setAlive(int x, int y) {
		grid[y][x] = true;
	}

	public int getX() {
		return grid[0].length;
	}

	public int getY() {
		return grid.length;
	}

	public int neighbors(int x, int y) {
		int xBound = getX()-1;
		int yBound = getY()-1;
		int n = 0;
		n += (x > 0 && y > 0 && grid[y-1][x-1] ? 1 : 0);
		n += (y > 0 && grid[y-1][x] ? 1 : 0);
		n += (x < xBound && y > 0 && grid[y-1][x+1] ? 1 : 0);
		n += (x > 0 && grid[y][x-1] ? 1 : 0);
		n += (x < xBound && grid[y][x+1] ? 1 : 0);
		n += (x > 0 && y < yBound && grid[y+1][x-1] ? 1 : 0);
		n += (y < yBound && grid[y+1][x] ? 1 : 0);
		n += (x < xBound && y < yBound && grid[y+1][x+1] ? 1 : 0);
			
		return n;
	}

}
