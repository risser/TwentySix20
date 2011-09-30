package com.twentysix20.lyrics;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.twentysix20.util.StringUtil;
import com.twentysix20.util.html.HtmlLoader;

public class GoogleURLLookup {
	private HtmlLoader loader;

	public GoogleURLLookup(HtmlLoader loader) {
		this.loader = loader;
	}

	public List<URL> lookup(String url) throws Exception {
		List<URL> urls = new ArrayList<URL>();

		String lookupResult = loader.getHtmlPage(url);
		String innerList = StringUtil.grab(lookupResult, "<ol","</ol");
		if (innerList != null) {
			String[] entries = innerList.split("<li class=\"g");
			for (String entry : entries) {
				if (entry.indexOf("<a href=\"") < 0) continue;
				String href = StringUtil.grab(entry, "<a href=\"", "\"");
				if (href.startsWith("/url?q="))
					href = StringUtil.grab(href,"=","&amp;");
				if (!href.startsWith("http://")) continue;
				urls.add(new URL(href));
			}
		}

		return urls;
	}
}