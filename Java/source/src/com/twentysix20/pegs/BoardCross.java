package com.twentysix20.pegs;



public class BoardCross extends Board
{

	static final int[][] theNeighbors ={{3,1}, {4,2}, {5,-1},
										{8,4}, {9,5}, {10,-1},
										{13,7}, {14,8}, {15,9}, {16,10}, {17,11}, {18,12}, {19,-1},
										{20,14}, {21,15}, {22,16}, {23,17}, {24,18}, {25,19}, {26,-1},
										{-1,21}, {-1,22}, {27,23}, {28,24}, {29,25}, {-1,26}, {-1,-1},
										{30,28}, {31,29}, {32,-1},
										{-1,31}, {-1,32}, {-1,-1}
									   };

	public BoardCross()
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
