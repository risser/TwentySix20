package com.twentysix20.lyrics;

import java.net.URL;
import java.util.List;

import com.twentysix20.util.html.HtmlLoader;
import com.twentysix20.util.html.HtmlUtil;
import com.twentysix20.util.html.InternetHtmlLoader;

public class GoogleSearch {
	static {
		System.setProperty("http.agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
	}

	private GoogleURLLookup googleLookup;

	public GoogleSearch(HtmlLoader loader) {
		googleLookup = new GoogleURLLookup(loader);
	}

	public List<URL> lookup(String search) throws Exception {
		String searchURL = search.replaceAll("[^A-Za-z0-9 ]","");
		searchURL = HtmlUtil.noSpace(searchURL);
		searchURL = "http://www.google.com/search?q=" + searchURL;
		return googleLookup.lookup(searchURL);
	}

	public static void main(String[] args) throws Exception {
		List<URL> urls = new GoogleSearch(new InternetHtmlLoader()).lookup("lyrics Coheed and Cambria - In Keeping Secrets of Silent Earth: 3");
		for (URL url : urls)
			System.out.println(url);
	}
}
