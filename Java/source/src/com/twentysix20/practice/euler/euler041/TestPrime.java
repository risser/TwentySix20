package com.twentysix20.practice.euler.euler041;

import com.twentysix20.testutil.TestCase2620;

public class TestPrime extends TestCase2620 {
	public void test2() {
		assertTrue(Prime.isPrime(2));
	}
	public void test3() {
		assertTrue(Prime.isPrime(3));
	}
	public void test4() {
		assertFalse(Prime.isPrime(4));
	}
	public void test5() {
		assertTrue(Prime.isPrime(5));
	}
	public void test6() {
		assertFalse(Prime.isPrime(6));
	}
	public void test9() {
		assertFalse(Prime.isPrime(9));
	}
	public void test17() {
		assertTrue(Prime.isPrime(17));
	}
	public void testBig() {
		assertTrue(Prime.isPrime(3559));
	}
	public void testBigger() {
		assertFalse(Prime.isPrime(3559*3571));
	}
}
