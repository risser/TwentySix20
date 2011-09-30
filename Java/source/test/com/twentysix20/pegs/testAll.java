package com.twentysix20.pegs;

import junit.framework.Test;
import junit.framework.TestSuite;

public class testAll {

	public static void main (String[] args) {
		junit.textui.TestRunner.run (suite());
	}
	public static Test suite ( ) {
		TestSuite suite= new TestSuite("All Peg Tests");
		suite.addTest(new TestSuite(testBoard.class));
		suite.addTest(new TestSuite(testHistory.class));
		suite.addTest(new TestSuite(testBoardTriangle.class));
	    return suite;
	}
}