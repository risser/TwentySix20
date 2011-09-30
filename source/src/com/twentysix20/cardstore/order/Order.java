package com.twentysix20.cardstore.order;

import java.util.Set;

import com.twentysix20.cardstore.supplyrecord.SupplyRecord;
import com.twentysix20.cardstore.vendor.Vendor;

public interface Order {

	public abstract void add(SupplyRecord record, int count);
	public abstract void remove(SupplyRecord record, int count);
	public abstract Set<SupplyRecord> recordsIncludedInOrder();

	public abstract Order getVendorOrder(Vendor vendor);

	public abstract int total();
	public abstract int totalWithoutPostage();

	public abstract int numberOfCards();
	public abstract int numberOfCardInstances();
	public abstract int numberOrderedForCardName(String name);
}