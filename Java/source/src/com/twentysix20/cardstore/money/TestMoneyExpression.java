package com.twentysix20.cardstore.money;

import org.junit.Test;
import static org.junit.Assert.*;


public class TestMoneyExpression {
	@Test public void add() {
		Money money1 = new Money(100);
		Money money2 = new Money(156);
		Money result = money1.plus(money2);
		assertEquals(256,result.cents());
	}

	@Test public void equals() {
		Money money1 = new Money(156);
		Money money2 = new Money(156);
		assertTrue(money1.equals(money2));
		assertTrue(money2.equals(money1));
		assertEquals(money1, money2);
		assertEquals(money2, money1);
	}

	@Test public void notEquals() {
		Money money1 = new Money(100);
		Money money2 = new Money(156);
		assertFalse(money1.equals(money2));
		assertFalse(money2.equals(money1));
	}

	@Test public void compare() {
		assertTrue(new Money(100).compareTo(new Money(150)) < 0);
		assertTrue(new Money(100).compareTo(new Money(100)) == 0);
		assertTrue(new Money(170).compareTo(new Money(150)) > 0);
	}
}