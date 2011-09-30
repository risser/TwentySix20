package com.twentysix20.cardstore.postage;


import com.twentysix20.cardstore.order.Order;

public class BasePlusPerCardPostage implements CacheablePostage {
	private int baseRate;
	private int perCard;
	private int hashcode;

	public BasePlusPerCardPostage(int baseRate, int perCard) {
		this.baseRate = baseRate;
		this.perCard = perCard;
		this.hashcode = calcHashCode();
	}

	@Override
	public int calculate(Order order) {
		int numberOfCards = order.numberOfCards();
		return calculate(numberOfCards);
	}

	public int calculate(int numberOfCards) {
		return baseRate + perCard * (numberOfCards - 1);
	}

	@Override
	public String toString() {
		return String.format(
				"BasePlusPerCardPostage [baseRate=%s, perCard=%s]", baseRate,
				perCard);
	}

	@Override
	public int hashCode() {
		return hashcode;
	}

	private int calcHashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + baseRate;
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
		BasePlusPerCardPostage other = (BasePlusPerCardPostage) obj;
		if (baseRate != other.baseRate)
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
