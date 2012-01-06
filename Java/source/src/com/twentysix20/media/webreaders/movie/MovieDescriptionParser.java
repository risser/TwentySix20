package com.twentysix20.media.webreaders.movie;

import com.twentysix20.util.StringUtil;
import com.twentysix20.util.html.HtmlLoader;

public class MovieDescriptionParser {

	private final HtmlLoader htmlLoader;

	public MovieDescriptionParser(HtmlLoader htmlLoader) {
		this.htmlLoader = htmlLoader;
	}

	public String parse(String url) throws Exception {
		String plotUrl = url + "/plotsummary";
		System.out.println("  Processing: "+plotUrl);
		String page = htmlLoader.getHtmlPage(plotUrl);
		String[] plots = page.split("<p class=\"plotpar\"");
		String shortestPlot = "";
		for (String plotPart : plots) {
			if (plotPart.contains("<title>")) continue;
			String plot = StringUtil.grab(plotPart, ">", "<");
			if ((plot.length() < shortestPlot.length()) || StringUtil.isEmpty(shortestPlot))
				shortestPlot = plot;
		}
		return shortestPlot;
	}

}
