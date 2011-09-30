package com.twentysix20.cardstore.postage;


import com.twentysix20.cardstore.order.Order;

public class BasePlusOneAndAHalfPerCardPostage implements CacheablePostage {
	private int baseRate;
	private int hashcode;

	public BasePlusOneAndAHalfPerCardPostage(int baseRate) {
		this.baseRate = baseRate;
		this.hashcode = calcHashCode();
	}

	@Override
	public int calculate(Order order) {
		int numberOfCards = order.numberOfCards();
		return calculate(numberOfCards);
	}

	public int calculate(int numberOfCards) {
		return baseRate + (numberOfCards - 1) + (numberOfCards - 1) / 2;
	}

	@Override
	public String toString() {
		return String.format(
				"BasePlusOneAndAHalfPerCardPostage [baseRate=%s, perCard=1.5]", baseRate);
	}

	@Override
	public int hashCode() {
		return hashcode;
	}

	private int calcHashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + baseRate;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BasePlusOneAndAHalfPerCardPostage other = (BasePlusOneAndAHalfPerCardPostage) obj;
		if (baseRate != other.baseRate)
			return false;
		return true;
	}

	@Override
	public int baseRate() {
		return baseRate;
	}
}