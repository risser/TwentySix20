package com.twentysix20.cardstore.supplyrecord;

import java.io.Serializable;

import com.twentysix20.cardstore.money.Money;
import com.twentysix20.cardstore.vendor.Vendor;

public class StandardSupplyRecord implements Serializable, SupplyRecord {
	private static final long serialVersionUID = 1L;

	private String cardName;
	private Vendor vendor;
	private String set;
	private String condition;
	private int quantity;
	private int cost;
	private int hashcode;

	public StandardSupplyRecord(String cardName, Vendor vendor, String set, String condition, int quantity, int cost) {
		this.cardName = cardName;
		this.vendor = vendor;
		this.set = set;
		this.condition = condition;
		this.quantity = quantity;
		this.cost = cost;
		this.hashcode = calcHashCode();
	}

	public StandardSupplyRecord(String cardName, Vendor vendor, String set, String condition, int quantity, String cost) {
		this(cardName, vendor, set, condition, quantity, Integer.parseInt(cost.replaceAll("\\.","")));
	}

	public String getCardName() {
		return cardName;
	}

	public String getVendorName() {
		return vendor.name();
	}

	public Vendor getVendor() {
		return vendor;
	}

	public String getSet() {
		return set;
	}

	public String getCondition() {
		return condition;
	}

	public int getQuantity() {
		return quantity;
	}

	public int getCost() {
		return cost;
	}

	@Override
	public String toString() {
		String string = super.toString();
		string = string.substring(string.lastIndexOf('.')+1);
		return string + String.format(
						"(cardName: %s; condition: %s; cost: %s; quantity: %s; set: %s; vendor: %s)",
						cardName, condition, new Money(cost), quantity, set, vendor);
	}

	@Override
	public int hashCode() {
		return hashcode;
	}

	private int calcHashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cardName == null) ? 0 : cardName.hashCode());
		result = prime * result
				+ ((condition == null) ? 0 : condition.hashCode());
		result = prime * result + cost;
		result = prime * result + quantity;
		result = prime * result + ((set == null) ? 0 : set.hashCode());
		result = prime * result + ((vendor == null) ? 0 : vendor.hashCode());
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
		StandardSupplyRecord other = (StandardSupplyRecord) obj;
		if (cardName == null) {
			if (other.cardName != null)
				return false;
		} else if (!cardName.equals(other.cardName))
			return false;
		if (condition == null) {
			if (other.condition != null)
				return false;
		} else if (!condition.equals(other.condition))
			return false;
		if (cost != other.cost)
			return false;
		if (quantity != other.quantity)
			return false;
		if (set == null) {
			if (other.set != null)
				return false;
		} else if (!set.equals(other.set))
			return false;
		if (vendor == null) {
			if (other.vendor != null)
				return false;
		} else if (!vendor.equals(other.vendor))
			return false;
		return true;
	}
}