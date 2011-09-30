package com.twentysix20.lyrics;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.twentysix20.util.FileUtil;
import com.twentysix20.util.html.HtmlLoader;


public class GoogleURLLookupTest {

	@Test public void blankFile() throws Exception {
		HtmlLoader loader = mock(HtmlLoader.class);
		when(loader.getHtmlPage(anyString())).thenReturn("");
		GoogleURLLookup urlReturn = new GoogleURLLookup(loader);
		List<URL> urls = urlReturn.lookup("Yes A Venture");
		assertEquals(new ArrayList<URL>(), urls);
	}

	@Test public void browserlookup() throws Exception {
		HtmlLoader loader = mock(HtmlLoader.class);
		when(loader.getHtmlPage(anyString())).thenReturn(FileUtil.readFileToString(this.getClass(),"browserlookup"));
		GoogleURLLookup urlReturn = new GoogleURLLookup(loader);
		List<URL> urls = urlReturn.lookup("Michael Jackson Beat It");
		ArrayList<URL> expected = new ArrayList<URL>();
		expected.add(new URL("http://www.lyrics007.com/Michael%20Jackson%20Lyrics/Beat%20It%20Lyrics.html"));
		expected.add(new URL("http://www.azlyrics.com/lyrics/michaeljackson/beatit.html"));
		expected.add(new URL("http://www.elyrics.net/read/m/michael-jackson-lyrics/beat-it-lyrics.html"));
		expected.add(new URL("http://www.sing365.com/music/lyric.nsf/beat-it-lyrics-michael-jackson/34fe024f05e6ee934825688e0026abc2"));
		expected.add(new URL("http://www.songlyrics.com/michael-jackson/beat-it-lyrics/"));
		expected.add(new URL("http://www.metrolyrics.com/beat-it-lyrics-michael-jackson.html"));
		expected.add(new URL("http://www.lyricsmode.com/lyrics/m/michael_jackson/beat_it.html"));
		expected.add(new URL("http://www.mp3lyrics.org/m/michael-jackson/beat-it/"));
		expected.add(new URL("http://www.lyricsfire.com/viewlyrics/michael-jackson/beat-it-lyrics.htm"));
		assertEquals(expected, urls);
	}

	@Test public void nonbrowser() throws Exception {
		HtmlLoader loader = mock(HtmlLoader.class);
		when(loader.getHtmlPage(anyString())).thenReturn(FileUtil.readFileToString(this.getClass(),"nonbrowserlookup"));
		GoogleURLLookup urlReturn = new GoogleURLLookup(loader);
		List<URL> urls = urlReturn.lookup("Michael Jackson Beat It");
		ArrayList<URL> expected = new ArrayList<URL>();
		expected.add(new URL("http://www.lyrics007.com/Michael%2520Jackson%2520Lyrics/Beat%2520It%2520Lyrics.html"));
		expected.add(new URL("http://www.azlyrics.com/lyrics/michaeljackson/beatit.html"));
		expected.add(new URL("http://www.elyrics.net/read/m/michael-jackson-lyrics/beat-it-lyrics.html"));
		expected.add(new URL("http://www.sing365.com/music/lyric.nsf/beat-it-lyrics-michael-jackson/34fe024f05e6ee934825688e0026abc2"));
		expected.add(new URL("http://www.songlyrics.com/michael-jackson/beat-it-lyrics/"));
		expected.add(new URL("http://www.metrolyrics.com/beat-it-lyrics-michael-jackson.html"));
		expected.add(new URL("http://www.lyricsmode.com/lyrics/m/michael_jackson/beat_it.html"));
		expected.add(new URL("http://www.mp3lyrics.org/m/michael-jackson/beat-it/"));
		expected.add(new URL("http://www.lyricsfire.com/viewlyrics/michael-jackson/beat-it-lyrics.htm"));
		assertEquals(expected, urls);
	}
}
