package com.twentysix20.media.webreaders.tv;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.twentysix20.media.webreaders.movie.ShowData;
import com.twentysix20.util.StringUtil;
import com.twentysix20.util.html.HtmlLoader;
import com.twentysix20.util.html.HtmlUtil;

public class TVEpisodePageParser {
	static private DateFormat formatter1 = new SimpleDateFormat("EEEE MMMM dd, yyyy");
	static private DateFormat formatter2 = new SimpleDateFormat("MMMM dd, yyyy");
	//1977-09-10T07:00:00Z
//	static private DateFormat outputFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
	static private DateFormat outputFormatter = new SimpleDateFormat("yyyy-MM-dd");
	private HtmlLoader loader;
	private CastPageParser castParser;

	public TVEpisodePageParser(HtmlLoader loader) {
		this.loader = loader;
		this.castParser = new CastPageParser(loader);
	}

	public ShowData parse(String url) throws Exception {
		System.out.println("  Processing Episode: "+url);
		String page = loader.getHtmlPage(url);
		ShowData showData = new ShowData();
	
		int titlePos = page.indexOf("og:title");
		String title = StringUtil.grab(page,"content=\"","\"",titlePos);
		showData.setEpisodeTitle(cleanSummary(title));

		int showNamePos = page.indexOf("<div class=\"title\">");
		showNamePos = page.indexOf("<a",showNamePos);
		showData.setShowName(StringUtil.grab(page,">","<",showNamePos));

		int numbersPos = page.indexOf("<div class=\"crumbs\">");
		String numbersStr = StringUtil.grab(page,"<span>","</span>",numbersPos).trim();
		numbersStr = numbersStr.replaceAll("[^0-9,]", "");
		String[] numbers = numbersStr.split(",");
		if (!StringUtil.isEmpty(numbers[0]))
			showData.setSeason(Integer.valueOf(numbers[0]));
		if (numbers.length > 1 && !StringUtil.isEmpty(numbers[1]))
			showData.setEpisode(Integer.valueOf(numbers[1]));

		int castPagePos = page.indexOf("subNavCastLnk");
		String castUrl = StringUtil.grab(page, "'", "'", castPagePos);
		castParser.parse(url + castUrl, showData);

		int datePos = page.indexOf("<h4>Air Date</h4>",showNamePos);
		String dateStr = StringUtil.grab(page, "<p>","</p>", datePos);
		showData.setYear("'"+parseDate(dateStr));

		int descPos = page.indexOf("<h3>Episode Summary</h3>",datePos);
		String summary = StringUtil.grab(page, "<p>","</p>", descPos);
		summary = cleanSummary(summary);
		showData.setDescription(summary);

		int starsPos = page.indexOf(">Stars</h4>",descPos);
		int guestPos = page.indexOf(">Guest Cast</h4>",starsPos);
		int recurPos = page.indexOf(">Recurring Roles</h4>",starsPos);
		if (showData.getActors().isEmpty()) {
			List<String> actors = new ArrayList<String>();
	
			String stars = StringUtil.grab(page, "<ul class=\"persons\">", "</ul>", starsPos);
			actors.addAll(processNames(stars, true));
	
			String guestStars = null;
			if (guestPos > -1) {
				guestStars = StringUtil.grab(page, "<ul class=\"persons\">", "</ul>", guestPos);
			}
	
			if (recurPos > -1) {
				String recurs = StringUtil.grab(page, "<ul class=\"persons\">", "</ul>", recurPos);
				actors.addAll(processNames(recurs, true));
			}
	
			if (guestStars != null)
				actors.addAll(processNames(guestStars, false));
	
			showData.setActors(actors);
		}

		int directorPos = page.indexOf(">Directors</h4>",guestPos);
		if (directorPos > -1) {
			String directors = StringUtil.grab(page, "<ul class=\"persons\">", "</ul>", directorPos);
			showData.setDirectors(processNames(directors, true));
		}

		int writerPos = page.indexOf(">Writers</h4>",directorPos);
		if (writerPos > -1) {
			String writers = StringUtil.grab(page, "<ul class=\"persons\">", "</ul>", writerPos);
			showData.setWriters(processNames(writers, true));
		}
		
		return showData;
	}

	private String parseDate(String dateStr) throws ParseException {
		if (!dateStr.matches(".*[0-9]+.*"))
			return dateStr;
		try {
			return outputFormatter.format(formatter1.parse(dateStr));
		} catch (ParseException p) {}
		return outputFormatter.format(formatter2.parse(dateStr));
	}

	private String cleanSummary(String summary) {
//		summary = StringUtil.simplify(summary);
		summary = HtmlUtil.cleanHtml(summary);
		return summary;
	}

	private List<String> processNames(String pagePart, boolean sort) {
		List<String> actors = new ArrayList<String>();
		if (StringUtil.isEmpty(pagePart)) return actors;

		String[] sections = pagePart.split("<li class=");
		for (String section : sections) {
			if (section.trim().isEmpty()) continue;
			section = section.substring(0,section.indexOf("</a>"));
			String actor = section.substring(section.lastIndexOf('>')+1);
//			actor = StringUtil.simplify(actor);
			actor = actor.replaceAll("  "," ").replaceAll(" \\([IV]+\\)","");
			actors.add(actor);
		}
		if (sort)
			Collections.sort(actors);
		return actors;
	}
}