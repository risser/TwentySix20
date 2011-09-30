package com.twentysix20.lyrics.parsers;

import com.twentysix20.lyrics.ArtistTitleParser;
import com.twentysix20.lyrics.exception.LyricParsingException;
import com.twentysix20.util.StringUtil;

public class ConjoinedArtistTitleParser implements ArtistTitleParser {

	private String preTag;
	private String midTag;
	private String postTag;
	private boolean flipflopped;

	public ConjoinedArtistTitleParser(String preTag, String midTag, String postTag) {
		this(preTag, midTag, postTag, false);
	}

	public ConjoinedArtistTitleParser(String preTag, String midTag, String postTag, boolean flipflopped) {
		this.preTag = preTag;
		this.midTag = midTag;
		this.postTag = postTag;
		this.flipflopped = flipflopped;
	}

	@Override
	public String parseArtist(String pageData) {
        return findArtistTitle(pageData)[artistIndex()].trim();
	}

	@Override
	public String parseTitle(String pageData) {
        return findArtistTitle(pageData)[1-artistIndex()].trim();
	}

	private int artistIndex() {
		return flipflopped ? 1 : 0;
	}

	private String[] findArtistTitle(String pageData) {
		String artistTitleString = StringUtil.grab(pageData, preTag, postTag);
//System.out.println("::"+preTag+" XXX "+postTag);		
        if (artistTitleString == null)
        	throw new LyricParsingException("Couldn't find artist/title data between tags.");
        	
        String[] ss = artistTitleString.split(midTag);
		return ss;
	}

}
