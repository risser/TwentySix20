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



public class TestEliminateMoreExpensiveStoresFilter {
	static private Vendor vendor1dollarA = new Vendor("shopA",new FlatRatePostage(100));
	static private Vendor vendor1dollarC = new Vendor("shopC",new FlatRatePostage(100));
	static private Vendor vendor2dollarB = new Vendor("shopB",new FlatRatePostage(200));

	@Test public void noRecordsNoList() {
		SupplyRecords filteredRecords = new EliminateMoreExpensiveStoresFilter(new ShoppingList()).filterRecords(new SupplyRecords());
		assertSize(0, filteredRecords);
	}

	@Test public void oneRecordOneItemList() {
		ShoppingList list = new ShoppingList("Example Card");

		SupplyRecords records = new SupplyRecords();
		records.add(new StandardSupplyRecord("Example Card",vendor1dollarA,"Set","Unplayed",3,"0.15"));
		SupplyRecords filteredRecords = new EliminateMoreExpensiveStoresFilter(list).filterRecords(records);
		assertSize(1, filteredRecords);
	}

	@Test public void OneRecordAgainstOneRecord() {
		ShoppingList list = new ShoppingList("Example Card");

		StandardSupplyRecord record1 = new StandardSupplyRecord("Example Card",vendor1dollarA,"Set","Unplayed",3,"0.15");
		StandardSupplyRecord record2 = new StandardSupplyRecord("Example Card",vendor1dollarC,"Set","Unplayed",3,"1.25");
		SupplyRecords records = new SupplyRecords();
		records.add(record1);
		records.add(record2);
		SupplyRecords filteredRecords = new EliminateMoreExpensiveStoresFilter(list).filterRecords(records);
		assertSize(1, filteredRecords);

		Set<SupplyRecord> expectedSet = new HashSet<SupplyRecord>();
		expectedSet.add(record1);

		assertEquals(expectedSet, filteredRecords);
	}

	@Test public void OneRecordAgainstOneRecord_ShippingMakesUpDifference() {
		ShoppingList list = new ShoppingList("Example Card");

		StandardSupplyRecord record1 = new StandardSupplyRecord("Example Card",vendor2dollarB,"Set","Unplayed",3,"0.15");
		StandardSupplyRecord record2 = new StandardSupplyRecord("Example Card",vendor1dollarA,"Set","Unplayed",3,"0.75");
		SupplyRecords records = new SupplyRecords();
		records.add(record1);
		records.add(record2);
		SupplyRecords filteredRecords = new EliminateMoreExpensiveStoresFilter(list).filterRecords(records);
		assertSize(2, filteredRecords);

		Set<SupplyRecord> expectedSet = new HashSet<SupplyRecord>();
		expectedSet.add(record1);
		expectedSet.add(record2);

		assertEquals(expectedSet, filteredRecords);
	}

	@Test public void OneRecordAgainstMultipleRecords() {
		ShoppingList list = new ShoppingList("Example Card 1","Example Card 2","Example Card 3","Example Card 4");

		StandardSupplyRecord record1a = new StandardSupplyRecord("Example Card 1",vendor1dollarA,"Set","Unplayed",3,"0.15");
		StandardSupplyRecord record1b = new StandardSupplyRecord("Example Card 2",vendor1dollarA,"Set","Unplayed",3,"0.15");
		StandardSupplyRecord record1c = new StandardSupplyRecord("Example Card 3",vendor1dollarA,"Set","Unplayed",3,"0.15");
		StandardSupplyRecord record1d = new StandardSupplyRecord("Example Card 4",vendor1dollarA,"Set","Unplayed",3,"0.15");
		StandardSupplyRecord record2 = new StandardSupplyRecord("Example Card 2",vendor1dollarC,"Set","Unplayed",3,"1.25");
		SupplyRecords records = new SupplyRecords();
		records.add(record1a);
		records.add(record1b);
		records.add(record1c);
		records.add(record1d);
		records.add(record2);
		SupplyRecords filteredRecords = new EliminateMoreExpensiveStoresFilter(list).filterRecords(records);
		assertSize(4, filteredRecords);

		Set<SupplyRecord> expectedSet = new HashSet<SupplyRecord>();
		expectedSet.add(record1a);
		expectedSet.add(record1b);
		expectedSet.add(record1c);
		expectedSet.add(record1d);

		assertEquals(expectedSet, filteredRecords);
	}

	@Test public void OneRecordAgainstMultipleRecords_ShippingMakesUpDifference() {
		ShoppingList list = new ShoppingList("Example Card 1","Example Card 2","Example Card 3","Example Card 4");

		StandardSupplyRecord record1a = new StandardSupplyRecord("Example Card 1",vendor2dollarB,"Set","Unplayed",3,"0.15");
		StandardSupplyRecord record1b = new StandardSupplyRecord("Example Card 2",vendor2dollarB,"Set","Unplayed",3,"0.15");
		StandardSupplyRecord record1c = new StandardSupplyRecord("Example Card 3",vendor2dollarB,"Set","Unplayed",3,"0.15");
		StandardSupplyRecord record1d = new StandardSupplyRecord("Example Card 4",vendor2dollarB,"Set","Unplayed",3,"0.15");
		StandardSupplyRecord record2 = new StandardSupplyRecord("Example Card 3",vendor1dollarA,"Set","Unplayed",3,"0.95"); 
		SupplyRecords records = new SupplyRecords();
		records.add(record1a);
		records.add(record1b);
		records.add(record1c);
		records.add(record1d);
		records.add(record2);
		SupplyRecords filteredRecords = new EliminateMoreExpensiveStoresFilter(list).filterRecords(records);
		assertSize(5, filteredRecords);

		Set<SupplyRecord> expectedSet = new HashSet<SupplyRecord>();
		expectedSet.add(record1a);
		expectedSet.add(record1b);
		expectedSet.add(record1c);
		expectedSet.add(record1d);
		expectedSet.add(record2);

		assertEquals(expectedSet, filteredRecords);
	}

