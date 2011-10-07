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
		verifyWithoutTag();
		verified = false;
		verifyWithTag();
		verified = true;
		return true;
	}

	private void verifyWithoutTag() {
		String verificationURL = "http://www.songmeanings.net/songs/view/3530822107858643696/";
		String verificationLyricStart = "Now that we are out of juvie";
		String verificationLyricEnd = "side, side, can't decide";
		String verificationArtist = "Scissors For Lefty";
		String verificationTitle = "Mama Your Boys Will Find A Home";
		verify(verificationURL, verificationLyricStart, verificationLyricEnd, verificationArtist, verificationTitle);
		System.out.println(" - Without Tags");
	}

	private void verifyWithTag() {
		String verificationURL = "http://www.songmeanings.net/songs/view/48917/";
		String verificationLyricStart = "Move yourself";
		String verificationLyricEnd = "Just receive it";
		String verificationArtist = "Yes";
		String verificationTitle = "Owner Of A Lonely Heart";
		verify(verificationURL, verificationLyricStart, verificationLyricEnd, verificationArtist, verificationTitle);
		System.out.println(" - With Tags");
	}

	@Override
	public String nameOfSite() {
		return NAME;
	}

	static public void main (String s[]) {
		new SongMeaningsFactory(new InternetHtmlLoader()).verify();
	}
}