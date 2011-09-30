package com.twentysix20.kata.movie;

public enum Day {
	SUN, MON, TUE, WED, THU, FRI, SAT;
	
	public boolean isWeekend() {
		return (this == SUN) || (this == SAT);
	}
}
