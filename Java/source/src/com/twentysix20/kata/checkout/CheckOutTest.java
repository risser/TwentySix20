package com.twentysix20.kata.checkout;


import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CheckOutTest {
	private Set<PricingRule> rules;

	@Before public void setUp() throws Exception {
		rules = new HashSet<PricingRule>();
		rules.add(new PricingRule("A",50,3,130));
		rules.add(new PricingRule("B",30,2,45));
		rules.add(new PricingRule("C",20));
		rules.add(new PricingRule("D",15));
	}

	@After public void tearDown() throws Exception {
		rules.clear();
		rules = null;
	}

	@Test public void oneItem() throws Exception {
		CheckOut co = new CheckOut(rules);
		co.scan("C");
		assertEquals(20, co.total());
	}

	@Test public void noItems() throws Exception {
		CheckOut co = new CheckOut(rules);
		assertEquals(0, co.total());
	}

	@Test public void twoItem() throws Exception {
		CheckOut co = new CheckOut(rules);
		multiScan(co, "CC");
		assertEquals(40, co.total());
	}

	@Test public void givenTests() throws Exception {
		assertCheckOut(80, "AB");
		assertCheckOut(115, "CDBA");
	}

	@Test public void manyWithDiscount() throws Exception {
		assertCheckOut(100, "AA");
		assertCheckOut(130, "AAA");
		assertCheckOut(180, "AAAA");
		assertCheckOut(230, "AAAAA");
		assertCheckOut(260, "AAAAAA");
		assertCheckOut(310, "AAAAAAA");
	}

	@Test public void manyNoDiscount() throws Exception {
		assertCheckOut(40, "CC");
		assertCheckOut(60, "CCC");
		assertCheckOut(80, "CCCC");
	}

	@Test public void manyDifferentKinds() throws Exception {
		assertCheckOut(160, "AAAB");
		assertCheckOut(175, "AAABB");
		assertCheckOut(255, "ABAABAB");
		assertCheckOut(190, "AAABBD");
		assertCheckOut(190, "DABABA");
	}

	@Test public void incremental() throws Exception {
		CheckOut co = new CheckOut(rules);
		assertEquals(0, co.total());
		co.scan("A");
		assertEquals(50, co.total());
		co.scan("B");
		assertEquals(80, co.total());
		co.scan("A");
		assertEquals(130, co.total());
		co.scan("A");
		assertEquals(160, co.total());
		co.scan("B");
		assertEquals(175, co.total());
	}
	private void assertCheckOut(int total, String skus) {
		CheckOut co = new CheckOut(rules);
		multiScan(co, skus);
		assertEquals(total, co.total());
	}

	private void multiScan(CheckOut co, String skus) {
		for (int i = 0; i < skus.length(); i++)
			co.scan(skus.substring(i, i+1));
	}
}
