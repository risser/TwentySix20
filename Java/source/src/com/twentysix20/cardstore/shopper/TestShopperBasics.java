package com.twentysix20.cardstore.shopper;


import static junit.framework.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.twentysix20.cardstore.list.ShoppingList;
import com.twentysix20.cardstore.order.MultiVendorOrder;
import com.twentysix20.cardstore.order.Order;
import com.twentysix20.cardstore.postage.FreePostage;
import com.twentysix20.cardstore.strategy.Unfiltered;
import com.twentysix20.cardstore.supplyrecord.StandardSupplyRecord;
import com.twentysix20.cardstore.supplyrecord.SupplyRecord;
import com.twentysix20.cardstore.supplyrecord.SupplyRecords;
import com.twentysix20.cardstore.vendor.Vendor;


public class TestShopperBasics {
	static ShoppingList singleItemList = new ShoppingList("itemA");
	static ShoppingList twoCopiesList = new ShoppingList("itemA","itemA");
	static ShoppingList twoCardList = new ShoppingList("itemA","itemB");
	static Vendor vendor1 = new Vendor("shop",FreePostage.FREE_POSTAGE);
	static Vendor vendor2 = new Vendor("shopA",FreePostage.FREE_POSTAGE);
	static Vendor vendor3 = new Vendor("shopB",FreePostage.FREE_POSTAGE);
	static Vendor vendor4 = new Vendor("shopC",FreePostage.FREE_POSTAGE);

	@Test public void oneItem() {
		SupplyRecords records = new SupplyRecords();
		StandardSupplyRecord record = new StandardSupplyRecord("itemA", vendor1, "set", "mint", 1, "1.00");
		records.add(record);

		Shopper shopper = new Shopper(new Unfiltered(), records);
		Set<MultiVendorOrder> results = shopper.shop(singleItemList);

		assertEquals(1,results.size());
		Order result = results.iterator().next();

		Order expectedOrder = new MultiVendorOrder(new StandardSupplyRecord("itemA", vendor1, "set", "mint", 1, "1.00"));
		assertEquals(expectedOrder,result);
	}

	@Test public void emptyStoreEmptyList() {
		SupplyRecords records = new SupplyRecords();

		Shopper shopper = new Shopper(new Unfiltered(), records);
		Set<MultiVendorOrder> results = shopper.shop(new ShoppingList());
		assertEquals(1,results.size());
		Order result = results.iterator().next();

		assertEquals(new MultiVendorOrder(),result);
	}

	@Test public void emptyStoreSingleList() {
		SupplyRecords records = new SupplyRecords();

		Shopper shopper = new Shopper(new Unfiltered(), records);
		Set<MultiVendorOrder> results = shopper.shop(singleItemList);
		assertEquals(0,results.size());
	}

	@Test public void oneItemEmptyList() {
		SupplyRecords records = new SupplyRecords();
		StandardSupplyRecord record = new StandardSupplyRecord("itemA", vendor1, "set", "mint", 1, "1.00");
		records.add(record);

		Shopper shopper = new Shopper(new Unfiltered(),records);
		Set<MultiVendorOrder> results = shopper.shop(new ShoppingList());
		assertEquals(1,results.size());
		Order result = results.iterator().next();
		
		assertEquals(new MultiVendorOrder(),result);
	}

	@Test public void TwoItemsTwoNeeded() {
		SupplyRecords records = new SupplyRecords();
		StandardSupplyRecord record = new StandardSupplyRecord("itemA", vendor1, "set", "mint", 2, "1.00");
		records.add(record);

		Shopper shopper = new Shopper(new Unfiltered(),records);
		Set<MultiVendorOrder> results = shopper.shop(twoCopiesList);
		assertEquals(1,results.size());
		Order result = results.iterator().next();

		SupplyRecord expectedRecord = new StandardSupplyRecord("itemA", vendor1, "set", "mint", 2, "1.00");
		Order expectedOrder = new MultiVendorOrder();
		expectedOrder.add(expectedRecord, 2);
		assertEquals(expectedOrder,result);
	}