	@Test public void allMultipleRecordsAgainstMultipleSmallerRecords() {
		ShoppingList list = new ShoppingList("Example Card 1","Example Card 2","Example Card 3","Example Card 4");

		StandardSupplyRecord record1a = new StandardSupplyRecord("Example Card 1",vendor1dollarA,"Set","Unplayed",3,"0.15");
		StandardSupplyRecord record1b = new StandardSupplyRecord("Example Card 2",vendor1dollarA,"Set","Unplayed",3,"0.35");
		StandardSupplyRecord record1c = new StandardSupplyRecord("Example Card 3",vendor1dollarA,"Set","Unplayed",3,"0.20");
		StandardSupplyRecord record1d = new StandardSupplyRecord("Example Card 4",vendor1dollarA,"Set","Unplayed",3,"0.25");
		StandardSupplyRecord record2a = new StandardSupplyRecord("Example Card 1",vendor1dollarC,"Set","Unplayed",3,"0.35"); 
		StandardSupplyRecord record2b = new StandardSupplyRecord("Example Card 2",vendor1dollarC,"Set","Unplayed",3,"0.45"); 
		StandardSupplyRecord record2c = new StandardSupplyRecord("Example Card 3",vendor1dollarC,"Set","Unplayed",3,"0.35"); 
		StandardSupplyRecord record2d = new StandardSupplyRecord("Example Card 4",vendor1dollarC,"Set","Unplayed",3,"0.25"); 
		SupplyRecords records = new SupplyRecords();
		records.add(record1a);
		records.add(record1b);
		records.add(record1c);
		records.add(record1d);
		records.add(record2a);
		records.add(record2b);
		records.add(record2c);
		records.add(record2d);
		SupplyRecords filteredRecords = new EliminateMoreExpensiveStoresFilter(list).filterRecords(records);
		assertSize(4, filteredRecords);

		Set<SupplyRecord> expectedSet = new HashSet<SupplyRecord>();
		expectedSet.add(record1a);
		expectedSet.add(record1b);
		expectedSet.add(record1c);
		expectedSet.add(record1d);

		assertEquals(expectedSet, filteredRecords);
	}

	@Test public void SomeMultipleRecordsAgainstMultipleSmallerRecords() {
		ShoppingList list = new ShoppingList("Example Card 1","Example Card 2","Example Card 3","Example Card 4");

		StandardSupplyRecord record1a = new StandardSupplyRecord("Example Card 1",vendor1dollarA,"Set","Unplayed",3,"0.15");
		StandardSupplyRecord record1b = new StandardSupplyRecord("Example Card 2",vendor1dollarA,"Set","Unplayed",3,"0.35");
		StandardSupplyRecord record1c = new StandardSupplyRecord("Example Card 3",vendor1dollarA,"Set","Unplayed",3,"0.20");
		StandardSupplyRecord record1d = new StandardSupplyRecord("Example Card 4",vendor1dollarA,"Set","Unplayed",3,"0.25");
		StandardSupplyRecord record2a = new StandardSupplyRecord("Example Card 1",vendor1dollarC,"Set","Unplayed",3,"0.35"); 
		StandardSupplyRecord record2c = new StandardSupplyRecord("Example Card 3",vendor1dollarC,"Set","Unplayed",3,"0.35"); 
		StandardSupplyRecord record2d = new StandardSupplyRecord("Example Card 4",vendor1dollarC,"Set","Unplayed",3,"0.25"); 
		SupplyRecords records = new SupplyRecords();
		records.add(record1a);
		records.add(record1b);
		records.add(record1c);
		records.add(record1d);
		records.add(record2a);
		records.add(record2c);
		records.add(record2d);
		SupplyRecords filteredRecords = new EliminateMoreExpensiveStoresFilter(list).filterRecords(records);
		assertSize(4, filteredRecords);

		Set<SupplyRecord> expectedSet = new HashSet<SupplyRecord>();
		expectedSet.add(record1a);
		expectedSet.add(record1b);
		expectedSet.add(record1c);
		expectedSet.add(record1d);

		assertEquals(expectedSet, filteredRecords);
	}

	@Test public void OneSmallerRecordAgainstMultipleRecordsWithLarger() {
		ShoppingList list = new ShoppingList("Example Card 1","Example Card 2","Example Card 3","Example Card 4");

		StandardSupplyRecord record1a = new StandardSupplyRecord("Example Card 1",vendor2dollarB,"Set","Unplayed",3,"0.25");
		StandardSupplyRecord record1b = new StandardSupplyRecord("Example Card 2",vendor2dollarB,"Set","Unplayed",3,"0.25");
		StandardSupplyRecord record1c = new StandardSupplyRecord("Example Card 3",vendor2dollarB,"Set","Unplayed",3,"0.25");
		StandardSupplyRecord record1d = new StandardSupplyRecord("Example Card 4",vendor2dollarB,"Set","Unplayed",3,"0.25");
		StandardSupplyRecord record2 = new StandardSupplyRecord("Example Card 3",vendor1dollarA,"Set","Unplayed",3,"0.15"); 
		SupplyRecords records = new SupplyRecords();
		records.add(record1a);
		records.add(record1b);
		records.add(record1c);
		records.add(record1d);
		records.add(record2);
		SupplyRecords filteredRecords = new EliminateMoreExpensiveStoresFilter(list).filterRecords(records);
		assertSize(5, filteredRecords);

		Set<SupplyRecord> expectedSet = new HashSet<SupplyRecord>();
		expectedSet.add(record1a);
		expectedSet.add(record1b);
		expectedSet.add(record1c);
		expectedSet.add(record1d);
		expectedSet.add(record2);

		assertEquals(expectedSet, filteredRecords);
	}

