package com.twentysix20.cardstore.supplyfilter;

import java.util.HashMap;
import java.util.Map;

import com.twentysix20.cardstore.supplyrecord.RecordFilter;
import com.twentysix20.cardstore.supplyrecord.SupplyRecord;
import com.twentysix20.cardstore.supplyrecord.SupplyRecords;


public class CheapestCardPerStoreFilter implements RecordFilter {
	static private CheapestCardPerStoreFilter me = new CheapestCardPerStoreFilter();
	static public CheapestCardPerStoreFilter instance() {
		return me;
	}

	public SupplyRecords filterRecords(SupplyRecords records) {
		Map<String, SupplyRecord> resultMap = new HashMap<String, SupplyRecord>();
		for (SupplyRecord record : records) {
			String key = record.getVendorName()+":"+record.getCardName();
			if (resultMap.containsKey(key)) {
				if (record.getCost() < resultMap.get(key).getCost())
					resultMap.put(key, record);
			} else {
				resultMap.put(key, record);
			}
		}
System.out.println(resultMap);
		return new SupplyRecords(resultMap.values());
	}
}