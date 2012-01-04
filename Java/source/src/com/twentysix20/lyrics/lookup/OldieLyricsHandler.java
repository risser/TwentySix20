package com.twentysix20.lyrics.lookup;

import com.twentysix20.lyrics.BaseLyricHandler;
import com.twentysix20.lyrics.parsers.ConjoinedArtistTitleParser;
import com.twentysix20.lyrics.parsers.OneShotLyricParser;
import com.twentysix20.util.html.InternetHtmlLoader;

public class OldieLyricsHandler extends BaseLyricHandler {

	public OldieLyricsHandler(String siteName, InternetHtmlLoader loader, String urlString) {
		super(siteName, loader, urlString);
	}

	@Override
	protected void setupParsers() {
        lyricParser = new OneShotLyricParser("<div class=\"lyrics\">", "</div>", true);
		atParser = new ConjoinedArtistTitleParser("<title>"," lyrics \\- ","</title>");
	}

	@Override
	protected void parseData() {
		super.parseData();

        if (lyrics.indexOf("Sorry, I have no <b>") > -1)
        	errorMessage = "Lyrics page exists, but no lyrics available.";
	}
}