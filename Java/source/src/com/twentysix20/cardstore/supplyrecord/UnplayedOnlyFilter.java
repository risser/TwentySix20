package com.twentysix20.cardstore.supplyrecord;

public class UnplayedOnlyFilter implements RecordFilter {
	static private UnplayedOnlyFilter me = new UnplayedOnlyFilter();
	static public UnplayedOnlyFilter instance() {
		return me;
	}

	public SupplyRecords filterRecords(SupplyRecords records) {
		SupplyRecords results = new SupplyRecords();
		for (SupplyRecord record : records) {
			if (record.getCondition().startsWith("Unplayed"))
				results.add(record);
		}

		return results;
	}
}