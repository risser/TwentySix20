package com.twentysix20.cardstore.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ShoppingList {
	private Map<String,Integer> map = new HashMap<String,Integer>();

	public ShoppingList(String...cardNames) {
		for (String name : cardNames)
			add(name,1);
	}

	public void add(String name, int i) {
		if (!map.containsKey(name)) {
			map.put(name,i);
		} else {
			Integer count = map.get(name);
			map.put(name, count + i);
		}
	}

	public int numberNeeded(String name) {
		return map.containsKey(name) ? map.get(name) : 0;
	}

	public Set<String> nameSet() {
		return map.keySet();
	}

	public void updateName(String oldName, String newName) {
		Integer count = map.remove(oldName);
		if (count == null) return;
		map.put(newName, count);
	}

	public String toString() {
		ArrayList<String> list = new ArrayList<String>(nameSet());
		Collections.sort(list);

		StringBuffer buf = new StringBuffer("Shopping List:\n");
		for (String cardName : list)
			buf.append(numberNeeded(cardName)).append("\t").append(cardName).append("\n");
		return buf.toString();
	}
}
