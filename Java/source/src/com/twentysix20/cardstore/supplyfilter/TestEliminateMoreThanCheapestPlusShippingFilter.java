package com.twentysix20.cardstore.supplyfilter;

import static com.twentysix20.testutil.TestCase2620.assertSize;
import static junit.framework.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.twentysix20.cardstore.list.ShoppingList;
import com.twentysix20.cardstore.postage.FlatRatePostage;
import com.twentysix20.cardstore.supplyrecord.StandardSupplyRecord;
import com.twentysix20.cardstore.supplyrecord.SupplyRecord;
import com.twentysix20.cardstore.supplyrecord.SupplyRecords;
import com.twentysix20.cardstore.vendor.Vendor;



public class TestEliminateMoreThanCheapestPlusShippingFilter {
	static private Vendor vendor1dollar = new Vendor("shopA",new FlatRatePostage(100));
	static private Vendor vendor2dollar = new Vendor("shopB",new FlatRatePostage(200));
	static private Vendor vendor3dollar = new Vendor("shopC",new FlatRatePostage(300));

	@Test public void noRecordsNoList() {
		SupplyRecords records = new SupplyRecords();
		ShoppingList list = new ShoppingList();
		SupplyRecords filteredRecords = new EliminateMoreThanCheapestPlusShippingFilter(list).filterRecords(records);
		assertSize(0, filteredRecords);
	}

	@Test public void oneRecordOneItemList() {
		ShoppingList list = new ShoppingList("Example Card");

		SupplyRecords records = new SupplyRecords();
		records.add(new StandardSupplyRecord("Example Card",vendor1dollar,"Set","Unplayed",3,"0.15"));
		SupplyRecords filteredRecords = new EliminateMoreThanCheapestPlusShippingFilter(list).filterRecords(records);
		assertSize(1, filteredRecords);
	}

	@Test public void twoRecordsOneIsTooBig() {
		ShoppingList list = new ShoppingList("Example Card");

		StandardSupplyRecord record1 = new StandardSupplyRecord("Example Card",vendor1dollar,"Set","Unplayed",3,"0.15");
		StandardSupplyRecord record2 = new StandardSupplyRecord("Example Card",vendor3dollar,"Set","Unplayed",3,"1.25");
		SupplyRecords records = new SupplyRecords();
		records.add(record1);
		records.add(record2);
		SupplyRecords filteredRecords = new EliminateMoreThanCheapestPlusShippingFilter(list).filterRecords(records);
		assertSize(1, filteredRecords);

		Set<SupplyRecord> expectedSet = new HashSet<SupplyRecord>();
		expectedSet.add(record1);

		assertEquals(expectedSet, filteredRecords);
	}

	@Test public void twoRecordsOtherOneIsTooBig() {
		ShoppingList list = new ShoppingList("Example Card");

		StandardSupplyRecord record1 = new StandardSupplyRecord("Example Card",vendor1dollar,"Set","Unplayed",3,"3.35");
		StandardSupplyRecord record2 = new StandardSupplyRecord("Example Card",vendor3dollar,"Set","Unplayed",3,"0.25");
		SupplyRecords records = new SupplyRecords();
		records.add(record1);
		records.add(record2);
		SupplyRecords filteredRecords = new EliminateMoreThanCheapestPlusShippingFilter(list).filterRecords(records);
		assertSize(1, filteredRecords);

		Set<SupplyRecord> expectedSet = new HashSet<SupplyRecord>();
		expectedSet.add(record2);

		assertEquals(expectedSet, filteredRecords);
	}

	@Test public void twoRecordsNeitherOneIsTooBig() {
		ShoppingList list = new ShoppingList("Example Card");

		StandardSupplyRecord record1 = new StandardSupplyRecord("Example Card",vendor1dollar,"Set","Unplayed",3,"0.15");
		StandardSupplyRecord record2 = new StandardSupplyRecord("Example Card",vendor3dollar,"Set","Unplayed",3,"0.25");
		SupplyRecords records = new SupplyRecords();
		records.add(record1);
		records.add(record2);
		SupplyRecords filteredRecords = new EliminateMoreThanCheapestPlusShippingFilter(list).filterRecords(records);
		assertSize(2, filteredRecords);

		Set<SupplyRecord> expectedSet = new HashSet<SupplyRecord>();
		expectedSet.add(record1);
		expectedSet.add(record2);

		assertEquals(expectedSet, filteredRecords);
	}

