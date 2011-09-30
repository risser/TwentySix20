package com.twentysix20.cardstore.postage;


import com.twentysix20.cardstore.order.Order;

public class BasePlusPerCardGroupPostage implements CacheablePostage {
	private int baseRate;
	private int cardsInGroup;
	private int perGroup;
	private int hashcode;

	public BasePlusPerCardGroupPostage(int baseRate, int cardsInGroup, int perGroup) {
		this.baseRate = baseRate;
		this.cardsInGroup = cardsInGroup;
		this.perGroup = perGroup;
		this.hashcode = calcHashCode();
	}

	@Override
	public int calculate(Order order) {
		int numberOfCards = order.numberOfCards();
		return calculate(numberOfCards);
	}

	public int calculate(int numberOfCards) {
		return baseRate + perGroup * (numberOfCards / cardsInGroup);
	}

	@Override
	public String toString() {
		return String.format(
				"BasePlusPerCardGroupPostage [baseRate=%s, perGroup=%s, cardsInGroup=%s]", baseRate,
				perGroup, cardsInGroup);
	}

	@Override
	public int hashCode() {
		return hashcode;
	}

	private int calcHashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + baseRate;
		result = prime * result + cardsInGroup;
		result = prime * result + perGroup;
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
		BasePlusPerCardGroupPostage other = (BasePlusPerCardGroupPostage) obj;
		if (baseRate != other.baseRate)
			return false;
		if (cardsInGroup != other.cardsInGroup)
			return false;
		if (perGroup != other.perGroup)
			return false;
		return true;
	}

	@Override
	public int baseRate() {
		return baseRate;
	}
}
