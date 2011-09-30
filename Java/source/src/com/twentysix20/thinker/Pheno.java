package com.twentysix20.thinker;

public class Pheno
{
//	Board board;	// no tictac.  Board is dumb board (like a piece of paper). Tictac is the judge.
//	Genome geno;		// no assumptions on length, style...?
	int runPtr;		// different Phenos will do different things against Geno
	int[] registers;  // make this a class, different Phenos can attach different registers
	// should actions be part of the Pheno, or a separate factory-like thing... Run This Action...

	public void setBoard(byte where, byte what)
	{
		//
	}

	public void setRegister(byte where, byte what)
	{
		//
	}

	public byte getRegister(byte where)
	{
		return 0;
	}

	public byte getBoard(byte where)
	{
		return 0;
	}

	public void incRunPtr()
	{
		this.incRunPtr(1);	// needs to be abstracted
	}

	public void incRunPtr(int i)
	{
		runPtr += 3 * i;
	}

	public static void main(String[] Args) throws Exception
	{
		System.out.println("I AM PHENO!.\n");
	}
}