package com.twentysix20.kata.supermarket;

public class BasicCostRule implements CostRule {
	private String name;
	private int baseCost;

	public BasicCostRule(String name, int baseCost) {
		super();
		this.name = name;
		this.baseCost = baseCost;
	}

	public String name() {
		return getName();
	}

	public String getName() {
		return name;
	}
	public int getBaseCost() {
		return baseCost;
	}

	@Override
	public int calculate(int count) {
		return count * getBaseCost();
	}
}
