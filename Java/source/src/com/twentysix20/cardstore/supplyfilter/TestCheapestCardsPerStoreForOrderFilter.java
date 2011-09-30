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



public class TestCheapestCardsPerStoreForOrderFilter {
	static private Vendor vendor1 = new Vendor("shop",new FlatRatePostage(200));
	static private Vendor vendor2 = new Vendor("shopA",new FlatRatePostage(200));
	static private Vendor vendor3 = new Vendor("shopB",new FlatRatePostage(200));

	@Test public void noRecordsNoList() {
		ShoppingList list = new ShoppingList();

		SupplyRecords filteredRecords = new CheapestCardsPerStoreForOrderFilter(list).filterRecords(new SupplyRecords());
		assertSize(0, filteredRecords);
	}

	@Test public void oneRecordOneItemList() {
		ShoppingList list = new ShoppingList("Example Card");

		SupplyRecords records = new SupplyRecords();
		records.add(new StandardSupplyRecord("Example Card",vendor1,"Set","Unplayed",3,"0.15"));
		SupplyRecords filteredRecords = new CheapestCardsPerStoreForOrderFilter(list).filterRecords(records);
		assertSize(1, filteredRecords);
	}

	@Test public void oneRecordNoList() {
		ShoppingList list = new ShoppingList();

		SupplyRecords records = new SupplyRecords();
		records.add(new StandardSupplyRecord("Example Card",vendor1,"Set","Unplayed",3,"0.15"));
		SupplyRecords filteredRecords = new CheapestCardsPerStoreForOrderFilter(list).filterRecords(records);
		assertSize(0, filteredRecords);
	}

	@Test public void MultiRecordsOneStoreOneItemList() {
		ShoppingList list = new ShoppingList("Example Card");

		SupplyRecords records = new SupplyRecords();
		records.add(new StandardSupplyRecord("Example Card",vendor1,"Set1","Unplayed",3,"0.15"));
		records.add(new StandardSupplyRecord("Example Card",vendor1,"Set2","Unplayed",3,"0.25"));
		StandardSupplyRecord record3 = new StandardSupplyRecord("Example Card",vendor1,"Set3","Unplayed",3,"0.12");
		records.add(record3);
		SupplyRecords filteredRecords = new CheapestCardsPerStoreForOrderFilter(list).filterRecords(records);
		assertSize(1, filteredRecords);

		Set<SupplyRecord> expectedSet = new HashSet<SupplyRecord>();
		expectedSet.add(record3);

		assertEquals(expectedSet, filteredRecords);
	}

	@Test public void MultiRecordsMultiStoresOneItemList() {
		ShoppingList list = new ShoppingList("Example Card");

		StandardSupplyRecord record1 = new StandardSupplyRecord("Example Card",vendor1,"Set1","Unplayed",3,"0.15");
		StandardSupplyRecord record2 = new StandardSupplyRecord("Example Card",vendor1,"Set2","Unplayed",3,"0.12");
		StandardSupplyRecord record3 = new StandardSupplyRecord("Example Card",vendor1,"Set3","Unplayed",3,"0.17");
		StandardSupplyRecord record4 = new StandardSupplyRecord("Example Card",vendor2,"Set1","Unplayed",3,"0.17");
		StandardSupplyRecord record5 = new StandardSupplyRecord("Example Card",vendor2,"Set2","Unplayed",3,"0.12");
		StandardSupplyRecord record6 = new StandardSupplyRecord("Example Card",vendor3,"Set3","Unplayed",3,"0.15");
		SupplyRecords records = new SupplyRecords(record1,record2,record3,record4,record5,record6);
		SupplyRecords filteredRecords = new CheapestCardsPerStoreForOrderFilter(list).filterRecords(records);
		assertSize(3, filteredRecords);

		Set<SupplyRecord> expectedSet = new HashSet<SupplyRecord>();
		expectedSet.add(record2);
		expectedSet.add(record5);
		expectedSet.add(record6);

		assertEquals(expectedSet, filteredRecords);
	}

	@Test public void OneStoreMultipleCopyList() {
		ShoppingList list = new ShoppingList();
		list.add("Example Card", 4);

		StandardSupplyRecord record1 = new StandardSupplyRecord("Example Card",vendor1,"Set1","Unplayed",3,"0.15");
		StandardSupplyRecord record2 = new StandardSupplyRecord("Example Card",vendor1,"Set2","Unplayed",3,"0.12");
		StandardSupplyRecord record3 = new StandardSupplyRecord("Example Card",vendor1,"Set3","Unplayed",3,"0.17");
		StandardSupplyRecord record4 = new StandardSupplyRecord("Example Card",vendor2,"Set1","Unplayed",3,"0.17");
		StandardSupplyRecord record5 = new StandardSupplyRecord("Example Card",vendor2,"Set2","Unplayed",3,"0.12");
		StandardSupplyRecord record6 = new StandardSupplyRecord("Example Card",vendor3,"Set3","Unplayed",6,"0.15");
		SupplyRecords records = new SupplyRecords(record1,record2,record3,record4,record5,record6);
		SupplyRecords filteredRecords = new CheapestCardsPerStoreForOrderFilter(list).filterRecords(records);
		assertSize(5, filteredRecords);

		Set<SupplyRecord> expectedSet = new HashSet<SupplyRecord>();
		expectedSet.add(record1);
		expectedSet.add(record2);
		expectedSet.add(record4);
		expectedSet.add(record5);
		expectedSet.add(record6);

		assertEquals(expectedSet, filteredRecords);
	}

	@Test public void OneStoreMultipleCopyList_NotQuiteEnough() {
		ShoppingList list = new ShoppingList();
		list.add("Example Card", 4);

		StandardSupplyRecord record1 = new StandardSupplyRecord("Example Card",vendor1,"Set1","Unplayed",3,"0.15");
		StandardSupplyRecord record2 = new StandardSupplyRecord("Example Card",vendor1,"Set2","Unplayed",3,"0.12");
		StandardSupplyRecord record3 = new StandardSupplyRecord("Example Card",vendor1,"Set3","Unplayed",3,"0.17");
		StandardSupplyRecord record4 = new StandardSupplyRecord("Example Card",vendor2,"Set1","Unplayed",2,"0.17");
		StandardSupplyRecord record5 = new StandardSupplyRecord("Example Card",vendor2,"Set2","Unplayed",1,"0.12");
		StandardSupplyRecord record6 = new StandardSupplyRecord("Example Card",vendor3,"Set3","Unplayed",2,"0.15");
		SupplyRecords records = new SupplyRecords(record1,record2,record3,record4,record5,record6);
		SupplyRecords filteredRecords = new CheapestCardsPerStoreForOrderFilter(list).filterRecords(records);
		assertSize(5, filteredRecords);

		Set<SupplyRecord> expectedSet = new HashSet<SupplyRecord>();
		expectedSet.add(record1);
		expectedSet.add(record2);
		expectedSet.add(record4);
		expectedSet.add(record5);
		expectedSet.add(record6);

		assertEquals(expectedSet, filteredRecords);
	}
}
