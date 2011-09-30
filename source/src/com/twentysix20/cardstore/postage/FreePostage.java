package com.twentysix20.cardstore.postage;


import com.twentysix20.cardstore.order.Order;

public class FreePostage implements Postage {
	static public Postage FREE_POSTAGE = new FreePostage();

	@Override
	public int calculate(Order order) {
		return 0;
	}

	@Override
	public String toString() {
		return String.format("FreePostage!!");
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}

	@Override
	public int baseRate() {
		return 0;
	}
}
