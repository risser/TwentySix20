package com.twentysix20.lyrics.lookup;

import com.twentysix20.lyrics.BaseLyricHandler;
import com.twentysix20.lyrics.parsers.ConjoinedArtistTitleParser;
import com.twentysix20.lyrics.parsers.OneShotLyricParser;
import com.twentysix20.util.html.InternetHtmlLoader;

public class LyricsModeHandler extends BaseLyricHandler {

	public LyricsModeHandler(String siteName, InternetHtmlLoader loader, String urlString) {
		super(siteName, loader, urlString);
	}

	@Override
	protected void parseData() {
		pageData = pageData.replaceFirst("Lyrics</title>", "lyrics</title>");
		try {
			super.parseData();
		} catch (RuntimeException e) {
	        if (pageData.contains("lyrics due to licensing restrictions"))
	        	errorMessage = "No lyrics due to licensing restrictions.";
	        else if (pageData.contains("nobody has submitted lyrics"))
	        	errorMessage = "The song is listed, but no lyrics have been submitted.";
	        else
	        	throw e;
		}
	}

	@Override
	protected void setupParsers() {
		lyricParser = new OneShotLyricParser("<div id='songlyrics_h' class='dn'>","</div>",true);
		atParser = new ConjoinedArtistTitleParser("<title>", " \\- ", " lyrics</title>");
//		atParser = new ConjoinedArtistTitleParser("<title>", " \\- ", " Lyrics</title>");
	}
}
