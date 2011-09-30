package com.twentysix20.cardstore.postage;


import com.twentysix20.cardstore.order.Order;

public class BasePlusPerCardWithCapPostage implements CacheablePostage {
	private int baseRate;
	private int perCard;
	private int capAmount;
	private int hashcode;

	public BasePlusPerCardWithCapPostage(int baseRate, int perCard, int capAmount) {
		this.baseRate = baseRate;
		this.perCard = perCard;
		this.capAmount = capAmount;
		this.hashcode = calcHashCode();
	}

	@Override
	public int calculate(Order order) {
		int numberOfCards = order.numberOfCards();
		return calculate(numberOfCards);
	}

	public int calculate(int numberOfCards) {
		int base = baseRate + perCard * (numberOfCards - 1);
		return capAmount < base ? capAmount : base;
	}

	@Override
	public String toString() {
		return String.format(
				"BasePlusPerCardWithCapPostage [baseRate=%s, perCard=%s, capAmount=%s]", 
				baseRate, perCard, capAmount);
	}

	@Override
	public int hashCode() {
		return hashcode;
	}

	private int calcHashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + baseRate;
		result = prime * result + capAmount;
		result = prime * result + perCard;
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
		BasePlusPerCardWithCapPostage other = (BasePlusPerCardWithCapPostage) obj;
		if (baseRate != other.baseRate)
			return false;
		if (capAmount != other.capAmount)
			return false;
		if (perCard != other.perCard)
			return false;
		return true;
	}

	@Override
	public int baseRate() {
		return baseRate;
	}
}
