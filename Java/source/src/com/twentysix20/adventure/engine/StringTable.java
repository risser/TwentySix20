package com.twentysix20.adventure.engine;

import java.util.*;
import com.twentysix20.adventure.engine.*;

public class StringTable
{

	static public final String INTRODUCTION = "introduction";
	static public final String INVENTORY = "inventory";
	static public final String ITEM_DELIMITER = "itemdelim";
	static public final String NOTHING = "nothing";
	static public final String I_SEE = "isee";

	static private Hashtable table = new Hashtable();

	public StringTable()
	{
		table.put(INTRODUCTION,"This is the introduction.");
		table.put(INVENTORY,"You are carrying: ");
		table.put(ITEM_DELIMITER,", ");
		table.put(NOTHING,"Nothing.");
		table.put(I_SEE,"I see: ");
	}

	public static String get(String which)
	{
		return (String)table.get(which);
	}

	public static void main(String[] Args) throws Exception
	{
		System.out.println("adventure.util.StringTable main");
	}
}