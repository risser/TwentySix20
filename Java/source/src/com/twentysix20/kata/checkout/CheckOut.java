package com.twentysix20.kata.checkout;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CheckOut {
	private Map<String,PricingRule> ruleMap = new HashMap<String,PricingRule>();
	private Map<String,Integer> countMap = new HashMap<String,Integer>();
	private int total = 0;

	public CheckOut(Set<PricingRule> rules) {
		for (PricingRule rule : rules) {
			ruleMap.put(rule.getSKU(), rule);
			countMap.put(rule.getSKU(), 0);
		}
	}

	public void scan(String itemSku) {
		addToTotal(getPriceForItem(itemSku));
	}

	private void addToTotal(int priceForItem) {
		total += priceForItem;
	}

	private int getPriceForItem(String itemSku) {
		PricingRule rule = ruleMap.get(itemSku);
		if (!rule.hasSpecialPricing())
			return rule.getUnitPrice();

		int count = countMap.get(itemSku) + 1;
		countMap.put(itemSku,count);
		return (count % rule.getDiscountGroupSize() == 0) ? rule.getDiscountedPrice() : rule.getUnitPrice();
	}

	public int total() {
		return total;
	}
}
