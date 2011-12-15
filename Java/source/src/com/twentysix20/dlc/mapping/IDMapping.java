package com.twentysix20.dlc.mapping;

import java.util.HashMap;
import java.util.Map;


public class IDMapping {

	private Map<Integer, String> mapping = new HashMap<Integer, String>();
	
	public IDMapping() {
		super();
	}
	
	public IDMapping(int mapFromID, String mapToString) {
		this();
		addMapping(mapFromID, mapToString);
	}

	public void addMapping(int mapFromID, String mapToString) {
		mapping.put(mapFromID, mapToString);
	}

	public String map(int incomingID, String originalString) {
		return mapping.containsKey(incomingID) ? 
			mapping.get(incomingID) : 
			originalString;
	}
}