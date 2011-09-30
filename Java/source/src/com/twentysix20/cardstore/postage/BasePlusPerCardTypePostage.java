package com.twentysix20.cardstore.postage;


import com.twentysix20.cardstore.order.Order;

public class BasePlusPerCardTypePostage implements Postage {
	private int baseRate;
	private int perCard;
	private int grouping;
	private int hashcode;

	public BasePlusPerCardTypePostage(int baseRate, int perCardType) {
		this(baseRate,perCardType,1);
	}

	public BasePlusPerCardTypePostage(int baseRate, int perCardType, int grouping) {
		this.baseRate = baseRate;
		this.perCard = perCardType;
		this.grouping = grouping;
		this.hashcode = calcHashCode();
	}

	@Override
	public int calculate(Order order) {
		int count = order.numberOfCardInstances();
		int multiplier = (count + grouping - 2) / grouping;
		return baseRate + perCard * multiplier;
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
		result = prime * result + grouping;
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
		BasePlusPerCardTypePostage other = (BasePlusPerCardTypePostage) obj;
		if (baseRate != other.baseRate)
			return false;
		if (grouping != other.grouping)
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
