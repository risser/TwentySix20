package com.twentysix20.cardstore;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.twentysix20.cardstore.list.ShoppingList;
import com.twentysix20.cardstore.money.Money;
import com.twentysix20.cardstore.order.MultiVendorOrder;
import com.twentysix20.cardstore.parser.CardPageParser;
import com.twentysix20.cardstore.parser.SearchParser;
import com.twentysix20.cardstore.shopper.Shopper;
import com.twentysix20.cardstore.strategy.TheStrategy;
import com.twentysix20.cardstore.supplyfilter.CheapestCardsPerStoreForOrderFilter;
import com.twentysix20.cardstore.supplyfilter.EliminateMoreExpensiveStoresFilter;
import com.twentysix20.cardstore.supplyfilter.EliminateMoreThanCheapestPlusShippingFilter;
import com.twentysix20.cardstore.supplyrecord.SupplyRecord;
import com.twentysix20.cardstore.supplyrecord.SupplyRecords;
import com.twentysix20.cardstore.supplyrecord.UnplayedOnlyFilter;
import com.twentysix20.cardstore.vendor.Vendor;
import com.twentysix20.util.ListMap;
import com.twentysix20.util.StringUtil;
import com.twentysix20.util.html.InternetHtmlLoader;

public class CardStore {
    public static Random rand = new Random();

    public static void main(String[] args) throws Exception {
        InputStreamReader isr = new InputStreamReader( System.in );
        BufferedReader stdin = new BufferedReader( isr );
        
        System.out.println("Enter Shopping List:");
        ShoppingList list = new ShoppingList();
        SupplyRecords inventory = new SupplyRecords();

        SearchParser searchParser = new SearchParser(new InternetHtmlLoader());
        CardPageParser cardPageParser = new CardPageParser(new InternetHtmlLoader());

        String input;
        while(!"".equals(input = stdin.readLine())) {
        	input = input.replaceAll("\\s+", " ");
        	int numberRequired = 0;
        	char c = input.charAt(0);
        	if ((c < '0') || (c > '9'))
        		numberRequired = 1;
        	else {
        		int spacePos = input.indexOf(' ');
        		String num = input.substring(0,spacePos);
        		numberRequired = Integer.parseInt(num);
        		input = input.substring(spacePos + 1);
        	}
        	String cardName = input.trim();
        	boolean alreadyLookedUp = (list.numberNeeded(cardName) > 0);
        	list.add(cardName, numberRequired);

        	if (alreadyLookedUp) {
            	System.out.println("Already looked up: "+cardName);
        	} else {
	        	System.out.println("» "+cardName);
	        	List<String> urlList = searchParser.getUrls(cardName);
	        	for (String url : urlList) {
	        		SupplyRecords records = cardPageParser.getSupplyRecords(url);
	        		if (records.isEmpty())
	        			System.out.println("[--- No cards found in URL: '"+url+"'. ---]");
					else {
						SupplyRecord item = records.iterator().next();
						list.updateName(cardName, item.getCardName());
						System.out.println(" * "+item.getSet()+" ("+records.size()+" version"+(records.size()==1?"":"s")+")");
					}
	        		SupplyRecords unplayedRecords = UnplayedOnlyFilter.instance().filterRecords(records);
	        		if (unplayedRecords.isEmpty())
	        			System.out.println("[--- No unplayed found! ---]");
	        		inventory.addAll(unplayedRecords);
	        	}
        	}
        }

        outputInventoryStats("Initial Inventory",inventory);
        SupplyRecords filteredInventory = new EliminateMoreThanCheapestPlusShippingFilter(list).filterRecords(inventory);
        outputInventoryStats("Inventory, filtered out cards more expensive than cheapest plus shipping",filteredInventory);
        filteredInventory = new CheapestCardsPerStoreForOrderFilter(list).filterRecords(filteredInventory);
        outputInventoryStats("Inventory, filtered to have only cards per store to meet demand",filteredInventory);
        filteredInventory = new EliminateMoreExpensiveStoresFilter(list).filterRecords(filteredInventory);
        outputInventoryStats("Inventory, filtered out more expensive stores",filteredInventory);

//		inventoryBreakdownPerCard(filteredInventory);
		inventoryBreakdownPerVendor(filteredInventory);
//		Shopper shopper = new Shopper(new RemoveMoreExpensiveUsedVendorsStrategy(list), filteredInventory);
		Shopper shopper = new Shopper(new TheStrategy(list), filteredInventory);
		Set<MultiVendorOrder> results = shopper.shop(list);

		System.out.println(results);
    }

