package com.twentysix20.cardstore.supplyfilter;

import static com.twentysix20.testutil.TestCase2620.assertSize;
import static junit.framework.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.twentysix20.cardstore.list.ShoppingList;
import com.twentysix20.cardstore.postage.BasePlusPerCardPostage;
import com.twentysix20.cardstore.postage.FlatRatePostage;
import com.twentysix20.cardstore.supplyrecord.StandardSupplyRecord;
import com.twentysix20.cardstore.supplyrecord.SupplyRecord;
import com.twentysix20.cardstore.supplyrecord.SupplyRecords;
import com.twentysix20.cardstore.vendor.Vendor;

public class TestEliminateMoreExpensiveStoresFilter2 {
	static private Vendor vendor1dollarA = new Vendor("shopA",new FlatRatePostage(100));
	static private Vendor vendor1dollarC = new Vendor("shopC",new FlatRatePostage(100));
	static private Vendor vendor2dollarB = new Vendor("shopB",new FlatRatePostage(200));
	static private Vendor incrementingVendor = new Vendor("shopInc",new BasePlusPerCardPostage(99, 1));
	static private ShoppingList list = new ShoppingList("Example Card 1","Example Card 2");

	@Test public void noRecordsNoList() {
		SupplyRecords filteredRecords = new EliminateMoreExpensiveStoresFilter(new ShoppingList()).filterRecords(new SupplyRecords());
		assertSize(0, filteredRecords);
	}

	@Test public void oneOutOfOne() {
		SupplyRecords records = new SupplyRecords(
				newCard(vendor1dollarA, "One", "0.15"), 
				newCard(vendor1dollarC, "One", "0.16")
		); 
		validateResults(records, vendor1dollarA);
	}

	@Test public void tie() {
		SupplyRecords records = new SupplyRecords(
				newCard(vendor1dollarA, "One", "0.15"), 
				newCard(vendor1dollarC, "One", "0.15")
		); 
		validateResults(records, vendor1dollarA, vendor1dollarC);
	}

	@Test public void oneOutOfMany() {
		SupplyRecords records = new SupplyRecords(
				newCard(vendor1dollarA, "One", "0.15"), 
				newCard(vendor1dollarA, "Two", "0.15"), 
				newCard(vendor1dollarC, "Two", "0.25")
		); 
		validateResults(records, vendor1dollarA);
	}

	@Test public void oneLessThanMany() {
		SupplyRecords records = new SupplyRecords(
				newCard(vendor1dollarA, "One", "0.15"), 
				newCard(vendor1dollarA, "Two", "0.25"), 
				newCard(vendor1dollarC, "Two", "0.15")
		); 
		validateResults(records, vendor1dollarA, vendor1dollarC);
	}

	@Test public void tieWithSingleItemHavingShipping() {
		SupplyRecords records = new SupplyRecords(
				newCard(vendor1dollarA, "One", "0.15"), 
				newCard(vendor1dollarA, "Two", "0.15"), 
				newCard(vendor2dollarB, "Two", "0.15")
		); 
		validateResults(records, vendor1dollarA);
	}

	@Test public void tieWithDoubleItemHavingShipping() {
		SupplyRecords records = new SupplyRecords(
				newCard(vendor2dollarB, "One", "0.15"), 
				newCard(vendor2dollarB, "Two", "0.15"), 
				newCard(vendor1dollarA, "Two", "0.15")
		); 
		validateResults(records, vendor1dollarA, vendor2dollarB);
	}

	@Test public void multipleTie() {
		SupplyRecords records = new SupplyRecords(
				newCard(vendor1dollarA, "One", "0.15"), 
				newCard(vendor1dollarA, "Two", "0.25"), 
				newCard(vendor1dollarC, "One", "0.15"),
				newCard(vendor1dollarC, "Two", "0.25")
		); 
		validateResults(records, vendor1dollarA, vendor1dollarC);
	}

	@Test public void multipleTieWithShipping() {
		SupplyRecords records = new SupplyRecords(
				newCard(vendor2dollarB, "One", "0.15"), 
				newCard(vendor2dollarB, "Two", "0.25"), 
				newCard(vendor1dollarC, "One", "0.15"),
				newCard(vendor1dollarC, "Two", "0.25")
		); 
		validateResults(records, vendor1dollarC);
	}

	@Test public void multipleLessButShippingMore() {
		SupplyRecords records = new SupplyRecords(
				newCard(vendor1dollarA, "One", "1.45"), 
				newCard(vendor1dollarA, "Two", "0.45"), 
				newCard(vendor2dollarB, "One", "0.35"),
				newCard(vendor2dollarB, "Two", "0.35")
		); 
		validateResults(records, vendor1dollarA, vendor2dollarB);
	}

	@Test public void multipleMoreButShippingLess() {
		SupplyRecords records = new SupplyRecords(
				newCard(vendor2dollarB, "One", "0.35"), 
				newCard(vendor2dollarB, "Two", "0.35"), 
				newCard(vendor1dollarA, "One", "1.45"),
				newCard(vendor1dollarA, "Two", "0.45")
		); 
		validateResults(records, vendor1dollarA, vendor2dollarB);
	}

	@Test public void tieWithDifferentPricesAndShipping() {
		SupplyRecords records = new SupplyRecords(
				newCard(vendor1dollarA, "One", "0.35"), 
				newCard(vendor1dollarA, "Two", "0.35"), 
				newCard(incrementingVendor, "Two", "0.36")
		); 
		validateResults(records, vendor1dollarA, incrementingVendor);
	}

	@Test public void tieWithIncrementingShipping() {
		SupplyRecords records = new SupplyRecords(
				newCard(vendor1dollarA, "One", "0.35"), 
				newCard(vendor1dollarA, "Two", "0.35"), 
				newCard(incrementingVendor, "One", "0.35"),
				newCard(incrementingVendor, "Two", "0.35")
		); 
		validateResults(records, vendor1dollarA, incrementingVendor);
	}

	@Test public void moreWithIncrementingShipping() {
		SupplyRecords records = new SupplyRecords(
				newCard(vendor1dollarA, "One", "0.35"), 
				newCard(vendor1dollarA, "Two", "0.35"), 
				newCard(incrementingVendor, "One", "0.36"),
				newCard(incrementingVendor, "Two", "0.35")
		); 
		// not sure if this is right.  Gotta think it through.
		validateResults(records, vendor1dollarA, incrementingVendor);
	}

//************************************	
	private void validateResults(SupplyRecords records, Vendor...vendors) {
		SupplyRecords filteredRecords = new EliminateMoreExpensiveStoresFilter(list).filterRecords(records);
		SupplyRecords expectedRecords = new SupplyRecords();
		Set<Vendor> vendorList = new HashSet<Vendor>(Arrays.asList(vendors));
		for (SupplyRecord record : filteredRecords)
			if (vendorList.contains(record.getVendor()))
				expectedRecords.add(record);
		assertEquals(expectedRecords.size(), filteredRecords.size());
		assertEquals(expectedRecords, filteredRecords);
	}

	private SupplyRecord newCard(Vendor vendor, String name, String cost) {
		return newCard(vendor, name, cost, 99);
	}

	private SupplyRecord newCard(Vendor vendor, String name, String cost, int count) {
		return new StandardSupplyRecord(name,vendor,"Set","Unplayed",count,cost);
	}
}