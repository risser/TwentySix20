package com.twentysix20.lyrics.lookup;

import com.twentysix20.lyrics.BaseLyricHandler;
import com.twentysix20.lyrics.parsers.OneShotLyricParser;
import com.twentysix20.lyrics.parsers.SeparateArtistTitleParser;
import com.twentysix20.util.html.InternetHtmlLoader;

public class LyricsVIPHandler extends BaseLyricHandler {

	public LyricsVIPHandler(String siteName, InternetHtmlLoader loader, String urlString) {
		super(siteName, loader, urlString);
	}

	@Override
	protected void setupParsers() {
		lyricParser = new OneShotLyricParser("</h2>","</td></tr></table>");
		atParser = new SeparateArtistTitleParser("<h2>", "</h2>", "<h1>", " Lyrics</h1>");
	}
}