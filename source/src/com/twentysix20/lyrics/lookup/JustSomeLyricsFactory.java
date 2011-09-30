package com.twentysix20.lyrics.lookup;

import com.twentysix20.lyrics.BaseLyricHandlerFactory;
import com.twentysix20.lyrics.LyricPageHandler;
import com.twentysix20.util.html.InternetHtmlLoader;

public class JustSomeLyricsFactory extends BaseLyricHandlerFactory {
	public static final String NAME = "JustSomeLyrics";

	public JustSomeLyricsFactory(InternetHtmlLoader loader) {
		super(loader);
	}

	@Override
	public LyricPageHandler getLyricPageHandler(String urlString) {
		return new JustSomeLyricsHandler(NAME, loader, urlString);
	}

	@Override
	public boolean matches(String url) {
		return url.contains("justsomelyrics.com") && url.endsWith("-Lyrics");
	}

	@Override
	public boolean verify() {
		String verificationURL = "http://www.justsomelyrics.com/389797/Pentagram-Forever-My-Queen-Lyrics";
		String verificationLyricStart = "I ran away with myself";
		String verificationLyricEnd = "Lookout";
		String verificationArtist = "Pentagram";
		String verificationTitle = "Forever My Queen";
		return verify(verificationURL, verificationLyricStart, verificationLyricEnd, verificationArtist, verificationTitle);
	}

	@Override
	public String nameOfSite() {
		return NAME;
	}
}
