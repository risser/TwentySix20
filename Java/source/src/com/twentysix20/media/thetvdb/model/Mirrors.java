package com.twentysix20.media.thetvdb.model;

import java.util.List;

public class Mirrors {
	public List<Mirror> mirrors;

	@Override
	public String toString() {
		return String.format("Mirrors [mirrorset=%s]", mirrors);
	}
}
