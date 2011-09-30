package com.twentysix20.media.webreaders.movie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.twentysix20.util.StringUtil;

public class ShowData {
	private String episodeTitle;
	private List<String> directors = new ArrayList<String>();
	private List<String> writers = new ArrayList<String>();
	private List<String> actors = new ArrayList<String>();
	private List<String> studios = new ArrayList<String>();
	private List<String> genres = new ArrayList<String>();
	private String year;
	private Rating rating;
	private String description;
	private Integer season;
	private Integer episode;
	private String showCode;
	private String showName;


	public ShowData() {}

	public ShowData(String exampleData) {
//		"0:The Card Game	1:The Cosby Show	2:2	3:23	4:csby	
//		5:Bill Cosby, Keshia Knight Pulliam, Lisa Bonet, Malcolm-Jamal Warner, Phylicia Rashad, Tempestt Bledsoe, Earle Hyman, Tanya Wright, Roscoe Lee Browne, Bill Gunn	
//		6:Jay Sandrich	7:	8:	9:Comedy, Family	10:NR	11:1986	12:Cliff's college English professor, Dr. Foster offers to fill in when Cliff's pinochle partner becomes ill. Theo buys a ring for his girlfriend.";
		String[] data = (exampleData+"\t\t\t\t\t\t\t\t\t\t\t\t.").split("\t");
		episodeTitle = data[0];
		directors = commaSeparatedStringToList(data[6]);
		writers = commaSeparatedStringToList(data[7]);
		actors = commaSeparatedStringToList(data[5]);
		studios = commaSeparatedStringToList(data[8]);
		genres = commaSeparatedStringToList(data[9]);
		year = data[11];
		rating = Rating.fromString(data[10]);
		description = data[12];
		season = Integer.valueOf(data[2]);
		episode = Integer.valueOf(data[3]);
		showCode = data[4];
		showName = data[1];
	}

	public String getEpisodeTitle() {
		return episodeTitle;
	}
	public void setEpisodeTitle(String title) {
		this.episodeTitle = title;
	}
	public List<String> getDirectors() {
		return directors;
	}
	public void setDirectors(List<String> directors) {
		this.directors = directors;
	}
	public List<String> getWriters() {
		return writers;
	}
	public void setWriters(List<String> writers) {
		this.writers = writers;
	}
	public List<String> getActors() {
		return actors;
	}
	public void setActors(List<String> actors) {
		this.actors = actors;
	}
	public List<String> getStudios() {
		return studios;
	}
	public void setStudios(List<String> companies) {
		this.studios = companies;
	}
	public List<String> getGenres() {
		return genres;
	}
	public void setGenres(List<String> genres) {
		this.genres = genres;
	}
	public String getDate() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public Rating getRating() {
		return rating;
	}
	public void setRating(Rating rating) {
		this.rating = rating;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

//	@Override
//	public String toString() {
//		return String
//				.format(
//						"ShowData [\n\ttitle=%s\n\tactors=%s\n\tdirectors=%s\n\twriters=%s\n\tcompanies=%s\n\tgenres=%s"
//						+"\n\trating=%s\n\tyear=%s\n\tdescription%s='%s']",
//						title, actors, directors, writers, companies, genres, 
//						rating, year, ((description != null) && (description.length() > 255) ? "*" : ""), description);
//	}

	public Integer getSeason() {
		return season;
	}
	public void setSeason(Integer season) {
		this.season = season;
	}
	public Integer getEpisode() {
		return episode;
	}
	public void setEpisode(Integer episode) {
		this.episode = episode;
	}
	public String getShowName() {
		return showName;
	}
	public void setShowName(String show) {
		this.showName = show;
	}
	public String getShowCode() {
		return showCode;
	}
	public void setShowCode(String showCode) {
		this.showCode = showCode;
	}

	@Override
	public String toString() {
		return String
				.format(
						"title=%s\n\tshow=%s\n\tseason=%s\n\tepisode=%s" +
						"\n\tshowCode=%s\n\tactors=%s\n\tdirectors=%s\n\twriters=%s" +
						"\n\tcompanies=%s\n\tgenres=%s" +
						"\n\trating=%s\n\tyear=%s\n\tdescription=%s",
						episodeTitle, showName, season, episode, showCode, 
						actors, directors, writers, 
						studios, genres, 
						rating, year, description);
	}

	public String tabbedString() {
		String tabbedStr = String
				.format(
						"%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s",
						episodeTitle, showName, season, episode, showCode, 
						s(actors), s(directors), s(writers), 
						s(studios), s(genres), 
						rating, year, description);
		return StringUtil.unescapeXml(tabbedStr);
	}

	private Object s(List<String> list) {
		if (list.isEmpty()) return "";

		String s = list.toString();
		if (s.length() > 255)
			s = s.replaceAll(", ", ",");
		if (s.length() > 255) {
			int comma = s.lastIndexOf(',',255);
			s = s.substring(0,comma)+" "+s.substring(comma);
		}
		return s.substring(1,s.length()-1);
	}

	private List<String> commaSeparatedStringToList(String string) {
		if (StringUtil.isEmpty(string))
			return new ArrayList<String>();
		return new ArrayList<String>(Arrays.asList(string.split(", ?")));
	}
}
