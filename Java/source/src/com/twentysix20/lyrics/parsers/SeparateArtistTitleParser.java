package com.twentysix20.lyrics.parsers;

import com.twentysix20.lyrics.ArtistTitleParser;
import com.twentysix20.lyrics.exception.LyricParsingException;
import com.twentysix20.util.StringUtil;

public class SeparateArtistTitleParser implements ArtistTitleParser {

	private String preArtistTag;
	private String postArtistTag;
	private String preTitleTag;
	private String postTitleTag;

	public SeparateArtistTitleParser(String preArtistTag, String postArtistTag, String preTitleTag, String postTitleTag) {
		this.preArtistTag = preArtistTag;
		this.postArtistTag = postArtistTag;
		this.preTitleTag = preTitleTag;
		this.postTitleTag= postTitleTag;
	}

	@Override
	public String parseArtist(String pageData) {
        String artist = StringUtil.grab(pageData, preArtistTag, postArtistTag);
        if (artist == null)
        	throw new LyricParsingException("Couldn't find artist data between tags.");
        return artist.trim();
	}

	@Override
	public String parseTitle(String pageData) {
        String title = StringUtil.grab(pageData, preTitleTag, postTitleTag);
        if (title == null)
        	throw new LyricParsingException("Couldn't find title data between tags.");
        return title.trim();
	}

}