	@Test public void twoRecordsNeitherOneIsTooBig2() {
		ShoppingList list = new ShoppingList("Example Card");

		StandardSupplyRecord record1 = new StandardSupplyRecord("Example Card",vendor1dollar,"Set","Unplayed",3,"0.15");
		StandardSupplyRecord record2 = new StandardSupplyRecord("Example Card",vendor3dollar,"Set","Unplayed",3,"1.10");
		SupplyRecords records = new SupplyRecords();
		records.add(record1);
		records.add(record2);
		SupplyRecords filteredRecords = new EliminateMoreThanCheapestPlusShippingFilter(list).filterRecords(records);
		assertSize(2, filteredRecords);

		Set<SupplyRecord> expectedSet = new HashSet<SupplyRecord>();
		expectedSet.add(record1);
		expectedSet.add(record2);

		assertEquals(expectedSet, filteredRecords);
	}

	@Test public void twoRecordsEqual() {
		ShoppingList list = new ShoppingList("Example Card");

		StandardSupplyRecord record1 = new StandardSupplyRecord("Example Card",vendor1dollar,"Set","Unplayed",3,"0.15");
		StandardSupplyRecord record2 = new StandardSupplyRecord("Example Card",vendor3dollar,"Set","Unplayed",3,"1.15");
		SupplyRecords records = new SupplyRecords();
		records.add(record1);
		records.add(record2);
		SupplyRecords filteredRecords = new EliminateMoreThanCheapestPlusShippingFilter(list).filterRecords(records);
		assertSize(1, filteredRecords);

		Set<SupplyRecord> expectedSet = new HashSet<SupplyRecord>();
		expectedSet.add(record1);

		assertEquals(expectedSet, filteredRecords);
	}

	@Test public void threeRecords_HigherCostButLowerShipping() {
System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");		
		ShoppingList list = new ShoppingList("Example Card");

		StandardSupplyRecord record1 = new StandardSupplyRecord("Example Card",vendor1dollar,"Set","Unplayed",3,"1.55"); //2.55
		StandardSupplyRecord record2 = new StandardSupplyRecord("Example Card",vendor2dollar,"Set","Unplayed",3,"3.50"); //3.50
		StandardSupplyRecord record3 = new StandardSupplyRecord("Example Card",vendor3dollar,"Set","Unplayed",3,"1.05"); //4.05
		SupplyRecords records = new SupplyRecords();
		records.add(record1);
		records.add(record2);
		records.add(record3);
		SupplyRecords filteredRecords = new EliminateMoreThanCheapestPlusShippingFilter(list).filterRecords(records);
		assertSize(2, filteredRecords);

		Set<SupplyRecord> expectedSet = new HashSet<SupplyRecord>();
		expectedSet.add(record1);
		expectedSet.add(record3);

		assertEquals(expectedSet, filteredRecords);
System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");		
	}

	@Test public void multipleCopies() {
		ShoppingList list = new ShoppingList("Example Card","Example Card","Example Card","Example Card");

		StandardSupplyRecord record1 = new StandardSupplyRecord("Example Card",vendor1dollar,"Set","Unplayed",2,"0.15");
		StandardSupplyRecord record2 = new StandardSupplyRecord("Example Card",vendor3dollar,"Set","Unplayed",4,"1.15");
		SupplyRecords records = new SupplyRecords();
		records.add(record1);
		records.add(record2);
		SupplyRecords filteredRecords = new EliminateMoreThanCheapestPlusShippingFilter(list).filterRecords(records);
		assertSize(2, filteredRecords);

		Set<SupplyRecord> expectedSet = new HashSet<SupplyRecord>();
		expectedSet.add(record1);
		expectedSet.add(record2);

		assertEquals(expectedSet, filteredRecords);
	}
}