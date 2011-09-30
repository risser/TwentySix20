package com.twentysix20.kata.potter;

import java.math.BigDecimal;

import com.twentysix20.testutil.TestCase2620;

public class TestDiscountSet extends TestCase2620 {
	public void testEmptyBasket() {
		DiscountSet basket = new DiscountSet();
		assertCost("0", basket);
	}

	public void testOneBook() {
		DiscountSet basket = new DiscountSet();
		basket.addBook(1);
		assertCost("8", basket);
	}

	public void testOneOtherBook() {
		DiscountSet basket = new DiscountSet();
		basket.addBook(2);
		assertCost("8", basket);
	}

	public void testTwoSameBooks() {
		DiscountSet basket = new DiscountSet();
		basket.addBook(1);
		try {
			basket.addBook(1);
			fail("Should have thrown exception.");
		} catch (IllegalArgumentException e) {
			//yay
		}
	}

	public void testTwoDifferentBooks() {
		DiscountSet basket = new DiscountSet();
		basket.addBook(1);
		basket.addBook(2);
		assertCost("15.2", basket);
	}

	public void testThreeDifferentBooks() {
		DiscountSet basket = new DiscountSet();
		basket.addBook(1);
		basket.addBook(2);
		basket.addBook(3);
		assertCost("21.6", basket);
	}

	public void testEmptyBasket_Has() {
		DiscountSet basket = new DiscountSet();
		assertFalse(basket.hasBook(1));
	}

	public void testOneBook_Has() {
		DiscountSet basket = new DiscountSet();
		basket.addBook(1);
		assertTrue(basket.hasBook(1));
		assertFalse(basket.hasBook(2));
	}

	public void testThreeBooks_Has() {
		DiscountSet basket = new DiscountSet();
		basket.addBook(1);
		basket.addBook(3);
		basket.addBook(4);
		assertTrue(basket.hasBook(1));
		assertFalse(basket.hasBook(2));
		assertTrue(basket.hasBook(3));
		assertTrue(basket.hasBook(4));
		assertFalse(basket.hasBook(5));
	}

	private void assertCost(String expectedCost, DiscountSet basket) {
		assertEquals(new BigDecimal(expectedCost), basket.cost());
	}
}