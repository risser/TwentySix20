package com.twentysix20.lyrics.lookup;

import com.twentysix20.lyrics.BaseLyricHandlerFactory;
import com.twentysix20.lyrics.LyricPageHandler;
import com.twentysix20.util.html.InternetHtmlLoader;

public class LyricsVIPFactory extends BaseLyricHandlerFactory {
	public static final String NAME = "LyricsVIP";

	public LyricsVIPFactory(InternetHtmlLoader loader) {
		super(loader);
	}

	@Override
	public LyricPageHandler getLyricPageHandler(String urlString) {
		return new LyricsVIPHandler(NAME, loader, urlString);
	}

	@Override
	public boolean matches(String url) {
		return url.contains("lyricsvip.com") && url.endsWith("Lyrics.html");
	}

	@Override
	public boolean verify() {
		String verificationURL = "http://www.lyricsvip.com/Sesame-Street/The-Twelve-Days-of-Christmas-Lyrics.html";
		String verificationLyricStart = "The Twelve days of Christmas";
		String verificationLyricEnd = "not and say we did";
		String verificationArtist = "Sesame Street";
		String verificationTitle = "The Twelve Days of Christmas";
		return verify(verificationURL, verificationLyricStart, verificationLyricEnd, verificationArtist, verificationTitle);
	}

	@Override
	public String nameOfSite() {
		return NAME;
	}
}
