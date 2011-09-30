package com.twentysix20.adventure.engine;

import com.twentysix20.adventure.engine.*;

public class Item extends Base
{
	private Room inRoom;
	private boolean inInv;

	public Item(String uid)
	{
		this(uid, "", "");
	}

	public Item(String uid, String name, String desc)
	{
		super(uid,name,desc);
		inRoom = null;
		inInv = false;
	}

	public boolean isInInventory()
	{
		return inInv;
	}
	public void putInInventory()
	{
		inInv = true;
		inRoom = null;
	}

	public boolean isInRoom()
	{
		return (inRoom != null);
	}
	public boolean isInRoom(Room room)
	{
		return (inRoom.equals(room));
	}

	public void putInRoom(Room room)
	{
		inRoom = room;
		inInv = false;
	}
	public Room whichRoom()
	{
		return inRoom;
	}

	public boolean exists()
	{
		return (isInRoom() || isInInventory());
	}

	public void selfDestruct()
	{
		inInv = false;
		inRoom = null;
	}

// "destroying" item should remove from current room
// should item be able to move from room to room?  Or should the code add/remove items from appropriate rooms?

	public static void main(String[] Args) throws Exception
	{
		System.out.println("adventure.engine.Item main");
	}
}