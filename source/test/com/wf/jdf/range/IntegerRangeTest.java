package com.wf.jdf.range;

import com.twentysix20.testutil.TestCase2620;


public class IntegerRangeTest extends TestCase2620 {
	private Range r;

	public void setUp() {
		r = new Range(0, 10);
	}

	public void testNewRangeCreation() {
		assertNotNull(r);
	}

	public void testContainsMiddleValue() {
		assertTrue(r.contains(5));
		assertTrue(r.contains(2));
	}

	public void testDoesNotContainBelowValue() {
		assertFalse(r.contains(-1));
	}

	public void testDoesNotContainAboveValue() {
		assertFalse(r.contains(11));
	}

	public void testDoesNotContainMax() {
		assertFalse(r.contains(10));
	}

	public void testContainsMin() {
		assertTrue(r.contains(0));
	}

	public void testSimpleEquality() {
		Range r2 = new Range(0, 10);
		assertEquals(r, r2);
		assertEquals(r2, r);
	}

	public void testSimpleInequality() {
		Range r2 = new Range(0, 11);
		assertFalse(r.equals(r2));
		assertFalse(r2.equals(r));
	}

	public void testNonRangeInequality() {
		assertFalse(r.equals("Hello"));
		assertFalse(r.equals(null));
	}

	public void testIntersectionReturnsIsRangeAndNotNull() {
		Range r2 = new Range(0, 10);
		assertTrue(r.intersection(r2) instanceof Range);
	}

	public void testDisjointIntersectionReturnsNull() {
		Range r2 = new Range(11, 12);
		assertNull(r.intersection(r2));
	}

	public void testToString() {
		assertEquals("[0,10)",r.toString());
	}

	public void testBasicIntersection1() {
		Range r2 = new Range(5, 15);
		assertEquals(new Range(5,10), r.intersection(r2));
	}

	public void testBasicIntersection2() {
		Range r2 = new Range(5, 15);
		assertEquals(new Range(5,10), r2.intersection(r));
	}

	public void testBasicIntersection3() {
		Range r2 = new Range(3, 13);
		assertEquals(new Range(3,10), r.intersection(r2));
	}

	public void testBasicIntersection4() {
		Range r2 = new Range(3, 13);
		assertEquals(new Range(3,10), r2.intersection(r));
	}

	public void testIntersectionDisjointAdjacent() {
		Range r2 = new Range(10,20);
		assertNull(r.intersection(r2));
		assertNull(r2.intersection(r));
	}

	public void testContainedIntersection() {
		Range r2 = new Range(3,7);
		assertEquals(r2,r.intersection(r2));
		assertEquals(r2,r2.intersection(r));
	}

	public void testIntersectionMaxMinusOne() {
		Range r2 = new Range(3,9);
		assertEquals(r2,r.intersection(r2));
		assertEquals(r2,r2.intersection(r));
	}

	public void testIntersectionMaxPlusOne() {
		Range r2 = new Range(3,11);
		assertEquals(new Range(3,10),r.intersection(r2));
		assertEquals(new Range(3,10),r2.intersection(r));
	}

	public void testIntersectionMinMinusOne() {
		Range r2 = new Range(-1,7);
		assertEquals(new Range(0,7),r.intersection(r2));
		assertEquals(new Range(0,7),r2.intersection(r));
	}

	public void testIntersectionMinPlusOne() {
		Range r2 = new Range(1,7);
		assertEquals(r2,r.intersection(r2));
		assertEquals(r2,r2.intersection(r));
	}
}