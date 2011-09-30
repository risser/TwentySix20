package com.twentysix20.cardstore.order;

import static junit.framework.Assert.assertEquals;

import org.junit.Test;

import com.twentysix20.cardstore.postage.FreePostage;
import com.twentysix20.cardstore.supplyrecord.StandardSupplyRecord;
import com.twentysix20.cardstore.vendor.Vendor;


public class TestSingleVendorOrder_NumberOfCards {
	static private Vendor freeVendor = new Vendor("shopA",FreePostage.FREE_POSTAGE);

	@Test public void totalEmptyOrder() {
		Order order = new SingleVendorOrder(freeVendor);
		assertEquals(0, order.numberOfCardInstances());
		assertEquals(0, order.numberOfCards());
	}

	@Test public void singleItemOrder() {
		StandardSupplyRecord record1 = new StandardSupplyRecord("itemA", freeVendor, "set", "mint", 1, "1.00");
		Order order = new SingleVendorOrder(freeVendor,record1);

		assertEquals(1, order.numberOfCards());
		assertEquals(1, order.numberOfCardInstances());
		assertEquals(1, order.numberOrderedForCardName("itemA"));
	}

	@Test public void multipleItemOrder() {
		StandardSupplyRecord record1 = new StandardSupplyRecord("itemA", freeVendor, "set", "mint", 1, "1.00");
		StandardSupplyRecord record2 = new StandardSupplyRecord("itemB", freeVendor, "set", "mint", 1, "1.50");
		Order order = new SingleVendorOrder(freeVendor,record1,record2);

		assertEquals(2, order.numberOfCards());
		assertEquals(2, order.numberOfCardInstances());
		assertEquals(1, order.numberOrderedForCardName("itemA"));
		assertEquals(1, order.numberOrderedForCardName("itemB"));
	}

	@Test public void ordersOfSameItem() {
		StandardSupplyRecord record1 = new StandardSupplyRecord("itemA", freeVendor, "set", "mint", 10, "1.00");
		Order order = new SingleVendorOrder(freeVendor,record1,record1,record1);

		assertEquals(3, order.numberOfCards());
		assertEquals(1, order.numberOfCardInstances());
		assertEquals(3, order.numberOrderedForCardName("itemA"));
	}

	@Test public void numberOrderedFromName_None() {
		StandardSupplyRecord record1 = new StandardSupplyRecord("itemA", freeVendor, "set", "mint", 10, "1.00");
		Order order = new SingleVendorOrder(freeVendor,record1);
		
		assertEquals(0, order.numberOrderedForCardName("itemB"));
	}
}
