package com.twentysix20.lyrics.lookup;

import com.twentysix20.lyrics.BaseLyricHandler;
import com.twentysix20.lyrics.parsers.ConjoinedArtistTitleParser;
import com.twentysix20.lyrics.parsers.TwoShotLyricParser;
import com.twentysix20.util.html.InternetHtmlLoader;

public class UULyricsHandler extends BaseLyricHandler {

	public UULyricsHandler(String siteName, InternetHtmlLoader loader, String urlString) {
		super(siteName, loader, urlString);
	}

	@Override
	protected void setupParsers() {
		lyricParser = new TwoShotLyricParser("Ringtone to your Cell","</div>","<div");
		atParser = new ConjoinedArtistTitleParser("<title>", " \\- ", " Lyrics</title>");
	}

	@Override
	protected void parseData() {
		super.parseData();

		if (lyrics.contains("lyrics not available yet"))
			errorMessage = "Lyric page exists, but no lyrics are available.";
	}
}