	@Test public void TwoItemsThreeNeeded() {
		SupplyRecords records = new SupplyRecords();
		StandardSupplyRecord record = new StandardSupplyRecord("itemA", vendor1, "set", "mint", 2, "1.00");
		records.add(record);

		Shopper shopper = new Shopper(new Unfiltered(),records);
		Set<MultiVendorOrder> results = shopper.shop(new ShoppingList("itemA","itemA","itemA"));
		assertEquals(0,results.size());
	}

	@Test public void TwoItemsOneNeeded() {
		SupplyRecords records = new SupplyRecords();
		StandardSupplyRecord record = new StandardSupplyRecord("itemA", vendor1, "set", "mint", 2, "1.00");
		records.add(record);

		Shopper shopper = new Shopper(new Unfiltered(),records);
		Set<MultiVendorOrder> results = shopper.shop(singleItemList);
		assertEquals(1,results.size());
		Order result = results.iterator().next();

		Order expectedOrder = new MultiVendorOrder(record);
		assertEquals(expectedOrder,result);
	}

	@Test public void wrongItemSingleList() {
		SupplyRecords records = new SupplyRecords();
		StandardSupplyRecord record = new StandardSupplyRecord("itemB", vendor1, "set", "mint", 1, "1.00");
		records.add(record);

		Shopper shopper = new Shopper(new Unfiltered(),records);
		Set<MultiVendorOrder> results = shopper.shop(singleItemList);
		assertEquals(0,results.size());
	}

	@Test public void twoDifferentItemsSameStore() {
		SupplyRecords records = new SupplyRecords();
		StandardSupplyRecord record1 = new StandardSupplyRecord("itemB", vendor1, "set", "mint", 1, "1.00");
		StandardSupplyRecord record2 = new StandardSupplyRecord("itemA", vendor1, "set", "mint", 1, "2.00");
		records.add(record1);
		records.add(record2);

		Shopper shopper = new Shopper(new Unfiltered(),records);
		Set<MultiVendorOrder> results = shopper.shop(twoCardList);
		assertEquals(1,results.size());
		Order result = results.iterator().next();

		Order expectedOrder = new MultiVendorOrder(record1,record2);
		assertEquals(expectedOrder,result);
	}

	@Test public void sameItemDifferentStores() {
		SupplyRecords records = new SupplyRecords();
		StandardSupplyRecord record1 = new StandardSupplyRecord("itemA", vendor2, "set", "mint", 1, "1.00");
		StandardSupplyRecord record2 = new StandardSupplyRecord("itemA", vendor3, "set", "mint", 1, "2.00");
		records.add(record1);
		records.add(record2);

		Shopper shopper = new Shopper(new Unfiltered(), records);
		Set<MultiVendorOrder> results = shopper.shop(singleItemList);
		assertEquals(1,results.size());
		Order result = results.iterator().next();

		Order expectedOrder = new MultiVendorOrder(record1);
		assertEquals(expectedOrder,result);
	}

	@Test public void sameItemThreeDifferentStores() {
		SupplyRecords records = new SupplyRecords();
		StandardSupplyRecord record1 = new StandardSupplyRecord("itemA", vendor2, "set", "mint", 1, "1.00");
		StandardSupplyRecord record2 = new StandardSupplyRecord("itemA", vendor3, "set", "mint", 1, "2.00");
		StandardSupplyRecord record3 = new StandardSupplyRecord("itemA", vendor4, "set", "mint", 1, "0.75");
		records.add(record1);
		records.add(record2);
		records.add(record3);

		Shopper shopper = new Shopper(new Unfiltered(), records);
		Set<MultiVendorOrder> results = shopper.shop(singleItemList);
		assertEquals(1,results.size());
		Order result = results.iterator().next();

		Order expectedOrder = new MultiVendorOrder(record3);
		assertEquals(expectedOrder,result);
	}


