package com.twentysix20.thinker;

public abstract class Command
{
	public abstract void execute(Pheno p, byte x, byte y);

	public static void main(String[] Args) throws Exception
	{
		System.out.println("action is abstract.\n");
	}
}