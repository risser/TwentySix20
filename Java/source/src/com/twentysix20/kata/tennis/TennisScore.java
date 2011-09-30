package com.twentysix20.kata.tennis;

public enum TennisScore {
	Love, Fifteen, Thirty, Forty, Deuce, Advantage, Winner;

	public TennisScore next() {
		if (Winner.equals(this))
			return this;

		boolean foundIt = false;
		for (TennisScore score : TennisScore.values())
			if (foundIt)
				return score;
			else if (score.equals(this))
				foundIt = true;

		throw new IllegalStateException("You should never have gotten this far.  Now you must die.");
	}

	public String toString() {
		switch(this) {
			case Fifteen : return "15";
			case Thirty : return "30";
			case Forty : return "40";
		default : 
			return super.toString();
		}
	}
}
