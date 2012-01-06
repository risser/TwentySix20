package com.twentysix20.media.webreaders.movie;

import com.twentysix20.util.StringUtil;
import com.twentysix20.util.html.HtmlLoader;

public class MovieTitleLookup {

	private final HtmlLoader htmlLoader;

	public MovieTitleLookup(HtmlLoader htmlLoader) {
		this.htmlLoader = htmlLoader;
	}

	// http://www.imdb.com/find?s=all&q=mission:+impossible
	public String search(String line) throws Exception {
		String url = ("http://www.imdb.com/find?s=tt&q=" + line).replace(' ', '+');
		System.out.println("Searching: " + url);
		String page = htmlLoader.getHtmlPage(url);
		if (page.contains("IMDb Title Search"))
			return processSearchPage(page);
		else
			return processExactPage(page);
	}

	private String processSearchPage(String page) {
		int firstPos = page.indexOf("</table>");
		int secondPos = page.indexOf("<br>", firstPos);
		String titleID = StringUtil.grab(page, "/title/", "/", secondPos);
		String title = StringUtil.grab(page, "';\">", "</a>", secondPos);
		System.out.println("  Found: " + title + " (" + titleID + ")");
		return titleID;
	}

	private String processExactPage(String page) {
		int firstPos = page.indexOf("canonical");
		String titleID = StringUtil.grab(page, "/title/", "/", firstPos);
		String title = StringUtil.grab(page, "\"og:title\" content=\"", "\"");
		System.out.println("  Exact Match: " + title + " (" + titleID + ")");
		return titleID;
	}

}
