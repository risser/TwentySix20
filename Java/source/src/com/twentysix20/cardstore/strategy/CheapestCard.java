package com.twentysix20.cardstore.strategy;

import java.util.ArrayList;
import java.util.List;

import com.twentysix20.cardstore.order.MultiVendorOrder;
import com.twentysix20.cardstore.supplyrecord.SupplyRecord;

public class CheapestCard implements CardShoppingStrategy {

	@Override
	public List<SupplyRecord> filterPossibleItems(List<String> cardNames, int index, List<SupplyRecord> possibles, MultiVendorOrder currentOrder) {
		List<SupplyRecord> filtered = new ArrayList<SupplyRecord>();
		int lowestCents = 0;

		for (SupplyRecord record : possibles)
			if (filtered.isEmpty() || (record.getCost() < lowestCents)) {
				filtered.clear();
				filtered.add(record);
				lowestCents = record.getCost();
			} else if (record.getCost() == lowestCents) {
				filtered.add(record);
			}

		return filtered;
	}
	
}