	@Test public void SomeMultipleRecordsAgainstMultipleSmallerRecords_ShippingMakesDifference() {
		ShoppingList list = new ShoppingList("Example Card 1","Example Card 2","Example Card 3","Example Card 4");

		StandardSupplyRecord record1a = new StandardSupplyRecord("Example Card 1",vendor2dollarB,"Set","Unplayed",3,"0.15");
		StandardSupplyRecord record1b = new StandardSupplyRecord("Example Card 2",vendor2dollarB,"Set","Unplayed",3,"0.35");
		StandardSupplyRecord record1c = new StandardSupplyRecord("Example Card 3",vendor2dollarB,"Set","Unplayed",3,"0.20");
		StandardSupplyRecord record1d = new StandardSupplyRecord("Example Card 4",vendor2dollarB,"Set","Unplayed",3,"0.25");
		StandardSupplyRecord record2a = new StandardSupplyRecord("Example Card 1",vendor1dollarC,"Set","Unplayed",3,"0.35"); 
		StandardSupplyRecord record2c = new StandardSupplyRecord("Example Card 3",vendor1dollarC,"Set","Unplayed",3,"0.35"); 
		StandardSupplyRecord record2d = new StandardSupplyRecord("Example Card 4",vendor1dollarC,"Set","Unplayed",3,"0.25"); 
		SupplyRecords records = new SupplyRecords();
		records.add(record1a);
		records.add(record1b);
		records.add(record1c);
		records.add(record1d);
		records.add(record2a);
		records.add(record2c);
		records.add(record2d);
		SupplyRecords filteredRecords = new EliminateMoreExpensiveStoresFilter(list).filterRecords(records);
		assertSize(7, filteredRecords);

		Set<SupplyRecord> expectedSet = new HashSet<SupplyRecord>();
		expectedSet.add(record1a);
		expectedSet.add(record1b);
		expectedSet.add(record1c);
		expectedSet.add(record1d);
		expectedSet.add(record2a);
		expectedSet.add(record2c);
		expectedSet.add(record2d);

		assertEquals(expectedSet, filteredRecords);
	}

	@Test public void SomeMultipleRecordsAgainstMultipleSmallerRecords_OneIsCheaper() {
		ShoppingList list = new ShoppingList("Example Card 1","Example Card 2","Example Card 3","Example Card 4");

		StandardSupplyRecord record1a = new StandardSupplyRecord("Example Card 1",vendor1dollarA,"Set","Unplayed",3,"0.15");
		StandardSupplyRecord record1b = new StandardSupplyRecord("Example Card 2",vendor1dollarA,"Set","Unplayed",3,"0.35");
		StandardSupplyRecord record1c = new StandardSupplyRecord("Example Card 3",vendor1dollarA,"Set","Unplayed",3,"0.20");
		StandardSupplyRecord record1d = new StandardSupplyRecord("Example Card 4",vendor1dollarA,"Set","Unplayed",3,"0.25");
		StandardSupplyRecord record2a = new StandardSupplyRecord("Example Card 1",vendor1dollarC,"Set","Unplayed",3,"0.35"); 
		StandardSupplyRecord record2c = new StandardSupplyRecord("Example Card 3",vendor1dollarC,"Set","Unplayed",3,"0.35"); 
		StandardSupplyRecord record2d = new StandardSupplyRecord("Example Card 4",vendor1dollarC,"Set","Unplayed",3,"0.20"); 
		SupplyRecords records = new SupplyRecords();
		records.add(record1a);
		records.add(record1b);
		records.add(record1c);
		records.add(record1d);
		records.add(record2a);
		records.add(record2c);
		records.add(record2d);
		SupplyRecords filteredRecords = new EliminateMoreExpensiveStoresFilter(list).filterRecords(records);
		assertSize(7, filteredRecords);

		Set<SupplyRecord> expectedSet = new HashSet<SupplyRecord>();
		expectedSet.add(record1a);
		expectedSet.add(record1b);
		expectedSet.add(record1c);
		expectedSet.add(record1d);
		expectedSet.add(record2a);
		expectedSet.add(record2c);
		expectedSet.add(record2d);

		assertEquals(expectedSet, filteredRecords);
	}

