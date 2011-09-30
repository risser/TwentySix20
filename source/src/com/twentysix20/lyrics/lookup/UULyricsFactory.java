package com.twentysix20.lyrics.lookup;

import com.twentysix20.lyrics.BaseLyricHandlerFactory;
import com.twentysix20.lyrics.LyricPageHandler;
import com.twentysix20.util.html.InternetHtmlLoader;

public class UULyricsFactory extends BaseLyricHandlerFactory {
	public static final String NAME = "UULyrics";

	public UULyricsFactory(InternetHtmlLoader loader) {
		super(loader);
	}

	@Override
	public LyricPageHandler getLyricPageHandler(String urlString) {
		return new UULyricsHandler(NAME, loader, urlString);
	}

	@Override
	public boolean matches(String url) {
		return url.matches(".*uulyrics.com/music/.+/song\\-.+/");
	}

	@Override
	public boolean verify() {
		String verificationURL = "http://www.uulyrics.com/music/scissors-for-lefty/song-next-to-argyle/";
		String verificationLyricStart = "Next To Argyle";
		String verificationLyricEnd = "...but I'm not";
		String verificationArtist = "Scissors for Lefty";
		String verificationTitle = "Next To Argyle";
		return verify(verificationURL, verificationLyricStart, verificationLyricEnd, verificationArtist, verificationTitle);
	}

	@Override
	public String nameOfSite() {
		return NAME;
	}
}
