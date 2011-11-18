package com.twentysix20.lyrics.lookup;

import com.twentysix20.lyrics.BaseLyricHandler;
import com.twentysix20.lyrics.parsers.ConjoinedArtistTitleParser;
import com.twentysix20.lyrics.parsers.OneShotLyricParser;
import com.twentysix20.util.html.InternetHtmlLoader;

public class LyricsManiaDanceHandler extends BaseLyricHandler {

	public LyricsManiaDanceHandler(String siteName, InternetHtmlLoader loader, String urlString) {
		super(siteName, loader, urlString);
	}

	@Override
	protected void parseData() {
		super.parseData();

        if (lyrics.startsWith("Artist:"))
        	lyrics = lyrics.substring(lyrics.indexOf('\n')).trim();

        if (lyrics.startsWith("Title:"))
        	lyrics = lyrics.substring(lyrics.indexOf('\n')).trim();
	}

	@Override
	protected void setupParsers() {
		lyricParser = new OneShotLyricParser("<td width=100%>","</td>");
		atParser = new ConjoinedArtistTitleParser("<TITLE>", " Lyrics ", "</TITLE>", true);
	}
}