	@Test public void twoAndOne() {
		SupplyRecords records = new SupplyRecords();
		StandardSupplyRecord record1 = new StandardSupplyRecord("itemA", vendor2, "set", "mint", 1, "1.00");
		StandardSupplyRecord record2 = new StandardSupplyRecord("itemA", vendor3, "set", "mint", 1, "0.75");
		StandardSupplyRecord record3 = new StandardSupplyRecord("itemB", vendor2, "set", "mint", 1, "1.00");
		records.add(record1);
		records.add(record2);
		records.add(record3);

		Shopper shopper = new Shopper(new Unfiltered(), records);
		Set<MultiVendorOrder> results = shopper.shop(twoCardList);
		assertEquals(1,results.size());
		Order result = results.iterator().next();

		Order expectedOrder = new MultiVendorOrder(record2,record3);
		assertEquals(expectedOrder,result);
	}

	@Test public void sameItemTwoStoresSamePrice() {
		SupplyRecords records = new SupplyRecords();
		StandardSupplyRecord record1 = new StandardSupplyRecord("itemA", vendor2, "set", "mint", 3, "1.00");
		StandardSupplyRecord record2 = new StandardSupplyRecord("itemA", vendor3, "set", "mint", 1, "2.00");
		StandardSupplyRecord record3 = new StandardSupplyRecord("itemA", vendor4, "set", "mint", 1, "1.00");
		records.add(record1);
		records.add(record2);
		records.add(record3);

		Shopper shopper = new Shopper(new Unfiltered(), records);
		Set<MultiVendorOrder> results = shopper.shop(singleItemList);
		assertEquals(2,results.size());
		
		Set<MultiVendorOrder> expectedResults = new HashSet<MultiVendorOrder>();
		expectedResults.add(new MultiVendorOrder(record1));
		expectedResults.add(new MultiVendorOrder(record3));

		assertEquals(expectedResults, results);
	}

	@Test public void TwoCopiesTwoStoresSamePrice() {
		SupplyRecords records = new SupplyRecords();
		StandardSupplyRecord record1 = new StandardSupplyRecord("itemA", vendor2, "set", "mint", 1, "1.00");
		StandardSupplyRecord record2 = new StandardSupplyRecord("itemA", vendor3, "set", "mint", 1, "2.00");
		StandardSupplyRecord record3 = new StandardSupplyRecord("itemA", vendor4, "set", "mint", 1, "1.00");
		records.add(record1);
		records.add(record2);
		records.add(record3);

		Shopper shopper = new Shopper(new Unfiltered(), records);
		Set<MultiVendorOrder> results = shopper.shop(twoCopiesList);
		assertEquals(1,results.size());
		Order result = results.iterator().next();

		Order expectedOrder = new MultiVendorOrder(record1,record3);
		assertEquals(expectedOrder,result);
	}

	@Test public void ThreeCopiesThreeDifferentStores() {
		SupplyRecords records = new SupplyRecords();
		StandardSupplyRecord record1 = new StandardSupplyRecord("itemA", vendor2, "set", "mint", 1, "1.00");
		StandardSupplyRecord record2 = new StandardSupplyRecord("itemA", vendor3, "set", "mint", 1, "2.00");
		StandardSupplyRecord record3 = new StandardSupplyRecord("itemA", vendor4, "set", "mint", 1, "1.00");
		records.add(record1);
		records.add(record2);
		records.add(record3);

		Shopper shopper = new Shopper(new Unfiltered(), records);
		Set<MultiVendorOrder> results = shopper.shop(new ShoppingList("itemA","itemA","itemA"));
		assertEquals(1,results.size());
		Order result = results.iterator().next();

		Order expectedOrder = new MultiVendorOrder(record1,record2,record3);
		assertEquals(expectedOrder,result);
	}

