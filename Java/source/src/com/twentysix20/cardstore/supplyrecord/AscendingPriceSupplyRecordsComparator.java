package com.twentysix20.cardstore.supplyrecord;

import java.util.Comparator;

public class AscendingPriceSupplyRecordsComparator implements Comparator<SupplyRecord> {

	@Override
	public int compare(SupplyRecord arg0, SupplyRecord arg1) {
		int cost0 = arg0.getCost() + arg0.getVendor().getPostage().baseRate();
		int cost1 = arg1.getCost() + arg1.getVendor().getPostage().baseRate();;
		if (cost0 == cost1)
			return arg1.getQuantity() - arg0.getQuantity();
		else
			return cost0 - cost1;
	}

}
