package com.twentysix20.kata.mine;

import org.junit.Test;
import static org.junit.Assert.*;


public class MultifieldSolverTest {
	@Test public void testZeros() throws Exception {
		MultifieldSolver solver = new MultifieldSolver(new DoNothingSolver());
		assertEquals(0, solver.solve("0 0").length);
	}
	@Test public void testOneByOne() throws Exception {
		MultifieldSolver solver = new MultifieldSolver(new DoNothingSolver());
		String[] result = solver.solve("1 1",".","0 0");
		assertEquals(2, result.length);
		assertEquals("Field #1:",result[0]);
		assertEquals(".",result[1]);
	}
	@Test public void testTwoOneByOnes() throws Exception {
		MultifieldSolver solver = new MultifieldSolver(new DoNothingSolver());
		String[] result = solver.solve("1 1",".","1 1","*","0 0");
		assertEquals(5, result.length);
		assertEquals("Field #1:",result[0]);
		assertEquals(".",result[1]);
		assertEquals("",result[2]);
		assertEquals("Field #2:",result[3]);
		assertEquals("*",result[4]);
	}
	@Test public void testSingleRowColumn() throws Exception {
		MultifieldSolver solver = new MultifieldSolver(new DoNothingSolver());
		String[] result = solver.solve("1 4","....","4 1",".",".",".",".","0 0");
		assertEquals(8, result.length);
		assertEquals("Field #1:",result[0]);
		assertEquals("....",result[1]);
		assertEquals("",result[2]);
		assertEquals("Field #2:",result[3]);
		assertEquals(".",result[4]);
		assertEquals(".",result[5]);
		assertEquals(".",result[6]);
		assertEquals(".",result[7]);
	}
	@Test public void testLarger() throws Exception {
		MultifieldSolver solver = new MultifieldSolver(new DoNothingSolver());
		String[] result = solver.solve("3 4","....",".**.","*..*","0 0");
		assertEquals(4, result.length);
		assertEquals("Field #1:",result[0]);
		assertEquals("....",result[1]);
		assertEquals(".**.",result[2]);
		assertEquals("*..*",result[3]);
	}
}
