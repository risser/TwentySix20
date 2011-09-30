package com.twentysix20.lyrics.lookup;

import com.twentysix20.lyrics.BaseLyricHandler;
import com.twentysix20.lyrics.parsers.ConjoinedArtistTitleParser;
import com.twentysix20.lyrics.parsers.OneShotLyricParser;
import com.twentysix20.util.html.InternetHtmlLoader;

public class LyricsManiaHandler extends BaseLyricHandler {

	public LyricsManiaHandler(String siteName, InternetHtmlLoader loader, String urlString) {
		super(siteName, loader, urlString);
	}

	@Override
	protected void setupParsers() {
		lyricParser = new OneShotLyricParser("<div id='songlyrics_h' class='dn'>","</div>");
		atParser = new ConjoinedArtistTitleParser("<title>", " - ", " Lyrics</title>");
	}
}
