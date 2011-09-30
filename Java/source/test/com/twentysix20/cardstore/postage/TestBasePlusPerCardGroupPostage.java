package com.twentysix20.cardstore.postage;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import com.twentysix20.cardstore.order.MultiVendorOrder;
import com.twentysix20.cardstore.order.Order;


public class TestBasePlusPerCardGroupPostage {
	@Test public void testOneItem(){
		Postage postage = new BasePlusPerCardGroupPostage(100, 17, 20);
		Order mockOrder = mock(MultiVendorOrder.class);
		when(mockOrder.numberOfCards()).thenReturn(1);
		int result = postage.calculate(mockOrder);
		assertEquals(100,result);
	}
	@Test public void testTwoItems(){
		Postage postage = new BasePlusPerCardGroupPostage(100, 17, 20);
		Order mockOrder = mock(MultiVendorOrder.class);
		when(mockOrder.numberOfCards()).thenReturn(2);
		int result = postage.calculate(mockOrder);
		assertEquals(100,result);
	}
	@Test public void testTenItems(){
		Postage postage = new BasePlusPerCardGroupPostage(100, 17, 20);
		Order mockOrder = mock(MultiVendorOrder.class);
		when(mockOrder.numberOfCards()).thenReturn(10);
		int result = postage.calculate(mockOrder);
		assertEquals(100,result);
	}
	@Test public void test16Items(){
		Postage postage = new BasePlusPerCardGroupPostage(100, 17, 20);
		Order mockOrder = mock(MultiVendorOrder.class);
		when(mockOrder.numberOfCards()).thenReturn(16);
		int result = postage.calculate(mockOrder);
		assertEquals(100,result);
	}
	@Test public void test17Items(){
		Postage postage = new BasePlusPerCardGroupPostage(100, 17, 20);
		Order mockOrder = mock(MultiVendorOrder.class);
		when(mockOrder.numberOfCards()).thenReturn(17);
		int result = postage.calculate(mockOrder);
		assertEquals(120,result);
	}
	@Test public void testOneHundredItems(){
		Postage postage = new BasePlusPerCardGroupPostage(100, 17, 20);
		Order mockOrder = mock(MultiVendorOrder.class);
		when(mockOrder.numberOfCards()).thenReturn(100);
		int result = postage.calculate(mockOrder);
		assertEquals(200,result);
	}
	@Test public void testOtherValues_4(){
		Postage postage = new BasePlusPerCardGroupPostage(200, 5, 1);
		Order mockOrder = mock(MultiVendorOrder.class);
		when(mockOrder.numberOfCards()).thenReturn(4);
		int result = postage.calculate(mockOrder);
		assertEquals(200,result);
	}
	@Test public void testOtherValues_5(){
		Postage postage = new BasePlusPerCardGroupPostage(200, 5, 1);
		Order mockOrder = mock(MultiVendorOrder.class);
		when(mockOrder.numberOfCards()).thenReturn(5);
		int result = postage.calculate(mockOrder);
		assertEquals(201,result);
	}
	@Test public void testOtherValues_49(){
		Postage postage = new BasePlusPerCardGroupPostage(200, 5, 1);
		Order mockOrder = mock(MultiVendorOrder.class);
		when(mockOrder.numberOfCards()).thenReturn(49);
		int result = postage.calculate(mockOrder);
		assertEquals(209,result);
	}
	@Test public void testOtherValues_50(){
		Postage postage = new BasePlusPerCardGroupPostage(200, 5, 1);
		Order mockOrder = mock(MultiVendorOrder.class);
		when(mockOrder.numberOfCards()).thenReturn(50);
		int result = postage.calculate(mockOrder);
		assertEquals(210,result);
	}
}
