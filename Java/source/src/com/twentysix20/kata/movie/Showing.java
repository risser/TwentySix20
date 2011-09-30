package com.twentysix20.kata.movie;

public class Showing {
	private int length;
	private Day day;
	private Floor floor;
	private boolean is3D;
	
	public Showing(int length, Day day, Floor floor, boolean is3d) {
		this.length = length;
		this.day = day;
		this.floor = floor;
		is3D = is3d;
	}
	public int getLength() {
		return length;
	}
	public Day getDay() {
		return day;
	}
	public Floor getFloor() {
		return floor;
	}
	public boolean is3D() {
		return is3D;
	}
}
