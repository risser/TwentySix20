package com.twentysix20.kata.tennis2;

import org.junit.Test;
import static org.junit.Assert.*;


public class TennisGameTest {
	@Test public void noScore() {
		TennisGame game = new TennisGame();
		assertEquals("Love - Love", game.getScore());
	}

	@Test public void oneScore() {
		TennisGame game = new TennisGame();
		game.servingPlayerScores();
		assertEquals("15 - Love", game.getScore());
	}

	@Test public void otherScore() {
		TennisGame game = new TennisGame();
		game.receivingPlayerScores();
		assertEquals("Love - 15", game.getScore());
	}

	@Test public void serverScoresTwice() {
		TennisGame game = new TennisGame();
		game.servingPlayerScores();
		game.servingPlayerScores();
		assertEquals("30 - Love", game.getScore());
	}

	@Test public void receiverScoresTwice() {
		TennisGame game = new TennisGame();
		game.receivingPlayerScores();
		game.receivingPlayerScores();
		assertEquals("Love - 30", game.getScore());
	}

	@Test public void mixedScores1() {
		TennisGame game = new TennisGame();
		game.servingPlayerScores();
		game.servingPlayerScores();
		game.servingPlayerScores();
		game.receivingPlayerScores();
		game.receivingPlayerScores();
		assertEquals("40 - 30", game.getScore());
	}

	@Test public void mixedScores2() {
		TennisGame game = new TennisGame();
		game.receivingPlayerScores();
		game.servingPlayerScores();
		game.receivingPlayerScores();
		game.servingPlayerScores();
		game.receivingPlayerScores();
		assertEquals("30 - 40", game.getScore());
	}

	@Test public void serverWinsStraightPoints() {
		TennisGame game = new TennisGame();
		game.servingPlayerScores();
		game.servingPlayerScores();
		game.servingPlayerScores();
		game.servingPlayerScores();
		assertEquals("Game: Server", game.getScore());
	}

	@Test public void receiverWinsStraightPoints() {
		TennisGame game = new TennisGame();
		game.receivingPlayerScores();
		game.receivingPlayerScores();
		game.receivingPlayerScores();
		game.receivingPlayerScores();
		assertEquals("Game: Receiver", game.getScore());
	}

	@Test public void mixedWinServer() {
		TennisGame game = new TennisGame();
		game.servingPlayerScores();
		game.receivingPlayerScores();
		game.servingPlayerScores();
		game.servingPlayerScores();
		game.receivingPlayerScores();
		game.servingPlayerScores();
		assertEquals("Game: Server", game.getScore());
	}

	@Test public void mixedWinReceiver() {
		TennisGame game = new TennisGame();
		game.receivingPlayerScores();
		game.servingPlayerScores();
		game.receivingPlayerScores();
		game.receivingPlayerScores();
		game.servingPlayerScores();
		game.receivingPlayerScores();
		assertEquals("Game: Receiver", game.getScore());
	}

	@Test public void tie15() {
		TennisGame game = new TennisGame();
		game.receivingPlayerScores();
		game.servingPlayerScores();
		assertEquals("15 - All", game.getScore());
	}

	@Test public void tie30() {
		TennisGame game = new TennisGame();
		game.receivingPlayerScores();
		game.servingPlayerScores();
		game.receivingPlayerScores();
		game.servingPlayerScores();
		assertEquals("30 - All", game.getScore());
	}

	@Test public void deuce() {
		TennisGame game = new TennisGame();
		game.receivingPlayerScores();
		game.servingPlayerScores();
		game.receivingPlayerScores();
		game.servingPlayerScores();
		game.receivingPlayerScores();
		game.servingPlayerScores();
		assertEquals("Deuce", game.getScore());
	}

	@Test public void backToDeuce() {
		TennisGame game = new TennisGame();
		game.receivingPlayerScores();
		game.servingPlayerScores();
		game.receivingPlayerScores();
		game.servingPlayerScores();
		game.servingPlayerScores();
		game.receivingPlayerScores();
		game.receivingPlayerScores();
		game.servingPlayerScores();
		assertEquals("Deuce", game.getScore());
	}

