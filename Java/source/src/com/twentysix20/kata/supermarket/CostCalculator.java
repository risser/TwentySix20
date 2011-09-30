package com.twentysix20.kata.supermarket;

import java.util.HashMap;
import java.util.Map;

public class CostCalculator {
	Map<String, CostRule> rules = new HashMap<String, CostRule>();
	Map<String, Integer> separatedItems = new HashMap<String, Integer>();

	public int cost(ItemCollection items) {
		for (Item item : items) {
			if (separatedItems.containsKey(item.getName()))
				separatedItems.put(item.getName(), separatedItems.get(item.getName())+1);
			else
				separatedItems.put(item.getName(), 1);
		}
		
		int cost = 0;
		for (String name : separatedItems.keySet())
			cost += rules.get(name).calculate(separatedItems.get(name));
		return cost;
	}

	public void addRule(CostRule costRule) {
		rules.put(costRule.name(), costRule);
	}

}
