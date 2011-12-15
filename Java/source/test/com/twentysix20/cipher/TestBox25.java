package com.twentysix20.cipher;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestBox25 {
	static final private String ALPHA_NO_Q = "ABCDEFGHIJKLMNOPRSTUVWXYZ";
	static final private String ALPHA_NO_J = "ABCDEFGHIKLMNOPQRSTUVWXYZ";

	@Test public void newBoxWithStringToFlatString() {
		Box25 b = new Box25(ALPHA_NO_Q);
		assertEquals(ALPHA_NO_Q, b.toFlatString());
	}
	@Test public void newBoxWithLowercaseStringToFlatString() {
		Box25 b = new Box25("abcdefghijklmnoprstuvwxyz");
		assertEquals(ALPHA_NO_Q, b.toFlatString());
	}
	@Test public void newBoxWithDroppedLetterToFlatString() {
		Box25 b = new Box25('Q');
		assertEquals(ALPHA_NO_Q, b.toFlatString());
	}
	@Test public void newBoxWithDroppedLowercaseLetterToFlatString() {
		Box25 b = new Box25('q');
		assertEquals(ALPHA_NO_Q, b.toFlatString());
	}
	@Test public void newBoxWithADifferentDroppedLetterToFlatString() {
		Box25 b = new Box25('J');
		assertEquals(ALPHA_NO_J, b.toFlatString());
	}
	@Test(expected=IllegalArgumentException.class) 
	public void fewerThan25CharsErrors() {
		new Box25("ABCDEF");
	}
	@Test(expected=IllegalArgumentException.class) 
	public void moreThan25CharsErrors() {
		new Box25("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
	}
	@Test(expected=IllegalArgumentException.class) 
	public void duplicateCharactersCausesError() {
		new Box25("ABCDEFGHIJKLMNOPRSTUVWXYP");
	}
	
	@Test public void positionOf() {
		Box25 b = new Box25('J');
		assertEquals(new RowColumn(0,0), b.positionOf('A'));
		assertEquals(new RowColumn(1,1), b.positionOf('G'));
		assertEquals(new RowColumn(1,3), b.positionOf('I'));
		assertEquals(new RowColumn(1,4), b.positionOf('K'));
		assertEquals(new RowColumn(4,2), b.positionOf('X'));
	}
	@Test(expected=IndexOutOfBoundsException.class) 
	public void positionOfMissingLetterCausesError() {
		new Box25('J').positionOf('J');
	}
	@Test(expected=IndexOutOfBoundsException.class) 
	public void positionOfMissingCharacterCausesError() {
		new Box25('J').positionOf('1');
	}
	@Test public void positionOfLowerCase() {
		Box25 b = new Box25('J');
		assertEquals(new RowColumn(1,1), b.positionOf('g'));
	}

	@Test public void charAt() {
		Box25 b = new Box25('J');
		assertEquals('B', b.charAt(0,1));
		assertEquals('G', b.charAt(1,1));
		assertEquals('I', b.charAt(1,3));
		assertEquals('K', b.charAt(1,4));
		assertEquals('X', b.charAt(4,2));
	}
	@Test public void charAtWithRowColumn() {
		Box25 b = new Box25('J');
		assertEquals('X', b.charAt(new RowColumn(4,2)));
	}
	@Test(expected=IndexOutOfBoundsException.class) 
	public void toLowRowCausesError() {
		new Box25('J').charAt(-1,4);
	}
	@Test(expected=IndexOutOfBoundsException.class) 
	public void toHighRowCausesError() {
		new Box25('J').charAt(5,4);
	}
	@Test(expected=IndexOutOfBoundsException.class) 
	public void toLowColumnCausesError() {
		new Box25('J').charAt(4,-1);
	}
	@Test(expected=IndexOutOfBoundsException.class) 
	public void toHighColumnCausesError() {
		new Box25('J').charAt(4,5);
	}

	@Test public void containsActuallyContains() {
		assertTrue(new Box25('J').contains('A'));
		assertTrue(new Box25('J').contains('C'));
		assertTrue(new Box25('J').contains('G'));
		assertTrue(new Box25('J').contains('K'));
		assertTrue(new Box25('J').contains('Z'));
	}
	@Test public void containsActuallyContainsForLowercaser() {
		assertTrue(new Box25('J').contains('a'));
	}
	@Test public void containsDoesntContain() {
		assertFalse(new Box25('J').contains('J'));
		assertFalse(new Box25('J').contains('1'));
	}
}
