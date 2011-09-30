package com.twentysix20.kata.stringcalc;

import com.twentysix20.testutil.TestCase2620;

public class TestCalc5 extends TestCase2620 {
	public void testOneNegative() throws Exception {
		try {
			StringCalc.add("-2");
			fail("Should have thrown exception.");
		} catch (Exception e) {
			assertEquals("Negatives not allowed: -2",e.getMessage());
		}
	}
	public void testOneNegativeOfTwo() throws Exception {
		try {
			StringCalc.add("-2,2");
			fail("Should have thrown exception.");
		} catch (Exception e) {
			assertEquals("Negatives not allowed: -2",e.getMessage());
		}
	}
	public void testSecondNegativeOfTwo() throws Exception {
		try {
			StringCalc.add("2,-2");
			fail("Should have thrown exception.");
		} catch (Exception e) {
			assertEquals("Negatives not allowed: -2",e.getMessage());
		}
	}
	public void testTwoNegativesOfTwo() throws Exception {
		try {
			StringCalc.add("-2,-3");
			fail("Should have thrown exception.");
		} catch (Exception e) {
			assertEquals("Negatives not allowed: -2,-3",e.getMessage());
		}
	}
}