package com.twentysix20.cardstore.shopper;

import static junit.framework.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.twentysix20.cardstore.list.ShoppingList;
import com.twentysix20.cardstore.order.MultiVendorOrder;
import com.twentysix20.cardstore.order.Order;
import com.twentysix20.cardstore.postage.FlatRatePostage;
import com.twentysix20.cardstore.strategy.Unfiltered;
import com.twentysix20.cardstore.supplyrecord.StandardSupplyRecord;
import com.twentysix20.cardstore.supplyrecord.SupplyRecord;
import com.twentysix20.cardstore.supplyrecord.SupplyRecords;
import com.twentysix20.cardstore.vendor.Vendor;

public class TestShopperWithShipping {
	static ShoppingList singleItemList = new ShoppingList("itemA");
	static ShoppingList twoCopiesList = new ShoppingList("itemA","itemA");
	static ShoppingList twoCardList = new ShoppingList("itemA","itemB");
	static Vendor vendorTwoBucks = new Vendor("shopTwoBucks",new FlatRatePostage(200));
	static Vendor vendorThreeBucks = new Vendor("shopThreeBucks",new FlatRatePostage(300));
	static Vendor vendorTwoBucks2 = new Vendor("shopTwoBucks2",new FlatRatePostage(200));
	static Vendor vendor250 = new Vendor("shopTwofifty",new FlatRatePostage(250));

	@Test public void oneItem() {
		SupplyRecords records = new SupplyRecords();
		StandardSupplyRecord record = new StandardSupplyRecord("itemA", vendorTwoBucks, "set", "mint", 1, "1.00");
		records.add(record);

		Shopper shopper = new Shopper(new Unfiltered(), records);
		Set<MultiVendorOrder> results = shopper.shop(singleItemList);

		assertEquals(1,results.size());
		Order result = results.iterator().next();

		Order expectedOrder = new MultiVendorOrder(new StandardSupplyRecord("itemA", vendorTwoBucks, "set", "mint", 1, "1.00"));
		assertEquals(expectedOrder,result);
	}

	@Test public void TwoItemsTwoNeeded() {
		SupplyRecords records = new SupplyRecords();
		StandardSupplyRecord record = new StandardSupplyRecord("itemA", vendorTwoBucks, "set", "mint", 2, "1.00");
		records.add(record);

		Shopper shopper = new Shopper(new Unfiltered(),records);
		Set<MultiVendorOrder> results = shopper.shop(twoCopiesList);
		assertEquals(1,results.size());
		Order result = results.iterator().next();

		SupplyRecord expectedRecord = new StandardSupplyRecord("itemA", vendorTwoBucks, "set", "mint", 2, "1.00");
		Order expectedOrder = new MultiVendorOrder();
		expectedOrder.add(expectedRecord, 2);
		assertEquals(expectedOrder,result);
	}

	@Test public void TwoItemsOneNeeded() {
		SupplyRecords records = new SupplyRecords();
		StandardSupplyRecord record = new StandardSupplyRecord("itemA", vendorTwoBucks, "set", "mint", 2, "1.00");
		records.add(record);

		Shopper shopper = new Shopper(new Unfiltered(),records);
		Set<MultiVendorOrder> results = shopper.shop(singleItemList);
		assertEquals(1,results.size());
		Order result = results.iterator().next();

		Order expectedOrder = new MultiVendorOrder(record);
		assertEquals(expectedOrder,result);
	}

	@Test public void twoDifferentItemsSameStore() {
		SupplyRecords records = new SupplyRecords();
		StandardSupplyRecord record1 = new StandardSupplyRecord("itemB", vendorTwoBucks, "set", "mint", 1, "1.00");
		StandardSupplyRecord record2 = new StandardSupplyRecord("itemA", vendorTwoBucks, "set", "mint", 1, "2.00");
		records.add(record1);
		records.add(record2);

		Shopper shopper = new Shopper(new Unfiltered(),records);
		Set<MultiVendorOrder> results = shopper.shop(twoCardList);
		assertEquals(1,results.size());
		Order result = results.iterator().next();

		Order expectedOrder = new MultiVendorOrder(record1,record2);
		assertEquals(expectedOrder,result);
	}

	@Test public void sameItemDifferentStoresSamePriceDifferentShipping() {
		SupplyRecords records = new SupplyRecords();
		StandardSupplyRecord record1 = new StandardSupplyRecord("itemA", vendorThreeBucks, "set", "mint", 1, "1.00");
		StandardSupplyRecord record2 = new StandardSupplyRecord("itemA", vendorTwoBucks, "set", "mint", 1, "1.00");
		records.add(record1);
		records.add(record2);

		Shopper shopper = new Shopper(new Unfiltered(), records);
		Set<MultiVendorOrder> results = shopper.shop(singleItemList);
		assertEquals(1,results.size());
		Order result = results.iterator().next();

		Order expectedOrder = new MultiVendorOrder(record2);
		assertEquals(expectedOrder,result);
	}

