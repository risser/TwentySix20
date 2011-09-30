package com.twentysix20.cardstore.postage;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Ignore;
import org.junit.Test;

import com.twentysix20.cardstore.order.MultiVendorOrder;
import com.twentysix20.cardstore.order.Order;


public class TestExtendedBasePlusPerCardGroupPostage {
	@Test public void testOneItem(){
		Postage postage = new ExtendedBasePlusPerCardGroupPostage(271, 9, 2, 17);
		Order mockOrder = mock(MultiVendorOrder.class);
		when(mockOrder.numberOfCards()).thenReturn(1);
		int result = postage.calculate(mockOrder);
		assertEquals(271,result);
	}
	@Test public void testTwoItems(){
		Postage postage = new ExtendedBasePlusPerCardGroupPostage(271, 9, 2, 17);
		Order mockOrder = mock(MultiVendorOrder.class);
		when(mockOrder.numberOfCards()).thenReturn(2);
		int result = postage.calculate(mockOrder);
		assertEquals(271,result);
	}
	@Test public void testNineItems(){
		Postage postage = new ExtendedBasePlusPerCardGroupPostage(271, 9, 2, 17);
		Order mockOrder = mock(MultiVendorOrder.class);
		when(mockOrder.numberOfCards()).thenReturn(9);
		int result = postage.calculate(mockOrder);
		assertEquals(271,result);
	}
	@Ignore @Test public void testTenItems(){
		Postage postage = new ExtendedBasePlusPerCardGroupPostage(271, 9, 2, 17);
		Order mockOrder = mock(MultiVendorOrder.class);
		when(mockOrder.numberOfCards()).thenReturn(10);
		int result = postage.calculate(mockOrder);
//		assertEquals(288,result);
	}
	@Test public void test11Items(){
		Postage postage = new ExtendedBasePlusPerCardGroupPostage(271, 9, 2, 17);
		Order mockOrder = mock(MultiVendorOrder.class);
		when(mockOrder.numberOfCards()).thenReturn(11);
		int result = postage.calculate(mockOrder);
		assertEquals(288,result);
	}
	@Ignore @Test public void test12Items(){
		Postage postage = new ExtendedBasePlusPerCardGroupPostage(271, 9, 2, 17);
		Order mockOrder = mock(MultiVendorOrder.class);
		when(mockOrder.numberOfCards()).thenReturn(12);
		int result = postage.calculate(mockOrder);
//		assertEquals(305,result);
	}
	@Ignore @Test public void test18Items(){
		Postage postage = new ExtendedBasePlusPerCardGroupPostage(271, 9, 2, 17);
		Order mockOrder = mock(MultiVendorOrder.class);
		when(mockOrder.numberOfCards()).thenReturn(18);
		int result = postage.calculate(mockOrder);
//		assertEquals(356,result);
	}
	@Test public void testOtherValues_5(){
		Postage postage = new ExtendedBasePlusPerCardGroupPostage(200, 5, 5, 1);
		Order mockOrder = mock(MultiVendorOrder.class);
		when(mockOrder.numberOfCards()).thenReturn(5);
		int result = postage.calculate(mockOrder);
		assertEquals(200,result);
	}
	@Ignore @Test public void testOtherValues_6(){
		Postage postage = new ExtendedBasePlusPerCardGroupPostage(200, 5, 5, 1);
		Order mockOrder = mock(MultiVendorOrder.class);
		when(mockOrder.numberOfCards()).thenReturn(6);
		int result = postage.calculate(mockOrder);
//		assertEquals(201,result);
	}
	@Ignore @Test public void testOtherValues_49(){
		Postage postage = new ExtendedBasePlusPerCardGroupPostage(200, 5, 5, 1);
		Order mockOrder = mock(MultiVendorOrder.class);
		when(mockOrder.numberOfCards()).thenReturn(49);
		int result = postage.calculate(mockOrder);
//		assertEquals(209,result);
	}
	@Ignore @Test public void testOtherValues_50(){
		Postage postage = new ExtendedBasePlusPerCardGroupPostage(200, 5, 5, 1);
		Order mockOrder = mock(MultiVendorOrder.class);
		when(mockOrder.numberOfCards()).thenReturn(50);
		int result = postage.calculate(mockOrder);
//		assertEquals(210,result);
	}
}
