package com.twentysix20.thinker;

import java.lang.Math;

public class Tictac
{
	public static int winner(int[] board) throws Exception
	{
		int sz = board.length;
		int d = (int)Math.round(Math.sqrt(sz));
		if (d*d != sz)
			throw new Exception("Board must be a square.");

		int base = 0;

		for (int i = 0; i < d && base == 0; i++)
		{
System.out.println("i: " + i + "=" + board[i]);
			base = board[i];
			for (int j = i + d; j < sz; j += d)
			{
System.out.println("j: " + j + "=" + board[j]);
				if (board[j] != base)
					base = 0;
			}
		}
System.out.println(base);
		if (base > 0)
			return base;
System.out.println("howdy!");
		for (int i = 0; i < sz && base == 0; i += d)
		{
System.out.println("x: " + i + "=" + board[i]);
			base = board[i];
			for (int j = i + 1; j < i + d; j += 1)
			{
System.out.println("y: " + j + "=" + board[j]);
				if (board[j] != base)
					base = 0;
			}
		}
		if (base > 0)
			return base;

System.out.println("snickers!");
		base = board[0];
		for (int j = d + 1; j < sz; j += (d+1))
		{
System.out.println("y: " + j + "=" + board[j]);
			if (board[j] != base)
				base = 0;
		}
		if (base > 0)
			return base;

System.out.println("children!");
		base = board[d-1];
		for (int j = 2*(d - 1); j < sz-1; j += (d-1))
		{
System.out.println("y: " + j + "=" + board[j]);
			if (board[j] != base)
				base = 0;
		}
		if (base > 0)
			return base;

		return 0;
	}

	public static boolean legal(int[] prev, int[] current, int turn) throws Exception
	{
		int sz = prev.length;
		int d = (int)Math.round(Math.sqrt(sz));
		if (d*d != sz)
			throw new Exception("Board must be a square.");

		if (sz != current.length)
			throw new Exception("Boards must be the same size.");

		boolean nogood = false;
		int piece = 0;
		for (int i = 0; i < sz; i++)
		{
			if (prev[i] != 0 && current[i] != prev[i])	// changed owners, or cleared
				nogood = true;
			if (prev[i] == 0 && current[i] != 0)	// went from nothing to something
				if (piece > 0)
					nogood = true; // if a new piece was already played
				else
					piece = current[i];
		}
		if (piece != turn) // if the piece played was by the wrong person
			nogood = true; // will also catch if no piece was played (0)

		return nogood;
	}

	public static void main(String[] Args) throws Exception
	{
		System.out.println("tictac were here.\n");
		int[] b = {1,0,1,0,
				   1,0,1,1,
				   1,1,2,2,
				   1,1,2,1};
		System.out.println("The winner would be: " + winner(b));
	}
}