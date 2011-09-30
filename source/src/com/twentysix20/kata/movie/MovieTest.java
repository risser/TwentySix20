package com.twentysix20.kata.movie;

import org.junit.Test;
import static org.junit.Assert.*;


public class MovieTest {
	@Test public void testGeneralAdmission() throws Exception {
		Showing showing = new Showing(90, Day.MON, Floor.PARQUET, false);
		Transaction booth = new Transaction(showing);
		booth.buyTicket(23, false);
		assertTotal(booth, 11.0);
	}
	@Test public void testStudent() throws Exception {
		Showing showing = new Showing(90, Day.MON, Floor.PARQUET, false);
		Transaction booth = new Transaction(showing);
		booth.buyTicket(23, true);
		assertTotal(booth, 8.0);
	}
	@Test public void testJustSenior() throws Exception {
		Showing showing = new Showing(90, Day.MON, Floor.PARQUET, false);
		Transaction booth = new Transaction(showing);
		booth.buyTicket(65, false);
		assertTotal(booth, 6.0);
	}
	@Test public void testAlmostSenior() throws Exception {
		Showing showing = new Showing(90, Day.MON, Floor.PARQUET, false);
		Transaction booth = new Transaction(showing);
		booth.buyTicket(64, false);
		assertTotal(booth, 11.0);
	}
	@Test public void testVerySenior() throws Exception {
		Showing showing = new Showing(90, Day.MON, Floor.PARQUET, false);
		Transaction booth = new Transaction(showing);
		booth.buyTicket(75, false);
		assertTotal(booth, 6.0);
	}
	@Test public void testJustChild() throws Exception {
		Showing showing = new Showing(90, Day.MON, Floor.PARQUET, false);
		Transaction booth = new Transaction(showing);
		booth.buyTicket(13, false);
		assertTotal(booth, 5.5);
	}
	@Test public void testAlmostChild() throws Exception {
		Showing showing = new Showing(90, Day.MON, Floor.PARQUET, false);
		Transaction booth = new Transaction(showing);
		booth.buyTicket(14, false);
		assertTotal(booth, 11.0);
	}
	@Test public void testVeryChild() throws Exception {
		Showing showing = new Showing(90, Day.MON, Floor.PARQUET, false);
		Transaction booth = new Transaction(showing);
		booth.buyTicket(5, false);
		assertTotal(booth, 5.5);
	}
	@Test public void testGroup() throws Exception {
		Showing showing = new Showing(90, Day.MON, Floor.PARQUET, false);
		Transaction booth = new Transaction(showing);
		for (int i = 0; i < 20; i++)
			booth.buyTicket(25, false);

		assertTotal(booth, 120.0);
	}
	@Test public void testAlmostGroup() throws Exception {
		Showing showing = new Showing(90, Day.MON, Floor.PARQUET, false);
		Transaction booth = new Transaction(showing);
		for (int i = 0; i < 19; i++)
			booth.buyTicket(25, false);

		assertTotal(booth, 209.0);
	}
	@Test public void testBigGroup() throws Exception {
		Showing showing = new Showing(90, Day.MON, Floor.PARQUET, false);
		Transaction booth = new Transaction(showing);
		for (int i = 0; i < 100; i++)
			booth.buyTicket(25, false);

		assertTotal(booth, 600.0);
	}

	@Test public void testMovieDay() throws Exception {
		Showing showing = new Showing(90, Day.THU, Floor.PARQUET, false);
		Transaction booth = new Transaction(showing);
		booth.buyTicket(23, false);
		assertTotal(booth, 9.0);
	}

	@Test public void testLongMovie() throws Exception {
		Showing showing = new Showing(121, Day.MON, Floor.PARQUET, false);
		Transaction booth = new Transaction(showing);
		booth.buyTicket(23, false);
		assertTotal(booth, 12.5);
	}

	@Test public void testAlmostLongMovie() throws Exception {
		Showing showing = new Showing(120, Day.MON, Floor.PARQUET, false);
		Transaction booth = new Transaction(showing);
		booth.buyTicket(23, false);
		assertTotal(booth, 11.0);
	}

