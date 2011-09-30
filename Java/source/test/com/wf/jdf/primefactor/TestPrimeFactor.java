package com.wf.jdf.primefactor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.twentysix20.testutil.TestCase2620;

public class TestPrimeFactor extends TestCase2620 {
	public void testPrimeFactor1() {
		assertPrimes(new Integer[]{}, 1);
	}
	public void testPrimeFactor2() {
		assertPrimes(new Integer[]{2}, 2);
	}
	public void testPrimeFactor3() {
		assertPrimes(new Integer[]{3}, 3);
	}
	public void testPrimeFactor4() {
		assertPrimes(new Integer[]{2,2}, 4);
	}
	public void testPrimeFactor6() {
		assertPrimes(new Integer[]{2,3}, 6);
	}
	public void testPrimeFactor8() {
		assertPrimes(new Integer[]{2,2,2}, 8);
	}
	public void testPrimeFactor9() {
		assertPrimes(new Integer[]{3,3}, 9);
	}
	public void testPrimeFactor25() {
		assertPrimes(new Integer[]{5,5}, 25);
	}
	public void testPrimeFactorBig() {
		assertPrimes(new Integer[]{2,7,17,29}, 6902);
	}
	public void testPrimeFactorMaxMinusOne() {
		assertPrimes(new Integer[]{2,3,3,7,11,31,151,331}, Integer.MAX_VALUE-1);
	}
	public void testPrimeFactorMax() {
		assertPrimes(new Integer[]{Integer.MAX_VALUE}, Integer.MAX_VALUE);
	}
	private void assertPrimes(Integer[] primes, int initialValue) {
		List<Integer> expected = Arrays.asList(primes);
		assertEquals(expected, PrimeFactors.generate(initialValue));
	}
}
