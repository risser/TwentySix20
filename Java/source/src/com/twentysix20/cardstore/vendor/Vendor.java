package com.twentysix20.cardstore.vendor;

import com.twentysix20.cardstore.order.Order;
import com.twentysix20.cardstore.postage.Postage;

public class Vendor implements Comparable<Vendor> {
	private Postage postage;
	private String name;
	private int hashCode;

	public Vendor(String name, Postage postage) {
		super();
		this.name = name;
		this.postage = postage;
		hashCode = calcHashCode();
	}

	public Postage getPostage() {
		return postage;
	}

	public int calcPostage(Order order) {
		return postage.calculate(order.getVendorOrder(this));
	}

	public String name() {
		return name;
	}

	@Override
	public int hashCode() {
		return hashCode;
	}

	private int calcHashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((postage == null) ? 0 : postage.hashCode());
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
		Vendor other = (Vendor) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (postage == null) {
			if (other.postage != null)
				return false;
		} else if (!postage.equals(other.postage))
			return false;
		return true;
	}

	@Override
	public String toString() {
		String string = super.toString();
		string = string.substring(string.lastIndexOf('.')+1);
		return string + String.format("(name: %s; postage: %s)", name, postage);
	}

	@Override
	public int compareTo(Vendor o) {
		return this.name().compareTo(o.name());
	}
}