	@Test public void SomeMultipleRecordsAgainstMultipleSmallerRecords_AdditionalCard() {
		ShoppingList list = new ShoppingList("Example Card 1","Example Card 2","Example Card 3","Example Card 4");

		StandardSupplyRecord record1a = new StandardSupplyRecord("Example Card 1",vendor1dollarA,"Set","Unplayed",3,"0.15");
		StandardSupplyRecord record1b = new StandardSupplyRecord("Example Card 2",vendor1dollarA,"Set","Unplayed",3,"0.35");
		StandardSupplyRecord record1d = new StandardSupplyRecord("Example Card 4",vendor1dollarA,"Set","Unplayed",3,"0.25");
		StandardSupplyRecord record2a = new StandardSupplyRecord("Example Card 1",vendor1dollarC,"Set","Unplayed",3,"0.35"); 
		StandardSupplyRecord record2c = new StandardSupplyRecord("Example Card 3",vendor1dollarC,"Set","Unplayed",3,"0.35"); 
		StandardSupplyRecord record2d = new StandardSupplyRecord("Example Card 4",vendor1dollarC,"Set","Unplayed",3,"0.35"); 
		SupplyRecords records = new SupplyRecords();
		records.add(record1a);
		records.add(record1b);
		records.add(record1d);
		records.add(record2a);
		records.add(record2c);
		records.add(record2d);
		SupplyRecords filteredRecords = new EliminateMoreExpensiveStoresFilter(list).filterRecords(records);
		assertSize(6, filteredRecords);

		Set<SupplyRecord> expectedSet = new HashSet<SupplyRecord>();
		expectedSet.add(record1a);
		expectedSet.add(record1b);
		expectedSet.add(record1d);
		expectedSet.add(record2a);
		expectedSet.add(record2c);
		expectedSet.add(record2d);

		assertEquals(expectedSet, filteredRecords);
	}

	@Test public void needMultiplesMultiplesMoreThanSingleSolution() {
		ShoppingList list = new ShoppingList("Example Card","Example Card");

		StandardSupplyRecord record1a = new StandardSupplyRecord("Example Card",vendor1dollarA,"Set","Unplayed",1,"0.15");
		StandardSupplyRecord record1b = new StandardSupplyRecord("Example Card",vendor1dollarA,"Set","Unplayed",1,"0.18");
		StandardSupplyRecord record2 = new StandardSupplyRecord("Example Card",vendor1dollarC,"Set","Unplayed",2,"0.15");
		SupplyRecords records = new SupplyRecords();
		records.add(record1a);
		records.add(record1b);
		records.add(record2);
		SupplyRecords filteredRecords = new EliminateMoreExpensiveStoresFilter(list).filterRecords(records);
		assertSize(1, filteredRecords);

		Set<SupplyRecord> expectedSet = new HashSet<SupplyRecord>();
		expectedSet.add(record2);

		assertEquals(expectedSet, filteredRecords);
	}

	@Test public void needMultiplesMultiplesCheaperThanSingleSolution() {
		ShoppingList list = new ShoppingList("Example Card","Example Card");

		StandardSupplyRecord record1a = new StandardSupplyRecord("Example Card",vendor1dollarA,"Set","Unplayed",1,"0.15");
		StandardSupplyRecord record1b = new StandardSupplyRecord("Example Card",vendor1dollarA,"Set","Unplayed",1,"0.18");
		StandardSupplyRecord record2 = new StandardSupplyRecord("Example Card",vendor1dollarC,"Set","Unplayed",2,"0.18");
		SupplyRecords records = new SupplyRecords();
		records.add(record1a);
		records.add(record1b);
		records.add(record2);
		SupplyRecords filteredRecords = new EliminateMoreExpensiveStoresFilter(list).filterRecords(records);
		assertSize(2, filteredRecords);

		Set<SupplyRecord> expectedSet = new HashSet<SupplyRecord>();
		expectedSet.add(record1a);
		expectedSet.add(record1b);

		assertEquals(expectedSet, filteredRecords);
	}

	@Test public void needMultiplesMultiplesOneCheaperThanSingleSolutionOneNot_A() {
		ShoppingList list = new ShoppingList("Example Card","Example Card");

		StandardSupplyRecord record1a = new StandardSupplyRecord("Example Card",vendor1dollarA,"Set","Unplayed",1,"0.15");
		StandardSupplyRecord record1b = new StandardSupplyRecord("Example Card",vendor1dollarA,"Set","Unplayed",1,"0.20");
		StandardSupplyRecord record2 = new StandardSupplyRecord("Example Card",vendor1dollarC,"Set","Unplayed",2,"0.18");
		SupplyRecords records = new SupplyRecords();
		records.add(record1a);
		records.add(record1b);
		records.add(record2);
		SupplyRecords filteredRecords = new EliminateMoreExpensiveStoresFilter(list).filterRecords(records);
		assertSize(3, filteredRecords);

		Set<SupplyRecord> expectedSet = new HashSet<SupplyRecord>();
		expectedSet.add(record1a);
		expectedSet.add(record1b);
		expectedSet.add(record2);

		assertEquals(expectedSet, filteredRecords);
	}

	@Test public void needMultiplesMultiplesOneCheaperThanSingleSolutionOneNot_B() {
		ShoppingList list = new ShoppingList("Example Card","Example Card");

		StandardSupplyRecord record1a = new StandardSupplyRecord("Example Card",vendor1dollarA,"Set","Unplayed",1,"0.15");
		StandardSupplyRecord record1b = new StandardSupplyRecord("Example Card",vendor1dollarA,"Set","Unplayed",1,"0.22");
		StandardSupplyRecord record2 = new StandardSupplyRecord("Example Card",vendor1dollarC,"Set","Unplayed",2,"0.18");
		SupplyRecords records = new SupplyRecords();
		records.add(record1a);
		records.add(record1b);
		records.add(record2);
		SupplyRecords filteredRecords = new EliminateMoreExpensiveStoresFilter(list).filterRecords(records);
		assertSize(3, filteredRecords);

		Set<SupplyRecord> expectedSet = new HashSet<SupplyRecord>();
		expectedSet.add(record1a);
		expectedSet.add(record1b);
		expectedSet.add(record2);

		assertEquals(expectedSet, filteredRecords);
	}