	@Test public void sameItemDifferentStoresMoreExpensivePriceCheaperShipping() {
		SupplyRecords records = new SupplyRecords();
		StandardSupplyRecord record1 = new StandardSupplyRecord("itemA", vendorThreeBucks, "set", "mint", 1, "1.00");
		StandardSupplyRecord record2 = new StandardSupplyRecord("itemA", vendorTwoBucks, "set", "mint", 1, "1.50");
		records.add(record1);
		records.add(record2);

		Shopper shopper = new Shopper(new Unfiltered(), records);
		Set<MultiVendorOrder> results = shopper.shop(singleItemList);
		assertEquals(1,results.size());
		Order result = results.iterator().next();

		Order expectedOrder = new MultiVendorOrder(record2);
		assertEquals(expectedOrder,result);
	}

	@Test public void sameItemDifferentStoresBalancedPriceAndShipping() {
		SupplyRecords records = new SupplyRecords();
		StandardSupplyRecord record1 = new StandardSupplyRecord("itemA", vendorThreeBucks, "set", "mint", 1, "1.00");
		StandardSupplyRecord record2 = new StandardSupplyRecord("itemA", vendorTwoBucks, "set", "mint", 1, "2.00");
		records.add(record1);
		records.add(record2);

		Shopper shopper = new Shopper(new Unfiltered(), records);
		Set<MultiVendorOrder> results = shopper.shop(singleItemList);
		assertEquals(2,results.size());
		
		Set<MultiVendorOrder> expectedResults = new HashSet<MultiVendorOrder>();
		expectedResults.add(new MultiVendorOrder(record1));
		expectedResults.add(new MultiVendorOrder(record2));

		assertEquals(expectedResults, results);
	}

