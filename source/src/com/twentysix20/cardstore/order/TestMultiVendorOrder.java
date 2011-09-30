package com.twentysix20.cardstore.order;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;

import org.junit.Test;

import com.twentysix20.cardstore.postage.FlatRatePostage;
import com.twentysix20.cardstore.postage.FreePostage;
import com.twentysix20.cardstore.supplyrecord.StandardSupplyRecord;
import com.twentysix20.cardstore.vendor.Vendor;


public class TestMultiVendorOrder {
	static private Vendor freeVendor = new Vendor("shopA",FreePostage.FREE_POSTAGE);
	static private Vendor twoBuckVendor = new Vendor("shopB",new FlatRatePostage(200));
	static private Vendor threeBuckVendor = new Vendor("shopC",new FlatRatePostage(300));

	@Test public void emptyOrder() {
		Order order = new MultiVendorOrder();
		assertEquals(0, order.total());
		assertEquals(0, order.numberOfCards());
		assertEquals(0, order.numberOrderedForCardName("itemA"));
	}

	@Test public void emptyOrderStillNoPostage() {
		Order order = new MultiVendorOrder();
		assertEquals(0, order.total());
	}

	@Test public void singleItemOrder() {
		StandardSupplyRecord record1 = new StandardSupplyRecord("itemA", freeVendor, "set", "mint", 1, "1.00");
		Order order = new MultiVendorOrder(record1);

		StandardSupplyRecord record2 = new StandardSupplyRecord("itemA", freeVendor, "set", "mint", 1, "1.00");
		Order expectedOrder = new MultiVendorOrder(record2);

		assertEquals(100, order.total());
		assertEquals(1, order.numberOfCards());
		assertEquals(1, order.numberOrderedForCardName("itemA"));
		assertEquals(expectedOrder, order);
	}

	@Test public void singleItemOrderWithPostage() {
		StandardSupplyRecord record1 = new StandardSupplyRecord("itemA", twoBuckVendor, "set", "mint", 1, "1.00");
		Order order = new MultiVendorOrder(record1);
		assertEquals(300, order.total());
	}

	@Test public void multipleItemOrder() {
		StandardSupplyRecord record1 = new StandardSupplyRecord("itemA", freeVendor, "set", "mint", 1, "1.00");
		StandardSupplyRecord record2 = new StandardSupplyRecord("itemB", freeVendor, "set", "mint", 1, "1.50");
		Order order = new MultiVendorOrder(record1,record2);

		StandardSupplyRecord record3 = new StandardSupplyRecord("itemA", freeVendor, "set", "mint", 1, "1.00");
		StandardSupplyRecord record4 = new StandardSupplyRecord("itemB", freeVendor, "set", "mint", 1, "1.50");
		Order expectedOrder = new MultiVendorOrder(record3,record4);

		assertEquals(250, order.total());
		assertEquals(2, order.numberOfCards());
		assertEquals(1, order.numberOrderedForCardName("itemA"));
		assertEquals(1, order.numberOrderedForCardName("itemB"));
		assertEquals(expectedOrder, order);
	}

	@Test public void ordersOfSameItem() {
		StandardSupplyRecord record1 = new StandardSupplyRecord("itemA", freeVendor, "set", "mint", 10, "1.00");
		Order order = new MultiVendorOrder(record1,record1,record1);
		Order expectedOrder = new MultiVendorOrder();

		StandardSupplyRecord record3 = new StandardSupplyRecord("itemA", freeVendor, "set", "mint", 10, "1.00");
		expectedOrder.add(record3, 3);

		assertEquals(300, order.total());
		assertEquals(3, order.numberOfCards());
		assertEquals(3, order.numberOrderedForCardName("itemA"));
		assertEquals(expectedOrder, order);
	}

	@Test(expected=IndexOutOfBoundsException.class) public void orderTooManyOfSameItem_OneAtaTime() {
		StandardSupplyRecord record1 = new StandardSupplyRecord("itemA", freeVendor, "set", "mint", 2, "1.00");
		new MultiVendorOrder(record1,record1,record1);
	}

	@Test(expected=IndexOutOfBoundsException.class) public void orderTooManyOfSameItem_ManyAtOnce() {
		StandardSupplyRecord record1 = new StandardSupplyRecord("itemA", freeVendor, "set", "mint", 5, "1.00");
		Order order = new MultiVendorOrder();
		order.add(record1, 10);
	}

	@Test public void removeOne() {
		StandardSupplyRecord record1 = new StandardSupplyRecord("itemA", freeVendor, "set", "mint", 10, "1.00");
		StandardSupplyRecord record3 = new StandardSupplyRecord("itemA", freeVendor, "set", "mint", 10, "1.00");

		Order order = new MultiVendorOrder(record1,record1,record1);
		order.remove(record1,1);
		
		Order expectedOrder = new MultiVendorOrder();
		expectedOrder.add(record3, 2);

		assertEquals(200, order.total());
		assertEquals(2, order.numberOfCards());
		assertEquals(2, order.numberOrderedForCardName("itemA"));
		assertEquals(expectedOrder, order);
	}

