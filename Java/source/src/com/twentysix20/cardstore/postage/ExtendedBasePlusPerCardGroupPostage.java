package com.twentysix20.cardstore.postage;


import com.twentysix20.cardstore.order.Order;

public class ExtendedBasePlusPerCardGroupPostage implements Postage {
	private int baseRate;
	private int extendedCount;
	private int cardsInGroup;
	private int perGroup;
	private int hashcode;

	public ExtendedBasePlusPerCardGroupPostage(int baseRate, int extendedCount, int cardsInGroup, int perGroup) {
		this.baseRate = baseRate;
		this.extendedCount = extendedCount;
		this.cardsInGroup = cardsInGroup;
		this.perGroup = perGroup;
		this.hashcode = calcHashCode();
	}

	@Override
	public int calculate(Order order) {
		if (order.numberOfCards() < extendedCount)
			return baseRate;
		return baseRate + perGroup * ((order.numberOfCards() - extendedCount) / cardsInGroup);
	}

	@Override
	public String toString() {
		return String.format(
				"ExtendedBasePlusPerCardGroupPostage [baseRate=%s, extendedCount=%s, perGroup=%s, cardsInGroup=%s]", 
				baseRate, extendedCount, perGroup, cardsInGroup);
	}

	@Override
	public int hashCode() {
		return hashcode;
	}

	private int calcHashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + baseRate;
		result = prime * result + extendedCount;
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
		ExtendedBasePlusPerCardGroupPostage other = (ExtendedBasePlusPerCardGroupPostage) obj;
		if (baseRate != other.baseRate)
			return false;
		if (extendedCount != other.extendedCount)
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
	public int extendedCount() {
		return extendedCount;
	}
}