	@Test public void backToDeuce2() {
		TennisGame game = new TennisGame();
		game.receivingPlayerScores();
		game.servingPlayerScores();
		game.receivingPlayerScores();
		game.servingPlayerScores();
		game.servingPlayerScores();
		game.receivingPlayerScores();
		game.receivingPlayerScores();
		game.servingPlayerScores();
		game.receivingPlayerScores();
		game.servingPlayerScores();
		game.receivingPlayerScores();
		game.servingPlayerScores();
		assertEquals("Deuce", game.getScore());
	}

	@Test public void deuceToWin() {
		TennisGame game = new TennisGame();
		game.receivingPlayerScores();
		game.servingPlayerScores();
		game.receivingPlayerScores();
		game.servingPlayerScores();
		game.servingPlayerScores();
		game.receivingPlayerScores();
		game.receivingPlayerScores();
		game.receivingPlayerScores();
		assertEquals("Game: Receiver", game.getScore());
	}

	@Test public void deuceToAdvantageServer() {
		TennisGame game = new TennisGame();
		game.receivingPlayerScores();
		game.servingPlayerScores();
		game.receivingPlayerScores();
		game.servingPlayerScores();
		game.receivingPlayerScores();
		game.servingPlayerScores();
		game.servingPlayerScores();
		assertEquals("Advantage: Server", game.getScore());
	}

	@Test public void deuceToAdvantageReceiver() {
		TennisGame game = new TennisGame();
		game.receivingPlayerScores();
		game.servingPlayerScores();
		game.receivingPlayerScores();
		game.servingPlayerScores();
		game.receivingPlayerScores();
		game.servingPlayerScores();
		game.receivingPlayerScores();
		assertEquals("Advantage: Receiver", game.getScore());
	}

	@Test public void backToDeuceToAdvantageServer() {
		TennisGame game = new TennisGame();
		game.receivingPlayerScores();
		game.servingPlayerScores();
		game.receivingPlayerScores();
		game.servingPlayerScores();
		game.receivingPlayerScores();
		game.servingPlayerScores();
		game.receivingPlayerScores();
		game.servingPlayerScores();
		game.servingPlayerScores();
		assertEquals("Advantage: Server", game.getScore());
	}

	@Test public void backToDeuceToAdvantageReceiver() {
		TennisGame game = new TennisGame();
		game.receivingPlayerScores();
		game.servingPlayerScores();
		game.receivingPlayerScores();
		game.servingPlayerScores();
		game.receivingPlayerScores();
		game.servingPlayerScores();
		game.receivingPlayerScores();
		game.servingPlayerScores();
		game.receivingPlayerScores();
		assertEquals("Advantage: Receiver", game.getScore());
	}

	@Test public void backToDeuceToGameServer() {
		TennisGame game = new TennisGame();
		game.receivingPlayerScores();
		game.servingPlayerScores();
		game.receivingPlayerScores();
		game.servingPlayerScores();
		game.receivingPlayerScores();
		game.servingPlayerScores();
		game.receivingPlayerScores();
		game.servingPlayerScores();
		game.receivingPlayerScores();
		game.servingPlayerScores();
		game.servingPlayerScores();
		game.servingPlayerScores();
		assertEquals("Game: Server", game.getScore());
	}

	@Test public void backToDeuceToGameReceiver() {
		TennisGame game = new TennisGame();
		game.receivingPlayerScores();
		game.servingPlayerScores();
		game.receivingPlayerScores();
		game.servingPlayerScores();
		game.receivingPlayerScores();
		game.servingPlayerScores();
		game.receivingPlayerScores();
		game.servingPlayerScores();
		game.receivingPlayerScores();
		game.servingPlayerScores();
		game.receivingPlayerScores();
		game.receivingPlayerScores();
		assertEquals("Game: Receiver", game.getScore());
	}
}
