package com.twentysix20.lyrics.lookup;

import com.twentysix20.lyrics.BaseLyricHandler;
import com.twentysix20.lyrics.parsers.ConjoinedArtistTitleParser;
import com.twentysix20.lyrics.parsers.OneShotLyricParser;
import com.twentysix20.util.html.InternetHtmlLoader;

public class LyricsWikiHandler extends BaseLyricHandler {
	public LyricsWikiHandler(String siteName, InternetHtmlLoader loader, String urlString) {
		super(siteName, loader, urlString);
	}

	@Override
	protected void setupParsers() {
		lyricParser = new OneShotLyricParser("<div class='lyricbox'>","<!--", true);
		atParser = new ConjoinedArtistTitleParser("<title>", ":", " Lyrics - Lyric Wiki");
	}

	@Override
	protected void parseData() {
        if (pageData.contains("Album by") && !pageData.contains("This song is performed by")) {
        	errorMessage = "Found album page, not song page.";
        	return;
        }

        super.parseData();

        if (lyrics.contains("Unfortunately, we are not licensed to display"))
        	errorMessage = "No lyrics due to licensing restrictions.";
	}
}