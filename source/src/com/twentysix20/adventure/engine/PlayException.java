package com.twentysix20.adventure.engine;

import com.twentysix20.adventure.engine.*;

public class PlayException extends Exception
{

	public PlayException()
	{
		super("Unknown Play Exception.");
	}
	public PlayException(String s)
	{
		super(s);
	}

	public static void main(String[] Args) throws Exception
	{
		System.out.println("adventure.engine.PlayException main");
	}
}