package com.twentysix20.cardstore.supplyrecord;

import com.twentysix20.cardstore.vendor.Vendor;

public interface SupplyRecord {

	public abstract String getCardName();

	public abstract String getVendorName();
	public abstract Vendor getVendor();

	public abstract String getSet();

	public abstract String getCondition();

	public abstract int getQuantity();

	public abstract int getCost();
	
	public boolean equals(Object o);
	public String toString();
	public int hashCode();

}