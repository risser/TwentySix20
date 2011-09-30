package com.twentysix20.lyrics;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;


public class LyricHandlerFactoryFactoryTest {
	@Test public void emptyFactory() throws Exception {
		LyricHandlerFactoryFactory factory = new LyricHandlerFactoryFactory();
		String url = "http://www.azlyrics.com/lyrics/michaeljackson/beatit.html";
		assertFalse(factory.hasHandlerFactory(url));
		assertNull(factory.getHandlerFactory(url));
	}

	@Test public void noMatchFactory() throws Exception {
		LyricHandlerFactoryFactory factory = new LyricHandlerFactoryFactory();
		LyricHandlerFactory mockParser = mock(LyricHandlerFactory.class);
		factory.addParserFactory(mockParser);
		String url = "http://www.azlyrics.com/lyrics/michaeljackson/beatit.html";
		assertFalse(factory.hasHandlerFactory(url));
		assertNull(factory.getHandlerFactory(url));
	}

	@Test public void matchFactory() throws Exception {
		String url = "http://www.azlyrics.com/lyrics/michaeljackson/beatit.html";
		LyricHandlerFactoryFactory factory = new LyricHandlerFactoryFactory();
		LyricHandlerFactory mockParser = mock(LyricHandlerFactory.class);
		when(mockParser.matches(url)).thenReturn(true);

		factory.addParserFactory(mockParser);
		assertTrue(factory.hasHandlerFactory(url));
		assertNotNull(factory.getHandlerFactory(url));
	}

	@Test public void twoParsers() throws Exception {
		String lyrics007 = "http://www.lyrics007.com/Michael%2520Jackson%2520Lyrics/Beat%2520It%2520Lyrics.html";
		String azlyrics = "http://www.azlyrics.com/lyrics/michaeljackson/beatit.html";

		LyricHandlerFactory mockAZLyricsParser = mock(LyricHandlerFactory.class);
		when(mockAZLyricsParser.matches(azlyrics)).thenReturn(true);

		LyricHandlerFactory mockLyrics007Parser = mock(LyricHandlerFactory.class);
		when(mockLyrics007Parser.matches(lyrics007)).thenReturn(true);

		LyricHandlerFactoryFactory factory = new LyricHandlerFactoryFactory();
		factory.addParserFactory(mockLyrics007Parser);
		factory.addParserFactory(mockAZLyricsParser);

		assertTrue(factory.hasHandlerFactory(azlyrics));
		assertTrue(factory.hasHandlerFactory(lyrics007));
		assertEquals(mockAZLyricsParser, factory.getHandlerFactory(azlyrics));
		assertEquals(mockLyrics007Parser, factory.getHandlerFactory(lyrics007));
	}

	
}
