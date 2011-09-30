package com.twentysix20.minesweeper;

import java.util.HashSet;
import java.util.Set;

public class MineSolver {
	private Board board;
	private boolean flagged[][];
	private Set<Position> positionsToRevisit = new HashSet<Position>();

	public MineSolver(Board board) {
		this.board = board;
		flagged = new boolean[board.getY()][board.getX()];
		Position p = new Position(randomInteger(board.getX()),randomInteger(board.getY()));
		clearOne(p);
	}

	public void clearSomeSquares() {
		boolean clearedSome = false;
		for (Position position : new HashSet<Position>(positionsToRevisit)) {
			boolean clearedIt = false;
			flagAllWhenBombNumberMatchesUnclearedNeighbors(position);
			clearedIt |= clearAroundZero(position);
			clearedIt |= clearWhenBombNumberMatchesFlaggedNeighbors(position);
			if (clearedIt)
				positionsToRevisit.remove(position);
			clearedSome |= clearedIt;
		}
		if (!clearedSome) {
			Position p = new Position(randomInteger(board.getX()),randomInteger(board.getY()));
			while (isFlagged(p) || board.isCleared(p))
				p = new Position(randomInteger(board.getX()),randomInteger(board.getY()));
			clearOne(p);
		}
	}

	private boolean isFlagged(Position p) {
		return flagged[p.y][p.x];
	}

	private boolean clearWhenBombNumberMatchesFlaggedNeighbors(Position position) {
//System.out.println(position+": "+nearbyFlagged(position)+" <-> "+board.nearbyUncleared(position));			
		if (nearbyFlagged(position) == board.nearbyUncleared(position)) {
			clearUnflaggedAllAround(position);
			return true;
		}
		return false;
	}

	private void flagAllWhenBombNumberMatchesUnclearedNeighbors(Position position) {
		int nearbyBombs = board.nearbyBombs(position);
		int nearbyUncleared = board.nearbyUncleared(position);
		int nearbyFlagged = nearbyFlagged(position);
		if (nearbyFlagged < nearbyUncleared && nearbyBombs == nearbyUncleared) {
			flagUnclearedAllAround(position);
		}
	}

	private boolean clearAroundZero(Position position) {
		if (board.nearbyBombs(position) == 0) {
			clearUnflaggedAllAround(position);
			return true;
		}
		return false;
	}

	private void clearUnflaggedAllAround(Position p) {
		for (int j = p.y - 1; j <= p.y + 1; j++)
			for (int i = p.x - 1; i <= p.x + 1; i++) {
				if ((p.x==i) && (p.y==j)) continue;
				try {
					if (!flagged[j][i])
						clearOne(new Position(i,j));
				} catch (IndexOutOfBoundsException e) {}
			}
	}

	private void flagUnclearedAllAround(Position p) {
		for (int j = p.y - 1; j <= p.y + 1; j++)
			for (int i = p.x - 1; i <= p.x + 1; i++) {
				if ((p.x==i) && (p.y==j)) continue;
				try {
					if (!board.isCleared(p))
						flagged[j][i] = true;
				} catch (IndexOutOfBoundsException e) {}
			}
	}

	private void clearOne(Position p) {
		if (!board.isCleared(p)) {
			board.clear(p);
			positionsToRevisit.add(p);
		}
	}

	public int nearbyFlagged(Position p) {
		int flagCount = 0;
		for (int j = p.y - 1; j <= p.y + 1; j++)
			for (int i = p.x - 1; i <= p.x + 1; i++) {
				if ((p.x==i) && (p.y==j)) continue;
				try {
					if (flagged[j][i])
						flagCount++;
				} catch (IndexOutOfBoundsException e) {}
			}
		return flagCount;
	}

	private static int randomInteger(int x) {
		return (int)Math.round(Math.floor(Math.random() * x));
	}

}
