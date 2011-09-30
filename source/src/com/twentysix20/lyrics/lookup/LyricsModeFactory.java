package com.twentysix20.lyrics.lookup;

import com.twentysix20.lyrics.BaseLyricHandlerFactory;
import com.twentysix20.lyrics.LyricPageHandler;
import com.twentysix20.util.html.InternetHtmlLoader;

public class LyricsModeFactory extends BaseLyricHandlerFactory {
	public static final String NAME = "LyricsMode";

	public LyricsModeFactory(InternetHtmlLoader loader) {
		super(loader);
	}

	@Override
	public LyricPageHandler getLyricPageHandler(String urlString) {
		return new LyricsModeHandler(NAME, loader, urlString);
	}

	@Override
	public boolean matches(String url) {
		return url.matches(".*lyricsmode.com/lyrics/./.+/.+.html");
	}

	@Override
	public boolean verify() {
		String verificationURL = "http://www.lyricsmode.com/lyrics/g/groovie_ghoulies/graveyard_girlfriend.html";
		String verificationLyricStart = "Down in the graveyard on a Saturday night";
		String verificationLyricEnd = "You're my graveyard girlfriend.";
		String verificationArtist = "Groovie Ghoulies";
		String verificationTitle = "Graveyard Girlfriend";
		return verify(verificationURL, verificationLyricStart, verificationLyricEnd, verificationArtist, verificationTitle);
	}

	@Override
	public String nameOfSite() {
		return NAME;
	}
}