	@Test public void needMultiplesMultiplesLessThanSingleSolution_ButShippingChangesIt() {
		ShoppingList list = new ShoppingList("Example Card","Example Card");

		StandardSupplyRecord record1a = new StandardSupplyRecord("Example Card",vendor2dollarB,"Set","Unplayed",1,"0.13");
		StandardSupplyRecord record1b = new StandardSupplyRecord("Example Card",vendor2dollarB,"Set","Unplayed",1,"0.15");
		StandardSupplyRecord record2 = new StandardSupplyRecord("Example Card",vendor1dollarA,"Set","Unplayed",2,"0.15");
		SupplyRecords records = new SupplyRecords();
		records.add(record1a);
		records.add(record1b);
		records.add(record2);
		SupplyRecords filteredRecords = new EliminateMoreExpensiveStoresFilter(list).filterRecords(records);
		assertSize(3, filteredRecords);

		Set<SupplyRecord> expectedSet = new HashSet<SupplyRecord>();
		expectedSet.add(record1a);
		expectedSet.add(record1b);
		expectedSet.add(record2);

		assertEquals(expectedSet, filteredRecords);
	}

	@Test public void needMultiplesMultiplesMoreThanSingleSolution_ShippingMakesDifference() {
		ShoppingList list = new ShoppingList("Example Card","Example Card");

		StandardSupplyRecord record1a = new StandardSupplyRecord("Example Card",vendor1dollarA,"Set","Unplayed",1,"0.15");
		StandardSupplyRecord record1b = new StandardSupplyRecord("Example Card",vendor1dollarA,"Set","Unplayed",1,"0.17");
		StandardSupplyRecord record2 = new StandardSupplyRecord("Example Card",vendor2dollarB,"Set","Unplayed",2,"0.15");
		SupplyRecords records = new SupplyRecords();
		records.add(record1a);
		records.add(record1b);
		records.add(record2);
		SupplyRecords filteredRecords = new EliminateMoreExpensiveStoresFilter(list).filterRecords(records);
		assertSize(3, filteredRecords);

		Set<SupplyRecord> expectedSet = new HashSet<SupplyRecord>();
		expectedSet.add(record1a);
		expectedSet.add(record1b);
		expectedSet.add(record2);

		assertEquals(expectedSet, filteredRecords);
	}

	@Test public void subsetIsExactlyEquals() {
		ShoppingList list = new ShoppingList("Example Card 1","Example Card 2","Example Card 3","Example Card 4");

		StandardSupplyRecord record1a = new StandardSupplyRecord("Example Card 1",vendor1dollarA,"Set","Unplayed",2,"0.10");
		StandardSupplyRecord record1b = new StandardSupplyRecord("Example Card 2",vendor1dollarA,"Set","Unplayed",2,"0.10");
		StandardSupplyRecord record1c = new StandardSupplyRecord("Example Card 3",vendor1dollarA,"Set","Unplayed",2,"0.10");
		StandardSupplyRecord record1d = new StandardSupplyRecord("Example Card 4",vendor1dollarA,"Set","Unplayed",2,"0.10");
		StandardSupplyRecord record2a = new StandardSupplyRecord("Example Card 1",vendor1dollarC,"Set","Unplayed",2,"0.10"); 
		StandardSupplyRecord record2c = new StandardSupplyRecord("Example Card 3",vendor1dollarC,"Set","Unplayed",2,"0.10"); 
		StandardSupplyRecord record2d = new StandardSupplyRecord("Example Card 4",vendor1dollarC,"Set","Unplayed",2,"0.10"); 
		SupplyRecords records = new SupplyRecords();
		records.add(record1a);
		records.add(record1b);
		records.add(record1c);
		records.add(record1d);
		records.add(record2a);
		records.add(record2c);
		records.add(record2d);
		SupplyRecords filteredRecords = new EliminateMoreExpensiveStoresFilter(list).filterRecords(records);
		assertSize(4, filteredRecords);

		Set<SupplyRecord> expectedSet = new HashSet<SupplyRecord>();
		expectedSet.add(record1a);
		expectedSet.add(record1b);
		expectedSet.add(record1c);
		expectedSet.add(record1d);

		assertEquals(expectedSet, filteredRecords);
	}