	@Test public void FourCopiesNotEnoughInDifferentStores() {
		SupplyRecords records = new SupplyRecords();
		StandardSupplyRecord record1 = new StandardSupplyRecord("itemA", vendor2, "set", "mint", 1, "1.00");
		StandardSupplyRecord record2 = new StandardSupplyRecord("itemA", vendor3, "set", "mint", 1, "2.00");
		StandardSupplyRecord record3 = new StandardSupplyRecord("itemA", vendor4, "set", "mint", 1, "1.00");
		records.add(record1);
		records.add(record2);
		records.add(record3);

		Shopper shopper = new Shopper(new Unfiltered(), records);
		Set<MultiVendorOrder> results = shopper.shop(new ShoppingList("itemA","itemA","itemA","itemA"));
		assertEquals(0,results.size());
	}

	@Test public void TwoCopiesThreeDifferentStores() {
		SupplyRecords records = new SupplyRecords();
		StandardSupplyRecord record1 = new StandardSupplyRecord("itemA", vendor2, "set", "mint", 2, "1.00");
		StandardSupplyRecord record2 = new StandardSupplyRecord("itemA", vendor3, "set", "mint", 1, "2.00");
		StandardSupplyRecord record3 = new StandardSupplyRecord("itemA", vendor4, "set", "mint", 1, "1.00");
		records.add(record1);
		records.add(record2);
		records.add(record3);

		Shopper shopper = new Shopper(new Unfiltered(), records);
		Set<MultiVendorOrder> results = shopper.shop(twoCopiesList);
		assertEquals(2,results.size());
		
		Set<MultiVendorOrder> expectedResults = new HashSet<MultiVendorOrder>();
		expectedResults.add(new MultiVendorOrder(record1, record1));
		expectedResults.add(new MultiVendorOrder(record1, record3));

		assertEquals(expectedResults, results);
	}

	@Test public void TwoCopiesThreeDifferentStoresOneIsCheaper() {
		SupplyRecords records = new SupplyRecords();
		StandardSupplyRecord record1 = new StandardSupplyRecord("itemA", vendor2, "set", "mint", 2, "1.00");
		StandardSupplyRecord record2 = new StandardSupplyRecord("itemA", vendor3, "set", "mint", 1, "0.50");
		StandardSupplyRecord record3 = new StandardSupplyRecord("itemA", vendor4, "set", "mint", 1, "1.00");
		records.add(record1);
		records.add(record2);
		records.add(record3);

		Shopper shopper = new Shopper(new Unfiltered(), records);
		Set<MultiVendorOrder> results = shopper.shop(twoCopiesList);
		assertEquals(2,results.size());
		
		Set<MultiVendorOrder> expectedResults = new HashSet<MultiVendorOrder>();
		expectedResults.add(new MultiVendorOrder(record1, record2));
		expectedResults.add(new MultiVendorOrder(record2, record3));

		assertEquals(expectedResults, results);
	}

	@Test public void TwoCopiesThreeDifferentStoresAllSame() {
		SupplyRecords records = new SupplyRecords();
		StandardSupplyRecord record1 = new StandardSupplyRecord("itemA", vendor2, "set", "mint", 2, "1.00");
		StandardSupplyRecord record2 = new StandardSupplyRecord("itemA", vendor3, "set", "mint", 1, "1.00");
		StandardSupplyRecord record3 = new StandardSupplyRecord("itemA", vendor4, "set", "mint", 1, "1.00");
		records.add(record1);
		records.add(record2);
		records.add(record3);

		Shopper shopper = new Shopper(new Unfiltered(), records);
		Set<MultiVendorOrder> results = shopper.shop(twoCopiesList);
		assertEquals(4,results.size());
		
		Set<MultiVendorOrder> expectedResults = new HashSet<MultiVendorOrder>();
		expectedResults.add(new MultiVendorOrder(record1, record1));
		expectedResults.add(new MultiVendorOrder(record1, record2));
		expectedResults.add(new MultiVendorOrder(record1, record3));
		expectedResults.add(new MultiVendorOrder(record2, record3));

		assertEquals(expectedResults, results);
	}
}