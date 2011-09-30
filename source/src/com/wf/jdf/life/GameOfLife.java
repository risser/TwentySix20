package com.wf.jdf.life;

public class GameOfLife {

	public Grid generateNextGrid(Grid grid) {
		int xBound = grid.getX();
		int yBound = grid.getY();
		Rules rules = new Rules();
		Grid newGrid = new Grid(xBound,yBound);
		for (int x = 0; x < xBound; x++)
			for (int y = 0; y < yBound; y++)
				if (rules.isAlive(grid.neighbors(x, y), grid.isAlive(x, y)))
					newGrid.setAlive(x,y);
		return newGrid;
	}

}
