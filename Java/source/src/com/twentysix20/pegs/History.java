package com.twentysix20.pegs;

public class History
{
	private int[] fromHole;
	private int[] toHole;
	private int moveCt;
	private long solutionCt;
	private long failureCt;

	public History(int holes) throws java.lang.IllegalArgumentException
	{
		if (holes < 1)
			throw new java.lang.IllegalArgumentException("Number of holes cannot be less than 1.");
		fromHole = new int[holes];
		toHole = new int[holes];
		moveCt = 0;
		solutionCt = 0;
		failureCt = 0;
	}

	public void addMove(int from, int to) throws java.lang.IllegalArgumentException
	{
		if (moveCt >= fromHole.length)
			throw new java.lang.IllegalArgumentException("Can't add any more moves. ("+moveCt+")");
		fromHole[moveCt] = from;
		toHole[moveCt] = to;
		moveCt++;
	}
	public void undoMove() throws java.lang.IllegalArgumentException
	{
		if (moveCt == 0)
			throw new java.lang.IllegalArgumentException("Can't undo any more moves. (0)");
		moveCt--;
	}

	public void storeSolution()
	{
		solutionCt++;
		System.out.println(toString());
	}
	public void storeFailure()
	{
		failureCt++;
//		if (getAttemptCount() % 100000 == 0)
//			System.out.println(getAttemptCount()+": "+new java.util.Date());
//		System.out.println(toString());
	}

	public long getAttemptCount()
	{
		return failureCt + solutionCt;
	}

	public long getFailureCount()
	{
		return failureCt;
	}

	public long getSolutionCount()
	{
		return solutionCt;
	}

	public long moveCount()
	{
		return moveCt;
	}

	public String toString()
	{
		String s = (toHole[0]+1)+": ";
		for (int i=0; i < moveCt; i++)
			s += (fromHole[i]+1) + "-" + (toHole[i]+1) + "; ";
		return s;
	}
}
