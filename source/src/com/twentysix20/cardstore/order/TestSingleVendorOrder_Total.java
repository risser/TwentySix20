package com.twentysix20.cardstore.order;

import static junit.framework.Assert.assertEquals;

import org.junit.Test;

import com.twentysix20.cardstore.postage.FlatRatePostage;
import com.twentysix20.cardstore.postage.FreePostage;
import com.twentysix20.cardstore.supplyrecord.StandardSupplyRecord;
import com.twentysix20.cardstore.vendor.Vendor;


public class TestSingleVendorOrder_Total {
	static private Vendor freeVendor = new Vendor("shopA",FreePostage.FREE_POSTAGE);
	static private Vendor twoBuckVendor = new Vendor("shopB",new FlatRatePostage(200));

	@Test public void totalEmptyOrder() {
		Order order = new SingleVendorOrder(freeVendor);
		assertEquals(0, order.total());
		assertEquals(0, order.totalWithoutPostage());
	}

	@Test public void singleItemOrder() {
		StandardSupplyRecord record1 = new StandardSupplyRecord("itemA", freeVendor, "set", "mint", 1, "1.00");
		Order order = new SingleVendorOrder(freeVendor,record1);
		assertEquals(100, order.total());
		assertEquals(100, order.totalWithoutPostage());
	}

	@Test public void singleItemOrderWithPostage() {
		StandardSupplyRecord record1 = new StandardSupplyRecord("itemA", twoBuckVendor, "set", "mint", 1, "1.00");
		Order order = new SingleVendorOrder(twoBuckVendor,record1);
		assertEquals(300, order.total());
		assertEquals(100, order.totalWithoutPostage());
	}

	@Test public void multipleItemOrder() {
		StandardSupplyRecord record1 = new StandardSupplyRecord("itemA", freeVendor, "set", "mint", 1, "1.00");
		StandardSupplyRecord record2 = new StandardSupplyRecord("itemB", freeVendor, "set", "mint", 1, "1.50");
		Order order = new SingleVendorOrder(freeVendor,record1,record2);

		assertEquals(250, order.total());
	}

	@Test public void multipleItemOrder_PostageSame() {
		StandardSupplyRecord record1 = new StandardSupplyRecord("itemA", twoBuckVendor, "set", "mint", 1, "1.00");
		StandardSupplyRecord record2 = new StandardSupplyRecord("itemB", twoBuckVendor, "set", "mint", 1, "1.50");
		Order order = new SingleVendorOrder(twoBuckVendor,record1,record2);

		assertEquals(450, order.total());
	}

	@Test public void ordersOfSameItem() {
		StandardSupplyRecord record1 = new StandardSupplyRecord("itemA", twoBuckVendor, "set", "mint", 10, "1.00");
		Order order = new SingleVendorOrder(twoBuckVendor,record1,record1,record1);

		assertEquals(500, order.total());
	}
}
