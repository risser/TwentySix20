package com.twentysix20.lyrics.lookup;

import com.twentysix20.lyrics.BaseLyricHandler;
import com.twentysix20.lyrics.parsers.ConjoinedArtistTitleParser;
import com.twentysix20.lyrics.parsers.OneShotLyricParser;
import com.twentysix20.util.html.InternetHtmlLoader;

public class LyricsWikiGracenoteHandler extends BaseLyricHandler {
	public LyricsWikiGracenoteHandler(String siteName, InternetHtmlLoader loader, String urlString) {
		super(siteName, loader, urlString);
	}

	@Override
	protected void setupParsers() {
		lyricParser = new OneShotLyricParser("<div class='lyricbox'>","<!--", true);
		atParser = new ConjoinedArtistTitleParser("<title>Gracenote:", ":", "- Lyric Wiki");
	}

	@Override
	protected void parseData() {
        super.parseData();

        if (lyrics.contains("Unfortunately, we are not licensed to display"))
        	errorMessage = "No lyrics due to licensing restrictions.";
	}
}