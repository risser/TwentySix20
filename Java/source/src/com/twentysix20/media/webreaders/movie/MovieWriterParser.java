package com.twentysix20.media.webreaders.movie;

import java.util.HashSet;
import java.util.Set;

import com.twentysix20.util.StringUtil;
import com.twentysix20.util.html.HtmlLoader;

public class MovieWriterParser {

	private final HtmlLoader htmlLoader;

	public MovieWriterParser(HtmlLoader htmlLoader) {
		this.htmlLoader = htmlLoader;
	}

	public Set<String> parse(String url) throws Exception {
		String creditUrl = url + "/fullcredits";
		System.out.println("  Processing: "+creditUrl);
		String page = htmlLoader.getHtmlPage(creditUrl);
		String writerString = StringUtil.grab(page, "Writing credit", "</table>"); 
		String[] writerParts = writerString.split("<tr>");
		Set<String> writers = new HashSet<String>();
		for (String writerPart : writerParts) {
			if (writerPart.contains("<small>")) continue;
			int localPos = writerPart.indexOf("/name/");
			String writer = StringUtil.grab(writerPart, ">", "<", localPos);
			writers.add(writer);
		}
		return writers;
	}

}
