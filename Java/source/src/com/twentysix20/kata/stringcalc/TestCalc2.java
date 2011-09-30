package com.twentysix20.kata.stringcalc;

import com.twentysix20.testutil.TestCase2620;

public class TestCalc2 extends TestCase2620 {
	public void testThreeNumbers() throws Exception {
		assertEquals(6,StringCalc.add("1,2,3"));
	}
	public void testManyNumbers_LargeInt() throws Exception {
		assertEquals(67891234,StringCalc.add("123456,64424321,2,3332333,11100,22"));
	}
}