	@Test public void sameItemDifferentStoresSamePriceSameShipping() {
		SupplyRecords records = new SupplyRecords();
		StandardSupplyRecord record1 = new StandardSupplyRecord("itemA", vendorTwoBucks, "set", "mint", 1, "1.00");
		StandardSupplyRecord record2 = new StandardSupplyRecord("itemA", vendor250, "set", "mint", 1, "1.00");
		StandardSupplyRecord record3 = new StandardSupplyRecord("itemA", vendorTwoBucks2, "set", "mint", 1, "1.00");
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

	@Test public void sameItemThreeDifferentStores() {
		SupplyRecords records = new SupplyRecords();
		StandardSupplyRecord record1 = new StandardSupplyRecord("itemA", vendorThreeBucks, "set", "mint", 1, "1.00");
		StandardSupplyRecord record2 = new StandardSupplyRecord("itemA", vendorTwoBucks2, "set", "mint", 1, "2.00");
		StandardSupplyRecord record3 = new StandardSupplyRecord("itemA", vendor250, "set", "mint", 1, "0.75");
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

	@Test public void groupingBetterThanTwoShippingCosts() {
		SupplyRecords records = new SupplyRecords();
		StandardSupplyRecord cheapItem1a = new StandardSupplyRecord("itemA", vendorTwoBucks, "set", "mint", 1, "0.44");
		StandardSupplyRecord cheapItem1b = new StandardSupplyRecord("itemA", vendorTwoBucks2, "set", "mint", 1, "0.60");
		StandardSupplyRecord priceyItem2a = new StandardSupplyRecord("itemB", vendorTwoBucks, "set", "mint", 1, "2.00");
		StandardSupplyRecord priceyItem2b = new StandardSupplyRecord("itemB", vendorTwoBucks2, "set", "mint", 1, "1.50");
		records.add(cheapItem1a);
		records.add(cheapItem1b);
		records.add(priceyItem2a);
		records.add(priceyItem2b);

		Shopper shopper = new Shopper(new Unfiltered(), records);
		Set<MultiVendorOrder> results = shopper.shop(twoCardList);
		assertEquals(1,results.size());
		Order result = results.iterator().next();

		Order expectedOrder = new MultiVendorOrder(cheapItem1b,priceyItem2b);
		assertEquals(expectedOrder,result);
	}

	@Test public void groupingBetterThanTwoShippingCosts_OtherWay() {
		SupplyRecords records = new SupplyRecords();
		StandardSupplyRecord cheapItem1a = new StandardSupplyRecord("itemA", vendorTwoBucks, "setA", "mint", 1, "0.44");
		StandardSupplyRecord cheapItem1b = new StandardSupplyRecord("itemA", vendorTwoBucks2, "setB", "mint", 1, "0.90");
		StandardSupplyRecord priceyItem2a = new StandardSupplyRecord("itemB", vendorTwoBucks, "setA", "mint", 1, "1.90");
		StandardSupplyRecord priceyItem2b = new StandardSupplyRecord("itemB", vendorTwoBucks2, "setB", "mint", 1, "1.50");
		records.add(cheapItem1a);
		records.add(cheapItem1b);
		records.add(priceyItem2a);
		records.add(priceyItem2b);

		Shopper shopper = new Shopper(new Unfiltered(), records);
		Set<MultiVendorOrder> results = shopper.shop(twoCardList);
		assertEquals(1,results.size());
		Order result = results.iterator().next();

		Order expectedOrder = new MultiVendorOrder(cheapItem1a,priceyItem2a);
		assertEquals(expectedOrder,result);
	}

	@Test public void groupingBetterThanTwoShippingCosts_TwoSets() {
		SupplyRecords records = new SupplyRecords();
		StandardSupplyRecord cheapItem1a = new StandardSupplyRecord("itemA", vendorTwoBucks, "setA", "mint", 1, "0.44"); 
		StandardSupplyRecord priceyItem2a1 = new StandardSupplyRecord("itemB", vendorTwoBucks, "setA", "mint", 1, "1.90"); //2.34
		StandardSupplyRecord priceyItem2a2 = new StandardSupplyRecord("itemB", vendorTwoBucks, "setB", "mint", 1, "1.95"); //2.39
		StandardSupplyRecord cheapItem1b = new StandardSupplyRecord("itemA", vendorTwoBucks2, "setA", "mint", 1, "0.90");
		StandardSupplyRecord priceyItem2b1 = new StandardSupplyRecord("itemB", vendorTwoBucks2, "setA", "mint", 1, "2.50"); //3.40
		StandardSupplyRecord priceyItem2b2 = new StandardSupplyRecord("itemB", vendorTwoBucks2, "setB", "mint", 1, "1.45"); //2.35
		records.add(cheapItem1a);
		records.add(cheapItem1b);
		records.add(priceyItem2a1);
		records.add(priceyItem2b1);
		records.add(priceyItem2a2);
		records.add(priceyItem2b2);

		Shopper shopper = new Shopper(new Unfiltered(), records);
		Set<MultiVendorOrder> results = shopper.shop(twoCardList);
		assertEquals(1,results.size());
		Order result = results.iterator().next();

		Order expectedOrder = new MultiVendorOrder(cheapItem1a,priceyItem2a1);
		assertEquals(expectedOrder,result);
	}

	@Test public void groupingBetterThanTwoShippingCosts_TwoSets_ChangeByTwoCents() {
		SupplyRecords records = new SupplyRecords();
		StandardSupplyRecord cheapItem1a = new StandardSupplyRecord("itemA", vendorTwoBucks, "setA", "mint", 1, "0.44"); 
		StandardSupplyRecord priceyItem2a1 = new StandardSupplyRecord("itemB", vendorTwoBucks, "setA", "mint", 1, "1.90"); //2.34
		StandardSupplyRecord priceyItem2a2 = new StandardSupplyRecord("itemB", vendorTwoBucks, "setB", "mint", 1, "1.95"); //2.39
		StandardSupplyRecord cheapItem1b = new StandardSupplyRecord("itemA", vendorTwoBucks2, "setA", "mint", 1, "0.90");
		StandardSupplyRecord priceyItem2b1 = new StandardSupplyRecord("itemB", vendorTwoBucks2, "setA", "mint", 1, "2.50"); //3.40
		StandardSupplyRecord priceyItem2b2 = new StandardSupplyRecord("itemB", vendorTwoBucks2, "setB", "mint", 1, "1.43"); //2.33
		records.add(cheapItem1a);
		records.add(cheapItem1b);
		records.add(priceyItem2a1);
		records.add(priceyItem2b1);
		records.add(priceyItem2a2);
		records.add(priceyItem2b2);

		Shopper shopper = new Shopper(new Unfiltered(), records);
		Set<MultiVendorOrder> results = shopper.shop(twoCardList);
		assertEquals(1,results.size());
		Order result = results.iterator().next();

		Order expectedOrder = new MultiVendorOrder(cheapItem1b,priceyItem2b2);
		assertEquals(expectedOrder,result);
	}

}
