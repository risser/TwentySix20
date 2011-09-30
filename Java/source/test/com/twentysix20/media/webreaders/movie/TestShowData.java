package com.twentysix20.media.webreaders.movie;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import com.twentysix20.testutil.TestCase2620;

public class TestShowData extends TestCase2620 {

	@Test public void testTabbedConstructor() {
		String exampleData = "The Card Game	The Cosby Show	2	23	csby	Bill Cosby, Keshia Knight Pulliam, Lisa Bonet, Malcolm-Jamal Warner, Phylicia Rashad, Tempestt Bledsoe, Earle Hyman, Tanya Wright, Roscoe Lee Browne, Bill Gunn	Jay Sandrich			Comedy, Family	NR	1986	Cliff's college English professor, Dr. Foster offers to fill in when Cliff's pinochle partner becomes ill. Theo buys a ring for his girlfriend.";
		ShowData data = new ShowData(exampleData);
		assertEquals("The Card Game", data.getEpisodeTitle());
		assertEquals("1986", data.getDate());
		assertEquals(Rating.NR, data.getRating());
		assertEquals("Cliff's college English professor, Dr. Foster offers to fill in when Cliff's pinochle partner becomes ill. Theo buys a ring for his girlfriend.", data.getDescription());
		assertEquals(new Integer("2"), data.getSeason());
		assertEquals(new Integer("23"), data.getEpisode());
		assertEquals("csby", data.getShowCode());
		assertEquals("The Cosby Show", data.getShowName());
		assertEquals(makeList("Jay Sandrich"), data.getDirectors());
		assertEquals(new ArrayList<String>(), data.getWriters());
		assertEquals(makeList("Bill Cosby, Keshia Knight Pulliam, Lisa Bonet, Malcolm-Jamal Warner, Phylicia Rashad, Tempestt Bledsoe, Earle Hyman, Tanya Wright, Roscoe Lee Browne, Bill Gunn"), data.getActors());
		assertEquals(new ArrayList<String>(), data.getStudios());
		assertEquals(makeList("Comedy, Family"), data.getGenres());
	}

	@Test public void testActorsWithNoSpacesAfterCommas() {
		String exampleData = "The Card Game	The Cosby Show	2	23	csby	Bill Cosby,Keshia Knight Pulliam,Lisa Bonet,Malcolm-Jamal Warner,Phylicia Rashad,Tempestt Bledsoe,Earle Hyman	Jay Sandrich			Comedy, Family	NR	1986	Cliff's college English professor, Dr. Foster offers to fill in when Cliff's pinochle partner becomes ill. Theo buys a ring for his girlfriend.";
		ShowData data = new ShowData(exampleData);
		assertEquals("The Card Game", data.getEpisodeTitle());
		assertEquals("1986", data.getDate());
		assertEquals(Rating.NR, data.getRating());
		assertEquals("Cliff's college English professor, Dr. Foster offers to fill in when Cliff's pinochle partner becomes ill. Theo buys a ring for his girlfriend.", data.getDescription());
		assertEquals(new Integer("2"), data.getSeason());
		assertEquals(new Integer("23"), data.getEpisode());
		assertEquals("csby", data.getShowCode());
		assertEquals("The Cosby Show", data.getShowName());
		assertEquals(makeList("Jay Sandrich"), data.getDirectors());
		assertEquals(new ArrayList<String>(), data.getWriters());
		assertEquals(makeList("Bill Cosby, Keshia Knight Pulliam, Lisa Bonet, Malcolm-Jamal Warner, Phylicia Rashad, Tempestt Bledsoe, Earle Hyman"), data.getActors());
		assertEquals(new ArrayList<String>(), data.getStudios());
		assertEquals(makeList("Comedy, Family"), data.getGenres());
	}

	private ArrayList<String> makeList(String string) {
		return new ArrayList<String>(Arrays.asList(string.split(", ")));
	}
}
