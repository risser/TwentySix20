package com.twentysix20.pegs;

public class Board
{
	protected boolean[] pegs;
	protected int[][] neighbors;

	public Board(int z, int n)
	{
		if (z < 3)
			throw new java.lang.IllegalArgumentException("Can't have a board with fewer than 3 holes.");
		if (n < 2)
			throw new java.lang.IllegalArgumentException("Can't have holes with fewer than 2 neighbors.");
		pegs = new boolean[z];
		neighbors = new int[z][n];

		for (int i=0; i < pegs.length; i++)
		{
			pegs[i] = true;
			for (int j=0; j < neighbors[i].length; j++)
				neighbors[i][j] = -1;
		}

		construct();
	}

	protected void construct()
	// override me to create your own boards.
	{
	}

	public int getSize()
	{
		return pegs.length;
	}

	public int getNeighborCount()
	{
		return neighbors[0].length;
	}

	public boolean hasPeg(int i)
	{
		return pegs[i];
	}

	public void insertPeg(int i)
	{
		if (hasPeg(i))
			throw new java.lang.IllegalArgumentException("Can't insert a peg into hole "+i+".  It already has a peg.");
		pegs[i] = true;
	}
	public void removePeg(int i)
	{
		if (!hasPeg(i))
			throw new java.lang.IllegalArgumentException("Can't remove a peg from hole "+i+".  There is no peg there.");
		pegs[i] = false;
	}

	public int getNeighbor(int i, int j)
	{
		return neighbors[i][j];
	}

	public boolean hasNeighbor(int hole, int direction)
	{
		return (neighbors[hole][direction] > -1);
	}

	public boolean legalJump(int hole, int direction)
	{
		return (hasNeighbor(hole,direction) &&
				hasPeg(getNeighbor(hole,direction)) &&
				hasNeighbor(getNeighbor(hole,direction),direction) &&
				!hasPeg(jumpDestination(hole,direction)) );
	}

	public int jumpDestination(int hole, int direction)
	{
		return getNeighbor(getNeighbor(hole,direction),direction);
	}
	public void jump(int hole, int direction)
	{
		if (!legalJump(hole,direction))
			throw new java.lang.IllegalArgumentException("Jumping from hole "+hole+" in the direction "+direction+" is an illegal jump.");
		removePeg(hole);
		removePeg(getNeighbor(hole,direction));
		insertPeg(jumpDestination(hole,direction));
	}
	public void unjump(int hole, int direction)
	{
		insertPeg(hole);
		insertPeg(getNeighbor(hole,direction));
		removePeg(jumpDestination(hole,direction));
	}

	public String boardState()
	{
		String s = "";
		for (int i=0; i < pegs.length; i++)
			s += (hasPeg(i)?"1":"0");
		return s;
	}

	public int getNumberOfPegs()
	{
		int ct = 0;
		for (int i=0; i < pegs.length; i++)
			if (hasPeg(i)) ct++;
		return ct;
	}
}
