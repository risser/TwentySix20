package com.twentysix20.cardstore.shopper;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.twentysix20.cardstore.supplyrecord.SupplyRecord;

public class RarestFirstDescendingPriceComparator implements Comparator<String> {
	private Map<String, List<SupplyRecord>> cardNameToRecords;

	public RarestFirstDescendingPriceComparator(Map<String, List<SupplyRecord>> cardNameToRecords) {
		this.cardNameToRecords = cardNameToRecords;
	}

	@Override
	public int compare(String cardName0, String cardName1) {
//System.out.println(cardName0+" :: "+cardName1);

		int cost0 = 100000;
		int cost1 = 100000;

		if (cardNameToRecords.containsKey(cardName0)) {
			List<SupplyRecord> cardList0 = cardNameToRecords.get(cardName0);
			SupplyRecord smallestPrice0 = cardList0.get(0);
			for (SupplyRecord record : cardList0)
				if (record.getCost() < smallestPrice0.getCost())
					smallestPrice0 = record;
			cost0 = smallestPrice0.getCost();
		}
		
		if (cardNameToRecords.containsKey(cardName1)) {
			List<SupplyRecord> cardList1 = cardNameToRecords.get(cardName1);
			SupplyRecord smallestPrice1 = cardList1.get(0);
			for (SupplyRecord record : cardList1)
				if (record.getCost() < smallestPrice1.getCost())
					smallestPrice1 = record;
			cost1 = smallestPrice1.getCost();
		}

		return cost1 - cost0;
	}

}
