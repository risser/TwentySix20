package com.twentysix20.media.webreaders.movie;

import com.twentysix20.util.StringUtil;
import com.twentysix20.util.html.FancyInternetHtmlLoader;
import com.twentysix20.util.html.HtmlLoader;

public class MovieDataParser {
	private HtmlLoader loader;

	public MovieDataParser(HtmlLoader loader) {
		this.loader = loader;
	}

	public ShowData parse(String url) throws Exception {
		System.out.println("Processing: "+url);
		String page = loader.getHtmlPage(url);

		ShowData data = new ShowData();
		int titlePos = page.indexOf("<h1");
		data.setEpisodeTitle(StringUtil.unescapeHTML(StringUtil.grab(page, ">", "<span", titlePos)));

		int yearPos = page.indexOf("<span",titlePos);
		String yearText = StringUtil.grab(page, "(", ")", yearPos);
		if (yearText.contains(">"))
			yearText = StringUtil.grab(yearText, ">", "<");
		data.setYear(yearText.replaceAll("[^0-9]", ""));

		int infobarPos = page.indexOf("<div", yearPos);
		String infoBarText = StringUtil.grab(page, "<div","</div>",infobarPos-1);
		if (!infoBarText.contains("alt=\""))
			data.setRating(Rating.NR);
		else
			data.setRating(Rating.valueOf(StringUtil.grab(infoBarText, "alt=\"", "\"")));

		String[] genreLines = infoBarText.split("<a ");
		for (String genreLine : genreLines) {
			if (genreLine.contains("infobar")) continue;
			String genre = StringUtil.grab(genreLine,">","<");
			if (!genre.contains("&nbsp;"))
				data.getGenres().add(genre);
		}

		int descPos = page.indexOf("<p itemprop=\"description\"",infobarPos);
		if (descPos < 0) {
			data.setDescription("<No plot summary found.>");
		} else {
			String desc = StringUtil.grab(page, ">", "</p>", descPos+1);
			if (desc.contains("plotsummary"))
				desc = new MovieDescriptionParser(new FancyInternetHtmlLoader()).parse(url);
			data.setDescription(StringUtil.unescapeHTML(desc));
		}

		int directorPos = page.indexOf("<div",descPos);
		String directorText = StringUtil.grab(page, "<div", "</div>", directorPos);
		while (directorText.contains("<a")) {
			String director = StringUtil.grab(directorText,">","<",directorText.indexOf("<a"));
			data.getDirectors().add(StringUtil.unescapeHTML(director));
			directorText = directorText.substring(directorText.indexOf("<a")+1);
		}

		int writerPos = page.indexOf("<div",directorPos+1);
		String writerText = StringUtil.grab(page, "<div", "</div>", writerPos);
		if (writerText.contains("more credit"))
			data.getWriters().addAll(new MovieWriterParser(new FancyInternetHtmlLoader()).parse(url));
		else {
			while (writerText.contains("<a")) {
				String writer = StringUtil.grab(writerText,">","<",writerText.indexOf("<a"));
				data.getWriters().add(StringUtil.unescapeHTML(writer));
				writerText = writerText.substring(writerText.indexOf("<a")+1);
			}
		}

		int castPos = page.indexOf("cast_list", writerPos);
		String castTableText = StringUtil.grab(page, ">","</table>", castPos);
		String[] castRows = castTableText.split("\\<tr class");
		for (String castRow : castRows) {
			if (castRow.contains("castlist_label")) continue;
			String nameCol = StringUtil.grab(castRow, "<td class=\"name\">", "</td>");
			String name = StringUtil.grab(nameCol, ">","<");
			data.getActors().add(StringUtil.unescapeHTML(name));
		}

		int companyPos = page.indexOf("<h3>Company Credits</h3>",castPos);
		String companyText = StringUtil.grab(page, "<div", "</div>", companyPos);
		while (companyText.contains("<a")) {
			String companyName = StringUtil.grab(companyText,">","<",companyText.indexOf("<a"));
			if ("Warner Bros. Pictures".equals(companyName))
				companyName = "Warner Brothers";
			if (!companyName.contains("See more"))
				data.getStudios().add(StringUtil.unescapeHTML(companyName));
			companyText = companyText.substring(companyText.indexOf("<a")+1);
		}

		return data;
	}
}