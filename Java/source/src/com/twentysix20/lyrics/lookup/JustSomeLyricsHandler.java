package com.twentysix20.lyrics.lookup;

import com.twentysix20.lyrics.BaseLyricHandler;
import com.twentysix20.lyrics.parsers.SeparateArtistTitleParser;
import com.twentysix20.lyrics.parsers.TwoShotLyricParser;
import com.twentysix20.util.html.InternetHtmlLoader;

public class JustSomeLyricsHandler extends BaseLyricHandler {

	public JustSomeLyricsHandler(String siteName, InternetHtmlLoader loader, String urlString) {
		super(siteName, loader, urlString);
	}

	@Override
	protected void setupParsers() {
		lyricParser = new TwoShotLyricParser("</div>","</div>","<div");
		atParser = new SeparateArtistTitleParser("rm_artist = '","';","rm_songtitle = '","';");
	}
}