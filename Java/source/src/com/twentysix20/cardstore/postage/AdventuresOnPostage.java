package com.twentysix20.cardstore.postage;


import com.twentysix20.cardstore.order.Order;

public class AdventuresOnPostage implements CacheablePostage {

	@Override
	public int calculate(Order order) {
		int count = order.numberOfCards();
		return calculate(count);
	}

	public int calculate(int count) {
		if (count <= 10)
			return 94;
		else if (count <= 20)
			return 134;
		else if (count <= 30)
			return 150;
		else if (count <= 70)
			return 250;
		else if (count <= 100)
			return 300;
		else 
			return 500;
	}

	@Override
	public int baseRate() {
		return 94;
	}

	@Override
	public String toString() {
		return "AdventuresOnPostage [baseRate=94, +50 every 10 or so...]";
	}
}
