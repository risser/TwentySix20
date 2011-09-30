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

		System.out.println("Enter URLs:");
		String url;
		while (!"".equals(url = stdin.readLine())) {
			data.add(new MovieDataParser(new FancyInternetHtmlLoader()).parse(url));
		}

		System.out.println("\n\n");
		for (ShowData show : data) {
			System.out.println(show.tabbedString());
		}
	}
}