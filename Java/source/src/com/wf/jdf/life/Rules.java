package com.wf.jdf.life;

public class Rules {

	public boolean isAlive(int neighbors, boolean isAlive) {
		switch (neighbors) {
		case 2:
			return isAlive;
		case 3:
			return true;
		default: 
			return false;
		}
	}

}
