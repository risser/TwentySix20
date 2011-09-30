package com.twentysix20.kata.supermarket;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestItemCollection {
	@Test public void oneCanOfBeans() {
		ItemCollection items = new ItemCollection();
		items.addItem("Can of Beans");

		CostCalculator calc = new CostCalculator();
		calc.addRule(new BasicCostRule("Can of Beans", 65));
		assertEquals(65, calc.cost(items));
	}

	@Test public void noItems() {
		ItemCollection items = new ItemCollection();

		CostCalculator calc = new CostCalculator();
		assertEquals(0, calc.cost(items));
	}

	@Test public void twoCansOfBeans() {
		ItemCollection items = new ItemCollection();
		items.addItems(2, "Can of Beans");

		CostCalculator calc = new CostCalculator();
		calc.addRule(new BasicCostRule("Can of Beans", 65));
		assertEquals(130, calc.cost(items));
	}

	@Test public void twoDifferentCansOfBeans() {
		ItemCollection items = new ItemCollection();
		items.addItem("Can of Navy Beans");
		items.addItem("Can of Kidney Beans");

		CostCalculator calc = new CostCalculator();
		calc.addRule(new BasicCostRule("Can of Navy Beans", 65));
		calc.addRule(new BasicCostRule("Can of Kidney Beans", 75));
		assertEquals(140, calc.cost(items));
	}

	@Test public void threeCansForTwoDollars() {
		ItemCollection items = new ItemCollection();
		items.addItems(3, "Can of Kidney Beans");

		CostCalculator calc = new CostCalculator();
		calc.addRule(new GroupRateCostRule("Can of Kidney Beans", 75, 3, 200));
		assertEquals(200, calc.cost(items));
	}

	@Test public void fourCansForMoreThanTwoDollars() {
		ItemCollection items = new ItemCollection();
		items.addItems(4, "Can of Kidney Beans");

		CostCalculator calc = new CostCalculator();
		calc.addRule(new GroupRateCostRule("Can of Kidney Beans", 75, 3, 200));
		assertEquals(275, calc.cost(items));
	}

	@Test public void differentCansForMoreThanTwoDollars() {
		ItemCollection items = new ItemCollection();
		items.addItems(3, "Can of Navy Beans");
		items.addItems(4, "Can of Kidney Beans");

		CostCalculator calc = new CostCalculator();
		calc.addRule(new BasicCostRule("Can of Navy Beans", 65));
		calc.addRule(new GroupRateCostRule("Can of Kidney Beans", 75, 3, 200));
		assertEquals(470, calc.cost(items));
	}

	@Test public void fiveCansForFourDollars() {
		ItemCollection items = new ItemCollection();
		items.addItems(5, "Can of Kidney Beans");

		CostCalculator calc = new CostCalculator();
		calc.addRule(new GroupRateCostRule("Can of Kidney Beans", 75, 5, 400));
		assertEquals(400, calc.cost(items));
	}
}
