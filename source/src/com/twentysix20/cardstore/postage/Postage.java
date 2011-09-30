package com.twentysix20.cardstore.postage;

import com.twentysix20.cardstore.order.Order;

public interface Postage {
	public int calculate(Order order);
	public int baseRate();
}
