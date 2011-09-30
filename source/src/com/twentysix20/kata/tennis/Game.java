package com.twentysix20.kata.tennis;

public class Game {
	private TennisScore[] scores = new TennisScore[2];

	public Game() {
		scores[0] = TennisScore.Love;
		scores[1] = TennisScore.Love;
	}

	public void scorePoint(int player) {
		if (TennisScore.Forty.equals(scores[player])) {
			scores[player] = TennisScore.Winner;
		} else {
			switch (scores[player]) {
			case Thirty : 
			case Deuce  :
				if (scores[player].next().equals(scores[1-player])) {
					scores[0] = TennisScore.Deuce;
					scores[1] = TennisScore.Deuce;
				} else {
					scores[player] = scores[player].next();
				}
				break;
			default : 
				scores[player] = scores[player].next();
			}
		}
	}

	public String getCurrentScore(int player) {
		return scores[player].toString();
	}

	public int getWinner() {
		if (scores[0].equals(TennisScore.Winner))
			return 0;
		if (scores[1].equals(TennisScore.Winner))
			return 1;
		return -1;
	}
}