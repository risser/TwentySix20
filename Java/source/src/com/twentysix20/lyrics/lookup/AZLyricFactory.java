package com.twentysix20.lyrics.lookup;

import com.twentysix20.lyrics.BaseLyricHandlerFactory;
import com.twentysix20.lyrics.LyricPageHandler;
import com.twentysix20.util.html.InternetHtmlLoader;

public class AZLyricFactory extends BaseLyricHandlerFactory {
	public static final String NAME = "AZLyrics";

	public AZLyricFactory(InternetHtmlLoader loader) {
		super(loader);
	}

	@Override
	public LyricPageHandler getLyricPageHandler(String urlString) {
		return new AZLyricHandler(NAME, loader, urlString);
	}

	@Override
	public boolean matches(String url) {
		return url.contains("azlyrics.com/lyrics");
	}

	@Override
	public boolean verify() {
		String verificationURL = "http://www.azlyrics.com/lyrics/wham/lastchristmas.html";
		String verificationLyricStart = "[CHORUS:]\nLast Christmas";
		String verificationLyricEnd = "I'll give it to someone special.";
		String verificationArtist = "WHAM!";
		String verificationTitle = "Last Christmas";
		return verify(verificationURL, verificationLyricStart, verificationLyricEnd, verificationArtist, verificationTitle);
	}

	@Override
	public String nameOfSite() {
		return NAME;
	}
}
