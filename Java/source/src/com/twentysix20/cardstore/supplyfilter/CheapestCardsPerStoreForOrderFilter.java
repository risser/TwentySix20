package com.twentysix20.cardstore.supplyfilter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.twentysix20.cardstore.list.ShoppingList;
import com.twentysix20.cardstore.supplyrecord.AscendingPriceSupplyRecordsComparator;
import com.twentysix20.cardstore.supplyrecord.RecordFilter;
import com.twentysix20.cardstore.supplyrecord.SupplyRecord;
import com.twentysix20.cardstore.supplyrecord.SupplyRecords;
import com.twentysix20.cardstore.vendor.Vendor;


public class CheapestCardsPerStoreForOrderFilter implements RecordFilter {
	private ShoppingList shoppingList;

	public CheapestCardsPerStoreForOrderFilter(ShoppingList shoppingList) {
		this.shoppingList = shoppingList;
	}

	public SupplyRecords filterRecords(SupplyRecords records) {
System.out.println("Filter: "+this.getClass().getSimpleName());		
		Map<Vendor, Map<String,List<SupplyRecord>>> vendorMap = new HashMap<Vendor, Map<String,List<SupplyRecord>>>();
		for (SupplyRecord record : records) {
			String name = record.getCardName();
			Vendor vendor = record.getVendor();
			Map<String,List<SupplyRecord>> nameMap = vendorMap.get(vendor);
			if (nameMap == null) {
				nameMap = new HashMap<String,List<SupplyRecord>>();
				vendorMap.put(vendor, nameMap);
			}
			List<SupplyRecord> list = nameMap.get(name);
			if (list == null) {
				list = new ArrayList<SupplyRecord>();
				nameMap.put(name, list);
			}
			list.add(record);
		}

		SupplyRecords finalSet = new SupplyRecords();
		for (Vendor vendor : vendorMap.keySet()) {
System.out.println("\n## Trimming: "+vendor.name());
			Map<String,List<SupplyRecord>> nameMap = vendorMap.get(vendor);
			for (String name : nameMap.keySet()) {
				List<SupplyRecord> list = nameMap.get(name);
				Collections.sort(list, new AscendingPriceSupplyRecordsComparator());
				int needed = shoppingList.numberNeeded(name);
				if (needed == 0) continue;

				int count = 0;
				int recordCount = 0;
				for (SupplyRecord record  : list) {
					finalSet.add(record);
					recordCount++;
					count += record.getQuantity();
					if (count >= needed)
						break;
				}
				int numberReduced = list.size()-recordCount;
				if (numberReduced > 0)
					System.out.println("## > removed "+numberReduced+" of "+list.size()+" : "+name);				
			}
		}
		return finalSet;
	}
}