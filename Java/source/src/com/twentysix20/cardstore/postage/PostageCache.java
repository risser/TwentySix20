package com.twentysix20.cardstore.postage;

import com.twentysix20.cardstore.order.Order;

public class PostageCache implements Postage {
	private int[] postage = new int[50];
	private CacheablePostage postageCalculator;

	public PostageCache(CacheablePostage postage) {
		this.postageCalculator = postage;
		fillPostage();
	}

	private void fillPostage() {
System.out.println("*** FILLING: "+postageCalculator);		
		for (int i = 0; i < postage.length; i++)
			postage[i] = postageCalculator.calculate(i+1); 
	}

	@Override
	public int baseRate() {
		return postageCalculator.baseRate();
	}

	@Override
	public int calculate(Order order) {
		int numberOfCards = order.numberOfCards();
		return postage[numberOfCards-1];
	}

	public String toString() {
		return postageCalculator.toString();
	}
}
