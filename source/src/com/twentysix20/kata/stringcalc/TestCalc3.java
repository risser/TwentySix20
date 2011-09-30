package com.twentysix20.kata.stringcalc;

import com.twentysix20.testutil.TestCase2620;

public class TestCalc3 extends TestCase2620 {
	public void testNewLine_TwoNumbers() throws Exception {
		assertEquals(6,StringCalc.add("2\n4"));
	}
	public void testNewLineAndComma() throws Exception {
		assertEquals(9,StringCalc.add("2\n4,3"));
	}
	public void testNewLinesAndCommas() throws Exception {
		assertEquals(22,StringCalc.add("2\n4,3\n1\n2,3,4\n3"));
	}
}