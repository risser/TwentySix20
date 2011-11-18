package com.twentysix20.dlc.rawxml;

import java.util.HashSet;
import java.util.Set;

public class RawSongContainer {
	private Set<RawSong> songs = new HashSet<RawSong>();

	public Set<RawSong> getSongs() {
		return songs;
	}

	public void setSongs(Set<RawSong> songs) {
		this.songs = songs;
	}

	@Override
	public String toString() {
		return "RawSongContainer {{" + songs + "}}";
	}
}
