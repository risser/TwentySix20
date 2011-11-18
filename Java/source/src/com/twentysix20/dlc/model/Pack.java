package com.twentysix20.dlc.model;


public class Pack extends ObjectWithSongs {

	public Pack(String name) {
		super(name);
	}

	@Override
	public String toString() {
		return "Pack [" + getName() + "]";
	}
}
