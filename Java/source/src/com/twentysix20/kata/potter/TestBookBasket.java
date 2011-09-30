package com.twentysix20.kata.potter;

import java.math.BigDecimal;

import com.twentysix20.testutil.TestCase2620;

public class TestBookBasket extends TestCase2620 {
	public void testEmptyBasket() {
		BookBasket basket = new BookBasket();
		assertCost("0", basket);
	}

	public void testOneBook() {
		BookBasket basket = new BookBasket();
		basket.addBook(1);
		assertCost("8", basket);
	}

	public void testOneOtherBook() {
		assertCost("8", new BookBasket(2));
	}

	public void testTwoSameBooks() {
		assertCost("16", new BookBasket(1,1));
	}

	public void testTwoDifferentBooks() {
		assertCost("15.2", new BookBasket(1,2));
	}

	public void testThreeSameBooks() {
		assertCost("24", new BookBasket(1,1,1));
	}

	public void testThreeDifferentBooks() {
		assertCost("21.6", new BookBasket(1,2,3));
	}

	public void testFiveDifferentBooks() {
		assertCost("30", new BookBasket(1,2,3,4,5));
	}

	public void testTwoAndOne() {
		assertCost("23.20", new BookBasket(1,1,2));
	}

	public void testTwoAndTwo() {
		assertCost("30.40", new BookBasket(1,1,2,2));
	}

	public void testTwoAndOneAndOne() {
		assertCost("29.60", new BookBasket(1,1,2,3));
	}

	public void testMany1() {
		assertCost("40.80", new BookBasket(1,1,2,3,3,4));
	}

	public void testMany2() {
		assertCost("38", new BookBasket(1,2,3,4,5,1));
	}

	public void testDoubleFours() {
		assertCost("51.20", new BookBasket(1,1,2,2,3,3,4,4));
	}

	public void testDoubleFours2() {
		assertCost("51.20", new BookBasket(1,1,2,2,3,3,4,5));
	}

	public void testDoubleFours3() {
		assertCost("51.20", new BookBasket(1,5,2,4,3,3,2,4));
	}

	public void testBigEdgeCase() {
		assertCost("141.20", new BookBasket(0, 0, 0, 0, 0, 
		           1, 1, 1, 1, 1, 
		           2, 2, 2, 2, 
		           3, 3, 3, 3, 3, 
		           4, 4, 4, 4));
	}

	private void assertCost(String expectedCost, BookBasket basket) {
		assertEquals(new BigDecimal(expectedCost), basket.cost());
	}
}
