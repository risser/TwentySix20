package com.twentysix20.lyrics.lookup;

import com.twentysix20.lyrics.BaseLyricHandler;
import com.twentysix20.lyrics.parsers.ConjoinedArtistTitleParser;
import com.twentysix20.lyrics.parsers.TwoShotLyricParser;
import com.twentysix20.util.html.InternetHtmlLoader;

public class LyricsKeeperHandler extends BaseLyricHandler {

	public LyricsKeeperHandler(String siteName, InternetHtmlLoader loader, String urlString) {
		super(siteName, loader, urlString);
	}

	@Override
	protected void setupParsers() {
        lyricParser = new TwoShotLyricParser("<div class=\"lyrics_text\">", "</div>", "<div id");
		atParser = new ConjoinedArtistTitleParser("description\" content=\""," \\- "," Lyrics:", true);
	}
}