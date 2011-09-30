package com.twentysix20.kata.tennis;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameTest {

	@Test public void newGame() {
		Game game = new Game();
		assertEquals("Love", game.getCurrentScore(0));
		assertEquals("Love", game.getCurrentScore(1));
		assertEquals(-1, game.getWinner());
	}

	@Test public void onePoint() {
		Game game = new Game();
		game.scorePoint(0);
		assertEquals("15", game.getCurrentScore(0));
		assertEquals("Love", game.getCurrentScore(1));
		assertEquals(-1, game.getWinner());
	}

	@Test public void twoPoints() {
		Game game = new Game();
		game.scorePoint(0);
		game.scorePoint(0);
		assertEquals("30", game.getCurrentScore(0));
		assertEquals("Love", game.getCurrentScore(1));
		assertEquals(-1, game.getWinner());
	}

	@Test public void threePoints() {
		Game game = new Game();
		game.scorePoint(0);
		game.scorePoint(0);
		game.scorePoint(0);
		assertEquals("40", game.getCurrentScore(0));
		assertEquals("Love", game.getCurrentScore(1));
		assertEquals(-1, game.getWinner());
	}

	@Test public void otherGuyThreePoints() {
		Game game = new Game();
		game.scorePoint(1);
		game.scorePoint(1);
		game.scorePoint(1);
		assertEquals("40", game.getCurrentScore(1));
		assertEquals("Love", game.getCurrentScore(0));
		assertEquals(-1, game.getWinner());
	}

	@Test public void backAndForth() {
		Game game = new Game();
		game.scorePoint(1);
		game.scorePoint(0);
		game.scorePoint(0);
		game.scorePoint(1);
		game.scorePoint(1);
		assertEquals("30", game.getCurrentScore(0));
		assertEquals("40", game.getCurrentScore(1));
		assertEquals(-1, game.getWinner());
	}

	@Test public void fortyLoveAndTheWin() {
		Game game = new Game();
		game.scorePoint(0);
		game.scorePoint(0);
		game.scorePoint(0);
		game.scorePoint(0);
		assertEquals("Winner", game.getCurrentScore(0));
		assertEquals("Love", game.getCurrentScore(1));
		assertEquals(0, game.getWinner());
	}

	@Test public void otherGuyWins() {
		Game game = new Game();
		game.scorePoint(0);
		game.scorePoint(1);
		game.scorePoint(1);
		game.scorePoint(1);
		game.scorePoint(1);
		assertEquals("15", game.getCurrentScore(0));
		assertEquals("Winner", game.getCurrentScore(1));
		assertEquals(1, game.getWinner());
	}

	@Test public void deuceCatchUp() {
		Game game = new Game();
		game.scorePoint(0);
		game.scorePoint(0);
		game.scorePoint(0);
		game.scorePoint(1);
		game.scorePoint(1);
		game.scorePoint(1);
		assertEquals("Deuce", game.getCurrentScore(0));
		assertEquals("Deuce", game.getCurrentScore(1));
		assertEquals(-1, game.getWinner());
	}

	@Test public void deuceBackAndForth() {
		Game game = new Game();
		game.scorePoint(1);
		game.scorePoint(0);
		game.scorePoint(1);
		game.scorePoint(0);
		game.scorePoint(1);
		game.scorePoint(0);
		assertEquals("Deuce", game.getCurrentScore(0));
		assertEquals("Deuce", game.getCurrentScore(1));
		assertEquals(-1, game.getWinner());
	}

	@Test public void advantage0() {
		Game game = new Game();
		game.scorePoint(0);
		game.scorePoint(0);
		game.scorePoint(0);
		game.scorePoint(1);
		game.scorePoint(1);
		game.scorePoint(1);
		game.scorePoint(0);
		assertEquals("Advantage", game.getCurrentScore(0));
		assertEquals("Deuce", game.getCurrentScore(1));
		assertEquals(-1, game.getWinner());
	}

	@Test public void advantage1() {
		Game game = new Game();
		game.scorePoint(0);
		game.scorePoint(0);
		game.scorePoint(0);
		game.scorePoint(1);
		game.scorePoint(1);
		game.scorePoint(1);
		game.scorePoint(1);
		assertEquals("Deuce", game.getCurrentScore(0));
		assertEquals("Advantage", game.getCurrentScore(1));
		assertEquals(-1, game.getWinner());
	}

	@Test public void advantageToDeuce1() {
		Game game = new Game();
		game.scorePoint(0);
		game.scorePoint(0);
		game.scorePoint(0);
		game.scorePoint(1);
		game.scorePoint(1);
		game.scorePoint(1);
		game.scorePoint(0);
		game.scorePoint(1);
		assertEquals("Deuce", game.getCurrentScore(0));
		assertEquals("Deuce", game.getCurrentScore(1));
		assertEquals(-1, game.getWinner());
	}

	@Test public void advantageToDeuce0() {
		Game game = new Game();
		game.scorePoint(0);
		game.scorePoint(0);
		game.scorePoint(0);
		game.scorePoint(1);
		game.scorePoint(1);
		game.scorePoint(1);
		game.scorePoint(1);
		game.scorePoint(0);
		assertEquals("Deuce", game.getCurrentScore(0));
		assertEquals("Deuce", game.getCurrentScore(1));
		assertEquals(-1, game.getWinner());
	}

	@Test public void advantageToWin0() {
		Game game = new Game();
		game.scorePoint(0);
		game.scorePoint(0);
		game.scorePoint(0);
		game.scorePoint(1);
		game.scorePoint(1);
		game.scorePoint(1);
		game.scorePoint(0);
		game.scorePoint(0);
		assertEquals("Winner", game.getCurrentScore(0));
		assertEquals("Deuce", game.getCurrentScore(1));
		assertEquals(0, game.getWinner());
	}

	@Test public void advantageToWin1() {
		Game game = new Game();
		game.scorePoint(0);
		game.scorePoint(0);
		game.scorePoint(0);
		game.scorePoint(1);
		game.scorePoint(1);
		game.scorePoint(1);
		game.scorePoint(1);
		game.scorePoint(1);
		assertEquals("Deuce", game.getCurrentScore(0));
		assertEquals("Winner", game.getCurrentScore(1));
		assertEquals(1, game.getWinner());
	}

	@Test public void advantageToDeuceToWin0() {
		Game game = new Game();
		game.scorePoint(0);
		game.scorePoint(0);
		game.scorePoint(0);
		game.scorePoint(1);
		game.scorePoint(1);
		game.scorePoint(1);
		game.scorePoint(0);
		game.scorePoint(1);
		game.scorePoint(0);
		game.scorePoint(0);
		assertEquals("Winner", game.getCurrentScore(0));
		assertEquals("Deuce", game.getCurrentScore(1));
		assertEquals(0, game.getWinner());
	}
}