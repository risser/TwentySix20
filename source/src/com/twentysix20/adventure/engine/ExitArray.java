package com.twentysix20.adventure.engine;

import com.twentysix20.adventure.engine.*;

public class ExitArray
{
	private Exit[] theList;

	public ExitArray()
	{
		theList = new Exit[Constants.MAX_EXITS];
	}

	public Exit getExit(int direction)
	{
		return theList[direction];
	}

	public void setExit(int direction, Exit exit)
	{
		theList[direction] = exit;
	}

	public boolean isExit(int direction)
	{
		return theList[direction] != null;
	}

	public static void main(String[] Args) throws Exception
	{
		System.out.println("adventure.engine.ExitArray main");
	}
}