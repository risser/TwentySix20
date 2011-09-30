package com.wf.jdf.life;

import com.twentysix20.testutil.TestCase2620;

public class TestRules extends TestCase2620 {
	public void testZeroNeighborsAlive() {
		Rules rules = new Rules();
		assertFalse(rules.isAlive(0,true));
	}
	public void testZeroNeighborsDead() {
		Rules rules = new Rules();
		assertFalse(rules.isAlive(0,false));
	}
	public void testOneNeighborAlive() {
		Rules rules = new Rules();
		assertFalse(rules.isAlive(1,true));
	}
	public void testOneNeighborDead() {
		Rules rules = new Rules();
		assertFalse(rules.isAlive(1,false));
	}
	public void testTwoNeighborsAlive() {
		Rules rules = new Rules();
		assertTrue(rules.isAlive(2,true));
	}
	public void testTwoNeighborsDead() {
		Rules rules = new Rules();
		assertFalse(rules.isAlive(2,false));
	}
	public void testThreeNeighborsAlive() {
		Rules rules = new Rules();
		assertTrue(rules.isAlive(3,true));
	}
	public void testThreeNeighborsDead() {
		Rules rules = new Rules();
		assertTrue(rules.isAlive(3,false));
	}
	public void testFourOrMoreNeighbors_AliveAndDead() {
		Rules rules = new Rules();
		for (int i = 4; i <= 8; i++) {
			assertFalse("FAILED! Should be dead, but was alive. neighbors = "+i+"; Alive",rules.isAlive(i,true));
			assertFalse("FAILED! Should be dead, but was alive. neighbors = "+i+"; Dead",rules.isAlive(i,false));
		}
	}
}
