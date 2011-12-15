package com.twentysix20.dlc;

import static org.junit.Assert.*;

import org.junit.Test;

import com.twentysix20.dlc.model.Genre;


public class TestGenre {

	@Test public void oneWordToString() {
		assertEquals("Rock", Genre.Rock.toString());
	}

	@Test public void twoWordsToString() {
		assertEquals("Southern Rock", Genre.Southern_Rock.toString());
	}

	@Test public void descriptionToString() {
		assertEquals("R&B/Soul/Funk", Genre.RnB_Soul_Funk.toString());
	}

	@Test public void oneWordFromString() {
		assertEquals(Genre.Metal, Genre.fromString("Metal"));
	}

	@Test public void twoWordsFromString() {
		assertEquals(Genre.Classic_Rock, Genre.fromString("Classic Rock"));
	}

	@Test public void slashedWordsFromString() {
		assertEquals(Genre.Pop_Rock, Genre.fromString("Pop/Rock"));
	}

	@Test public void dashedWordsFromString() {
		assertEquals(Genre.Pop_Rock, Genre.fromString("Pop-Rock"));
	}

	@Test public void ampersandFromString() {
		assertEquals(Genre.RnB_Soul_Funk, Genre.fromString("R&B/Soul/Funk"));
	}
	
}
