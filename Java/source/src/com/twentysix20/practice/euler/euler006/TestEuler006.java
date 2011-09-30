package com.twentysix20.practice.euler.euler006;

import com.twentysix20.testutil.TestCase2620;

public class TestEuler006 extends TestCase2620 {
	public void testSums1() {
		assertEquals(0L, Euler006.calculate(1));
	}
	public void testSums2() {
		assertEquals(4L, Euler006.calculate(2));
	}
	public void testSums10() {
		assertEquals(2640L, Euler006.calculate(10));
	}
	
	public void testSums_FinalTest() {
		assertEquals(25164150L, Euler006.calculate(100));
	}
}
