package com.twentysix20.dlc;

import java.util.HashMap;
import java.util.Map;

public class DiscFactory {
	static private Map<String, Disc> discMap = new HashMap<String, Disc>();

	private DiscFactory() {}

	static public Disc getDisc(String name) {
		if (!discMap.containsKey(name))
			discMap.put(name, new Disc(name));

		return discMap.get(name);
	}

	public static void clear() {
		discMap.clear();
	}
}
