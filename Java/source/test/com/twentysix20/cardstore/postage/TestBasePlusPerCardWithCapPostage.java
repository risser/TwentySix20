package com.twentysix20.cardstore.postage;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import com.twentysix20.cardstore.order.MultiVendorOrder;
import com.twentysix20.cardstore.order.Order;


public class TestBasePlusPerCardWithCapPostage {
	@Test public void testOneItem(){
		Postage postage = new BasePlusPerCardWithCapPostage(100, 10, 700);
		Order mockOrder = mock(MultiVendorOrder.class);
		when(mockOrder.numberOfCards()).thenReturn(1);
		int result = postage.calculate(mockOrder);
		assertEquals(100,result);
	}
	@Test public void testTwoItems(){
		Postage postage = new BasePlusPerCardWithCapPostage(100, 10, 700);
		Order mockOrder = mock(MultiVendorOrder.class);
		when(mockOrder.numberOfCards()).thenReturn(2);
		int result = postage.calculate(mockOrder);
		assertEquals(110,result);
	}
	@Test public void testTenItems(){
		Postage postage = new BasePlusPerCardWithCapPostage(100, 10, 700);
		Order mockOrder = mock(MultiVendorOrder.class);
		when(mockOrder.numberOfCards()).thenReturn(10);
		int result = postage.calculate(mockOrder);
		assertEquals(190,result);
	}
	@Test public void testSixtyItems(){
		Postage postage = new BasePlusPerCardWithCapPostage(100, 10, 700);
		Order mockOrder = mock(MultiVendorOrder.class);
		when(mockOrder.numberOfCards()).thenReturn(60);
		int result = postage.calculate(mockOrder);
		assertEquals(690,result);
	}
	@Test public void testSixtyOneItems(){
		Postage postage = new BasePlusPerCardWithCapPostage(100, 10, 700);
		Order mockOrder = mock(MultiVendorOrder.class);
		when(mockOrder.numberOfCards()).thenReturn(61);
		int result = postage.calculate(mockOrder);
		assertEquals(700,result);
	}
	@Test public void testSixtyTwoItems(){
		Postage postage = new BasePlusPerCardWithCapPostage(100, 10, 700);
		Order mockOrder = mock(MultiVendorOrder.class);
		when(mockOrder.numberOfCards()).thenReturn(62);
		int result = postage.calculate(mockOrder);
		assertEquals(700,result);
	}
	@Test public void testOneHundredItems(){
		Postage postage = new BasePlusPerCardWithCapPostage(100, 10, 700);
		Order mockOrder = mock(MultiVendorOrder.class);
		when(mockOrder.numberOfCards()).thenReturn(100);
		int result = postage.calculate(mockOrder);
		assertEquals(700,result);
	}
	@Test public void testOtherValues(){
		Postage postage = new BasePlusPerCardWithCapPostage(184, 5, 734);
		Order mockOrder = mock(MultiVendorOrder.class);
		when(mockOrder.numberOfCards()).thenReturn(6);
		int result = postage.calculate(mockOrder);
		assertEquals(209,result);
	}
	@Test public void testOtherValues_Capped(){
		Postage postage = new BasePlusPerCardWithCapPostage(184, 5, 734);
		Order mockOrder = mock(MultiVendorOrder.class);
		when(mockOrder.numberOfCards()).thenReturn(200);
		int result = postage.calculate(mockOrder);
		assertEquals(734,result);
	}
}
