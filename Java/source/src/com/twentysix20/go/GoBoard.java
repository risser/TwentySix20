package com.twentysix20.go;

import java.util.*;

public class GoBoard
{
	static public final int EMPTY = 0;
	static public final int BLACK = 1;
	static public final int WHITE = 2;

	static public final String stupid = "0123456789!$^()@}`]_-~';,.";
//	static public final String stupid = "0123456789!$^(){}[]_-~';,.";

	private int size;
	private int[][] board;

	public GoBoard(int sz) throws Exception
	{
		if ((sz < 3) || (sz > 19))
			throw new Exception("Bad size ("+sz+").  Minimum size is 3, maximum size is 19.");
		size = sz;
		board = new int[size][size];

	}

	public GoBoard(String s) throws Exception
	{
		this(s.charAt(0)-'A');
		int x = 0;
		int y = 0;

		for (int i = 1; i < s.length(); i++)
		{
			char c = s.charAt(i);
			int ct = stupid.indexOf(c) + 1;
			int pc = GoBoard.EMPTY;
			if (ct == 0)
				if (Character.isUpperCase(c))
				{
					ct = c - 'A' + 1;
					pc = GoBoard.BLACK;
				}
				else
				{
					ct = c - 'a' + 1;
					pc = GoBoard.WHITE;
				}
			for (int j = 0; j < ct; j++)
			{
				setPiece(x,y,pc);
				y += ((++x % size) == 0 ? 1 : 0);
				x %= size;
			}
		}
/*
		for (int i = 1; i < s.length(); i++)
		{
			switch (s.charAt(i))
			{
				case 'X': setPiece(x,y,GoBoard.EMPTY); break;
				case 'B': setPiece(x,y,GoBoard.BLACK); break;
				case 'W': setPiece(x,y,GoBoard.WHITE); break;
			}
			y += ((++x % size) == 0 ? 1 : 0);
			x %= size;
		}
*/
	}

	public int getSize()
	{
		return size;
	}

	public boolean equals(GoBoard go) throws Exception
	{
		boolean same = true;
		for (int j = 0;same && (j<size);j++)
			for (int i = 0; same && (i<size); i++)
				same = (go.getPiece(i,j) == board[i][j]);
		return same;
	}

	public boolean isEmpty(int x, int y) throws Exception
	{
		if ((x < 0) || (x >= size) || (y < 0) || (y >= size))
			throw new Exception("Bad placement ("+x+","+y+").  Must be (0,0) to ("+(size-1)+","+(size-1)+").");
		return board[x][y] == GoBoard.EMPTY;
	}

	public int getPiece(int x, int y) throws Exception
	{
		if ((x < 0) || (x >= size) || (y < 0) || (y >= size))
			throw new Exception("Bad placement ("+x+","+y+").  Must be (0,0) to ("+(size-1)+","+(size-1)+").");
		return board[x][y];
	}
	public void setPiece(int x, int y, int piece) throws Exception
	{
		if ((x < 0) || (x >= size) || (y < 0) || (y >= size))
			throw new Exception("Bad placement ("+x+","+y+").  Must be (0,0) to ("+(size-1)+","+(size-1)+").");
		if (!((piece == GoBoard.EMPTY) || (piece == GoBoard.WHITE) || (piece == GoBoard.BLACK)))
			throw new Exception("Bad piece choice: "+piece);
		board[x][y] = piece;
	}
	public void removePiece(int x, int y) throws Exception
	{
		if ((x < 0) || (x >= size) || (y < 0) || (y >= size))
			throw new Exception("Bad placement ("+x+","+y+").  Must be (0,0) to ("+(size-1)+","+(size-1)+").");
		board[x][y] = GoBoard.EMPTY;
	}

	public String encode() throws Exception
	{
		int ct = 0;
		int pc = getPiece(0,0);

		StringBuffer bob = new StringBuffer(new Character((char)('A'+size)).toString());
		for (int y = 0; y < size; y++)
			for (int x = 0; x < size; x++)
			{
				int it = getPiece(x,y);
				if ((it == pc) && (ct < 26))
				{
					ct++;
				}
				else
				{
					if (pc == GoBoard.BLACK)
						bob.append(new Character((char)('A'+ct-1)).toString());
					if (pc == GoBoard.WHITE)
						bob.append(new Character((char)('a'+ct-1)).toString());
					if (pc == GoBoard.EMPTY)
						bob.append(stupid.charAt(ct-1));
					ct = 1;
					pc = it;
				}
/*
BBBWWWXXX
W
1
/*
0123456789
[](){},.:;
!@#$^*
*/
			}
			if (pc == GoBoard.BLACK)
				bob.append(new Character((char)('A'+ct-1)).toString());
			if (pc == GoBoard.WHITE)
				bob.append(new Character((char)('a'+ct-1)).toString());
			if (pc == GoBoard.EMPTY)
				bob.append(stupid.charAt(ct-1));

/*
		int x = 0;
		int y = 0;
		while (y * size + x < size * size)
		{
			switch (getPiece(x,y))
			{
				case GoBoard.EMPTY: bob.append("X"); break;
				case GoBoard.BLACK: bob.append("B"); break;
				case GoBoard.WHITE: bob.append("W"); break;
			}

			y += ((++x % size) == 0 ? 1 : 0);
			x %= size;
		}
*/
		return bob.toString();

	}

	static public void main(String[] args) throws Exception
	{
		String s = "JA,..1a";
		if (args.length == 1)
			s = args[0];
		GoBoard bobo = new GoBoard(s);
		String fred = bobo.encode();
		System.out.println(s.equals(fred)+": "+fred);
	}
}