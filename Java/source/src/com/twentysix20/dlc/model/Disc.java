package com.twentysix20.dlc.model;



public class Disc extends ObjectWithSongs {

	public Disc(String name) {
		super(name);
	}

	@Override
	public String toString() {
		return "Disc [" + getName() + "]";
	}
}
