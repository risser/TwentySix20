package com.twentysix20.cardstore.postage;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import com.twentysix20.cardstore.order.MultiVendorOrder;
import com.twentysix20.cardstore.order.Order;


public class TestBasePlusPerCardTypePostage {
	@Test public void testOneItemOneGrouping(){
		Postage postage = new BasePlusPerCardTypePostage(100, 10, 1);
		Order mockOrder = mock(MultiVendorOrder.class);
		when(mockOrder.numberOfCardInstances()).thenReturn(1);
		int result = postage.calculate(mockOrder);
		assertEquals(100,result);
	}
	@Test public void testTwoItemOneGrouping(){
		Postage postage = new BasePlusPerCardTypePostage(100, 10, 1);
		Order mockOrder = mock(MultiVendorOrder.class);
		when(mockOrder.numberOfCardInstances()).thenReturn(2);
		int result = postage.calculate(mockOrder);
		assertEquals(110,result);
	}
	@Test public void testTenItemOneGrouping(){
		Postage postage = new BasePlusPerCardTypePostage(100, 10, 1);
		Order mockOrder = mock(MultiVendorOrder.class);
		when(mockOrder.numberOfCardInstances()).thenReturn(10);
		int result = postage.calculate(mockOrder);
		assertEquals(190,result);
	}
	@Test public void testOneItemFourGrouping(){
		Postage postage = new BasePlusPerCardTypePostage(100, 10, 4);
		Order mockOrder = mock(MultiVendorOrder.class);
		when(mockOrder.numberOfCardInstances()).thenReturn(1);
		int result = postage.calculate(mockOrder);
		assertEquals(100,result);
	}
	@Test public void testTwoItemFourGrouping(){
		Postage postage = new BasePlusPerCardTypePostage(100, 10, 4);
		Order mockOrder = mock(MultiVendorOrder.class);
		when(mockOrder.numberOfCardInstances()).thenReturn(2);
		int result = postage.calculate(mockOrder);
		assertEquals(110,result);
	}
	@Test public void testNineItemFourGrouping(){
		Postage postage = new BasePlusPerCardTypePostage(100, 10, 4);
		Order mockOrder = mock(MultiVendorOrder.class);
		when(mockOrder.numberOfCardInstances()).thenReturn(9);
		int result = postage.calculate(mockOrder);
		assertEquals(120,result);
	}
	@Test public void testTenItemFourGrouping(){
		Postage postage = new BasePlusPerCardTypePostage(100, 10, 4);
		Order mockOrder = mock(MultiVendorOrder.class);
		when(mockOrder.numberOfCardInstances()).thenReturn(10);
		int result = postage.calculate(mockOrder);
		assertEquals(130,result);
	}
	@Test public void testOneItemTwoGrouping(){
		Postage postage = new BasePlusPerCardTypePostage(100, 10, 2);
		Order mockOrder = mock(MultiVendorOrder.class);
		when(mockOrder.numberOfCardInstances()).thenReturn(1);
		int result = postage.calculate(mockOrder);
		assertEquals(100,result);
	}
	@Test public void testTwoItemTwoGrouping(){
		Postage postage = new BasePlusPerCardTypePostage(100, 10, 2);
		Order mockOrder = mock(MultiVendorOrder.class);
		when(mockOrder.numberOfCardInstances()).thenReturn(2);
		int result = postage.calculate(mockOrder);
		assertEquals(110,result);
	}
	@Test public void testThreeItemTwoGrouping(){
		Postage postage = new BasePlusPerCardTypePostage(100, 10, 2);
		Order mockOrder = mock(MultiVendorOrder.class);
		when(mockOrder.numberOfCardInstances()).thenReturn(3);
		int result = postage.calculate(mockOrder);
		assertEquals(110,result);
	}
	@Test public void testFourItemTwoGrouping(){
		Postage postage = new BasePlusPerCardTypePostage(100, 10, 2);
		Order mockOrder = mock(MultiVendorOrder.class);
		when(mockOrder.numberOfCardInstances()).thenReturn(4);
		int result = postage.calculate(mockOrder);
		assertEquals(120,result);
	}
}
