package com.twentysix20.adventure.engine;

import java.util.*;
import com.twentysix20.adventure.engine.*;

public class ItemList
{
/// implement a BaseList, then extend for the gets/removes, etc.  Everything else should be the same?
	private Hashtable theList;

	public ItemList()
	{
		theList = new Hashtable();
	}

	public Item getItem(String uid)
	{
		return (Item)theList.get(uid);
	}

	public void addItem(String uid, Item item)
	{
		theList.put(uid,item);
	}

	public Item removeItem(String uid)
	{
		return (Item)theList.remove(uid);
	}

	public Enumeration itemsInRoom(Room room)
	{
		return getItemEnum(room);
	}
	public Enumeration itemsInInventory()
	{
		return getItemEnum(null);
	}
	private Enumeration getItemEnum(Room inRoom)
	{
		Vector victor = new Vector();
		Enumeration ike = theList.keys();
		while (ike.hasMoreElements())
		{
			Item item = (Item)theList.get(ike.nextElement());
			boolean Flag;
			if (inRoom != null)
				Flag = item.isInRoom(inRoom);
			else
				Flag = item.isInInventory();
			victor.add(item);
		}
		return victor.elements();
	}

	public static void main(String[] Args) throws Exception
	{
		System.out.println("adventure.engine.ItemList main");
	}
}