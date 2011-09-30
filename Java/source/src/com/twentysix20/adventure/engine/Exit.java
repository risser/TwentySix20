package com.twentysix20.adventure.engine;

import java.util.*;
import com.twentysix20.adventure.engine.*;

public class Exit extends Base
{

	private String Uid;
	private Room toRoom;
	private int State;
	// script variables

	public Exit(String uid)
	{
		this(uid, null);
	}

	public Exit(String uid, Room to)
	{
		super(uid);
		toRoom = to;
		State = Constants.CLOSED;
	}


	public String getId()
	{
		return Uid;
	}

	public Room getTo()
	{
		return toRoom;
	}

	public int getState()
	{
		return State;
	}
	public void setState(int state)
	{
		State = state;
		// error checking
	}

	public boolean isPlaceholder()
	{
		return (toRoom == null);
	}

	public static void main(String[] Args) throws Exception
	{
		System.out.println("adventure.engine.Exit main");
	}
}