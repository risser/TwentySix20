package com.twentysix20.lyrics.lookup;

import com.twentysix20.lyrics.BaseLyricHandlerFactory;
import com.twentysix20.lyrics.LyricPageHandler;
import com.twentysix20.util.html.InternetHtmlLoader;

public class SongMeaningsFactory extends BaseLyricHandlerFactory {
	public static final String NAME = "SongMeanings.net";

	public SongMeaningsFactory(InternetHtmlLoader loader) {
		super(loader);
	}

	@Override
	public LyricPageHandler getLyricPageHandler(String urlString) {
		return new SongMeaningsHandler(NAME, loader, urlString);
	}

	@Override
	public boolean matches(String url) {
		return url.contains("songmeanings.net/songs/view");
	}

	@Override
	public boolean verify() {
		String verificationURL = "http://www.songmeanings.net/songs/view/48917/";
		String verificationLyricStart = "Move yourself";
		String verificationLyricEnd = "Just receive it";
		String verificationArtist = "Yes";
		String verificationTitle = "Owner Of A Lonely Heart";
		return verify(verificationURL, verificationLyricStart, verificationLyricEnd, verificationArtist, verificationTitle);
	}

	@Override
	public String nameOfSite() {
		return NAME;
	}

	static public void main (String s[]) {
		new SongMeaningsFactory(new InternetHtmlLoader()).verify();
	}
}