package com.twentysix20.cardstore.shopper;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.twentysix20.cardstore.supplyrecord.SupplyRecord;

public class DescendingCountComparator implements Comparator<String> {
	private Map<String, List<SupplyRecord>> cardNameToRecords;

	public DescendingCountComparator(Map<String, List<SupplyRecord>> cardNameToRecords) {
		this.cardNameToRecords = cardNameToRecords;
	}

	@Override
	public int compare(String cardName0, String cardName1) {
//System.out.println(cardName0+" :: "+cardName1);

		int cost0 = 100000;
		int cost1 = 100000;

		if (cardNameToRecords.containsKey(cardName0)) {
			List<SupplyRecord> cardList0 = cardNameToRecords.get(cardName0);
			cost0 = cardList0.size();
		}
		
		if (cardNameToRecords.containsKey(cardName1)) {
			List<SupplyRecord> cardList1 = cardNameToRecords.get(cardName1);
			cost1 = cardList1.size();
		}

		return cost0 - cost1;
	}

}
