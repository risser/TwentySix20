package com.twentysix20.kata.stringcalc;

import com.twentysix20.testutil.TestCase2620;

public class TestCalc1 extends TestCase2620 {
	public void testEmptyString() throws Exception {
		assertEquals(0,StringCalc.add(""));
	}
	public void testOneNumber() throws Exception {
		assertEquals(1,StringCalc.add("1"));
	}
	public void testOneNumber_Zero() throws Exception {
		assertEquals(0,StringCalc.add("0"));
	}
	public void testOneNumber2() throws Exception {
		assertEquals(2,StringCalc.add("2"));
	}
	public void testOneNumber_LargeInt() throws Exception {
		assertEquals(21234567,StringCalc.add("21234567"));
	}
	public void testTwoNumbers() throws Exception {
		assertEquals(3,StringCalc.add("1,2"));
	}
	public void testTwoNumbers_Zero() throws Exception {
		assertEquals(2,StringCalc.add("0,2"));
	}
	public void testTwoZeros() throws Exception {
		assertEquals(0,StringCalc.add("0,0"));
	}
	public void testTwoNumbers_LargeInt() throws Exception {
		assertEquals(65547777,StringCalc.add("123456,65424321"));
	}
}