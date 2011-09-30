package com.twentysix20.lyrics;

import com.twentysix20.util.StringUtil;

public class LyricPrint {

	public static String print(String originalArtistTitle, LyricPageHandler handler) {
		StringBuilder sb = new StringBuilder();
		sb.append("From: ").append(handler.nameOfSite());
		sb.append("\nProvided: ").append(originalArtistTitle);
		sb.append("\nFound: ").append(handler.artist()).append(" - ").append(handler.title());
		sb.append("\nTiming: ").append(handler.timing()/1000).append(".").append(StringUtil.padLeft(""+(handler.timing() % 1000),3,'0')).append(" seconds");
		if (!StringUtil.isEmpty(handler.errorMessage()))
			sb.append("\nError: ").append(handler.errorMessage());
		else {
			sb.append("\n----------------\n").append(handler.lyrics());
		}
		return sb.append("\n").toString();
	}

}
