package com.twentysix20.cardstore.order;

import static junit.framework.Assert.assertEquals;

import java.util.HashSet;

import org.junit.Test;

import com.twentysix20.cardstore.postage.FlatRatePostage;
import com.twentysix20.cardstore.postage.FreePostage;
import com.twentysix20.cardstore.supplyrecord.StandardSupplyRecord;
import com.twentysix20.cardstore.supplyrecord.SupplyRecord;
import com.twentysix20.cardstore.vendor.Vendor;


public class TestSingleVendorOrder_addRemove {
	static private Vendor freeVendor = new Vendor("shopA",FreePostage.FREE_POSTAGE);
	static private Vendor twoBuckVendor = new Vendor("shopB",new FlatRatePostage(200));

	@Test public void testGetVendorOrder() {
		Order order = new SingleVendorOrder(freeVendor);

		assertEquals(order, order.getVendorOrder(freeVendor));
		assertEquals(new HashSet<SupplyRecord>(),order.recordsIncludedInOrder());
	}

	@Test(expected=IllegalArgumentException.class) public void testGetVendorOrder_WrongVendor() {
		Order order = new SingleVendorOrder(freeVendor);

		order.getVendorOrder(twoBuckVendor);
	}

	@Test(expected=IllegalArgumentException.class) public void testNewOrder_WrongVendor() {
		SingleVendorOrder order = new SingleVendorOrder(freeVendor);
		StandardSupplyRecord record1 = new StandardSupplyRecord("itemA", twoBuckVendor, "set", "mint", 1, "1.00");
		order.add(record1,1);
	}
	
	@Test(expected=NullPointerException.class) public void testNullVendorOnConstructor() {
		Vendor nullVendor = null;
		new SingleVendorOrder(nullVendor);
	}
	
	@Test(expected=NullPointerException.class) public void testNullOrderOnConstructor() {
		SingleVendorOrder nullOrder = null;
		new SingleVendorOrder(nullOrder);
	}

	@Test public void equalityOfSingleItemOrder() {
		StandardSupplyRecord record1 = new StandardSupplyRecord("itemA", freeVendor, "set", "mint", 1, "1.00");
		Order order = new SingleVendorOrder(freeVendor);
		order.add(record1,1);

		StandardSupplyRecord record2 = new StandardSupplyRecord("itemA", freeVendor, "set", "mint", 1, "1.00");
		Order expectedOrder = new SingleVendorOrder(freeVendor);
		expectedOrder.add(record2,1);

		HashSet<SupplyRecord> expectedSet = new HashSet<SupplyRecord>();
		expectedSet.add(record2);

		assertEquals(expectedOrder, order);
		assertEquals(expectedSet,order.recordsIncludedInOrder());
	}

	@Test public void multipleItemOrder() {
		StandardSupplyRecord record1 = new StandardSupplyRecord("itemA", freeVendor, "set", "mint", 1, "1.00");
		StandardSupplyRecord record2 = new StandardSupplyRecord("itemB", freeVendor, "set", "mint", 1, "1.50");
		Order order = new SingleVendorOrder(freeVendor,record1,record2);

		StandardSupplyRecord record3 = new StandardSupplyRecord("itemA", freeVendor, "set", "mint", 1, "1.00");
		StandardSupplyRecord record4 = new StandardSupplyRecord("itemB", freeVendor, "set", "mint", 1, "1.50");
		Order expectedOrder = new SingleVendorOrder(freeVendor,record3,record4);

		HashSet<SupplyRecord> expectedSet = new HashSet<SupplyRecord>();
		expectedSet.add(record3);
		expectedSet.add(record4);

		assertEquals(expectedOrder, order);
		assertEquals(expectedSet,order.recordsIncludedInOrder());
	}

	@Test public void testNewOrderFromOrder() {
		StandardSupplyRecord record1 = new StandardSupplyRecord("itemA", twoBuckVendor, "set", "mint", 11, "1.00");
		StandardSupplyRecord record2 = new StandardSupplyRecord("itemB", twoBuckVendor, "set", "mint", 11, "1.50");
		SingleVendorOrder order = new SingleVendorOrder(record1,record1,record1,record2);

		SingleVendorOrder newOrder = new SingleVendorOrder(order);

		assertEquals(650, newOrder.total());
		assertEquals(450, newOrder.totalWithoutPostage());
		assertEquals(4, newOrder.numberOfCards());
		assertEquals(2, newOrder.numberOfCardInstances());
	}

