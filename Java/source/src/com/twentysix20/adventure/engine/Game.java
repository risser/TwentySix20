package com.twentysix20.adventure.engine;

import com.twentysix20.adventure.engine.*;
import java.io.Writer;

public class Game
{
	private ItemList masterItemList;
	private RoomList masterRoomList;
	private ExitList masterExitList;
	private String introductionText;
	private int score;
	private int turn;
	private Room currentRoom;
	private boolean firstTurn;
	private Output output;


	public Game()
	{
		masterItemList = new ItemList();
		masterRoomList = new RoomList();
		masterExitList = new ExitList();
		score = 0;
		turn = 0;
		output = null;
	}

	public String performTurn(String input) throws Exception
	{
//		input = SubstituteList.substitute(input);

		turn++;

		if (currentRoom == null)
			throw new PlayException("Your current room is NULL.  This is an invalid setup.");

		if (turn==1)
			output.println(StringTable.get(StringTable.INTRODUCTION));

		output.println(currentRoom.getName());
		if (!currentRoom.wasVisited())
			output.println(currentRoom.getDescription());
		currentRoom.visit();

		output.printObjectNames(masterItemList.itemsInRoom(currentRoom),
								StringTable.get(StringTable.I_SEE),
								StringTable.get(StringTable.ITEM_DELIMITER),
								StringTable.get(StringTable.NOTHING));

		return "";
	}

	public void setIntroText(String s)
	{
		introductionText = s;
	}

	public void setOutput(Writer w)
	{
		output = new Output(w);
	}

	public static void main(String[] Args) throws Exception
	{
		System.out.println("adventure.engine.Game main");
	}
}