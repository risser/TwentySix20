package com.wf.jdf.life;

import com.twentysix20.testutil.TestCase2620;

public class TestGrid extends TestCase2620 {

	public void testCreateBlankGrid() {
		Grid grid = new Grid(3,3);
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				assertFalse(grid.isAlive(i,j));
	}

	public void testMakeOneCellAlive() {
		Grid grid = new Grid(3,3);
		grid.setAlive(1,1);
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				if (i == 1 && j == 1)
					assertTrue(grid.isAlive(i,j));
				else
					assertFalse("Should be false!!",grid.isAlive(i,j));
	}

	public void testGetXY() {
		Grid grid = new Grid(3,6);
		assertEquals(3,grid.getX());
		assertEquals(6,grid.getY());
	}
}
