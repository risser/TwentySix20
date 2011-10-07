package com.twentysix20.lyrics.lookup;

import com.twentysix20.lyrics.BaseLyricHandler;
import com.twentysix20.lyrics.parsers.ConjoinedArtistTitleParser;
import com.twentysix20.lyrics.parsers.OneShotLyricParser;
import com.twentysix20.util.html.InternetHtmlLoader;

public class TuneWikiHandler extends BaseLyricHandler {

	public TuneWikiHandler(String siteName, InternetHtmlLoader loader, String urlString) {
		super(siteName, loader, urlString);
	}

	@Override
	protected void setupParsers() {
        lyricParser = new OneShotLyricParser("<div class=\"lyrics-text\">", "</div>", true);
		atParser = new ConjoinedArtistTitleParser("<title>"," LYRICS \\- ","</title>", true);
	}

	@Override
	protected void parseData() {
		pageData = pageData.replaceAll("<div class=\"lyrics-text\".*?>", "<div class=\"lyrics-text\">");
        super.parseData();
	}
}