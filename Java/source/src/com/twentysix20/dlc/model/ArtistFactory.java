package com.twentysix20.dlc.model;

import java.util.HashMap;
import java.util.Map;

public class ArtistFactory {
	static private Map<String, Artist> artistMap = new HashMap<String, Artist>();

	private ArtistFactory() {}

	static public Artist getArtist(String name) {
		if (!artistMap.containsKey(name))
			artistMap.put(name, new Artist(name));

		return artistMap.get(name);
	}

	public static void clear() {
		artistMap.clear();
	}
}
