package com.twentysix20.media.webreaders.movie;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.twentysix20.util.html.FancyInternetHtmlLoader;

public class MovieReader {
	public static void main(String[] args) throws Exception {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader stdin = new BufferedReader(isr);
		List<ShowData> data = new ArrayList<ShowData>();

		System.out.println("Enter Titles, Numbers or URLs:");
		String line;
		while (!"".equals(line = stdin.readLine())) {
			String url = line;
			if (!line.contains("imdb.com")) {
				if (!line.matches("([Tt][Tt])[0-9]+"))
					line = new MovieTitleLookup(new FancyInternetHtmlLoader()).search(line);
				url = "http://www.imdb.com/title/"+line;
			}
			if (!url.startsWith("http://"))
				url = "http://"+url;
			data.add(new MovieDataParser(new FancyInternetHtmlLoader()).parse(url));
		}

		System.out.println("\n\n");
		for (ShowData show : data) {
			show.setDescription("(Review description and genres): "+show.getDescription());
			System.out.println(show.tabbedString());
		}
	}
}