	@Test public void notEnoughToMeet() {
		ShoppingList list = new ShoppingList();
		list.add("Example Card 1",2);
		list.add("Example Card 2",2);
		list.add("Example Card 3",2);
		list.add("Example Card 4",2);

		StandardSupplyRecord record1a = new StandardSupplyRecord("Example Card 1",vendor1dollarA,"Set","Unplayed",1,"0.10");
		StandardSupplyRecord record1b = new StandardSupplyRecord("Example Card 2",vendor1dollarA,"Set","Unplayed",2,"0.10");
		StandardSupplyRecord record1c = new StandardSupplyRecord("Example Card 3",vendor1dollarA,"Set","Unplayed",2,"0.10");
		StandardSupplyRecord record1d = new StandardSupplyRecord("Example Card 4",vendor1dollarA,"Set","Unplayed",2,"0.10");
		StandardSupplyRecord record2a = new StandardSupplyRecord("Example Card 1",vendor1dollarC,"Set","Unplayed",2,"0.11"); 
		StandardSupplyRecord record2c = new StandardSupplyRecord("Example Card 3",vendor1dollarC,"Set","Unplayed",2,"0.11"); 
		StandardSupplyRecord record2d = new StandardSupplyRecord("Example Card 4",vendor1dollarC,"Set","Unplayed",2,"0.11"); 
		SupplyRecords records = new SupplyRecords();
		records.add(record1a);
		records.add(record1b);
		records.add(record1c);
		records.add(record1d);
		records.add(record2a);
		records.add(record2c);
		records.add(record2d);
		SupplyRecords filteredRecords = new EliminateMoreExpensiveStoresFilter(list).filterRecords(records);
		assertSize(7, filteredRecords);

		Set<SupplyRecord> expectedSet = new HashSet<SupplyRecord>();
		expectedSet.add(record1a);
		expectedSet.add(record1b);
		expectedSet.add(record1c);
		expectedSet.add(record1d);
		expectedSet.add(record2a);
		expectedSet.add(record2c);
		expectedSet.add(record2d);

		assertEquals(expectedSet, filteredRecords);
	}

	@Test public void notEnoughOfAnotherToMeet() {
		ShoppingList list = new ShoppingList();
		list.add("Example Card 1",2);
		list.add("Example Card 2",2);

		StandardSupplyRecord record1a = new StandardSupplyRecord("Example Card 1",vendor1dollarA,"Set","Unplayed",1,"0.10");
		StandardSupplyRecord record1b = new StandardSupplyRecord("Example Card 2",vendor1dollarA,"Set","Unplayed",2,"0.10");
		StandardSupplyRecord record2a = new StandardSupplyRecord("Example Card 2",vendor1dollarC,"Set","Unplayed",2,"0.11"); 
		SupplyRecords records = new SupplyRecords();
		records.add(record1a);
		records.add(record1b);
		records.add(record2a);
		SupplyRecords filteredRecords = new EliminateMoreExpensiveStoresFilter(list).filterRecords(records);
		assertSize(2, filteredRecords);

		Set<SupplyRecord> expectedSet = new HashSet<SupplyRecord>();
		expectedSet.add(record1a);
		expectedSet.add(record1b);

		assertEquals(expectedSet, filteredRecords);
	}

	@Test public void equalsSameShipping() {
		ShoppingList list = new ShoppingList();
		list.add("Example Card 1",1);
		list.add("Example Card 2",1);
		list.add("Example Card 3",1);
		list.add("Example Card 4",1);

		StandardSupplyRecord record1a = new StandardSupplyRecord("Example Card 1",vendor1dollarA,"Set","Unplayed",2,"0.99");
		StandardSupplyRecord record1b = new StandardSupplyRecord("Example Card 2",vendor1dollarA,"Set","Unplayed",2,"0.99");
		StandardSupplyRecord record1c = new StandardSupplyRecord("Example Card 3",vendor1dollarA,"Set","Unplayed",2,"0.99");
		StandardSupplyRecord record1d = new StandardSupplyRecord("Example Card 4",vendor1dollarA,"Set","Unplayed",2,"0.99");
		StandardSupplyRecord record2a = new StandardSupplyRecord("Example Card 1",vendor1dollarC,"Set","Unplayed",2,"0.99"); 
		StandardSupplyRecord record2c = new StandardSupplyRecord("Example Card 3",vendor1dollarC,"Set","Unplayed",2,"0.99"); 
		StandardSupplyRecord record2d = new StandardSupplyRecord("Example Card 4",vendor1dollarC,"Set","Unplayed",2,"0.99"); 
		SupplyRecords records = new SupplyRecords();
		records.add(record1a);
		records.add(record1b);
		records.add(record1c);
		records.add(record1d);
		records.add(record2a);
		records.add(record2c);
		records.add(record2d);
		SupplyRecords filteredRecords = new EliminateMoreExpensiveStoresFilter(list).filterRecords(records);
		assertSize(4, filteredRecords);

		Set<SupplyRecord> expectedSet = new HashSet<SupplyRecord>();
		expectedSet.add(record1a);
		expectedSet.add(record1b);
		expectedSet.add(record1c);
		expectedSet.add(record1d);

		assertEquals(expectedSet, filteredRecords);
	}

	@Test public void equalsNonSubsets() {
		ShoppingList list = new ShoppingList();
		list.add("Example Card 1",1);
		list.add("Example Card 2",1);
		list.add("Example Card 3",1);
		list.add("Example Card 4",1);

		StandardSupplyRecord record1a = new StandardSupplyRecord("Example Card 1",vendor1dollarA,"Set","Unplayed",2,"0.99");
		StandardSupplyRecord record1b = new StandardSupplyRecord("Example Card 2",vendor1dollarA,"Set","Unplayed",2,"0.99");
		StandardSupplyRecord record1d = new StandardSupplyRecord("Example Card 4",vendor1dollarA,"Set","Unplayed",2,"0.99");
		StandardSupplyRecord record2a = new StandardSupplyRecord("Example Card 1",vendor1dollarC,"Set","Unplayed",2,"0.99"); 
		StandardSupplyRecord record2c = new StandardSupplyRecord("Example Card 3",vendor1dollarC,"Set","Unplayed",2,"0.99"); 
		StandardSupplyRecord record2d = new StandardSupplyRecord("Example Card 4",vendor1dollarC,"Set","Unplayed",2,"0.99"); 
		SupplyRecords records = new SupplyRecords();
		records.add(record1a);
		records.add(record1b);
		records.add(record1d);
		records.add(record2a);
		records.add(record2c);
		records.add(record2d);
		SupplyRecords filteredRecords = new EliminateMoreExpensiveStoresFilter(list).filterRecords(records);
		assertSize(6, filteredRecords);

		Set<SupplyRecord> expectedSet = new HashSet<SupplyRecord>();
		expectedSet.add(record1a);
		expectedSet.add(record1b);
		expectedSet.add(record1d);
		expectedSet.add(record2a);
		expectedSet.add(record2c);
		expectedSet.add(record2d);

		assertEquals(expectedSet, filteredRecords);
	}

