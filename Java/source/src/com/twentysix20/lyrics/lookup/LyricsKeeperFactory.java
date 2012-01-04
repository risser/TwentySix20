package com.twentysix20.lyrics.lookup;

import com.twentysix20.lyrics.BaseLyricHandlerFactory;
import com.twentysix20.lyrics.LyricPageHandler;
import com.twentysix20.util.html.InternetHtmlLoader;

public class LyricsKeeperFactory extends BaseLyricHandlerFactory {
	public static final String NAME = "Lyrics-Keeper";

	public LyricsKeeperFactory(InternetHtmlLoader loader) {
		super(loader);
	}

	@Override
	public LyricPageHandler getLyricPageHandler(String urlString) {
		return new LyricsKeeperHandler(NAME, loader, urlString);
	}

	@Override
	public boolean matches(String url) {
		return url.matches(".*lyrics-keeper.com/en/.+html");
	}

	@Override
	public boolean verify() {
		String verificationURL = "http://lyrics-keeper.com/en/willie-hutch/the-glow.html";
		String verificationLyricStart = "Now all the masters";
		String verificationLyricEnd = "The glow";
		String verificationArtist = "Willie Hutch";
		String verificationTitle = "The Glow";
		return verify(verificationURL, verificationLyricStart, verificationLyricEnd, verificationArtist, verificationTitle);
	}

	@Override
	public String nameOfSite() {
		return NAME;
	}

	static public void main (String s[]) {
		new LyricsKeeperFactory(new InternetHtmlLoader()).verify();
	}
}