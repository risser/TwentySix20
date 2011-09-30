package com.twentysix20.cardstore.shopper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.twentysix20.cardstore.list.ShoppingList;
import com.twentysix20.cardstore.money.Money;
import com.twentysix20.cardstore.order.MultiVendorOrder;
import com.twentysix20.cardstore.strategy.CardShoppingStrategy;
import com.twentysix20.cardstore.supplyrecord.AscendingPriceSupplyRecordsComparator;
import com.twentysix20.cardstore.supplyrecord.SupplyRecord;
import com.twentysix20.cardstore.supplyrecord.SupplyRecords;
import com.twentysix20.util.Timer;

public class Shopper {
	private CardShoppingStrategy strategy;
	private SupplyRecords storeContents;
	private List<String> cardNames;
	private int[] cheapestTotalForRemainingCards;
	private int[] cheapestTotalForRemainingCardsWithMulties;
	private Map<String,List<SupplyRecord>> cardNameToRecords;
	private MultiVendorOrder tempOrder;
	private Set<MultiVendorOrder> results;
	private int currentSmallestResultTotal = Integer.MAX_VALUE;
	private ShoppingList shoppingList;
	private Timer timer;
	private int[] currentIndex;

	public Shopper(CardShoppingStrategy cardShoppingStrategy, SupplyRecords records) {
		strategy = cardShoppingStrategy;
		this.storeContents = records;
		setupCardNameToRecordsMap();
	}

	public Set<MultiVendorOrder> shop(ShoppingList list) {
		this.shoppingList = list;
		timer = new Timer("Shopper").start();

		results = new HashSet<MultiVendorOrder>();
		tempOrder = new MultiVendorOrder();
		setupCardNameList(list);
		if (!cardNameToRecords.keySet().containsAll(cardNames)) {
			HashSet<String> set = new HashSet<String>(cardNames);
			set.removeAll(cardNameToRecords.keySet());
			System.out.println("Some cards were not found: "+set);
			System.out.println("Please remove them from the list in order to continue shopping.");
			return results;
		}

		calculateCheapestTotals(list);

		doIt(0);

		System.out.println('\n');
		System.out.println(timer.stop());
		timer = null;
		return results;
	}

	private void doIt(int index) {
		if (index >= cardNames.size()) {
			evaluateAndAddOrder();
		} else {
			processPartOfOrder(index);
		}
	}

	private void processPartOfOrder(int index) {
		int cheapestRemainingTotal = cheapestTotalForRemainingCards[index];
		if ((tempOrder.total() + cheapestRemainingTotal) > currentSmallestResultTotal) {
			return;
		}

		String name = cardNames.get(index);
		int numberNeeded = shoppingList.numberNeeded(name);
		boolean alreadyOrderedSome = false;
		if (numberNeeded > 1) {
			// don't need to check if the number needed is one.  If so, it won't ever be less than one.
			int numberAlreadyOrdered = tempOrder.numberOrderedForCardName(name);
			alreadyOrderedSome = (numberAlreadyOrdered > 0);
			numberNeeded -= numberAlreadyOrdered;
			int cheapestRemainingTotalWithMulties = cheapestTotalForRemainingCardsWithMulties[index];
			if (!alreadyOrderedSome && (tempOrder.total() + cheapestRemainingTotalWithMulties) > currentSmallestResultTotal) {
				return;
			}
		}

		List<SupplyRecord> possibles = new ArrayList<SupplyRecord>(); 
		possibles.addAll(cardNameToRecords.get(name));
		if (alreadyOrderedSome)
			possibles.removeAll(tempOrder.recordsIncludedInOrder());
		possibles = strategy.filterPossibleItems(cardNames, index, possibles, tempOrder);

		if (!alreadyOrderedSome)
			currentIndex[index] = 0;
		for (SupplyRecord currentRecord : possibles) {
			boolean enoughSupply = currentRecord.getQuantity() >= numberNeeded;
			int numberToOrder = enoughSupply ? numberNeeded : currentRecord.getQuantity();
			tempOrder.add(currentRecord, numberToOrder);
			int tempTotal = tempOrder.total();
			if ((tempTotal <= currentSmallestResultTotal) || results.isEmpty())
				doIt(index + (enoughSupply ? 1 : 0));
			tempOrder.remove(currentRecord, numberToOrder);
			currentIndex[index]++;
		}
	}

	private void evaluateAndAddOrder() {
System.out.println("\nORDER:");
System.out.println(timer);
System.out.println(tempOrder);
		if (results.isEmpty()) {
			addOrderToResults();
		} else {
			int tempTotal = tempOrder.total();
			if (tempTotal < currentSmallestResultTotal) {
				results.clear();
				addOrderToResults();
			} else if (tempTotal == currentSmallestResultTotal) {
				addOrderToResults();
			}
		}
	}

	private void addOrderToResults() {
		MultiVendorOrder newOrder = new MultiVendorOrder(tempOrder);
		results.add(newOrder);
		currentSmallestResultTotal = tempOrder.total();
	}

	private void setupCardNameList(ShoppingList list) {
		cardNames = new ArrayList<String>();
		for (String name : list.nameSet())
			cardNames.add(name);
//		Collections.sort(cardNames, new DescendingCountComparator(cardNameToRecords));
		Collections.sort(cardNames, new DescendingPriceComparator(cardNameToRecords));
	}

	private void calculateCheapestTotals(ShoppingList list) {
System.out.println("\nCHEAPEST VERSION: ");
		cheapestTotalForRemainingCards = new int[cardNames.size()];
		cheapestTotalForRemainingCardsWithMulties = new int[cardNames.size()];
		currentIndex = new int[cardNames.size()];
		int rollingTotal = 0;
		int rollingMultiesTotal = 0;
		for (int index = cardNames.size() - 1; index >= 0; index--) {
			String name = cardNames.get(index);
			int numberNeeded = list.numberNeeded(name);
			int cheapestCost = Integer.MAX_VALUE;
			for (SupplyRecord record : cardNameToRecords.get(name)) {
				int probableCardCost = record.getCost();
				if (probableCardCost < cheapestCost)
					cheapestCost = probableCardCost;
			}
System.out.println(numberNeeded + " @ " + new Money(cheapestCost) + ": "+name+" ("+cardNameToRecords.get(name).size()+")"); 
			rollingTotal += cheapestCost;
			rollingMultiesTotal += cheapestCost * numberNeeded;

			cheapestTotalForRemainingCards[index] = rollingTotal;
			cheapestTotalForRemainingCardsWithMulties[index] = rollingMultiesTotal;
		}
System.out.println("cheapestTotals: "+Arrays.toString(cheapestTotalForRemainingCards));
System.out.println("cheapestMultis: "+Arrays.toString(cheapestTotalForRemainingCardsWithMulties));
	}

	private void setupCardNameToRecordsMap() {
		cardNameToRecords = new HashMap<String,List<SupplyRecord>>();
		for (SupplyRecord record : storeContents) {
			List<SupplyRecord> list = cardNameToRecords.get(record.getCardName());
			if (list == null) {
				list = new ArrayList<SupplyRecord>();
				cardNameToRecords.put(record.getCardName(),list);
			}
			list.add(record);
		}

		for (List<SupplyRecord> list : cardNameToRecords.values()) {
			Collections.sort(list,new AscendingPriceSupplyRecordsComparator());
		}
	}
}
