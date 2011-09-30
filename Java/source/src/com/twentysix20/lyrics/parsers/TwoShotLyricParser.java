package com.twentysix20.lyrics.parsers;

import com.twentysix20.lyrics.LyricCleanup;
import com.twentysix20.lyrics.LyricParser;
import com.twentysix20.lyrics.exception.LyricParsingException;
import com.twentysix20.util.StringUtil;
import com.twentysix20.util.html.HtmlUtil;

public class TwoShotLyricParser implements LyricParser {

	private String preTag1;
	private String preTag2;
	private String postTag;
	private boolean denumberfy;

	public TwoShotLyricParser(String preTag, String postTag) {
		this(preTag, preTag, postTag);
	}

	public TwoShotLyricParser(String preTag1, String preTag2, String postTag) {
		this(preTag1, preTag2, postTag, false);
	}

	public TwoShotLyricParser(String preTag1, String preTag2, String postTag, boolean denumberfy) {
		this.preTag1 = preTag1;
		this.preTag2 = preTag2;
		this.postTag = postTag;
		this.denumberfy = denumberfy;
	}

	@Override
	public String parseLyrics(String pageData, String artistName) {
		int pos = pageData.indexOf(preTag1);
		if (pos < 0)
        	throw new LyricParsingException("Couldn't find first tag on page.");

		String lyrics = StringUtil.grab(pageData, preTag2, postTag, pos+1);
        if (lyrics == null)
        	throw new LyricParsingException("Couldn't find lyrics between tags.");

        if (denumberfy)
        	lyrics = HtmlUtil.denumberfy(lyrics).trim();
        lyrics = LyricCleanup.clean(lyrics, artistName);

        return lyrics;
	}

}
