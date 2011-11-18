package com.twentysix20.dlc.model;



public class Artist extends ObjectWithSongs {

	public Artist(String name) {
		super(name);
	}

	@Override
	public String toString() {
		return "Artist [" + getName() + "]";
	}
}
