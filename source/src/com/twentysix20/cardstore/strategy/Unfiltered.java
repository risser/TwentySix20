package com.twentysix20.cardstore.strategy;

import java.util.List;

import com.twentysix20.cardstore.order.MultiVendorOrder;
import com.twentysix20.cardstore.supplyrecord.SupplyRecord;

public class Unfiltered implements CardShoppingStrategy {

	@Override
	public List<SupplyRecord> filterPossibleItems(List<String> cardNames, int index, List<SupplyRecord> possibles, MultiVendorOrder currentOrder) {
		return possibles;
	}
	
}
