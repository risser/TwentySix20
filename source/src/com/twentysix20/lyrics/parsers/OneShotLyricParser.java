package com.twentysix20.lyrics.parsers;

import com.twentysix20.lyrics.LyricCleanup;
import com.twentysix20.lyrics.LyricParser;
import com.twentysix20.lyrics.exception.LyricParsingException;
import com.twentysix20.util.StringUtil;
import com.twentysix20.util.html.HtmlUtil;

public class OneShotLyricParser implements LyricParser {
	private String preTag;
	private String postTag;
	private boolean denumberfy;

	public OneShotLyricParser(String preTag, String postTag) {
		this(preTag, postTag, false);
	}

	public OneShotLyricParser(String preTag, String postTag, boolean denumberfy) {
		this.preTag = preTag;
		this.postTag = postTag;
		this.denumberfy = denumberfy;
	}

	@Override
	public String parseLyrics(String pageData, String artistName) {
//		System.out.println("::'"+preTag+"'::'"+postTag+"'::");		
		String lyrics = StringUtil.grab(pageData, preTag, postTag);
        if (lyrics == null)
        	throw new LyricParsingException("Couldn't find lyrics between tags.");

        if (denumberfy)
        	lyrics = HtmlUtil.denumberfy(lyrics).trim();
        lyrics = LyricCleanup.clean(lyrics, artistName);

        return lyrics;
	}

}
