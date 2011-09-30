package com.twentysix20.cardstore.postage;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.twentysix20.cardstore.order.Order;
import com.twentysix20.cardstore.order.SingleVendorOrder;
import com.twentysix20.cardstore.supplyrecord.StandardSupplyRecord;
import com.twentysix20.cardstore.vendor.Vendor;


public class TestBasePlusPerCardForEachInstancePostage {
	static private Vendor vendor1 = new Vendor("shopA",new FlatRatePostage(100));

	@Test public void testOneItemOneCount(){
		Postage postage = new BasePlusPerCardForEachInstancePostage(254, 5, 4);
		StandardSupplyRecord record1 = new StandardSupplyRecord("Example Card",vendor1,"Set","Unplayed",33,"3.35");
		Order order = new SingleVendorOrder(record1);

		int result = postage.calculate(order);
		assertEquals(254,result);
	}

	@Test public void testOneItemTwoCount(){
		Postage postage = new BasePlusPerCardForEachInstancePostage(254, 5, 4);
		StandardSupplyRecord record1 = new StandardSupplyRecord("Example Card",vendor1,"Set","Unplayed",33,"3.35");
		Order order = new SingleVendorOrder(record1, record1);

		int result = postage.calculate(order);
		assertEquals(254,result);
	}

	@Test public void testOneItemFourCount(){
		Postage postage = new BasePlusPerCardForEachInstancePostage(254, 5, 4);
		StandardSupplyRecord record1 = new StandardSupplyRecord("Example Card",vendor1,"Set","Unplayed",33,"3.35");
		Order order = new SingleVendorOrder(record1);
		order.add(record1, 3);

		int result = postage.calculate(order);
		assertEquals(254,result);
	}

	@Test public void testOneItemFiveCount(){
		Postage postage = new BasePlusPerCardForEachInstancePostage(254, 5, 4);
		StandardSupplyRecord record1 = new StandardSupplyRecord("Example Card",vendor1,"Set","Unplayed",33,"3.35");
		Order order = new SingleVendorOrder(record1);
		order.add(record1, 4);

		int result = postage.calculate(order);
		assertEquals(259,result);
	}

	@Test public void testTwoItemsOneEach(){
		Postage postage = new BasePlusPerCardForEachInstancePostage(254, 5, 4);
		StandardSupplyRecord record1 = new StandardSupplyRecord("Example Card1",vendor1,"Set","Unplayed",33,"3.35");
		StandardSupplyRecord record2 = new StandardSupplyRecord("Example Card2",vendor1,"Set","Unplayed",33,"3.35");
		Order order = new SingleVendorOrder(record1,record2);

		int result = postage.calculate(order);
		assertEquals(259,result);
	}

	@Test public void testTwoItemsFourEach(){
		Postage postage = new BasePlusPerCardForEachInstancePostage(254, 5, 4);
		StandardSupplyRecord record1 = new StandardSupplyRecord("Example Card1",vendor1,"Set","Unplayed",33,"3.35");
		StandardSupplyRecord record2 = new StandardSupplyRecord("Example Card2",vendor1,"Set","Unplayed",33,"3.35");
		Order order = new SingleVendorOrder(record1,record2);
		order.add(record1,3);
		order.add(record2,3);

		int result = postage.calculate(order);
		assertEquals(259,result);
	}

	@Test public void testTwoItemsFourAndFive(){
		Postage postage = new BasePlusPerCardForEachInstancePostage(254, 5, 4);
		StandardSupplyRecord record1 = new StandardSupplyRecord("Example Card1",vendor1,"Set","Unplayed",33,"3.35");
		StandardSupplyRecord record2 = new StandardSupplyRecord("Example Card2",vendor1,"Set","Unplayed",33,"3.35");
		Order order = new SingleVendorOrder(record1,record2);
		order.add(record1,3);
		order.add(record2,4);

		int result = postage.calculate(order);
		assertEquals(264,result);
	}

	@Test public void testTwoItemsFiveAndFour(){
		Postage postage = new BasePlusPerCardForEachInstancePostage(254, 5, 4);
		StandardSupplyRecord record1 = new StandardSupplyRecord("Example Card1",vendor1,"Set","Unplayed",33,"3.35");
		StandardSupplyRecord record2 = new StandardSupplyRecord("Example Card2",vendor1,"Set","Unplayed",33,"3.35");
		Order order = new SingleVendorOrder(record1,record2);
		order.add(record1,4);
		order.add(record2,3);

		int result = postage.calculate(order);
		assertEquals(264,result);
	}

	@Test public void testTwoItemsFiveAndFive(){
		Postage postage = new BasePlusPerCardForEachInstancePostage(254, 5, 4);
		StandardSupplyRecord record1 = new StandardSupplyRecord("Example Card1",vendor1,"Set","Unplayed",33,"3.35");
		StandardSupplyRecord record2 = new StandardSupplyRecord("Example Card2",vendor1,"Set","Unplayed",33,"3.35");
		Order order = new SingleVendorOrder(record1,record2);
		order.add(record1,4);
		order.add(record2,5);

		int result = postage.calculate(order);
		assertEquals(269,result);
	}

	@Test public void testLots(){
		Postage postage = new BasePlusPerCardForEachInstancePostage(254, 5, 4);
		StandardSupplyRecord record1 = new StandardSupplyRecord("Example Card1",vendor1,"Set","Unplayed",33,"3.35");
		StandardSupplyRecord record2 = new StandardSupplyRecord("Example Card2",vendor1,"Set","Unplayed",33,"3.35");
		StandardSupplyRecord record3 = new StandardSupplyRecord("Example Card3",vendor1,"Set","Unplayed",33,"3.35");
		Order order = new SingleVendorOrder(record1,record2,record3);
		order.add(record1,2);
		order.add(record2,5);
		order.add(record3,13);

		int result = postage.calculate(order);
		assertEquals(284,result);
	}

	@Test public void testLotsPlusOne(){
		Postage postage = new BasePlusPerCardForEachInstancePostage(254, 5, 4);
		StandardSupplyRecord record1 = new StandardSupplyRecord("Example Card1",vendor1,"Set","Unplayed",33,"3.35");
		StandardSupplyRecord record2 = new StandardSupplyRecord("Example Card2",vendor1,"Set","Unplayed",33,"3.35");
		StandardSupplyRecord record3 = new StandardSupplyRecord("Example Card3",vendor1,"Set","Unplayed",33,"3.35");
		Order order = new SingleVendorOrder(record1,record2,record3);
		order.add(record1,3);
		order.add(record2,6);
		order.add(record3,14);

		int result = postage.calculate(order);
		assertEquals(284,result);
	}

	@Test public void testLotsPlusSome(){
		Postage postage = new BasePlusPerCardForEachInstancePostage(254, 5, 4);
		StandardSupplyRecord record1 = new StandardSupplyRecord("Example Card1",vendor1,"Set","Unplayed",33,"3.35");
		StandardSupplyRecord record2 = new StandardSupplyRecord("Example Card2",vendor1,"Set","Unplayed",33,"3.35");
		StandardSupplyRecord record3 = new StandardSupplyRecord("Example Card3",vendor1,"Set","Unplayed",33,"3.35");
		Order order = new SingleVendorOrder(record1,record2,record3);
		order.add(record1,4);
		order.add(record2,6);
		order.add(record3,16);

		int result = postage.calculate(order);
		assertEquals(294,result);
	}
}
