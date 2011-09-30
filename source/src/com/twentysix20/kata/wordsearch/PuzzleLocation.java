package com.twentysix20.kata.wordsearch;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PuzzleLocation {
	public static final int[] DIRECTION_EAST = new int[]{0,1};
	public static final int[] DIRECTION_WEST = new int[]{0,-1};
	public static final int[] DIRECTION_NORTH = new int[]{-1,0};
	public static final int[] DIRECTION_SOUTH = new int[]{1,0};
	public static final int[] DIRECTION_NE = new int[]{-1,1};
	public static final int[] DIRECTION_NW = new int[]{-1,-1};
	public static final int[] DIRECTION_SW = new int[]{1,-1};
	public static final int[] DIRECTION_SE = new int[]{1,1};
	public static final Set<int[]> DIRECTIONS = new HashSet<int[]>();
	static {
		DIRECTIONS.add(DIRECTION_EAST);
		DIRECTIONS.add(DIRECTION_WEST);
		DIRECTIONS.add(DIRECTION_NORTH);
		DIRECTIONS.add(DIRECTION_SOUTH);
		DIRECTIONS.add(DIRECTION_NE);
		DIRECTIONS.add(DIRECTION_NW);
		DIRECTIONS.add(DIRECTION_SW);
		DIRECTIONS.add(DIRECTION_SE);
	}

	private int[] direction;
	private int x;
	private int y;

	public PuzzleLocation(int y, int x, int[] direction) {
		this.y = y;
		this.x = x;
		this.direction = direction;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(direction);
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PuzzleLocation other = (PuzzleLocation) obj;
		if (!Arrays.equals(direction, other.direction))
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	public int[] getDirection() {
		return direction;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