	@Test public void equalsDifferentShipping1() {
		ShoppingList list = new ShoppingList();
		list.add("Example Card 1",1);
		list.add("Example Card 2",1);
		list.add("Example Card 3",1);
		list.add("Example Card 4",1);

		StandardSupplyRecord record1a = new StandardSupplyRecord("Example Card 1",vendor1dollarA,"Set","Unplayed",2,"0.99");
		StandardSupplyRecord record1b = new StandardSupplyRecord("Example Card 2",vendor1dollarA,"Set","Unplayed",2,"0.99");
		StandardSupplyRecord record1c = new StandardSupplyRecord("Example Card 3",vendor1dollarA,"Set","Unplayed",2,"0.99");
		StandardSupplyRecord record1d = new StandardSupplyRecord("Example Card 4",vendor1dollarA,"Set","Unplayed",2,"0.99");
		StandardSupplyRecord record2a = new StandardSupplyRecord("Example Card 1",vendor2dollarB,"Set","Unplayed",2,"0.99"); 
		StandardSupplyRecord record2c = new StandardSupplyRecord("Example Card 3",vendor2dollarB,"Set","Unplayed",2,"0.99"); 
		StandardSupplyRecord record2d = new StandardSupplyRecord("Example Card 4",vendor2dollarB,"Set","Unplayed",2,"0.99"); 
		SupplyRecords records = new SupplyRecords();
		records.add(record1a);
		records.add(record1b);
		records.add(record1c);
		records.add(record1d);
		records.add(record2a);
		records.add(record2c);
		records.add(record2d);
		SupplyRecords filteredRecords = new EliminateMoreExpensiveStoresFilter(list).filterRecords(records);
		assertSize(4, filteredRecords);

		Set<SupplyRecord> expectedSet = new HashSet<SupplyRecord>();
		expectedSet.add(record1a);
		expectedSet.add(record1b);
		expectedSet.add(record1c);
		expectedSet.add(record1d);

		assertEquals(expectedSet, filteredRecords);
	}

	@Test public void equalsDifferentShipping2() {
		ShoppingList list = new ShoppingList();
		list.add("Example Card 1",1);
		list.add("Example Card 2",1);
		list.add("Example Card 3",1);
		list.add("Example Card 4",1);

		StandardSupplyRecord record1a = new StandardSupplyRecord("Example Card 1",vendor2dollarB,"Set","Unplayed",2,"0.99");
		StandardSupplyRecord record1b = new StandardSupplyRecord("Example Card 2",vendor2dollarB,"Set","Unplayed",2,"0.99");
		StandardSupplyRecord record1c = new StandardSupplyRecord("Example Card 3",vendor2dollarB,"Set","Unplayed",2,"0.99");
		StandardSupplyRecord record1d = new StandardSupplyRecord("Example Card 4",vendor2dollarB,"Set","Unplayed",2,"0.99");
		StandardSupplyRecord record2a = new StandardSupplyRecord("Example Card 1",vendor1dollarC,"Set","Unplayed",2,"0.99"); 
		StandardSupplyRecord record2c = new StandardSupplyRecord("Example Card 3",vendor1dollarC,"Set","Unplayed",2,"0.99"); 
		StandardSupplyRecord record2d = new StandardSupplyRecord("Example Card 4",vendor1dollarC,"Set","Unplayed",2,"0.99"); 
		SupplyRecords records = new SupplyRecords();
		records.add(record1a);
		records.add(record1b);
		records.add(record1c);
		records.add(record1d);
		records.add(record2a);
		records.add(record2c);
		records.add(record2d);
		SupplyRecords filteredRecords = new EliminateMoreExpensiveStoresFilter(list).filterRecords(records);
		assertSize(7, filteredRecords);

		Set<SupplyRecord> expectedSet = new HashSet<SupplyRecord>();
		expectedSet.add(record1a);
		expectedSet.add(record1b);
		expectedSet.add(record1c);
		expectedSet.add(record1d);
		expectedSet.add(record2a);
		expectedSet.add(record2c);
		expectedSet.add(record2d);

		assertEquals(expectedSet, filteredRecords);
	}

