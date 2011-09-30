package com.twentysix20.cardstore.postage;


import com.twentysix20.cardstore.order.Order;

public class TrollAndToadPostage implements CacheablePostage {

	@Override
	public int calculate(Order order) {
		int count = order.numberOfCards();
		return calculate(count);
	}

	public int calculate(int count) {
		if (count <= 99)
			return 99;
		else if (count < 103)
			return 433;
		else if (count < 114)
			return 456;
		else if (count < 121)
			return 477;
		else if (count < 126)
			return 504;
		else if (count < 137)
			return 526;
		else if (count < 145)
			return 549;
		else 
			return 650; // no idea, but there's no pattern, so I give up.
	}

	@Override
	public int baseRate() {
		return 99;
	}

	@Override
	public String toString() {
		return "TrollAndToadPostage [baseRate=99, > 99=433 etc.]";
	}
}