package com.twentysix20.adventure.engine;

import java.util.*;
import com.twentysix20.adventure.engine.*;

public class ExitList
{
	private Hashtable theList;

	public ExitList()
	{
		theList = new Hashtable();
	}

	public Exit getExit(String uid)
	{
		return (Exit)theList.get(uid);
	}

	public void addExit(String uid, Exit exit)
	{
		theList.put(uid,exit);
	}

	public static void main(String[] Args) throws Exception
	{
		System.out.println("adventure.engine.ExitList main");
	}
}