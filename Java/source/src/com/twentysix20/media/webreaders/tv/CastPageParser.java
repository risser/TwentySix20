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

	public void parse(String url, ShowData showData) throws Exception {
		System.out.println("    Processing Cast : "+url);
		String page = null;
		try {
			page = loader.getHtmlPage(url);
		} catch (IOException ioe) {
			System.out.println(" -- ERROR loading cast page.");
			LogUtil.printException(ioe);
			return;
		}
		List<String> stars = capturePeople(page, "Star", true);
		List<String> recurringRoles = capturePeople(page, "Recurring Role", true);
		List<String> guestStars = capturePeople(page, "Guest Star", false);
		List<String> cameos = capturePeople(page, "Cameo", true);
		List<String> specialGuestStars = capturePeople(page, "Special Guest Star", true);

		showData.getActors().addAll(stars);
		showData.getActors().addAll(recurringRoles);
		showData.getActors().addAll(specialGuestStars);
		showData.getActors().addAll(cameos);
		showData.getActors().addAll(guestStars);
	}

	private List<String> capturePeople(String page, String header, boolean sort) {
System.out.println("ACTORS: "+header);
		int sectionPosition = page.indexOf("<h3>"+header+"</h3>");
		if (sectionPosition < 0)
			sectionPosition = page.indexOf("<h3>"+header+"s</h3>");

		List<String> actors = new ArrayList<String>();
		if (sectionPosition < 0) {
			System.out.println("-- none found. ");
			return actors;
		}

		String section = StringUtil.grab(page, "<ul class=\"people\">", "<div class=\"MODULE_HEAD\">", sectionPosition);
		int personPos = 0;
		while ((personPos = section.indexOf("\"full_name\"", personPos)) >= 0) {
			personPos = section.indexOf("<a", personPos);
			String actor = StringUtil.grab(section, "\">", "<", personPos);
System.out.println(actor);
			if (actor != null) {
				actors.add(cleanActorName(actor));
			}
		}

		if (sort)
			Collections.sort(actors);

		return actors;
	}

	private String cleanActorName(String actor) {
		actor = actor.replaceAll("  "," ").replaceAll(" \\([IV]+\\)","");
		return actor;
	}

}
