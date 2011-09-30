package com.twentysix20.kata.bowling;

public class Game {
	private String rollStr;

	public Game(String rolls) {
		rollStr = rolls;
	}

	public int score() {
		int score = 0;
		String extraRolls = null;
		if (rollStr.charAt(18) == 'X') {
			extraRolls = rollStr.substring(19);
			rollStr = rollStr.substring(0,19);
			System.out.println(rollStr+"::"+extraRolls);
		}

		boolean previousBallWasStrike = false;
		boolean previousPreviousBallWasStrike = false;
		for (char c : rollStr.replaceAll("X-","X").toCharArray()) {
			int pins = convertToPins(c);
			score += pins;
			if (previousBallWasStrike)
				score += pins;
			if (previousPreviousBallWasStrike)
				score += pins;

			previousPreviousBallWasStrike = previousBallWasStrike;
			previousBallWasStrike = (pins == 10);
		}
		
		if (previousBallWasStrike) {
			score += convertToPins(extraRolls.charAt(0));
			score += convertToPins(extraRolls.charAt(1));
		}
		
		if (previousPreviousBallWasStrike) {
			score += convertToPins(extraRolls.charAt(0));
		}

		return score;
	}

	private int convertToPins(char c) {
		if ((c >= '0') && (c <= '9')) {
			return c - 48;
		} else if (c == 'X') {
			return 10;
		}
		return 0;
	}

}
