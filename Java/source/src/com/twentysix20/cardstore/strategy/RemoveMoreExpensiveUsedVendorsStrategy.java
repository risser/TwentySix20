package com.twentysix20.cardstore.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.twentysix20.cardstore.list.ShoppingList;
import com.twentysix20.cardstore.order.MultiVendorOrder;
import com.twentysix20.cardstore.supplyrecord.SupplyRecord;
import com.twentysix20.cardstore.vendor.Vendor;

public class RemoveMoreExpensiveUsedVendorsStrategy implements CardShoppingStrategy {
	private ShoppingList list;

	public RemoveMoreExpensiveUsedVendorsStrategy(ShoppingList list) {
		super();
		this.list = list;
	}

	@Override
	public List<SupplyRecord> filterPossibleItems(List<String> cardNames, int index, List<SupplyRecord> possibles, MultiVendorOrder currentOrder) {
//System.out.print(index+"."+(index % 3 == 0 ? "\n" : ""));
//System.out.println(" $$$ === \n $$$ name: "+index+":"+cardNames.get(index));
//System.out.println(" $$$ possibles: "+possibles.size());
		Set<Vendor> currentVendors = currentOrder.vendorsOnOrder();
		if ((index == 0) || (currentVendors.size() == 0))
			return possibles;

		List<SupplyRecord> updatedList = new ArrayList<SupplyRecord>();
		String cardName = cardNames.get(index);
		int numberNeeded = list.numberNeeded(cardName);
		if (numberNeeded > 1)
			numberNeeded -= currentOrder.numberOrderedForCardName(cardName);

		SupplyRecord cheapestForUsedVendors = null;
		int cheapestCost = Integer.MAX_VALUE;

		for (SupplyRecord possibleRecord : possibles) {
			Vendor vendor = possibleRecord.getVendor();
			if (currentVendors.contains(vendor)) {
				if (possibleRecord.getCost() < cheapestCost) {
					if (cheapestForUsedVendors != null)
						updatedList.remove(cheapestForUsedVendors);
					updatedList.add(possibleRecord);
					cheapestForUsedVendors = possibleRecord;
					cheapestCost = possibleRecord.getCost();
				}
			} else {
				updatedList.add(possibleRecord);
			}
		}

		return updatedList;
	}
}