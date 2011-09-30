package com.twentysix20.thinker;

public class BadMoveException extends ThinkerException
{
	public BadMoveException(String s)
	{
		super("Bad Move: " + s);
	}

	public static void main(String[] Args) throws Exception
	{
		System.out.println("Bad Move Exception.\n");
	}
}
