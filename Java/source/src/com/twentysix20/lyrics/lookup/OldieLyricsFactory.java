package com.twentysix20.lyrics.lookup;

import com.twentysix20.lyrics.BaseLyricHandlerFactory;
import com.twentysix20.lyrics.LyricPageHandler;
import com.twentysix20.util.html.InternetHtmlLoader;

public class OldieLyricsFactory extends BaseLyricHandlerFactory {
	public static final String NAME = "Oldie Lyrics";

	public OldieLyricsFactory(InternetHtmlLoader loader) {
		super(loader);
	}

	@Override
	public LyricPageHandler getLyricPageHandler(String urlString) {
		return new OldieLyricsHandler(NAME, loader, urlString);
	}

	@Override
	public boolean matches(String url) {
		return url.matches(".*oldielyrics.com/lyrics.+html");
	}

	@Override
	public boolean verify() {
		String verificationURL = "http://www.oldielyrics.com/lyrics/bob_seger_the_silver_bullet_band/old_time_rock_and_roll.html";
		String verificationLyricStart = "Just take those old records off the shelf";
		String verificationLyricEnd = "With that old time rock 'n' roll";
		String verificationArtist = "BOB SEGER & THE SILVER BULLET BAND";
		String verificationTitle = "Old Time Rock And Roll";
		return verify(verificationURL, verificationLyricStart, verificationLyricEnd, verificationArtist, verificationTitle);
	}

	@Override
	public String nameOfSite() {
		return NAME;
	}

	static public void main (String s[]) {
		new OldieLyricsFactory(new InternetHtmlLoader()).verify();
	}
}