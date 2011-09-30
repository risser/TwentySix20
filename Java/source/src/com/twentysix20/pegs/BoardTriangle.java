package com.twentysix20.pegs;



public class BoardTriangle extends Board
{

	static final int[][] theNeighbors ={{1,2,-1},
										{3,4,2},
										{4,5,-1},
										{6,7,4},
										{7,8,5},
										{8,9,-1},
										{10,11,7},
										{11,12,8},
										{12,13,9},
										{13,14,-1},
										{-1,-1,11},
										{-1,-1,12},
										{-1,-1,13},
										{-1,-1,14},
										{-1,-1,-1}};

	public BoardTriangle()
	{
		super(theNeighbors.length,theNeighbors[0].length*2);
	}

	protected void construct()
	// override me to create your own boards.
	{
		int nCt = theNeighbors[0].length;
		for (int i=0; i < theNeighbors.length; i++)
			for (int j=0; j < nCt; j++)
				if (theNeighbors[i][j] > -1)
				{
					neighbors[i][j] = theNeighbors[i][j];
					neighbors[theNeighbors[i][j]][j+nCt] = i;
				}
	}
}
