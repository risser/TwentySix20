package com.wf.jdf.life;

import com.twentysix20.testutil.TestCase2620;

public class TestGameOfLife extends TestCase2620 {
	public void testEmpty() {
		Grid grid = new Grid (3,3);
		GameOfLife game = new GameOfLife();
		Grid newGrid = game.generateNextGrid(grid);
		assertGrid(grid, newGrid);
	}

	public void testOneAtZeroZero() {
		Grid grid = new Grid (3,3);
		grid.setAlive(0, 0);
		GameOfLife game = new GameOfLife();
		Grid newGrid = game.generateNextGrid(grid);
		assertGrid(new Grid(3,3), newGrid);
	}

	public void testOneAtOneOne() {
		Grid grid = new Grid (3,3);
		grid.setAlive(1, 1);
		GameOfLife game = new GameOfLife();
		Grid newGrid = game.generateNextGrid(grid);
		assertGrid(new Grid(3,3), newGrid);
	}

	public void testOneAtTwoTwo() {
		Grid grid = new Grid (3,3);
		grid.setAlive(2, 2);
		GameOfLife game = new GameOfLife();
		Grid newGrid = game.generateNextGrid(grid);
		assertGrid(new Grid(3,3), newGrid);
	}

	public void testTwoApart() {
		Grid grid = new Grid (3,3);
		grid.setAlive(0, 1);
		grid.setAlive(2, 2);
		GameOfLife game = new GameOfLife();
		Grid newGrid = game.generateNextGrid(grid);
		assertGrid(new Grid(3,3), newGrid);
	}

	public void testTwoNextToEachOther() {
		Grid grid = new Grid (3,3);
		grid.setAlive(0, 0);
		grid.setAlive(0, 1);
		GameOfLife game = new GameOfLife();
		Grid newGrid = game.generateNextGrid(grid);
		assertGrid(new Grid(3,3), newGrid);
	}

	public void testTwoNextToEachOther2() {
		Grid grid = new Grid (3,3);
		grid.setAlive(1, 2);
		grid.setAlive(1, 1);
		GameOfLife game = new GameOfLife();
		Grid newGrid = game.generateNextGrid(grid);
		assertGrid(new Grid(3,3), newGrid);
	}

	public void testTwoNeighborsLine() {
		Grid grid = new Grid(3,3);
		grid.setAlive(0,0);
		grid.setAlive(1,0);
		grid.setAlive(2,0);

		Grid expected = new Grid(3,3);
		expected.setAlive(1,0);
		expected.setAlive(1,1);
		GameOfLife game = new GameOfLife();
		Grid newGrid = game.generateNextGrid(grid);
		assertGrid(expected, newGrid);
	}

	public void testTwoNeighborsCorner() {
		Grid grid = new Grid(3,3);
		grid.setAlive(0,0);
		grid.setAlive(1,0);
		grid.setAlive(0,1);

		Grid expected = new Grid(3,3);
		expected.setAlive(0,0);
		expected.setAlive(0,1);
		expected.setAlive(1,0);
		expected.setAlive(1,1);
		GameOfLife game = new GameOfLife();
		Grid newGrid = game.generateNextGrid(grid);
		assertGrid(expected, newGrid);
	}

	private void assertGrid(Grid expected, Grid actual) {
		for (int x = 0; x < expected.getX(); x++)
			for (int y = 0; y < expected.getY(); y++)
				assertEquals("Cell ("+x+","+y+") is not the same.",expected.isAlive(x, y),actual.isAlive(x, y));
	}
}
