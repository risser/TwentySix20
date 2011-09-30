package com.twentysix20.cardstore.postage;


import com.twentysix20.cardstore.order.Order;
import com.twentysix20.cardstore.supplyrecord.SupplyRecord;

public class BasePlusPerCardForEachInstancePostage implements Postage {
	private int baseRate;
	private int perCard;
	private int perCardGrouping;
	private int hashcode;

	public BasePlusPerCardForEachInstancePostage(int baseRate, int perCard, int perCardGrouping) {
		this.baseRate = baseRate;
		this.perCard = perCard;
		this.perCardGrouping = perCardGrouping;
		this.hashcode = calcHashCode();
	}

	@Override
	public int calculate(Order order) {
		int total = baseRate - perCard;
		for (SupplyRecord record : order.recordsIncludedInOrder()) {
			int count = order.numberOrderedForCardName(record.getCardName());
			total += ((count - 1) / perCardGrouping + 1) * perCard;
		}
		return total;
	}

	@Override
	public String toString() {
		return String.format(
				"BasePlusPerCardPlusPerInstancePostage [baseRate=%s, perCard=%s, perCardGrouping=%s]",
				baseRate, perCard, perCardGrouping);
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
		result = prime * result + perCardGrouping;
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
		BasePlusPerCardForEachInstancePostage other = (BasePlusPerCardForEachInstancePostage) obj;
		if (baseRate != other.baseRate)
			return false;
		if (perCard != other.perCard)
			return false;
		if (perCardGrouping != other.perCardGrouping)
			return false;
		return true;
	}

	@Override
	public int baseRate() {
		return baseRate;
	}
}
