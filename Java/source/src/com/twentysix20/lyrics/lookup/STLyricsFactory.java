package com.twentysix20.lyrics.lookup;

import com.twentysix20.lyrics.BaseLyricHandlerFactory;
import com.twentysix20.lyrics.LyricPageHandler;
import com.twentysix20.util.html.InternetHtmlLoader;

public class STLyricsFactory extends BaseLyricHandlerFactory {
	public static final String NAME = "STLyrics";

	public STLyricsFactory(InternetHtmlLoader loader) {
		super(loader);
	}

	@Override
	public LyricPageHandler getLyricPageHandler(String urlString) {
		return new STLyricsHandler(NAME, loader, urlString);
	}

	@Override
	public boolean matches(String url) {
		return url.matches(".*stlyrics.com/songs/./.+/.+.html?") || url.matches(".*stlyrics.com/lyrics/..+/.+.html?");
	}

	@Override
	public boolean verify() {
		String verificationURL = "http://www.stlyrics.com/songs/p/parliament4673/flashlight208970.html";
		String verificationLyricStart = "Now, I lay me down to sleep";
		String verificationLyricEnd = "Under the sun (x9)";
		String verificationArtist = "Parliament";
		String verificationTitle = "Flash Light";
		return verify(verificationURL, verificationLyricStart, verificationLyricEnd, verificationArtist, verificationTitle);
	}

	@Override
	public String nameOfSite() {
		return NAME;
	}
}