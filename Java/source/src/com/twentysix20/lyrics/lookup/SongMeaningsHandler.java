package com.twentysix20.lyrics.lookup;

import com.twentysix20.lyrics.BaseLyricHandler;
import com.twentysix20.lyrics.parsers.ConjoinedArtistTitleParser;
import com.twentysix20.lyrics.parsers.OneShotLyricParser;
import com.twentysix20.util.html.InternetHtmlLoader;

public class SongMeaningsHandler extends BaseLyricHandler {

	public SongMeaningsHandler(String siteName, InternetHtmlLoader loader, String urlString) {
		super(siteName, loader, urlString);
	}

	@Override
	protected void setupParsers() {
        lyricParser = new OneShotLyricParser("<div id=\"songText2\">", "</div>");
		atParser = new ConjoinedArtistTitleParser("Lyrics | "," \\- ","</title>");
	}

	@Override
	protected void parseData() {
        super.parseData();
        int pos = lyrics.lastIndexOf("---");
        if (pos < 0) return;
        int writtenPos = lyrics.lastIndexOf("written by");
        if (writtenPos < 0) return;

        if (pos < writtenPos) // assume this is the 'written by'/copyright tag
        	lyrics = lyrics.substring(0,pos).trim();
	}
}