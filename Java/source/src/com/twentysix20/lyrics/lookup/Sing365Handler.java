package com.twentysix20.lyrics.lookup;

import com.twentysix20.lyrics.BaseLyricHandler;
import com.twentysix20.lyrics.parsers.ConjoinedArtistTitleParser;
import com.twentysix20.lyrics.parsers.TwoShotLyricParser;
import com.twentysix20.util.html.InternetHtmlLoader;

public class Sing365Handler extends BaseLyricHandler {

	public Sing365Handler(String siteName, InternetHtmlLoader loader, String urlString) {
		super(siteName, loader, urlString);
	}

	@Override
	protected void setupParsers() {
        lyricParser = new TwoShotLyricParser("Ringtones to Cell</span>", "<br>", "<img src=");
		atParser = new ConjoinedArtistTitleParser("<title>"," \\- "," LYRICS</title>");
	}

	@Override
	protected void parseData() {
		super.parseData();

        if (lyrics.indexOf("Sorry, I have no <b>") > -1)
        	errorMessage = "Lyrics page exists, but no lyrics available.";
	}
}