package com.twentysix20.lyrics.lookup;

import com.twentysix20.lyrics.BaseLyricHandler;
import com.twentysix20.lyrics.parsers.ConjoinedArtistTitleParser;
import com.twentysix20.lyrics.parsers.OneShotLyricParser;
import com.twentysix20.util.html.InternetHtmlLoader;

public class LyricsFreakHandler extends BaseLyricHandler {

	public LyricsFreakHandler(String siteName, InternetHtmlLoader loader, String urlString) {
		super(siteName, loader, urlString);
	}

	@Override
	protected void setupParsers() {
		lyricParser = new OneShotLyricParser("<div id='content_h' class='dn'>","</div>", true);
		atParser = new ConjoinedArtistTitleParser("<title>", " Lyrics - ", "</title>", true);
	}

	@Override
	protected void parseData() {
        if (pageData.contains("At the moment nobody has submitted")) {
        	errorMessage = "No lyrics found.";
        	return;
        }
        if (pageData.contains("We are not in a position to display these lyrics")) {
        	errorMessage = "No lyrics due to licensing restrictions.";
        	return;
        }
        super.parseData();

        while (lyrics.startsWith("Songwriters:"))
        	lyrics = lyrics.substring(lyrics.indexOf('\n')).trim();
	}
}