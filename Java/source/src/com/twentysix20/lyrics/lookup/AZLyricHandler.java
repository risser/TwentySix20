package com.twentysix20.lyrics.lookup;

import com.twentysix20.lyrics.BaseLyricHandler;
import com.twentysix20.lyrics.parsers.ConjoinedArtistTitleParser;
import com.twentysix20.lyrics.parsers.OneShotLyricParser;
import com.twentysix20.util.html.InternetHtmlLoader;

public class AZLyricHandler extends BaseLyricHandler {

	public AZLyricHandler(String siteName, InternetHtmlLoader loader, String urlString) {
		super(siteName, loader, urlString);
	}

	@Override
	protected void setupParsers() {
		lyricParser = new OneShotLyricParser("<!-- start of lyrics -->","<!-- end of lyrics -->");
		atParser = new ConjoinedArtistTitleParser("<title>", " LYRICS \\- ", "</title>");
	}
}
