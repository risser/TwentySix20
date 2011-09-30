package com.wf.jdf.checkers2;

public class Piece {
	private int x;
	private int y;

	public Piece(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public boolean canMove() {
		return true;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
}
