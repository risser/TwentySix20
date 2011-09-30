package com.twentysix20.cardstore.order;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.twentysix20.cardstore.money.Money;
import com.twentysix20.cardstore.supplyrecord.SupplyRecord;
import com.twentysix20.cardstore.vendor.Vendor;

public class MultiVendorOrder implements Order {
	private Map<Vendor,SingleVendorOrder> vendorMap = new HashMap<Vendor,SingleVendorOrder>(); 
	private int numberOfCards = 0;

	public MultiVendorOrder() {}

	public MultiVendorOrder(SupplyRecord...records) {
		for (SupplyRecord record : records)
			add(record, 1);
	}

	public MultiVendorOrder(MultiVendorOrder orderToCopy) {
		for (SingleVendorOrder order : orderToCopy.vendorMap.values()) {
			SingleVendorOrder newOrder = new SingleVendorOrder(order);
			vendorMap.put(order.vendor(), newOrder);
			numberOfCards += newOrder.numberOfCards();
		}
	}

	public void add(SupplyRecord record, int count) {
		Vendor vendor = record.getVendor();
		SingleVendorOrder order = vendorMap.get(vendor);
		if (order == null) {
			order = new SingleVendorOrder(vendor);
			vendorMap.put(vendor, order);
		}
		order.add(record, count);
		numberOfCards += count;
	}

	public void remove(SupplyRecord record, int count) {
		Vendor vendor = record.getVendor();
		if (!vendorMap.containsKey(vendor))
			throw new NullPointerException("No records for the vendor associated with this record: "+record);

		SingleVendorOrder order = vendorMap.get(vendor);
		order.remove(record, count);
		if (order.numberOfCards() == 0)
			vendorMap.remove(vendor);

		numberOfCards -= count;
	}

	public int total() {
		int total = 0;
		for (Order order : vendorMap.values()) 
			total += order.total();

		return total;
	}

	@Override
	public int totalWithoutPostage() {
		int total = 0;
		for (Order order : vendorMap.values()) 
			total += order.totalWithoutPostage();

		return total;
	}

	public Order getVendorOrder(Vendor vendor) {
		return vendorMap.get(vendor);
	}

	public int numberOfCards() {
		return numberOfCards;
	}

	public int numberOrderedForCardName(String name) {
		if (name == null) throw new NullPointerException();

		int count = 0;
		for (Order order : vendorMap.values()) {
			count += order.numberOrderedForCardName(name);
		}

		return count;
	}

	public int numberOfCardInstances() {
		int count = 0;
		for (Order order : vendorMap.values()) {
			count += order.numberOfCardInstances();
		}

		return count;
	}

	@Override
	public Set<SupplyRecord> recordsIncludedInOrder() {
		Set<SupplyRecord> phatSet = new HashSet<SupplyRecord>();
		for (Order order : vendorMap.values()) {
			phatSet.addAll(order.recordsIncludedInOrder());
		}
		return phatSet;
	}

	public Set<Vendor> vendorsOnOrder() {
		return vendorMap.keySet();
	}

	public String toString() {
		StringBuffer buf = new StringBuffer("\n====================\nMulti-Vendor Order:\n====================\n");
		StringBuffer totalBuf = new StringBuffer();
		totalBuf.append("  TOTAL: ").append(new Money(total())).append("\n");
		totalBuf.append("  COUNT: ").append(numberOfCards()).append("\n");
		totalBuf.append("====================\n");
		buf.append(totalBuf);

		List<Vendor> list = new ArrayList<Vendor>(vendorMap.keySet());
		Collections.sort(list);

		for (Vendor vendor : list)
			buf.append(vendorMap.get(vendor).toString());

		buf.append("====================\n").append(totalBuf);

		return buf.toString();
	}

	@Override
	public int hashCode() {
		return 31 + vendorMap.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MultiVendorOrder other = (MultiVendorOrder) obj;
		if (vendorMap == null) {
			if (other.vendorMap != null)
				return false;
		} else if (!vendorMap.equals(other.vendorMap))
			return false;
		return true;
	}
}