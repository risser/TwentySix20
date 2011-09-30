package com.twentysix20.cardstore.supplyfilter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.twentysix20.cardstore.list.ShoppingList;
import com.twentysix20.cardstore.order.SingleVendorOrder;
import com.twentysix20.cardstore.supplyrecord.AscendingPriceSupplyRecordsComparator;
import com.twentysix20.cardstore.supplyrecord.RecordFilter;
import com.twentysix20.cardstore.supplyrecord.SupplyRecord;
import com.twentysix20.cardstore.supplyrecord.SupplyRecords;
import com.twentysix20.cardstore.vendor.Vendor;

public class EliminateMoreExpensiveStoresFilter implements RecordFilter {
	private ShoppingList shoppingList;

	public EliminateMoreExpensiveStoresFilter(ShoppingList shoppingList) {
		this.shoppingList = shoppingList;
	}

	public SupplyRecords filterRecords(SupplyRecords records) {
System.out.println("Filter: "+this.getClass().getSimpleName());		
		Map<Vendor, Map<String,List<SupplyRecord>>> vendorNameMap = new HashMap<Vendor, Map<String,List<SupplyRecord>>>();
		for (SupplyRecord record : records) {
			Vendor vendor = record.getVendor();
			Map<String,List<SupplyRecord>> nameRecordMap = vendorNameMap.get(vendor);
			if (nameRecordMap == null) {
				nameRecordMap = new HashMap<String,List<SupplyRecord>>();
				vendorNameMap.put(vendor, nameRecordMap);
			}
			List<SupplyRecord> recordList = nameRecordMap.get(record.getCardName());
			if (recordList == null) {
				recordList = new ArrayList<SupplyRecord>();
				nameRecordMap.put(record.getCardName(), recordList);
			}
			recordList.add(record);
		}

		Set<Vendor> eliminatedVendors = new HashSet<Vendor>();
		for (Vendor primaryVendor : vendorNameMap.keySet()) {
//			if (eliminatedVendors.contains(primaryVendor)) continue;
			Map<String, List<SupplyRecord>> nameRecordListMap = vendorNameMap.get(primaryVendor);

			// if the Primary doesn't have enough to meet, we can't do an accurate compare.
			// if it has too few & they are too expensive, it could get cut as a comparing though.
//			boolean hasEnough = true;
//			for (String name : nameRecordListMap.keySet()) {
//				int numberNeeded = shoppingList.numberNeeded(name);
//				int numberHas = 0;
//				for (SupplyRecord record : nameRecordListMap.get(name))
//					numberHas += record.getQuantity();
//
//				hasEnough = (numberHas >= numberNeeded);
//				if (!hasEnough) break;
//			}
//			if (!hasEnough) continue;

			for (Vendor comparingVendor : vendorNameMap.keySet()) {
				if (primaryVendor.equals(comparingVendor)) continue;
				if (eliminatedVendors.contains(comparingVendor)) continue;

				Map<String, List<SupplyRecord>> primaryRecordMap = nameRecordListMap;
				Map<String, List<SupplyRecord>> comparingMap = vendorNameMap.get(comparingVendor);
				if (!primaryRecordMap.keySet().containsAll(comparingMap.keySet())) continue;

//				SingleVendorOrder primaryLargeOrder = new SingleVendorOrder(primaryVendor);
//				SingleVendorOrder comparingLargeOrder = new SingleVendorOrder(comparingVendor);
				boolean isEliminatable = true;
				for (List<SupplyRecord> comparingRecords : comparingMap.values()) {
					String cardName = comparingRecords.iterator().next().getCardName();
					int numberNeeded = shoppingList.numberNeeded(cardName);
					List<SupplyRecord> primaryRecords = primaryRecordMap.get(cardName);
					int numberHaveForPrimary = 0;
					for (SupplyRecord record : primaryRecords)
						numberHaveForPrimary += record.getQuantity();
					if (numberHaveForPrimary < numberNeeded) {
						isEliminatable = false;
						break;
					}

					Collections.sort(primaryRecords,new AscendingPriceSupplyRecordsComparator());
					Collections.sort(comparingRecords,new AscendingPriceSupplyRecordsComparator());
					
					SupplyRecord primaryRecord = primaryRecords.get(primaryRecords.size()-1);
					SupplyRecord comparingRecord = comparingRecords.get(0);
					SingleVendorOrder primaryOrder = new SingleVendorOrder(primaryRecord);
					SingleVendorOrder comparingOrder = new SingleVendorOrder(comparingRecord);
					if ((comparingRecord.getCost() < primaryRecord.getCost())
							|| (comparingOrder.total() < primaryOrder.total())) {
						isEliminatable = false;
						break;
					}
/*					
					int numberNeededPrimary = shoppingList.numberNeeded(cardName);
					int numberNeededComparing = numberNeededPrimary;

					int primaryCost = 0;
					for (SupplyRecord record : primaryRecords) {
						int actualCount = (record.getQuantity() < numberNeededPrimary ? record.getQuantity() : numberNeededPrimary);
						primaryCost += record.getCost() * actualCount;
						primaryLargeOrder.add(record, actualCount);
						numberNeededPrimary -= actualCount;
					}
					if (numberNeededPrimary > 0) // this means the primary has fewer than the comparing, so we can't properly compare the comparing
						isEliminatable = false;
					
					int comparingCost = 0;
					for (SupplyRecord record : comparingRecords) {
						int actualCount = (record.getQuantity() < numberNeededComparing ? record.getQuantity() : numberNeededComparing);
						comparingCost += record.getCost() * actualCount;
						comparingLargeOrder.add(record, actualCount);
						numberNeededComparing -= actualCount;
					}

					// but it's okay for comparing to have fewer than primary.  If that happens, it will probably fail to be eliminated here because of that.
					if (comparingCost < primaryCost)
						isEliminatable = false;
*/						
				}
				if (isEliminatable) {
//					if (isEliminatable && (comparingLargeOrder.total() >= primaryLargeOrder.total())) {
					eliminatedVendors.add(comparingVendor);
System.out.println("\nPrimary Vendor : "+primaryVendor.name()+" : "+primaryVendor+"\n  "+primaryRecordMap.keySet());
System.out.println("contains vendor: "+comparingVendor.name()+" : "+comparingVendor+"\n  "+comparingMap.keySet());
System.out.println("!!! Eliminated "+comparingVendor.name());					
				}
			}
		}
		
		SupplyRecords finalSet = new SupplyRecords();
		for (SupplyRecord record : records) {
			if (!eliminatedVendors.contains(record.getVendor()))
				finalSet.add(record);
		}

		return finalSet;
	}
}
