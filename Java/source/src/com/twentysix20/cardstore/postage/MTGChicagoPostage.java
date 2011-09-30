package com.twentysix20.cardstore.postage;


import com.twentysix20.cardstore.order.Order;

public class MTGChicagoPostage implements CacheablePostage {
//1=1.88;2=1.90;3=1.93;4=1.96;5=1.98;6=2.01;7=2.04;8=2.07;9=2.09;10=2.12;11=2.15;12=2.17;13=2.20;14=2.23;15=2.25;38=2.88;39=2.90;40=2.93
//     0      2      5      8     10     13     16     19     21      24      27      29      32      35      37

	@Override
	public int calculate(Order order) {
		int count = order.numberOfCards();
		return calculate(count);
	}

	public int calculate(int count) {
		return 188 + (count-1) * 3 - (count+1) / 3;
	}

	@Override
	public int baseRate() {
		return 188;
	}

	@Override
	public String toString() {
		return "MTGChicagoPostage [baseRate=188, perCard=2 or 3]";
	}
}