	private static void outputInventoryStats(String displayString, SupplyRecords inventory) {
		BigInteger combos = BigInteger.ONE;
		System.out.println("\n"+displayString+":");

		ListMap<String,SupplyRecord> countMap = new ListMap<String,SupplyRecord>();
		for (SupplyRecord record : inventory)
			countMap.put(record.getCardName(), record);

		List<String> names = new ArrayList<String>(countMap.keySet());
		Collections.sort(names);
		for (String name : names) {
			List<SupplyRecord> list = countMap.getList(name);
			String count = ""+list.size();
			System.out.println(StringUtil.padRight(count, 5, ' ')+name);
			combos = combos.multiply(new BigInteger(count));
		}
		outputComboBreakdown(combos);
		System.out.println("Card selection: "+inventory);
	}

	private static void inventoryBreakdownPerCard(SupplyRecords inventory) {
		System.out.println("CARD breakdown:");
		Map<String,Set<SupplyRecord>> nameToRecordsMap = new HashMap<String,Set<SupplyRecord>>();
		for (SupplyRecord record : inventory) {
			String name = record.getCardName();
			Set<SupplyRecord> records = nameToRecordsMap.get(name);
			if (records == null) {
				records = new HashSet<SupplyRecord>();
				nameToRecordsMap.put(name, records);
			}
			records.add(record);
		}
		for (String name : nameToRecordsMap.keySet()) {
//			System.out.println(name + ":");
			for (SupplyRecord record : nameToRecordsMap.get(name))
				System.out.println(String.format("%s\t%s\t%s\t%s\t%s",name,record.getVendorName(),new Money(record.getCost()),new Money(record.getCost()+record.getVendor().getPostage().baseRate()),record.getQuantity()));
		}
	}

	private static void inventoryBreakdownPerVendor(SupplyRecords inventory) {
		System.out.println("VENDOR breakdown:");
		Map<Vendor,Set<SupplyRecord>> vendorToRecordsMap = new HashMap<Vendor,Set<SupplyRecord>>();
		Map<String,Set<SupplyRecord>> nameToRecordsMap = new HashMap<String,Set<SupplyRecord>>();
		for (SupplyRecord record : inventory) {
			Vendor vendor = record.getVendor();
			Set<SupplyRecord> setForVendor = vendorToRecordsMap.get(vendor);
			if (setForVendor == null) {
				setForVendor = new HashSet<SupplyRecord>();
				vendorToRecordsMap.put(vendor, setForVendor);
			}
			setForVendor.add(record);
			
			String name = record.getCardName();
			Set<SupplyRecord> setForName = nameToRecordsMap.get(name);
			if (setForName == null) {
				setForName = new HashSet<SupplyRecord>();
				nameToRecordsMap.put(name, setForName);
			}
			setForName.add(record);
		}
		for (Vendor vendor : vendorToRecordsMap.keySet()) {
			Set<SupplyRecord> recordsForVendor = vendorToRecordsMap.get(vendor);
			for (SupplyRecord record : recordsForVendor)
				System.out.println(String.format("%s\t%s\t%s\t%s\t%s\t%s",
						record.getCardName(),
						vendor.name(),
						new Money(record.getCost()),
						new Money(vendor.getPostage().baseRate()),
						new Money(record.getCost()+vendor.getPostage().baseRate()),
						nameToRecordsMap.get(record.getCardName()).size()
				));
		}
	}

	private static void outputComboBreakdown(BigInteger combos) {
		StringBuffer comboBuf = new StringBuffer(combos.toString());
		for (int i = 3; i < comboBuf.length(); i = i + 4)
			comboBuf.insert(comboBuf.length()-i,",");
			
		System.out.println("Total Combinations: "+comboBuf);
	}
}