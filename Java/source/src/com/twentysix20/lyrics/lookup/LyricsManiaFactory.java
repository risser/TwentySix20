package com.twentysix20.lyrics.lookup;

import com.twentysix20.lyrics.BaseLyricHandlerFactory;
import com.twentysix20.lyrics.LyricPageHandler;
import com.twentysix20.util.html.InternetHtmlLoader;

public class LyricsManiaFactory extends BaseLyricHandlerFactory {
	public static final String NAME = "LyricsMania";

	public LyricsManiaFactory(InternetHtmlLoader loader) {
		super(loader);
	}

	@Override
	public LyricPageHandler getLyricPageHandler(String urlString) {
		return new LyricsManiaHandler(NAME, loader, urlString);
	}

	@Override
	public boolean matches(String url) {
		return url.contains("lyricsmania.com") && url.contains("_lyrics_") && !url.contains("_album_lyrics_");
	}

	@Override
	public boolean verify() {
		String verificationURL = "http://www.lyricsmania.com/10,000_angels_lyrics_edie_brickell_and_the_new_bohemians.html";
		String verificationLyricStart = "I'm feeling feelings like I never felt before";
		String verificationLyricEnd = "the Lord my soul to keep";
		String verificationArtist = "Edie Brickell And The New Bohemians";
		String verificationTitle = "10,000 Angels";
		return verify(verificationURL, verificationLyricStart, verificationLyricEnd, verificationArtist, verificationTitle);
	}

	@Override
	public String nameOfSite() {
		return NAME;
	}
}