	@Test public void mostEqualsWithOneMore_NoShipping() {
		ShoppingList list = new ShoppingList();
		list.add("Example Card 1",1);
		list.add("Example Card 2",1);
		list.add("Example Card 3",1);
		list.add("Example Card 4",1);

		StandardSupplyRecord record1a = new StandardSupplyRecord("Example Card 1",vendor1dollarA,"Set","Unplayed",2,"0.99");
		StandardSupplyRecord record1b = new StandardSupplyRecord("Example Card 2",vendor1dollarA,"Set","Unplayed",2,"0.99");
		StandardSupplyRecord record1c = new StandardSupplyRecord("Example Card 3",vendor1dollarA,"Set","Unplayed",2,"0.99");
		StandardSupplyRecord record1d = new StandardSupplyRecord("Example Card 4",vendor1dollarA,"Set","Unplayed",2,"0.99");
		StandardSupplyRecord record2a = new StandardSupplyRecord("Example Card 1",vendor1dollarC,"Set","Unplayed",2,"1.00"); 
		StandardSupplyRecord record2c = new StandardSupplyRecord("Example Card 3",vendor1dollarC,"Set","Unplayed",2,"0.99"); 
		StandardSupplyRecord record2d = new StandardSupplyRecord("Example Card 4",vendor1dollarC,"Set","Unplayed",2,"0.99"); 
		SupplyRecords records = new SupplyRecords();
		records.add(record1a);
		records.add(record1b);
		records.add(record1c);
		records.add(record1d);
		records.add(record2a);
		records.add(record2c);
		records.add(record2d);
		SupplyRecords filteredRecords = new EliminateMoreExpensiveStoresFilter(list).filterRecords(records);
		assertSize(4, filteredRecords);

		Set<SupplyRecord> expectedSet = new HashSet<SupplyRecord>();
		expectedSet.add(record1a);
		expectedSet.add(record1b);
		expectedSet.add(record1c);
		expectedSet.add(record1d);

		assertEquals(expectedSet, filteredRecords);
	}

	@Test public void mostEqualsWithOneMore_WithShipping() {
		ShoppingList list = new ShoppingList();
		list.add("Example Card 1",1);
		list.add("Example Card 2",1);
		list.add("Example Card 3",1);
		list.add("Example Card 4",1);

		StandardSupplyRecord record1a = new StandardSupplyRecord("Example Card 1",vendor2dollarB,"Set","Unplayed",2,"0.99");
		StandardSupplyRecord record1b = new StandardSupplyRecord("Example Card 2",vendor2dollarB,"Set","Unplayed",2,"0.99");
		StandardSupplyRecord record1c = new StandardSupplyRecord("Example Card 3",vendor2dollarB,"Set","Unplayed",2,"0.99");
		StandardSupplyRecord record1d = new StandardSupplyRecord("Example Card 4",vendor2dollarB,"Set","Unplayed",2,"0.99");
		StandardSupplyRecord record2a = new StandardSupplyRecord("Example Card 1",vendor1dollarC,"Set","Unplayed",2,"1.00"); 
		StandardSupplyRecord record2c = new StandardSupplyRecord("Example Card 3",vendor1dollarC,"Set","Unplayed",2,"0.99"); 
		StandardSupplyRecord record2d = new StandardSupplyRecord("Example Card 4",vendor1dollarC,"Set","Unplayed",2,"0.99"); 
		SupplyRecords records = new SupplyRecords();
		records.add(record1a);
		records.add(record1b);
		records.add(record1c);
		records.add(record1d);
		records.add(record2a);
		records.add(record2c);
		records.add(record2d);
		SupplyRecords filteredRecords = new EliminateMoreExpensiveStoresFilter(list).filterRecords(records);
		assertSize(7, filteredRecords);

		Set<SupplyRecord> expectedSet = new HashSet<SupplyRecord>();
		expectedSet.add(record1a);
		expectedSet.add(record1b);
		expectedSet.add(record1c);
		expectedSet.add(record1d);
		expectedSet.add(record2a);
		expectedSet.add(record2c);
		expectedSet.add(record2d);

		assertEquals(expectedSet, filteredRecords);
	}

	@Test public void mostEqualsWithOneWAYMore_WithShipping() {
		ShoppingList list = new ShoppingList();
		list.add("Example Card 1",1);
		list.add("Example Card 2",1);
		list.add("Example Card 3",1);
		list.add("Example Card 4",1);

		StandardSupplyRecord record1a = new StandardSupplyRecord("Example Card 1",vendor2dollarB,"Set","Unplayed",2,"0.99");
		StandardSupplyRecord record1b = new StandardSupplyRecord("Example Card 2",vendor2dollarB,"Set","Unplayed",2,"0.99");
		StandardSupplyRecord record1c = new StandardSupplyRecord("Example Card 3",vendor2dollarB,"Set","Unplayed",2,"0.99");
		StandardSupplyRecord record1d = new StandardSupplyRecord("Example Card 4",vendor2dollarB,"Set","Unplayed",2,"0.99");
		StandardSupplyRecord record2a = new StandardSupplyRecord("Example Card 1",vendor1dollarC,"Set","Unplayed",2,"4.00"); 
		StandardSupplyRecord record2c = new StandardSupplyRecord("Example Card 3",vendor1dollarC,"Set","Unplayed",2,"0.99"); 
		StandardSupplyRecord record2d = new StandardSupplyRecord("Example Card 4",vendor1dollarC,"Set","Unplayed",2,"0.99"); 
		SupplyRecords records = new SupplyRecords();
		records.add(record1a);
		records.add(record1b);
		records.add(record1c);
		records.add(record1d);
		records.add(record2a);
		records.add(record2c);
		records.add(record2d);
		SupplyRecords filteredRecords = new EliminateMoreExpensiveStoresFilter(list).filterRecords(records);
		assertSize(7, filteredRecords);

		Set<SupplyRecord> expectedSet = new HashSet<SupplyRecord>();
		expectedSet.add(record1a);
		expectedSet.add(record1b);
		expectedSet.add(record1c);
		expectedSet.add(record1d);
		expectedSet.add(record2a);
		expectedSet.add(record2c);
		expectedSet.add(record2d);

		assertEquals(expectedSet, filteredRecords);
	}
}