	@Test public void testVeryLongMovie() throws Exception {
		Showing showing = new Showing(300, Day.MON, Floor.PARQUET, false);
		Transaction booth = new Transaction(showing);
		booth.buyTicket(23, false);
		assertTotal(booth, 12.5);
	}

	@Test public void testLoge() throws Exception {
		Showing showing = new Showing(110, Day.MON, Floor.LOGE, false);
		Transaction booth = new Transaction(showing);
		booth.buyTicket(23, false);
		assertTotal(booth, 13.0);
	}

	@Test public void test3D() throws Exception {
		Showing showing = new Showing(110, Day.MON, Floor.PARQUET, true);
		Transaction booth = new Transaction(showing);
		booth.buyTicket(23, false);
		assertTotal(booth, 14.0);
	}

	@Test public void testCoupleAtLongMovieOnMovieDay() throws Exception {
		Showing showing = new Showing(124, Day.THU, Floor.PARQUET, false);
		Transaction booth = new Transaction(showing);
		booth.buyTicket(25, true);
		booth.buyTicket(23, false);
		assertTotal(booth, 18.0);
	}
//		 ~~ Arriving at your booth: 'Couple' ~~
//		 Today (THU) they want to see a 124 min. long movie from the parquet.
//		  -> 25 years old student buys a ticket
//		  -> 23 years old person buys a ticket
//		  # (1) EXPECTED '18.0' FOR [ Couple ] GOT '0.0'                             #
//
//		  ~~ Arriving at your booth: 'Student Course' ~~
//		 Today (THU) they want to see a 120 min. long movie from the parquet.
//		  -> 21 years old student buys a ticket
//		  -> 23 years old student buys a ticket
//		  -> 23 years old student buys a ticket
//		  -> 21 years old student buys a ticket
//		  -> 21 years old student buys a ticket//		  -> 23 years old student buys a ticket
//		  -> 23 years old student buys a ticket
//		  -> 21 years old student buys a ticket
//		  -> 22 years old student buys a ticket
//		  -> 22 years old student buys a ticket
//		  -> 23 years old student buys a ticket
//		  -> 23 years old student buys a ticket
//		  -> 23 years old student buys a ticket
//		  -> 21 years old student buys a ticket
//		  -> 23 years old student buys a ticket
//		  -> 21 years old student buys a ticket
//		  -> 55 years old person buys a ticket
//		  -> 23 years old student buys a ticket
//		  -> 23 years old student buys a ticket
//		  -> 21 years old student buys a ticket
//		  -> 23 years old student buys a ticket
//		  -> 23 years old student buys a ticket
//		  -> 22 years old student buys a ticket
//		  # (2) EXPECTED '138.0' FOR [ Student Course ] GOT '0.0'                    #
//
//		  ~~ Arriving at your booth: 'Group of Kids' ~~
//		 Today (SUN) they want to see a 91 min. long 3D movie from the parquet.
//		  -> 11 years old person buys a ticket
//		  -> 12 years old person buys a ticket
//		  -> 10 years old person buys a ticket
//		  -> 12 years old person buys a ticket
//		  -> 12 years old person buys a ticket
//		  # (3) EXPECTED '50.0' FOR [ Group of Kids ] GOT '0.0'                      #
//
//		  ~~ Arriving at your booth: 'Family of Four' ~~
//		 Today (SAT) they want to see a 89 min. long movie from the loge.
//		  -> 12 years old person buys a ticket
//		  -> 46 years old person buys a ticket
//		  -> 45 years old person buys a ticket
//		  -> 21 years old student buys a ticket
//		  # (4) EXPECTED '49.5' FOR [ Family of Four ] GOT '0.0'                     #
//
//		  ~~ Arriving at your booth: 'Senior with Grandson' ~~
//		 Today (FRI) they want to see a 95 min. long 3D movie from the parquet.
//		  -> 6 years old person buys a ticket
//		  -> 65 years old person buys a ticket
//		  # (5) EXPECTED '17.5' FOR [ Senior with Grandson ] GOT '0.0'               #;

	private void assertTotal(Transaction booth, double expectedTotal) {
		assertEquals(expectedTotal, booth.total(),0.00001);
	}

}
