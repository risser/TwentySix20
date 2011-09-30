package com.twentysix20.practice.euler.euler041;

import com.twentysix20.testutil.TestCase2620;

public class TestPanDigit extends TestCase2620 {
	public void test1() {
		assertTrue(PanDigit.isPanDigit(1));
	}
	public void test2() {
		assertFalse(PanDigit.isPanDigit(2));
	}
	public void test23() {
		assertFalse(PanDigit.isPanDigit(23));
	}
	public void test12() {
		assertTrue(PanDigit.isPanDigit(12));
	}
	public void test21() {
		assertTrue(PanDigit.isPanDigit(21));
	}
	public void test32() {
		assertFalse(PanDigit.isPanDigit(32));
	}
	public void test321() {
		assertTrue(PanDigit.isPanDigit(321));
	}
	public void test20() {
		assertFalse(PanDigit.isPanDigit(20));
	}
	public void test2143() {
		assertTrue(PanDigit.isPanDigit(2143));
	}
	public void test2142() {
		assertFalse(PanDigit.isPanDigit(2142));
	}
	public void test5142() {
		assertFalse(PanDigit.isPanDigit(5142));
	}
	public void test50142() {
		assertFalse(PanDigit.isPanDigit(50142));
	}
	public void test321456781() {
		assertFalse(PanDigit.isPanDigit(321456781));
	}
	public void test321456789() {
		assertTrue(PanDigit.isPanDigit(321456789));
	}
}
