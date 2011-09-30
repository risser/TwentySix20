package com.twentysix20.adventure.engine;

public class Constants
{

	static public final int NORTH = 0;
	static public final int NORTHEAST = 1;
	static public final int EAST = 2;
	static public final int SOUTHEAST = 3;
	static public final int SOUTH = 4;
	static public final int SOUTHWEST = 5;
	static public final int WEST = 6;
	static public final int NORTHWEST = 7;
	static public final int UP = 8;
	static public final int DOWN = 9;

	static public final int MAX_EXITS = DOWN;

	static public final int OPEN = 0;
	static public final int HIDDEN = 1;
	static public final int CLOSED = 2;

	public static void main(String[] Args) throws Exception
	{
		System.out.println("adventure.util.Constants main");
	}
}