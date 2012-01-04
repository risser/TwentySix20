package com.twentysix20.media.webreaders.tv;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.twentysix20.media.webreaders.movie.ShowData;
import com.twentysix20.util.LogUtil;
import com.twentysix20.util.StringUtil;
import com.twentysix20.util.html.HtmlLoader;

public class CastPageParser {

	private final HtmlLoader loader;

	public CastPageParser(HtmlLoader loader) {
		this.loader = loader;
	}

	public List<String> parse(String url, String subpage) throws Exception {
		url = url + "/cast/" + subpage + "/";
		System.out.println("    Processing Cast Page: "+url);
		String page = null;
		try {
			page = loader.getHtmlPage(url);
		} catch (IOException ioe) {
			System.out.println(" -- ERROR loading cast page.");
			LogUtil.printException(ioe);
			return new ArrayList<String>();
		}
		return capturePeople(page);
	}

	private List<String> capturePeople(String page) {
		List<String> persons = new ArrayList<String>();
		int pos = page.indexOf("<li class=\"person\">");
		if (pos < 0)
			return persons;
		page = page.substring(pos+2);
		String[] personBits = page.split("<li class=\"person\">");
		for (String personBit : personBits) {
			int pos2 = personBit.indexOf("<a href=\"/people/");
			String person = StringUtil.grab(personBit, ">", "<", pos2);
			if (person != null)
				persons.add(cleanActorName(person));
		}

		Collections.sort(persons);

		return persons;
	}

	private String cleanActorName(String actor) {
		actor = actor.replaceAll("  "," ").replaceAll(" \\([IV]+\\)","");
		return actor;
	}

}
