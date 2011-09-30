package com.twentysix20.cardstore.money;

import com.twentysix20.util.StringUtil;

public class Money implements Comparable<Money> {
	static final public Money ZERO = new Money(0);
	
	private int cents;

	public Money(int cents) {
		this.cents = cents;
	}

	public Money(String money) {
		if (money == null) throw new NullPointerException();
		String[] split = money.split("\\.");
		cents = Integer.valueOf(StringUtil.padLeft(split[0].replaceAll(",",""),1,'0'))*100;
		if (split.length > 1) {
			String pennies = StringUtil.padRight(split[1],2,'0');
			if (pennies.length() > 2)
				pennies = pennies.substring(0, 2);
			cents += Integer.valueOf(pennies);
		}
	}

	public double value() {
		return cents / 100.0;
	}

	public int cents() {
		return cents;
	}

	public String toString() {
		int pennies = cents % 100;
		return (cents / 100) + "." + (pennies < 10 ? "0" : "") + pennies;
	}

	public Money plus(Money money2) {
		return new Money(money2.cents + cents);
	}

	public Money minus(Money money2) {
		return new Money(cents - money2.cents);
	}

	public Money times(int i) {
		return new Money(cents * i);
	}

	@Override
	public int hashCode() {
		return (""+cents).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Money other = (Money) obj;
		return (cents == other.cents);
	}

	@Override
	public int compareTo(Money otherMoney) {
		return this.cents - otherMoney.cents;
	}
}