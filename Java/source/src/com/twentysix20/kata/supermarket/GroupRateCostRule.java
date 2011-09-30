package com.twentysix20.kata.supermarket;

public class GroupRateCostRule implements CostRule {
	private String name;
	private int groupCount;
	private int groupCost;
	private int baseCost;

	public GroupRateCostRule(String name, int baseCost, int groupCount, int groupCost) {
		super();
		this.name = name;
		this.groupCount = groupCount;
		this.groupCost = groupCost;
		this.baseCost = baseCost;
	}

	public String name() {
		return getName();
	}

	public String getName() {
		return name;
	}
	public int getGroupCount() {
		return groupCount;
	}
	public int getGroupCost() {
		return groupCost;
	}
	public int getBaseCost() {
		return baseCost;
	}

	@Override
	public int calculate(int count) {
		int cost = 0;
		while (count >= getGroupCount()) {
			cost += getGroupCost();
			count -= getGroupCount();
		}
		cost += count * getBaseCost();

		return cost;
	}
}
