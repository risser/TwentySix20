package com.twentysix20.media.tvboxer;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import com.twentysix20.media.tvboxer.FileToShowMatcher;
import com.twentysix20.media.webreaders.movie.ShowData;


public class TestFileToShowMatcher {
	@Test public void testMatches() {
		ShowData show = createShow("Psych", 2, 3);
		FileToShowMatcher matcher = new FileToShowMatcher();
		File file = new File("Psych 02.03 Test Show.mp4");
		assertTrue(matcher.matches(file, show));
	}

	@Test public void testNonMatchTitle() {
		ShowData show = createShow("Get Smart", 2, 3);
		FileToShowMatcher matcher = new FileToShowMatcher();
		File file = new File("Got Smart 02.03 Test Show.mp4");
		assertFalse(matcher.matches(file, show));
	}

	@Test public void testNonMatchSeason() {
		ShowData show = createShow("Psych", 1, 3);
		FileToShowMatcher matcher = new FileToShowMatcher();
		File file = new File("Psych 02.03 Test Show.mp4");
		assertFalse(matcher.matches(file, show));
	}

	@Test public void testNonMatchEpisode() {
		ShowData show = createShow("Psych", 1, 3);
		FileToShowMatcher matcher = new FileToShowMatcher();
		File file = new File("Psych 01.02 Test Show.mp4");
		assertFalse(matcher.matches(file, show));
	}

	@Test public void testMatchesTens() {
		ShowData show = createShow("Psych", 10, 13);
		FileToShowMatcher matcher = new FileToShowMatcher();
		File file = new File("Psych 10.13 Test Show.mp4");
		assertTrue(matcher.matches(file, show));
	}

	@Test public void testMatchesWithoutThe() {
		ShowData show = createShow("The Cosby Show", 1, 1);
		FileToShowMatcher matcher = new FileToShowMatcher();
		File file = new File("Cosby Show 01.01 Test Show.mp4");
		assertTrue(matcher.matches(file, show));
	}

	@Test public void testNoMatchWithThemPrefix() {
		ShowData show = createShow("Them Squad", 1, 1);
		FileToShowMatcher matcher = new FileToShowMatcher();
		File file = new File("M Squad 01.01 Test Show.mp4");
		assertFalse(matcher.matches(file, show));
	}

	private ShowData createShow(String title, int season, int episode) {
		ShowData show = new ShowData();
		show.setShowName(title);
		show.setSeason(season);
		show.setEpisode(episode);
		return show;
	}
}