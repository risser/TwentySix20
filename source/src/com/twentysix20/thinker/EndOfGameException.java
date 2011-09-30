package com.twentysix20.thinker;

public class EndOfGameException extends ThinkerException
{
	public EndOfGameException(String s)
	{
		super("End of Game: " + s);
	}

	public static void main(String[] Args) throws Exception
	{
		System.out.println("EndOfGameException.\n");
	}
}
