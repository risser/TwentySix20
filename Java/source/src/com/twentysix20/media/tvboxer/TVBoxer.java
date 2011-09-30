package com.twentysix20.media.tvboxer;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.twentysix20.media.webreaders.movie.ShowData;
import com.twentysix20.util.FileUtil;

public class TVBoxer {
	public static void main(String[] args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader stdin = new BufferedReader(isr);

		System.out.println("Enter Directories:");
		String directoryNames = stdin.readLine();

		Set<File> directories = parseAndValidateDirectories(directoryNames);


		System.out.println("Enter Tabbed Information:");
		String line;
		Set<ShowData> shows = new HashSet<ShowData>();
		while (!"".equals(line = stdin.readLine())) {
			ShowData showData = new ShowData(line);
			shows.add(showData);
		}

		Set<File> files = FileUtil.findFilesInSubdirectories(directories, new TVFileFilter());
		Set<ShowData> matchedShows = new HashSet<ShowData>();
		List<String> unmatchedFiles = new ArrayList<String>();
		for (File file : files) {
			System.out.println("\nProcessing: "+file.getName());
			ShowData matchingShow = getMatchingDataForFile(file, shows);
			if (matchingShow == null) {
				System.out.println("-- No matching show data for file");
				unmatchedFiles.add(file.getName());
			} else {
				MP4Facade tvFile = new MP4Facade(file);
				try {
					new MP4Updater(tvFile).updateWithShow(matchingShow);
				} catch (IllegalStateException e) {
					System.out.println("-- No Apple Box found.  Couldn't process file.  Please set one up.");
					continue;
				}
				tvFile.writeUpdates();
				matchedShows.add(matchingShow);
				tvFile.close();
			}
		}

		shows.removeAll(matchedShows);
		if (!shows.isEmpty()) {
			System.out.println("\n\nUnmatched Shows:");
			List<String> unmatchedShows = new ArrayList<String>();
			for (ShowData show : shows) {
				unmatchedShows.add(String.format(" - %s %02d.%02d: %s",show.getShowName(), show.getSeason(), show.getEpisode(), show.getEpisodeTitle()));
			}
			Collections.sort(unmatchedShows);
			for (String outputLine : unmatchedShows) {
				System.out.println(outputLine);
			}
		}

		if (!unmatchedFiles.isEmpty()) {
			Collections.sort(unmatchedFiles);
			System.out.println("\n\nUnmatched Files:");
			for (String file : unmatchedFiles) {
				System.out.println(String.format(" - %s",file));
			}
		}

		System.out.println("\nComplete!");
	}

	private static Set<File> parseAndValidateDirectories(String directoryNames) throws IOException {
		Set<File> directories = new HashSet<File>();
		for (String directoryName : directoryNames.split(";")) {
			File directory = new File(directoryName);
			if (!directory.isDirectory())
				throw new IOException("Directory '"+directoryName+"' must actually be a directory!");
			directories.add(directory);
		}
		return directories;
	}

	private static ShowData getMatchingDataForFile(File file, Set<ShowData> shows) {
		FileToShowMatcher matcher = new FileToShowMatcher();
		for (ShowData show : shows) {
			if (matcher.matches(file, show))
				return show;
		}
		return null;
	}
}