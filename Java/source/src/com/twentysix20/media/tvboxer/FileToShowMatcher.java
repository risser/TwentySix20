package com.twentysix20.media.tvboxer;

import java.io.File;

import com.twentysix20.media.webreaders.movie.ShowData;
import com.twentysix20.util.StringUtil;

public class FileToShowMatcher {

	public boolean matches(File file, ShowData show) {
		String name = file.getName();
		String paddedSeason = StringUtil.padLeft(show.getSeason().toString(), 2, '0');
		String paddedEpisode = StringUtil.padLeft(show.getEpisode().toString(), 2, '0');
		String prefix = show.getShowName() + " " + paddedSeason + "." + paddedEpisode;
		String matchRegex = prefix + ".*";
		if (matchRegex.startsWith("The "))
			matchRegex = "(The )?"+matchRegex.substring(4);
		return name.matches(matchRegex);
	}

}
