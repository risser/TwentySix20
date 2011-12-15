package com.twentysix20.dlc.model;

import java.util.HashSet;
import java.util.Set;


public class ObjectWithSongs implements Comparable<ObjectWithSongs> {

	private final String name;
	private Set<Song> songs = new HashSet<Song>();

	public ObjectWithSongs(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (this == other)
			return true;
		if (other == null)
			return false;
		if (getClass() != other.getClass())
			return false;

		return name.equals(((ObjectWithSongs)other).getName());
	}

	public Set<Song> getSongs() {
		return songs;
	}

	public void addSong(Song song) {
		songs.add(song);
	}

	@Override
	public int compareTo(ObjectWithSongs o) {
		return name.toUpperCase().compareTo(o.getName().toUpperCase());
	}
}
