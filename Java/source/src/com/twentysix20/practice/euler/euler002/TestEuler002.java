package com.twentysix20.practice.euler.euler002;

import com.twentysix20.testutil.TestCase2620;

public class TestEuler002 extends TestCase2620 {
	public void testNewFib1() {
		assertEquals(0L, Euler002.calculate(1));
	}
	public void testNewFib2() {
		assertEquals(2L, Euler002.calculate(2));
	}
	public void testNewFib8() {
		assertEquals(10L, Euler002.calculate(8));
	}
	public void testNewFib7() {
		assertEquals(2L, Euler002.calculate(7));
	}
	public void testNewFib34() {
		assertEquals(44L, Euler002.calculate(34));
	}
	public void testNewFib35() {
		assertEquals(44L, Euler002.calculate(35));
	}
	public void testNewFib143() {
		assertEquals(44L, Euler002.calculate(143));
	}
	public void testNewFib144() {
		assertEquals(188L, Euler002.calculate(144));
	}
	
	public void testNewFib_FinalTest() {
		assertEquals(4613732L, Euler002.calculate(4000000));
	}
}
