package com.twentysix20.lyrics.lookup;

import com.twentysix20.lyrics.BaseLyricHandlerFactory;
import com.twentysix20.lyrics.LyricPageHandler;
import com.twentysix20.util.html.InternetHtmlLoader;

public class LyricsFreakFactory extends BaseLyricHandlerFactory {
	public static final String NAME = "LyricsFreak";

	public LyricsFreakFactory(InternetHtmlLoader loader) {
		super(loader);
	}

	@Override
	public boolean matches(String url) {
		return url.matches(".*lyricsfreak.com/.+html");
	}

	@Override
	public boolean verify() {
		String verificationURL = "http://www.lyricsfreak.com/t/throwing+muses/reel_20137075.html";
		String verificationLyricStart = "I don't want this";
		String verificationLyricEnd = "Tell it";
		String verificationArtist = "Throwing Muses";
		String verificationTitle = "Reel";
		return verify(verificationURL, verificationLyricStart, verificationLyricEnd, verificationArtist, verificationTitle);
	}

	@Override
	public LyricPageHandler getLyricPageHandler(String urlString) {
		return new LyricsFreakHandler(NAME, loader, urlString);
	}

	@Override
	public String nameOfSite() {
		return NAME;
	}

	static public void main (String s[]) {
		new LyricsFreakFactory(new InternetHtmlLoader()).verify();
	}
}