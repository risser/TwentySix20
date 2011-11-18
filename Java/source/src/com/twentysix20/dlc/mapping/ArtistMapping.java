package com.twentysix20.dlc.mapping;

import java.util.HashMap;
import java.util.Map;


public class ArtistMapping implements StringMapping {

	private Map<String, String> mapping = new HashMap<String, String>();
	
	public ArtistMapping(String mapFromString, String mapToString) {
		addMapping(mapFromString, mapToString);
	}

	public void addMapping(String mapFromString, String mapToString) {
		mapping.put(mapFromString, mapToString);
	}

	@Override
	public String map(String incomingString) {
		return mapping.containsKey(incomingString) ? 
			mapping.get(incomingString) : 
			incomingString;
	}

}
