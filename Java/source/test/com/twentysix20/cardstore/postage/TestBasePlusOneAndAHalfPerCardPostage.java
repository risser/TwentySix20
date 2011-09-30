package com.twentysix20.cardstore.postage;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import com.twentysix20.cardstore.order.MultiVendorOrder;
import com.twentysix20.cardstore.order.Order;


public class TestBasePlusOneAndAHalfPerCardPostage {
	@Test public void testOneItem(){
		verifyPostage(279, 1, 279);
		verifyPostage(279, 2, 280);
		verifyPostage(279, 3, 282);
		verifyPostage(279, 4, 283);
		verifyPostage(279, 5, 285);
		verifyPostage(279, 6, 286);
		verifyPostage(279, 7, 288);
	}

	private void verifyPostage(int baseRate, int numberOfCards, int expected) {
		Postage postage = new BasePlusOneAndAHalfPerCardPostage(baseRate);
		Order mockOrder = mock(MultiVendorOrder.class);
		when(mockOrder.numberOfCards()).thenReturn(numberOfCards);
		int result = postage.calculate(mockOrder);
		assertEquals(expected,result);
	}
}
