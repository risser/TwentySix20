package com.twentysix20.cardstore.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.twentysix20.cardstore.list.ShoppingList;
import com.twentysix20.cardstore.order.MultiVendorOrder;
import com.twentysix20.cardstore.supplyrecord.SupplyRecord;
import com.twentysix20.cardstore.vendor.Vendor;

public class TheStrategy implements CardShoppingStrategy {
	private ShoppingList list;

	public TheStrategy(ShoppingList list) {
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
		List<SupplyRecord> usedVendorList = new ArrayList<SupplyRecord>();
		List<SupplyRecord> unusedVendorList = new ArrayList<SupplyRecord>();
		String cardName = cardNames.get(index);
		int numberNeeded = list.numberNeeded(cardName);
		if (numberNeeded > 1)
			numberNeeded -= currentOrder.numberOrderedForCardName(cardName);

//		boolean needsExactlyOne = (numberNeeded == 1);
		SupplyRecord cheapestForUsedVendors = null;

		for (SupplyRecord possibleRecord : possibles) {
			Vendor vendor = possibleRecord.getVendor();
			if (currentVendors.contains(vendor)) {
//System.out.println(" $$$ Using "+record.getVendorName());
				usedVendorList.add(possibleRecord);
				if (cheapestForUsedVendors == null || possibleRecord.getCost() < cheapestForUsedVendors.getCost())
					cheapestForUsedVendors = possibleRecord;
			} else {
				unusedVendorList.add(possibleRecord);
			}
		}

		if ((cheapestForUsedVendors != null) && (numberNeeded < cheapestForUsedVendors.getQuantity())) {
			updatedList.add(cheapestForUsedVendors);
		} else {
			updatedList.addAll(usedVendorList);
		}
/*
		boolean needsExactlyOne = (numberNeeded == 1);
		SupplyRecord cheapestForUsedVendors = null;

		Iterator<SupplyRecord> itrPossibles = possibles.iterator();
		while (itrPossibles.hasNext()) {
			SupplyRecord record = itrPossibles.next();
			if (currentVendors.contains(record.getVendor())) {
//System.out.println(" $$$ Using "+record.getVendorName());
				if (needsExactlyOne) {
					if (cheapestForUsedVendors == null || record.getCost() < cheapestForUsedVendors.getCost())
						cheapestForUsedVendors = record;
				} else {
					updatedList.add(record);
				}
			} else {
				unusedVendorList.add(record);
			}
		}

		if (needsExactlyOne && cheapestForUsedVendors != null) {
			updatedList.add(cheapestForUsedVendors);
		}
*/
/*
//System.out.println(String.format(">>> %s : %s",cardName,numberNeeded));

		List<SupplyRecord> usedVendorList = new ArrayList<SupplyRecord>();

		Iterator<SupplyRecord> itrPossibles = possibles.iterator();
		while (itrPossibles.hasNext()) {
			SupplyRecord record = itrPossibles.next();
			if (currentVendors.contains(record.getVendor())) {
//System.out.println(" $$$ Using "+record.getVendorName());
//System.out.println(String.format(">>>>> %s : %s",numberNeeded,record));					
				usedVendorList.add(record);
			} else {
				unusedVendorList.add(record);
			}
		}
		Collections.sort(usedVendorList,new AscendingPriceSupplyRecordsComparator());
		for (SupplyRecord record : usedVendorList) {
			if (numberNeeded > 0) {
				updatedList.add(record);
				numberNeeded -= record.getQuantity();
			}
		}
*/
//System.out.println(" $$$ Cheapest record was: "+cheapestExisting);
//System.out.println(" $$$ Remaining records: "+unusedVendorList);
		updatedList.addAll(unusedVendorList);

		return updatedList;
	}
}