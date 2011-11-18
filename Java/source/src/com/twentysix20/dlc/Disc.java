package com.twentysix20.dlc;


public class Disc extends ObjectWithSongs {

	public Disc(String name) {
		super(name);
	}

	@Override
	public String toString() {
		return "Disc [" + getName() + "]";
	}
}
