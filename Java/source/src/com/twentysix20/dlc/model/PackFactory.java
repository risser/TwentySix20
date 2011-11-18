package com.twentysix20.dlc.model;

import java.util.HashMap;
import java.util.Map;

public class PackFactory {
	static private Map<String, Pack> packMap = new HashMap<String, Pack>();

	private PackFactory() {}

	static public Pack getPack(String name) {
		if (!packMap.containsKey(name))
			packMap.put(name, new Pack(name));

		return packMap.get(name);
	}

	public static void clear() {
		packMap.clear();
	}
}
