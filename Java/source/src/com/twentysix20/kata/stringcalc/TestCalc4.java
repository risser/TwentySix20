package com.twentysix20.kata.stringcalc;

import com.twentysix20.testutil.TestCase2620;

public class TestCalc4 extends TestCase2620 {
	public void testNewDelimiter() throws Exception {
		assertEquals(3,StringCalc.add("//;\n1;2"));
	}
	public void testNewDelimiterDash() throws Exception {
		assertEquals(3,StringCalc.add("//-\n1-2"));
	}
	public void testNewDelimiterBracket() throws Exception {
		assertEquals(3,StringCalc.add("//]\n1]2"));
	}
	public void testNewDelimiterStar() throws Exception {
		assertEquals(3,StringCalc.add("//*\n1*2"));
	}
	public void testNewDelimiterX() throws Exception {
		assertEquals(3,StringCalc.add("//x\n1x2"));
	}
	public void testMultipleNewDelimiter() throws Exception {
		assertEquals(10,StringCalc.add("//;\n1;2;3;4"));
	}
	public void testNewAndOldDelimiters() throws Exception {
		assertEquals(15,StringCalc.add("//;\n1;2\n3;4,5"));
	}
}