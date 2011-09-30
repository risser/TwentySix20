package com.twentysix20.kata.bowling;

import org.junit.Test;
import static org.junit.Assert.*;

public class BowlingGameTest {
	@Test public void gutterGame() throws Exception {
		Game g = new Game("0-0-0-0-0-0-0-0-0-0-");
		assertEquals(0,g.score());
	}

	@Test public void ninePerFrame() throws Exception {
		Game g = new Game("9-9-9-9-9-9-9-9-9-9-");
		assertEquals(90,g.score());
	}

	@Test public void allOnes() throws Exception {
		Game g = new Game("11111111111111111111");
		assertEquals(20,g.score());
	}

	@Test public void oneStrike() throws Exception {
		Game g = new Game("X-------------------");
		assertEquals(10,g.score());
	}

	@Test public void oneStrikePlusOneBall() throws Exception {
		Game g = new Game("X-1-----------------");
		assertEquals(12,g.score());
	}

	@Test public void oneStrikePlusOtherBall() throws Exception {
		Game g = new Game("X--1----------------");
		assertEquals(12,g.score());
	}

	@Test public void oneStrikePlusTwoBalls() throws Exception {
		Game g = new Game("X-11----------------");
		assertEquals(14,g.score());
	}

	@Test public void twoStrikes() throws Exception {
		Game g = new Game("X-X-----------------");
		assertEquals(30,g.score());
	}

	@Test public void threeStrikes() throws Exception {
		Game g = new Game("X-X-X---------------");
		assertEquals(60,g.score());
	}

	@Test public void twoStrikesWithCrapInBetween() throws Exception {
		Game g = new Game("X-25X---------------");
		assertEquals(34,g.score());
	}

	@Test public void twoStrikesThenCrap() throws Exception {
		Game g = new Game("X-X-2525-------------");
		assertEquals(53,g.score());
	}

	@Test public void perfectGame() throws Exception {
		Game g = new Game("X-X-X-X-X-X-X-X-X-XXX");
		assertEquals(300,g.score());
	}

	@Test public void almostPerfectGame() throws Exception {
		Game g = new Game("X-X-X-X-X-X-X-X-X-XX9");
		assertEquals(299,g.score());
	}

	@Test public void blowingThePerfectGame() throws Exception {
		Game g = new Game("X-X-X-X-X-X-X-X-X-X--");
		assertEquals(270,g.score());
	}

	@Test public void nearlyPerfectGame() throws Exception {
		Game g = new Game("X-X-X-X-X-X-X-X-X-X45");
		assertEquals(283,g.score());
	}

	@Test public void eightStrikesThenStrike() throws Exception {
		Game g = new Game("X-X-X-X-X-X-X-X-45XXX");
		assertEquals(262,g.score());
	}

}
