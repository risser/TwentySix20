package com.twentysix20.cardstore.postage;


import com.twentysix20.cardstore.order.Order;

public class FlatRatePostage implements Postage {
	private int flatRate;

	public FlatRatePostage(int flatRate) {
		this.flatRate = flatRate;
	}

	@Override
	public int calculate(Order order) {
		return flatRate;
	}

	@Override
	public String toString() {
		return String.format("FlatRatePostage [flatRate=%s]", flatRate);
	}

	@Override
	public int hashCode() {
		return flatRate + 31;
	}

	@Override
	public boolean equals(Object obj) {
System.out.println("COMPARING POSTAGE");		
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FlatRatePostage other = (FlatRatePostage) obj;
		if (flatRate != other.flatRate)
			return false;
		return true;
	}

	@Override
	public int baseRate() {
		return flatRate;
	}
}
