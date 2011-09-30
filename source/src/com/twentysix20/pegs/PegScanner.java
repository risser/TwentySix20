package com.twentysix20.pegs;



public class PegScanner
{

	public static void solve(Board b, History track)
	{
		if (b.getNumberOfPegs() == 1)
		{
//			System.out.println(track);
			track.storeSolution();
		}
		else
		{
			boolean noMoves = true;
			for (int i=0; i < b.getSize(); i++)
			{
				if (b.hasPeg(i))
				{
					for (int dir=0; dir < b.getNeighborCount(); dir++)
					{
						if (b.legalJump(i,dir))
						{
							b.jump(i,dir);
							track.addMove(i,b.jumpDestination(i,dir));
//							solve(b,track+" "+(i+1)+"-"+(b.jumpDestination(i,dir)+1)+";");
							solve(b,track);
							b.unjump(i,dir);
							track.undoMove();
							noMoves = false;
						}
					}
				}
			}
			if (noMoves)
			{
				track.storeFailure();
			}
		}
	}

	public static void main(String[] args)
	{
//		Board board = new BoardTriangle();
		Board board = new BoardCross();
		History h = new History(board.getSize());
		for (int i=0; i < board.getSize()/*6*/; i++)/*board.getSize()*/
		{
System.out.println(i+1);
			board.removePeg(i);
			solve(board,h);
			board.insertPeg(i);
System.out.println("So far: "+h.getSolutionCount()+" solutions in "+h.getAttemptCount()+" tries.");
		}
	}
}