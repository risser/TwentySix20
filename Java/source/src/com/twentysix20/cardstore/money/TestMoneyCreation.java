package com.twentysix20.cardstore.money;

import org.junit.Test;
import static org.junit.Assert.*;


public class TestMoneyCreation {
	@Test public void createMoneyFromCents() {
		Money money = new Money(100);
		assertEquals(1,money.value(),0.00001);
		assertEquals("1.00",money.toString());
		assertEquals(100,money.cents());
	}

	@Test public void createMoneyWithCents() {
		Money money = new Money(155);
		assertEquals(1.55,money.value(),0.00001);
		assertEquals("1.55",money.toString());
		assertEquals(155,money.cents());
	}

	@Test public void createMoneyNoDecimal() {
		Money money = new Money("2");
		assertEquals(2,money.value(),0.00001);
		assertEquals("2.00",money.toString());
		assertEquals(200,money.cents());
	}

	@Test public void createMoneyOnlyDecimal() {
		Money money = new Money("2.");
		assertEquals(2,money.value(),0.00001);
		assertEquals("2.00",money.toString());
		assertEquals(200,money.cents());
	}

	@Test public void createMoneyOneDecimal() {
		Money money = new Money("2.5");
		assertEquals(2.5,money.value(),0.00001);
		assertEquals("2.50",money.toString());
		assertEquals(250,money.cents());
	}

	@Test public void createMoneyTwoDecimals() {
		Money money = new Money("2.53");
		assertEquals(2.53,money.value(),0.00001);
		assertEquals("2.53",money.toString());
		assertEquals(253,money.cents());
	}

	@Test public void createMoneyThreeDecimals() {
		Money money = new Money("2.555");
		assertEquals(2.55,money.value(),0.00001);
		assertEquals("2.55",money.toString());
		assertEquals(255,money.cents());
	}

	@Test public void createMoneyEmptyString() {
		Money money = new Money("");
		assertEquals(0,money.value(),0.00001);
		assertEquals("0.00",money.toString());
		assertEquals(0,money.cents());
	}

	@Test public void createMoneyOnlyPennies() {
		Money money = new Money(".01");
		assertEquals(0.01,money.value(),0.00001);
		assertEquals("0.01",money.toString());
		assertEquals(1,money.cents());
	}

	@Test public void createMoneyWithComma() {
		Money money = new Money("1,199.01");
		assertEquals(1199.01,money.value(),0.00001);
		assertEquals("1199.01",money.toString());
		assertEquals(119901,money.cents());
	}

	@Test(expected = NullPointerException.class)
	public void createMoneyNull() {
		new Money(null);
	}
}