package com.twentysix20.cardstore.shopper;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.twentysix20.cardstore.supplyrecord.SupplyRecord;

public class DescendingPriceGapDescendingPriceComparator implements Comparator<String> {
	private Map<String, List<SupplyRecord>> cardNameToRecords;

	public DescendingPriceGapDescendingPriceComparator(Map<String, List<SupplyRecord>> cardNameToRecords) {
		this.cardNameToRecords = cardNameToRecords;
	}

	@Override
	public int compare(String cardName0, String cardName1) {
		List<SupplyRecord> cardList0 = cardNameToRecords.get(cardName0);
		List<SupplyRecord> cardList1 = cardNameToRecords.get(cardName1);

		SupplyRecord smallestPrice0 = cardList0.get(0);
		SupplyRecord biggestPrice0 = cardList0.get(0);
		for (SupplyRecord record : cardList0)
			if (record.getCost() < smallestPrice0.getCost())
				smallestPrice0 = record;
			else if (record.getCost() > biggestPrice0.getCost())
				biggestPrice0 = record;
		int diff0 = biggestPrice0.getCost() - smallestPrice0.getCost();
		
		SupplyRecord smallestPrice1 = cardList1.get(0);
		SupplyRecord biggestPrice1 = cardList1.get(0);
		for (SupplyRecord record : cardList1)
			if (record.getCost() < smallestPrice1.getCost())
				smallestPrice1 = record;
			else if (record.getCost() > biggestPrice1.getCost())
				biggestPrice1 = record;
		int diff1 = biggestPrice1.getCost() - smallestPrice1.getCost();
System.out.println(">> "+cardName0+" : "+diff0+" ("+smallestPrice0.getCost() + " - " + biggestPrice0.getCost()+")");		
System.out.println(">> "+cardName1+" : "+diff1+" ("+smallestPrice1.getCost() + " - " + biggestPrice1.getCost()+")");		

		if (diff1 == diff0)
			return biggestPrice1.getCost() - biggestPrice0.getCost();
		else
			return diff1 - diff0;
	}

}
