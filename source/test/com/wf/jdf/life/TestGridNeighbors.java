package com.wf.jdf.life;

import com.twentysix20.testutil.TestCase2620;

public class TestGridNeighbors extends TestCase2620 {
	public void testEmptyGrid() {
		Grid grid = new Grid(3,3);
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				assertEquals(0,grid.neighbors(i,j));
	}
	public void testCenterSquare() {
		Grid grid = new Grid(3,3);
		grid.setAlive(1,1);
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				if (i == 1 && j == 1)
					assertEquals(0,grid.neighbors(i, j));
				else
					assertEquals(1,grid.neighbors(i, j));
	}
	public void testEmptyCenterSquare() {
		Grid grid = new Grid(3,3);
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				if (i != 1 || j != 1)
					grid.setAlive(i,j);
		assertEquals(2,grid.neighbors(0,0));
		assertEquals(4,grid.neighbors(1,0));
		assertEquals(2,grid.neighbors(2,0));
		assertEquals(4,grid.neighbors(0,1));
		assertEquals(8,grid.neighbors(1,1));
		assertEquals(4,grid.neighbors(2,1));
		assertEquals(2,grid.neighbors(0,2));
		assertEquals(4,grid.neighbors(1,2));
		assertEquals(2,grid.neighbors(2,2));
	}
	public void testFullSquare() {
		Grid grid = new Grid(3,3);
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				grid.setAlive(i,j);
		assertEquals(3,grid.neighbors(0,0));
		assertEquals(5,grid.neighbors(1,0));
		assertEquals(3,grid.neighbors(2,0));
		assertEquals(5,grid.neighbors(0,1));
		assertEquals(8,grid.neighbors(1,1));
		assertEquals(5,grid.neighbors(2,1));
		assertEquals(3,grid.neighbors(0,2));
		assertEquals(5,grid.neighbors(1,2));
		assertEquals(3,grid.neighbors(2,2));
	}
}
