package com.twentysix20.lyrics.lookup;

import com.twentysix20.lyrics.BaseLyricHandler;
import com.twentysix20.lyrics.parsers.ConjoinedArtistTitleParser;
import com.twentysix20.lyrics.parsers.TwoShotLyricParser;
import com.twentysix20.util.html.InternetHtmlLoader;

public class Lyrics007Handler extends BaseLyricHandler {

	public Lyrics007Handler(String siteName, InternetHtmlLoader loader, String urlString) {
		super(siteName, loader, urlString);
	}

	@Override
	protected void setupParsers() {
		lyricParser = new TwoShotLyricParser("<br><br><br>","<script");
		atParser = new ConjoinedArtistTitleParser("<Title>", " - ", " Lyrics</Title>");
	}
}