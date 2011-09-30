package com.twentysix20.lyrics.lookup;

import com.twentysix20.lyrics.BaseLyricHandlerFactory;
import com.twentysix20.lyrics.LyricPageHandler;
import com.twentysix20.util.html.InternetHtmlLoader;

public class Sing365Factory extends BaseLyricHandlerFactory {
	public static final String NAME = "Sing365";

	public Sing365Factory(InternetHtmlLoader loader) {
		super(loader);
	}

	@Override
	public LyricPageHandler getLyricPageHandler(String urlString) {
		return new Sing365Handler(NAME, loader, urlString);
	}

	@Override
	public boolean matches(String url) {
		return url.matches(".*sing365.com/music/[lL]yric.nsf/.+\\-lyrics\\-.+/.+");
	}

	@Override
	public boolean verify() {
		String verificationURL = "http://www.sing365.com/music/lyric.nsf/No-One-Knows-lyrics-Queens-Of-the-Stone-Age/3E072161AFE6A74C48256C31001B0A55";
		String verificationLyricStart = "We get some rules to follow";
		String verificationLyricEnd = "No one knows";
		String verificationArtist = "QUEENS OF THE STONE AGE";
		String verificationTitle = "NO ONE KNOWS";
		return verify(verificationURL, verificationLyricStart, verificationLyricEnd, verificationArtist, verificationTitle);
	}

	@Override
	public String nameOfSite() {
		return NAME;
	}
}