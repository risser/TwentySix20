package com.twentysix20.lyrics.lookup;

import com.twentysix20.lyrics.BaseLyricHandlerFactory;
import com.twentysix20.lyrics.LyricPageHandler;
import com.twentysix20.util.html.InternetHtmlLoader;

public class Lyrics007Factory extends BaseLyricHandlerFactory {
	public static final String NAME = "Lyrics007";

	public Lyrics007Factory(InternetHtmlLoader loader) {
		super(loader);
	}

	@Override
	public boolean matches(String url) {
		return url.matches(".*lyrics007.com/.+Lyrics/.+Lyrics.html");
	}

	@Override
	public boolean verify() {
		String verificationURL = "http://www.lyrics007.com/wham!%2520Lyrics/Last%2520Christmas%2520Lyrics.html";
		String verificationLyricStart = "Chorus:";
		String verificationLyricEnd = "give it to someone";
		String verificationArtist = "wham!";
		String verificationTitle = "Last Christmas";
		return verify(verificationURL, verificationLyricStart, verificationLyricEnd, verificationArtist, verificationTitle);
	}

	@Override
	public LyricPageHandler getLyricPageHandler(String urlString) {
		return new Lyrics007Handler(NAME, loader, urlString);
	}

	@Override
	public String nameOfSite() {
		return NAME;
	}
}