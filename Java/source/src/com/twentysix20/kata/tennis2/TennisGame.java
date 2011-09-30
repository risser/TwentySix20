package com.twentysix20.kata.tennis2;

public class TennisGame {
	static public String[] scores = new String[]{"Love","15","30","40"};

	private int server = 0;
	private int receiver = 0;

	public Object getScore() {
		boolean tie = (server == receiver);
		if (tie && server >= 3) return "Deuce";

		if ((server > 3) || (receiver > 3)) 
			if (Math.abs(server - receiver) > 1)
				return "Game: " + (server > receiver ? "Server" : "Receiver");
			else
				return "Advantage: " + (server > receiver ? "Server" : "Receiver");

		return scores[server] + " - " + ((tie && server > 0) ? "All" : scores[receiver]);
	}

	public void servingPlayerScores() {
		server += 1;
	}

	public void receivingPlayerScores() {
		receiver += 1;
	}
}