package com.twentysix20.media.webreaders.tv;

import java.util.HashSet;
import java.util.Set;

import com.twentysix20.media.webreaders.movie.ShowData;
import com.twentysix20.util.StringUtil;
import com.twentysix20.util.html.FancyInternetHtmlLoader;
import com.twentysix20.util.html.HtmlLoader;

public class TVSummaryPageParser {
	private HtmlLoader loader;

	public TVSummaryPageParser(HtmlLoader loader) {
		this.loader = loader;
	}

	public Set<ShowData> parse(String url) throws Exception {
		System.out.println("Processing: "+url);
		String page = loader.getHtmlPage(url);
		Set<ShowData> showData = new HashSet<ShowData>();

		String[] urls = page.split("<h3 >");
		for (String section : urls) {
			if (section.contains("<head>")) continue;
			String pageUrl = StringUtil.grab(section, "href=\"", "\"");
			if (!pageUrl.contains("//episode")) {
				ShowData data = new TVEpisodePageParser(new FancyInternetHtmlLoader()).parse(pageUrl);
				showData.add(data);
			}
		}

		return showData;
	}
}