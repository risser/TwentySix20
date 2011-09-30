package com.twentysix20.cardstore.supplyfilter;

import java.util.HashMap;
import java.util.Map;

import com.twentysix20.cardstore.list.ShoppingList;
import com.twentysix20.cardstore.order.Order;
import com.twentysix20.cardstore.order.SingleVendorOrder;
import com.twentysix20.cardstore.supplyrecord.RecordFilter;
import com.twentysix20.cardstore.supplyrecord.SupplyRecord;
import com.twentysix20.cardstore.supplyrecord.SupplyRecords;

public class EliminateMoreThanCheapestPlusShippingFilter implements RecordFilter {
	private ShoppingList shoppingList;

	public EliminateMoreThanCheapestPlusShippingFilter(ShoppingList shoppingList) {
		this.shoppingList = shoppingList;
	}

	@Override
	public SupplyRecords filterRecords(SupplyRecords records) {
System.out.println("Filter: "+this.getClass().getSimpleName());		
		Map<String,Order> cheapestMap = new HashMap<String,Order>();
		for (SupplyRecord record : records) {
			String name = record.getCardName();
			if (record.getQuantity() < shoppingList.numberNeeded(name))
				continue;

			Order tempOrder = new SingleVendorOrder(record);
			Order cheapestSoFar = cheapestMap.get(name);
			if ((cheapestSoFar == null) ||
					(tempOrder.total() < cheapestSoFar.total())) 
				cheapestMap.put(name, tempOrder);
		}

		SupplyRecords filterSet = new SupplyRecords();
		for (SupplyRecord record : records) {
			Order cheapestCardOrder = cheapestMap.get(record.getCardName());
			SupplyRecord cheapestRecord = (cheapestCardOrder == null ? null : cheapestCardOrder.recordsIncludedInOrder().iterator().next());
			if ((cheapestCardOrder == null) || record.equals(cheapestRecord) || (record.getCost() < cheapestCardOrder.total()))
				// this is < and not <=.  Even though this means we might overlook a match that reduces the number
				// of shops & therefore shipping, that's okay, because the whole idea is that ordering just the
				// one card is cheaper or equal to buying it with any order.  It will always be cheaper (or equal)
				// to order it alone than to include it with an order at one of the shops we're eliminating.
				// okay, unless you go over 50 cards with that one store and shipping drops to zero.
				filterSet.add(record);
			else
				System.out.println("Cutting ("+record.getCardName()+" (price) not < "+cheapestCardOrder.total()+" (price+shipping) at "+cheapestRecord.getVendorName()+"): "+record);
		}

		return filterSet;
	}

}