	@Test public void numberOrderedFromName_One() {
		StandardSupplyRecord record1 = new StandardSupplyRecord("itemA", freeVendor, "set", "mint", 10, "1.00");

		Order order = new MultiVendorOrder(record1);
		
		assertEquals(1, order.numberOrderedForCardName("itemA"));
	}

	@Test public void numberOrderedFromName_None() {
		StandardSupplyRecord record1 = new StandardSupplyRecord("itemA", freeVendor, "set", "mint", 10, "1.00");

		Order order = new MultiVendorOrder(record1);
		
		assertEquals(0, order.numberOrderedForCardName("itemB"));
	}

	@Test public void numberOrderedFromName_MoreThan1_SameVendor() {
		StandardSupplyRecord record1 = new StandardSupplyRecord("itemA", freeVendor, "set", "mint", 10, "1.00");

		Order order = new MultiVendorOrder(record1,record1,record1,record1);
		
		assertEquals(4, order.numberOrderedForCardName("itemA"));
	}

	@Test public void numberOrderedFromName_MoreThan1_DifferentVendors() {
		StandardSupplyRecord record1 = new StandardSupplyRecord("itemA", freeVendor, "set", "mint", 10, "1.00");
		StandardSupplyRecord record2 = new StandardSupplyRecord("itemA", twoBuckVendor, "set", "mint", 4, "1.00");

		Order order = new MultiVendorOrder(record1,record1,record2,record2);
		
		assertEquals(4, order.numberOrderedForCardName("itemA"));
	}

	@Test public void removeAndAddSameKind() {
		StandardSupplyRecord record1 = new StandardSupplyRecord("itemA", freeVendor, "set", "mint", 10, "1.00");

		StandardSupplyRecord record3 = new StandardSupplyRecord("itemA", freeVendor, "set", "mint", 10, "1.00");
		Order order = new MultiVendorOrder(record1,record1,record1);
		order.remove(record3,1);
		
		Order expectedOrder = new MultiVendorOrder(record3,record1);

		assertEquals(200, order.total());
		assertEquals(expectedOrder, order);
	}

	@Test public void removeAll() {
		StandardSupplyRecord record1 = new StandardSupplyRecord("itemA", freeVendor, "set", "mint", 10, "1.00");

		Order order = new MultiVendorOrder(record1,record1,record1);
		order.remove(record1,3);
		
		Order expectedOrder = new MultiVendorOrder();

		assertEquals(0, order.total());
		assertEquals(0, order.numberOfCards());
		assertEquals(expectedOrder, order);
	}

	@Test(expected=NullPointerException.class) public void removeNonExistant() {
		StandardSupplyRecord record1 = new StandardSupplyRecord("itemA", freeVendor, "set", "mint", 10, "1.00");
		StandardSupplyRecord record2 = new StandardSupplyRecord("itemB", freeVendor, "set", "mint", 10, "1.00");

		Order order = new MultiVendorOrder(record1,record1,record1);
		order.remove(record2,1);
	}

	@Test(expected=NullPointerException.class) public void removeTooMany_OneAtaTime() {
		StandardSupplyRecord record1 = new StandardSupplyRecord("itemA", freeVendor, "set", "mint", 10, "1.00");

		Order order = new MultiVendorOrder(record1,record1);
		order.remove(record1,1);
		order.remove(record1,1);
		order.remove(record1,1);
	}

	@Test(expected=IndexOutOfBoundsException.class) public void removeTooMany_AllAtOnce() {
		StandardSupplyRecord record1 = new StandardSupplyRecord("itemA", freeVendor, "set", "mint", 10, "1.00");

		Order order = new MultiVendorOrder(record1,record1,record1);
		order.remove(record1,4);
	}

	@Test public void differentVendorsPostage() {
		StandardSupplyRecord record1 = new StandardSupplyRecord("itemA", threeBuckVendor, "set", "mint", 10, "1.00");
		StandardSupplyRecord record2 = new StandardSupplyRecord("itemA", twoBuckVendor, "set", "mint", 4, "1.00");

		Order order = new MultiVendorOrder(record1,record1,record2,record2);
		
		assertEquals(900, order.total());
	}

	@Test public void createFromAnotherMultiVendorOrder() {
		StandardSupplyRecord record1 = new StandardSupplyRecord("itemA", threeBuckVendor, "set", "mint", 10, "1.00");
		StandardSupplyRecord record2 = new StandardSupplyRecord("itemA", twoBuckVendor, "set", "mint", 4, "1.00");
		StandardSupplyRecord record3 = new StandardSupplyRecord("itemB", freeVendor, "set", "mint", 10, "1.00");

		MultiVendorOrder order = new MultiVendorOrder(record1, record1, record1,record2,record3);
		MultiVendorOrder newOrder = new MultiVendorOrder(order);

		assertEquals(1000, newOrder.total());
		assertEquals(5, newOrder.numberOfCards());
		assertEquals(3, newOrder.numberOfCardInstances());
		assertFalse(newOrder == order);
		assertFalse(newOrder.getVendorOrder(freeVendor) == order.getVendorOrder(freeVendor));
		assertFalse(newOrder.getVendorOrder(twoBuckVendor) == order.getVendorOrder(twoBuckVendor));
		assertFalse(newOrder.getVendorOrder(threeBuckVendor) == order.getVendorOrder(threeBuckVendor));
	}
}