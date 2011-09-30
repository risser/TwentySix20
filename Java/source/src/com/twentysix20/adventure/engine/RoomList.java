package com.twentysix20.adventure.engine;

import java.util.*;
import com.twentysix20.adventure.engine.*;

public class RoomList
{
	private Hashtable theList;

	public RoomList()
	{
		theList = new Hashtable();
	}

	public Room getRoom(String uid)
	{
		return (Room)theList.get(uid);
	}

	public void addRoom(String uid, Room exit)
	{
		theList.put(uid,exit);
	}

	public static void main(String[] Args) throws Exception
	{
		System.out.println("adventure.engine.RoomList main");
	}
}