package com.twentysix20.dlc.mapping;

import java.util.HashMap;
import java.util.Map;


public class StringMapping {

	private Map<String, String> mapping = new HashMap<String, String>();
	
	public StringMapping() {
		super();
	}
	
	public StringMapping(String mapFromString, String mapToString) {
		this();
		addMapping(mapFromString, mapToString);
	}

	public void addMapping(String mapFromString, String mapToString) {
		mapping.put(mapFromString, mapToString);
	}

	public String map(String incomingString) {
		return mapping.containsKey(incomingString) ? 
			mapping.get(incomingString) : 
			incomingString;
	}
}
