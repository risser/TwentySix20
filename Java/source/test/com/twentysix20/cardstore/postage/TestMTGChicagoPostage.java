package com.twentysix20.cardstore.postage;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.twentysix20.cardstore.order.Order;
import com.twentysix20.cardstore.order.SingleVendorOrder;
import com.twentysix20.cardstore.supplyrecord.StandardSupplyRecord;
import com.twentysix20.cardstore.vendor.Vendor;


public class TestMTGChicagoPostage {
	static private Vendor vendor1 = new Vendor("shopA",new MTGChicagoPostage());

	@Test public void testOneItemOneCount(){
		Postage postage = new MTGChicagoPostage();
		StandardSupplyRecord record1 = new StandardSupplyRecord("Example Card",vendor1,"Set","Unplayed",50,"3.35");
		Order order = new SingleVendorOrder(record1);

		int result = postage.calculate(order);
		assertEquals(188,result);
	}

	@Test public void testOneItemTwoCount(){
		int result = testItems(2);
		assertEquals(190,result);
	}

	@Test public void testOneAndOne(){
		Postage postage = new MTGChicagoPostage();
		StandardSupplyRecord record1 = new StandardSupplyRecord("Example Card1",vendor1,"Set","Unplayed",50,"3.35");
		StandardSupplyRecord record2 = new StandardSupplyRecord("Example Card2",vendor1,"Set","Unplayed",50,"3.35");
		Order order = new SingleVendorOrder(record1, record2);

		int result = postage.calculate(order);
		assertEquals(190,result);
	}
/*
	@Test public void test3Items(){
		int result = testItems(3);
		assertEquals(193,result);
	}

	@Test public void test4Items(){
		int result = testItems(4);
		assertEquals(196,result);
	}

	@Test public void test5Items(){
		int result = testItems(5);
		assertEquals(198,result);
	}

	@Test public void test6Items(){
		int result = testItems(6);
		assertEquals(201,result);
	}

	@Test public void test7Items(){
		int result = testItems(7);
		assertEquals(204,result);
	}

	@Test public void test8Items(){
		int result = testItems(8);
		assertEquals(207,result);
	}

	@Test public void test9Items(){
		int result = testItems(9);
		assertEquals(209,result);
	}

	@Test public void test10Items(){
		int result = testItems(10);
		assertEquals(212,result);
	}

	@Test public void test37Items(){
		int result = testItems(38);
		assertEquals(285,result);
	}

	@Test public void test38Items(){
		int result = testItems(38);
		assertEquals(288,result);
	}

	@Test public void test39Items(){
		int result = testItems(39);
		assertEquals(290,result);
	}

	@Test public void test40Items(){
		int result = testItems(40);
		assertEquals(293,result);
	}
*/
	private int testItems(int number) {
		Postage postage = new MTGChicagoPostage();
		StandardSupplyRecord record1 = new StandardSupplyRecord("Example Card",vendor1,"Set","Unplayed",50,"3.35");
		Order order = new SingleVendorOrder(record1);
		order.add(record1,number-1);

		return postage.calculate(order);
	}
}
