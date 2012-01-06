package com.twentysix20.media.tvboxer;

import java.util.List;

import com.coremedia.iso.boxes.apple.AppleItemListBox;
import com.twentysix20.media.webreaders.movie.Rating;
import com.twentysix20.media.webreaders.movie.ShowData;
import com.twentysix20.util.StringUtil;

public class MP4Updater {
	static private final String INFO_BOX_NOTICE = "Video captured and processed by 2620 Enterprises.";

	private MP4Facade tvFile;
	private AppleItemListBox appleBox;
	private ITunesMetaDataFacade metaData;

	public MP4Updater(MP4Facade tvFile) {
		this.tvFile = tvFile;
		this.appleBox = tvFile.getAppleItemListBox();
		this.metaData = new ITunesMetaDataFacade(appleBox);
	}

	public void updateWithShow(ShowData matchingShow) {
		updateEpisodeTitle(matchingShow.getEpisodeTitle());
		updateActors(matchingShow.getActors());
		updateGenres(matchingShow.getGenres());
		updateSeason(matchingShow.getSeason());
		updateEpisode(matchingShow.getEpisode());
		updateEpisodeCode(matchingShow.getShowCode(), matchingShow.getSeason(), matchingShow.getEpisode());
		updateShowName(matchingShow.getShowName());
		updateDescription(matchingShow.getDescription());
		updateRecordingDate(matchingShow.getDate());
		updateMediaType(ITunesMediaType.TV);
		updateRating(matchingShow.getRating());
		updateDirectors(matchingShow.getDirectors());
		updateWriters(matchingShow.getWriters());
		updateStudios(matchingShow.getStudios());
		updateCopyWarning(INFO_BOX_NOTICE);
	}

	private void updateRating(Rating rating) {
		metaData.setRating(rating);
	}

	private void updateShowName(String showName) {
		metaData.setAlbumName(showName);
		metaData.setAlbumArtistName(showName);
		metaData.setShowName(showName);
	}

	private void updateDescription(String description) {
		metaData.setSynopsis(description);
		metaData.setDescription(description);
	}

	private void updateEpisodeCode(String showCode, Integer season, Integer episode) {
		String code = showCode + 
				StringUtil.padLeft(season.toString(), 2, '0') + 
				"." + 
				StringUtil.padLeft(episode.toString(), 2, '0');
		metaData.setEpisodeCode(code);
	}

	private void updateEpisode(Integer episode) {
		metaData.setEpisodeNumber(episode);
	}

	private void updateSeason(Integer season) {
		metaData.setSeasonNumber(season);
	}

	public void updateEpisodeTitle(String episodeTitle) {
		String actualTitle = episodeTitle + (tvFile.isIpod() ? " (iPod)" : "");
		metaData.setEpisodeTitle(actualTitle);
	}

	public void updateRecordingDate(String date) {
		if (date.startsWith("'"))
			date = date.substring(1);
		metaData.setReleaseDateString(date);
	}

	public void updateMediaType(ITunesMediaType type) {
		metaData.setMediaType(type);
	}

	public void updateActors(List<String> actors) {
		metaData.setActors(actors);
		metaData.setCast(actors);
	}

	public void updateGenres(List<String> genres) {
		metaData.setGenres(genres);
	}

	public void updateDirectors(List<String> directors) {
		metaData.setDirectors(directors);
	}

	public void updateWriters(List<String> writers) {
		metaData.setScreenwriters(writers);
	}

	public void updateStudios(List<String> studios) {
		if (studios == null || studios.isEmpty())
			metaData.setStudio(null);
		else
			metaData.setStudio(studios.get(0));
	}

	public void updateCopyWarning(String warning) {
		metaData.setCopyWarning(warning);
	}
}