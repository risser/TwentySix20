package com.twentysix20.minesweeper;

public class Board {
	private boolean[][] bombs;
	private boolean[][] cleared;
	private int x,y;
	private int bombTotal = 0;
	private int clearedTotal = 0;

	public Board(int x, int y) {
		this.x = x;
		this.y = y;
		bombs = new boolean[y][x];
		cleared = new boolean[y][x];
	}

	public void setBomb(Position p) {
		if (!bombs[p.y][p.x]) {
			bombs[p.y][p.x] = true;
			bombTotal++;
		}
	}

	public boolean clear(Position p) {
		if (cleared[p.y][p.x])
			return true;
		cleared[p.y][p.x] = true;
		clearedTotal++;
		return !bombs[p.y][p.x]; 
	}

	protected int getX() {
		return x;
	}

	protected int getY() {
		return y;
	}

	public int getBombCount() {
		return bombTotal;
	}

	public int getClearedCount() {
		return clearedTotal;
	}

	public boolean isCleared(Position p) {
		return cleared[p.y][p.x];
	}

	public boolean solved() {
		return bombTotal == x * y - clearedTotal;
	}

	public String bombLayout() {
		StringBuilder s = new StringBuilder();
		for (boolean[] row : bombs) {
			for (boolean square : row) {
				s.append(square ? '*' : '.');
			}
			s.append('\n');
		}
		return s.toString();
	}

	public String clearedSoFar() {
		StringBuilder s = new StringBuilder();
		for (int j = 0; j < this.y; j++) {
			for (int i = 0; i < this.x; i++) {
				Position p = new Position(i,j);
				String c = cleared[j][i] ? (bombs[j][i] ? "*" : (nearbyBombs(p) == 0 ? "·" : ""+nearbyBombs(p))) : "÷";
//System.out.println(i+":"+j+":"+c);				
				s.append(c);
			}
			s.append('\n');
		}
		return s.toString();
	}

	public int nearbyBombs(Position p) {
		int bombCount = 0;
		for (int j = p.y - 1; j <= p.y + 1; j++)
			for (int i = p.x - 1; i <= p.x + 1; i++) {
				if ((p.x==i) && (p.y==j)) continue;
				try {
					if (bombs[j][i])
						bombCount++;
				} catch (IndexOutOfBoundsException e) {}
			}
		return bombCount;
	}

	public int nearbyUncleared(Position p) {
		int bombCount = 0;
		for (int j = p.y - 1; j <= p.y + 1; j++)
			for (int i = p.x - 1; i <= p.x + 1; i++) {
				if ((p.x==i) && (p.y==j)) continue;
				try {
					if (bombs[j][i])
						bombCount++;
				} catch (IndexOutOfBoundsException e) {}
			}
		return bombCount;
	}

	public boolean finished() {
		return solved() || uncoveredBomb();
	}

	public boolean uncoveredBomb() {
		boolean uncoveredBomb = false;
		for (int j = 0; j < this.y; j++)
			for (int i = 0; i < this.x; i++)
				uncoveredBomb |= (cleared[j][i] && bombs[j][i]);
		return uncoveredBomb;
	}
}
