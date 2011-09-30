package com.twentysix20.cardstore.order;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.twentysix20.cardstore.money.Money;
import com.twentysix20.cardstore.postage.TemporaryFlatRatePostage;
import com.twentysix20.cardstore.supplyrecord.SupplyRecord;
import com.twentysix20.cardstore.vendor.Vendor;
import com.twentysix20.util.StringUtil;

public class SingleVendorOrder implements Order {
	private static final Integer ZERO = Integer.valueOf(0);
	private Map<SupplyRecord,Integer> orderMap = new HashMap<SupplyRecord,Integer>();
	private Map<String,Integer> nameToCountMap = new HashMap<String,Integer>();
	private Vendor theVendor;
	private int numberOfCards = 0;
	private int total = 0;

	public SingleVendorOrder(Vendor vendor) {
		if (vendor == null)
			throw new NullPointerException();

		theVendor = vendor;
	}

	public SingleVendorOrder(Vendor vendor, SupplyRecord...records) {
		this(vendor);
		for (SupplyRecord record : records)
			add(record, 1);
	}

	public SingleVendorOrder(SingleVendorOrder order) {
		if (order.numberOfCards() > 0) {
			theVendor = order.orderMap.keySet().iterator().next().getVendor();
			for (Map.Entry<SupplyRecord, Integer> entry : order.orderMap.entrySet())
				add(entry.getKey(),entry.getValue());
		}
	}

	public SingleVendorOrder(SupplyRecord...records) {
		this(records[0].getVendor(),records);
	}

	@Override
	public void add(SupplyRecord record, int count) {
		if (!theVendor.equals(record.getVendor()))
			throw new IllegalArgumentException("Can't add supply records from a different vendor. (was: "+record.getVendor()+"; should be: "+theVendor+")");

		int oldCount = orderMap.containsKey(record) ? orderMap.get(record) : 0;
		int available = record.getQuantity();
		if (oldCount + count > available)
			throw new IndexOutOfBoundsException("Not enough items for order! Can't order ("+count+" + "+oldCount+") items from "+record);
		orderMap.put(record, count + oldCount);
		numberOfCards += count;
		total += record.getCost() * count;
		adjustNameToCountMap(record, count);
	}

	@Override
	public void remove(SupplyRecord record, int count) {
		if (!orderMap.containsKey(record))
			throw new NullPointerException("No record of this kind to remove: "+record);

		int oldCount = orderMap.get(record);
		if (count > oldCount)
			throw new IndexOutOfBoundsException("Not enough items to remove.  Trying to remove " + count + " from " + oldCount + ".");

		if (oldCount - count == 0)
			orderMap.remove(record);
		else
			orderMap.put(record, oldCount - count);
		
		numberOfCards -= count;
		total -= record.getCost() * count;
		adjustNameToCountMap(record, -count);
	}

	private void adjustNameToCountMap(SupplyRecord record, int count) {
		Integer numberOfCardsForName = nameToCountMap.get(record.getCardName());
		if (numberOfCardsForName == null)
			numberOfCardsForName = ZERO;
		nameToCountMap.put(record.getCardName(),numberOfCardsForName + count);
	}

	@Override
	public Order getVendorOrder(Vendor vendor) {
		if (!theVendor.equals(vendor))
			throw new IllegalArgumentException("For SingleVendorOrder, can't get a vendor order from a different vendor. (asked for: "+vendor+"; but order is for "+theVendor+")");
		return this;
	}

	@Override
	public int numberOfCardInstances() {
		return orderMap.size();
	}

	@Override
	public int numberOfCards() {
		return numberOfCards;
	}

	@Override
	public int numberOrderedForCardName(String name) {
		if (name == null) throw new NullPointerException();

		Integer count = nameToCountMap.get(name);
		return count == null ? 0 : count;
	}

	@Override
	public Set<SupplyRecord> recordsIncludedInOrder() {
		return orderMap.keySet();
	}

	@Override
	public int total() {
		return totalWithoutPostage() + theVendor.calcPostage(getVendorOrder(theVendor));
	}

	@Override
	public int totalWithoutPostage() {
		return total;
	}

	@Override
	public int hashCode() {
		return 31 + orderMap.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SingleVendorOrder other = (SingleVendorOrder) obj;
		if (orderMap == null) {
			if (other.orderMap != null)
				return false;
		} else if (!orderMap.equals(other.orderMap))
			return false;
		return true;
	}

	@Override public String toString() {
		StringBuffer buf = new StringBuffer("\n-=-=-=-=-=-=-=-=-=-\n"+theVendor.name()+" Order:\n");
		buf.append("------------------\nNumber of Cards: ").append(numberOfCards()).append("\n------------------\n");

		int total = total();
		int totalWithoutPostage = totalWithoutPostage();
		int shipping = total - totalWithoutPostage;

		buf.append("Subtotal: $").append(StringUtil.padLeft(new Money(totalWithoutPostage).toString(),6,' ')).append("\n");
		buf.append("Shipping: $").append(StringUtil.padLeft(new Money(shipping).toString(),6,' '));
		if (theVendor.getPostage() instanceof TemporaryFlatRatePostage)
			buf.append("** Temporary Stand-In Postage\n");
		buf.append("\n------------------\n");
		buf.append("Total:    $").append(new Money(total)).append("\n------------------\n");
		Map<String,List<SupplyRecord>> recordsByNameMap = new HashMap<String,List<SupplyRecord>>();
		for (SupplyRecord record : orderMap.keySet()) {
			String name = record.getCardName();
			List<SupplyRecord> list = recordsByNameMap.get(name);
			if (list == null)
				list = new ArrayList<SupplyRecord>();
			list.add(record);
			recordsByNameMap.put(name,list);
		}
		List<String> nameList = new ArrayList<String>(recordsByNameMap.keySet());
		Collections.sort(nameList);
		for (String name : nameList) {
			buf.append("+ ").append(name).append("\n");
			for (SupplyRecord record : recordsByNameMap.get(name)) {
				buf.append("  $");
				String count = " "+orderMap.get(record).toString();
				buf.append(StringUtil.padLeft(new Money(record.getCost()).toString(),6,' '));
				buf.append(" : ").append(count.substring(count.length()-2)).append(" ");
				String desc = record.getSet() + " " + record.getCondition();
				buf.append(StringUtil.padRight(desc,35,' ')).append(record).append("\n");
			}
			buf.append("\n");
		}
		buf.append("-=-=-=-=-=-=-=-=-=-\n");

		return buf.toString();
	}

	public Vendor vendor() {
		return theVendor;
	}
}