	@Test public void ordersOfSameItem() {
		StandardSupplyRecord record1 = new StandardSupplyRecord("itemA", freeVendor, "set", "mint", 10, "1.00");
		Order order = new SingleVendorOrder(freeVendor,record1,record1,record1);

		StandardSupplyRecord record3 = new StandardSupplyRecord("itemA", freeVendor, "set", "mint", 10, "1.00");
		Order expectedOrder = new SingleVendorOrder(freeVendor);
		expectedOrder.add(record3, 3);

		HashSet<SupplyRecord> expectedSet = new HashSet<SupplyRecord>();
		expectedSet.add(record3);

		assertEquals(expectedOrder, order);
		assertEquals(expectedSet,order.recordsIncludedInOrder());
	}

	@Test(expected=IndexOutOfBoundsException.class) public void orderTooManyOfSameItem_OneAtaTime() {
		StandardSupplyRecord record1 = new StandardSupplyRecord("itemA", freeVendor, "set", "mint", 2, "1.00");
		new SingleVendorOrder(freeVendor,record1,record1,record1);
	}

	@Test(expected=IndexOutOfBoundsException.class) public void orderTooManyOfSameItem_ManyAtOnce() {
		StandardSupplyRecord record1 = new StandardSupplyRecord("itemA", freeVendor, "set", "mint", 5, "1.00");
		Order order = new SingleVendorOrder(freeVendor);
		order.add(record1, 10);
	}

	@Test public void removeOne() {
		StandardSupplyRecord record1 = new StandardSupplyRecord("itemA", freeVendor, "set", "mint", 10, "1.00");
		StandardSupplyRecord record3 = new StandardSupplyRecord("itemA", freeVendor, "set", "mint", 10, "1.00");

		Order order = new SingleVendorOrder(freeVendor,record1,record1,record1);
		order.remove(record1,1);
		
		Order expectedOrder = new SingleVendorOrder(freeVendor);
		expectedOrder.add(record3, 2);

		assertEquals(expectedOrder, order);
	}

	@Test public void removeAndAddSameKind() {
		StandardSupplyRecord record1 = new StandardSupplyRecord("itemA", freeVendor, "set", "mint", 10, "1.00");
		StandardSupplyRecord record3 = new StandardSupplyRecord("itemA", freeVendor, "set", "mint", 10, "1.00");
		Order order = new SingleVendorOrder(freeVendor,record1,record1,record1);
		order.remove(record3,1);
		
		Order expectedOrder = new SingleVendorOrder(freeVendor,record3,record1);

		assertEquals(expectedOrder, order);
	}

	@Test public void removeSeveral() {
		StandardSupplyRecord record1 = new StandardSupplyRecord("itemA", freeVendor, "set", "mint", 10, "1.00");
		StandardSupplyRecord record3 = new StandardSupplyRecord("itemA", freeVendor, "set", "mint", 10, "1.00");
		Order order = new SingleVendorOrder(freeVendor,record1,record1,record1,record1,record1);
		order.remove(record3,3);
		
		Order expectedOrder = new SingleVendorOrder(freeVendor,record1,record1);

		assertEquals(expectedOrder, order);
	}

	@Test public void removeAll() {
		StandardSupplyRecord record1 = new StandardSupplyRecord("itemA", freeVendor, "set", "mint", 10, "1.00");

		Order order = new SingleVendorOrder(freeVendor,record1,record1,record1);
		order.remove(record1,3);
		
		Order expectedOrder = new SingleVendorOrder(freeVendor);

		assertEquals(expectedOrder, order);
	}

	@Test(expected=NullPointerException.class) public void removeNonExistant() {
		StandardSupplyRecord record1 = new StandardSupplyRecord("itemA", freeVendor, "set", "mint", 10, "1.00");
		StandardSupplyRecord record2 = new StandardSupplyRecord("itemB", freeVendor, "set", "mint", 10, "1.00");

		Order order = new SingleVendorOrder(freeVendor,record1,record1,record1);
		order.remove(record2,1);
	}

	@Test(expected=NullPointerException.class) public void removeTooMany_OneAtaTime() {
		StandardSupplyRecord record1 = new StandardSupplyRecord("itemA", freeVendor, "set", "mint", 10, "1.00");

		Order order = new SingleVendorOrder(freeVendor,record1,record1);
		order.remove(record1,1);
		order.remove(record1,1);
		order.remove(record1,1);
	}

	@Test(expected=IndexOutOfBoundsException.class) public void removeTooMany_AllAtOnce() {
		StandardSupplyRecord record1 = new StandardSupplyRecord("itemA", freeVendor, "set", "mint", 10, "1.00");

		Order order = new SingleVendorOrder(freeVendor,record1,record1,record1);
		order.remove(record1,4);
	}
}
