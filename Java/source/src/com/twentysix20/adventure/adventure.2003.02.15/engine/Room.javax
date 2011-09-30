package com.twentysix20.adventure.engine;

import com.twentysix20.adventure.engine.*;

public class Room extends Base
{

	private ExitArray Exits;
	private boolean Visited;
	private int Score;

	public Room(String uid)
	{
		this(uid, "", "", 0);
	}

	public Room(String uid, String name, String desc, int score)
	{
		super(uid,name,desc);
		Visited = false;
		Exits = new ExitArray();
	}

	public boolean wasVisited()
	{
		return Visited;
	}

	public void visit()
	{
		Visited = true;
	}

	public void addExit(int direction, Exit exit) throws Exception
	{
		if (!Exits.isExit(direction))
			Exits.setExit(direction, exit);
		else
			throw new Exception("This direction for this room has already been set.");
	}

	public Exit getExit(int direction)
	{
		return Exits.getExit(direction);
	}

	public int getScore()
	{
		return Score;
	}
	public void setScore(int score)
	{
		Score = score;
	}
	public boolean hasScore()
	{
		return (Score > 0);
	}

	public static void main(String[] Args) throws Exception
	{
		System.out.println("adventure.engine.Room main");
	}
}