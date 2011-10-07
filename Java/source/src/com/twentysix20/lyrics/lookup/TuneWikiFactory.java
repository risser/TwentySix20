package com.twentysix20.lyrics.lookup;

import com.twentysix20.lyrics.BaseLyricHandlerFactory;
import com.twentysix20.lyrics.LyricPageHandler;
import com.twentysix20.util.html.InternetHtmlLoader;

public class TuneWikiFactory extends BaseLyricHandlerFactory {
	public static final String NAME = "TuneWiki";

	public TuneWikiFactory(InternetHtmlLoader loader) {
		super(loader);
	}

	@Override
	public LyricPageHandler getLyricPageHandler(String urlString) {
		return new TuneWikiHandler(NAME, loader, urlString);
	}

	@Override
	public boolean matches(String url) {
		return url.matches(".*tunewiki.com/lyrics/.+/.+.aspx")
				&& !url.contains("/dir/");
	}

	@Override
	public boolean verify() {
		String verificationURL = "http://www.tunewiki.com/lyrics/scissors-for-lefty/nickels-and-dimes-s783249.aspx";
		String verificationLyricStart = "I used to stand";
		String verificationLyricEnd = "nickels and dimes";
		String verificationArtist = "SCISSORS FOR LEFTY";
		String verificationTitle = "NICKELS AND DIMES";
		return verify(verificationURL, verificationLyricStart, verificationLyricEnd, verificationArtist, verificationTitle);
	}

	@Override
	public String nameOfSite() {
		return NAME;
	}

	static public void main (String s[]) {
		new TuneWikiFactory(new InternetHtmlLoader()).verify();
	}
}