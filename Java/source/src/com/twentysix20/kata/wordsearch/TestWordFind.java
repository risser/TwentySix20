package com.twentysix20.kata.wordsearch;

import java.util.List;

import com.twentysix20.testutil.TestCase2620;

public class TestWordFind extends TestCase2620 {
	public void testFoundForward() throws Exception {
		Puzzle puz = new Puzzle("","CATZ","ZZZZ","ZZZZ","ZZZZ");
		List<PuzzleLocation> list = WordFinder.find("CAT",puz);
		assertLocation(0,0,PuzzleLocation.DIRECTION_EAST, list.get(0));
	}

	public void testFoundForward_False() throws Exception {
		Puzzle puz = new Puzzle("","CAZT","ZZZZ","ZZZZ","ZZZZ");
		List<PuzzleLocation> list = WordFinder.find("CAT",puz);
		assertSize(0,list);
	}

	public void testFoundBackward() throws Exception {
		Puzzle puz = new Puzzle("","ZZZZ","TACX","ZZZZ","ZZZZ");
		List<PuzzleLocation> list = WordFinder.find("CAT",puz);
		assertLocation(1,2,PuzzleLocation.DIRECTION_WEST, list.get(0));
	}

	public void testFoundDown() throws Exception {
		Puzzle puz = new Puzzle("","ZZZZ","ZCZZ","ZAZZ","ZTZZ");
		List<PuzzleLocation> list = WordFinder.find("CAT",puz);
		assertLocation(1,1,PuzzleLocation.DIRECTION_SOUTH, list.get(0));
	}

	public void testFoundDown_False() throws Exception {
		Puzzle puz = new Puzzle("","ZZZZ","ZZZZ","ZCZZ","ZAZZ");
		List<PuzzleLocation> list = WordFinder.find("CAT",puz);
		assertSize(0,list);
	}

	public void testFoundUp() throws Exception {
		Puzzle puz = new Puzzle("","ZZZZ","ZTZZ","ZAZZ","ZCZZ");
		List<PuzzleLocation> list = WordFinder.find("CAT",puz);
		assertLocation(3,1,PuzzleLocation.DIRECTION_NORTH, list.get(0));
	}

	public void testFoundNE() throws Exception {
		Puzzle puz = new Puzzle("","ZZZZ","ZZZT","ZZAZ","ZCZZ");
		List<PuzzleLocation> list = WordFinder.find("CAT",puz);
		assertLocation(3,1,PuzzleLocation.DIRECTION_NE, list.get(0));
	}

	public void testFoundNW() throws Exception {
		Puzzle puz = new Puzzle("","ZZZZ","ZTZZ","ZZAZ","ZCZC");
		List<PuzzleLocation> list = WordFinder.find("CAT",puz);
		assertLocation(3,3,PuzzleLocation.DIRECTION_NW, list.get(0));
	}

	public void testFoundSE() throws Exception {
		Puzzle puz = new Puzzle("","ZZZZ","ZCZZ","ZZAZ","ZZZT");
		List<PuzzleLocation> list = WordFinder.find("CAT",puz);
		assertLocation(1,1,PuzzleLocation.DIRECTION_SE, list.get(0));
	}

	public void testFoundSW() throws Exception {
		Puzzle puz = new Puzzle("","ZZZC","ZZAZ","ZTZZ","ZZZT");
		List<PuzzleLocation> list = WordFinder.find("CAT",puz);
		assertLocation(0,3,PuzzleLocation.DIRECTION_SW, list.get(0));
	}

	public void testFoundTwo() throws Exception {
		Puzzle puz = new Puzzle("","ZZZZ","ZTZZ","ZAAZ","ZCZC");
		List<PuzzleLocation> list = WordFinder.find("CAT",puz);
		assertLocation(3,1,PuzzleLocation.DIRECTION_NORTH, list.get(0));
		assertLocation(3,3,PuzzleLocation.DIRECTION_NW, list.get(1));
	}

	private void assertLocation(int y, int x, int[] direction, PuzzleLocation loc) {
		assertEquals(y, loc.getY());
		assertEquals(x, loc.getX());
		assertEquals(direction, loc.getDirection());
	}
}