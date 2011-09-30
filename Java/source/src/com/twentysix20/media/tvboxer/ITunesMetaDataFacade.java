package com.twentysix20.media.tvboxer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.coremedia.iso.IsoFileConvenienceHelper;
import com.coremedia.iso.boxes.apple.AbstractAppleMetaDataBox;
import com.coremedia.iso.boxes.apple.AppleAlbumBox;
import com.coremedia.iso.boxes.apple.AppleArtistBox;
import com.coremedia.iso.boxes.apple.AppleCustomGenreBox;
import com.coremedia.iso.boxes.apple.AppleDescriptionBox;
import com.coremedia.iso.boxes.apple.AppleGenericBox;
import com.coremedia.iso.boxes.apple.AppleItemListBox;
import com.coremedia.iso.boxes.apple.AppleMediaTypeBox;
import com.coremedia.iso.boxes.apple.AppleNetworkBox;
import com.coremedia.iso.boxes.apple.AppleRecordingYearBox;
import com.coremedia.iso.boxes.apple.AppleShowBox;
import com.coremedia.iso.boxes.apple.AppleSynopsisBox;
import com.coremedia.iso.boxes.apple.AppleTrackTitleBox;
import com.coremedia.iso.boxes.apple.AppleTvEpisodeBox;
import com.coremedia.iso.boxes.apple.AppleTvEpisodeNumberBox;
import com.coremedia.iso.boxes.apple.AppleTvSeasonBox;
import com.twentysix20.media.tvboxer.boxes.AppleMediaRatingBox;
import com.twentysix20.media.tvboxer.boxes.AppleVideoInfoBox;
import com.twentysix20.media.webreaders.movie.Rating;

public class ITunesMetaDataFacade {

	static Map<Class<? extends AbstractAppleMetaDataBox>, String> classToKeyMap = new HashMap<Class<? extends AbstractAppleMetaDataBox>, String>();

	private final AppleItemListBox appleItemListBox;
	private AppleMediaRatingBox ratingBox;
	private AppleVideoInfoBox videoInfoBox;

	public ITunesMetaDataFacade(AppleItemListBox container) {
		this.appleItemListBox = container;
		for (AppleGenericBox x : appleItemListBox.getBoxes(AppleGenericBox.class)) {
			if (AppleMediaRatingBox.isTheTypeOf(x))
				ratingBox = new AppleMediaRatingBox(x);
			if (AppleVideoInfoBox.isTheTypeOf(x))
				videoInfoBox = new AppleVideoInfoBox(x);
		}
	}

	public String getEpisodeTitle() {
		return getBoxValue(AppleTrackTitleBox.class);
	}
	public void setEpisodeTitle(String title) {
		setBoxValue(AppleTrackTitleBox.class, title);
	}

	public List<String> getActors() {
		return stringToList(getBoxValue(AppleArtistBox.class));
	}
	public void setActors(List<String> actors) {
		String actorString = listToString(actors);
		if (actorString.length() > 255) {
			actorString = actorString.replaceAll(", ", ",");
			while (actorString.length() > 255) {
				actorString = actorString.substring(0, actorString.lastIndexOf(','));
			}
		}
		setBoxValue(AppleArtistBox.class, actorString);
	}

	public List<String> getGenres() {
		return stringToList(getBoxValue(AppleCustomGenreBox.class));
	}
	public void setGenres(List<String> genres) {
		setBoxValue(AppleCustomGenreBox.class, listToString(genres));
	}

	public String getShowName() {
		return getBoxValue(AppleShowBox.class);
	}
	public void setShowName(String name) {
		setBoxValue(AppleShowBox.class, name);
	}

	public String getNetwork() {
		return getBoxValue(AppleNetworkBox.class);
	}
	public void setNetwork(String network) {
		setBoxValue(AppleNetworkBox.class, network);
	}

	public int getSeasonNumber() {
		String seasonStr = getBoxValue(AppleTvSeasonBox.class);
		return (seasonStr == null ? 0 : Integer.valueOf(seasonStr));
	}
	public void setSeasonNumber(int season) {
		setBoxValue(AppleTvSeasonBox.class, Integer.toString(season));
	}

	public int getEpisodeNumber() {
		String episodeStr = getBoxValue(AppleTvEpisodeBox.class);
		return (episodeStr == null ? 0 : Integer.valueOf(episodeStr));
	}
	public void setEpisodeNumber(int episode) {
		setBoxValue(AppleTvEpisodeBox.class, Integer.toString(episode));
	}

	public String getEpisodeCode() {
		return getBoxValue(AppleTvEpisodeNumberBox.class);
	}
	public void setEpisodeCode(String code) {
		setBoxValue(AppleTvEpisodeNumberBox.class, code);
	}

	public String getReleaseDateString() {
		return getBoxValue(AppleRecordingYearBox.class);
	}
	public void setReleaseDateString(String date) {
		if (!date.matches("[0-9]{4}(\\-[0-9]{2}\\-[0-9]{2})?"))
			throw new IllegalArgumentException("Invalid date format.  Must be integer year or yyyy-mm-dd");
		setBoxValue(AppleRecordingYearBox.class, date);
	}

	public String getSynopsis() {
		return getBoxValue(AppleSynopsisBox.class);
	}
	public void setSynopsis(String desc) {
		setBoxValue(AppleSynopsisBox.class, desc);
	}

	public String getDescription() {
		return getBoxValue(AppleDescriptionBox.class);
	}
	public void setDescription(String desc) {
		setBoxValue(AppleDescriptionBox.class, desc);
	}

	public ITunesMediaType getMediaType() {
		return ITunesMediaType.fromString(getBoxValue(AppleMediaTypeBox.class));
	}
	public void setMediaType(ITunesMediaType type) {
		setBoxValue(AppleMediaTypeBox.class, type.getValueAsString());
	}

	public Rating getRating() {
		return (ratingBox == null ? null : ratingBox.getRating());
	}
	public void setRating(Rating rating) {
		if (ratingBox == null) {
			ratingBox = new AppleMediaRatingBox(rating);
			appleItemListBox.addBox(ratingBox.getUnderlyingBox());
		}

		ratingBox.setRating(rating);
	}

	public void setCast(List<String> cast) {
		createVideoInfoBoxIfNeeded();
		videoInfoBox.setCast(cast);
	}

	public void setDirectors(List<String> directors) {
		createVideoInfoBoxIfNeeded();
		videoInfoBox.setDirectors(directors);
	}

	public void setCodirectors(List<String> codirectors) {
		createVideoInfoBoxIfNeeded();
		videoInfoBox.setCodirectors(codirectors);
	}

	public void setScreenwriters(List<String> screenwriters) {
		createVideoInfoBoxIfNeeded();
		videoInfoBox.setScreenwriters(screenwriters);
	}

	public void setProducers(List<String> producers) {
		createVideoInfoBoxIfNeeded();
		videoInfoBox.setProducers(producers);
	}

	public void setStudio(String studio) {
		createVideoInfoBoxIfNeeded();
		videoInfoBox.setStudio(studio);
	}

	public void setCopyWarning(String copyWarning) {
		createVideoInfoBoxIfNeeded();
		videoInfoBox.setCopyWarning(copyWarning);
	}

	private void createVideoInfoBoxIfNeeded() {
		if (videoInfoBox == null) {
			videoInfoBox = new AppleVideoInfoBox();
			appleItemListBox.addBox(videoInfoBox.getUnderlyingBox());
		}
	}

	public String getAlbumName() {
		return getBoxValue(AppleAlbumBox.class);
	}
	public void setAlbumName(String code) {
		setBoxValue(AppleAlbumBox.class, code);
	}

	private String getBoxValue(Class<? extends AbstractAppleMetaDataBox> cls) {
		String key = getKeyForClass(cls);
		AbstractAppleMetaDataBox box = (AbstractAppleMetaDataBox)IsoFileConvenienceHelper.get(appleItemListBox, key);
		return (box == null ? null : box.getValue());
	}

	private void setBoxValue(Class<? extends AbstractAppleMetaDataBox> cls, String value) {
		String key = getKeyForClass(cls);
		AbstractAppleMetaDataBox box = (AbstractAppleMetaDataBox)IsoFileConvenienceHelper.get(appleItemListBox, key);
	    if (box == null) {
		    try {
				box = cls.newInstance();
			} catch (InstantiationException e) {
				throw new RuntimeException("Instantiation Exception.", e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException("Illegal Access Exception.", e);
			}
			appleItemListBox.addBox(box);
	    }
		box.setValue(value);
	}

	private String getKeyForClass(Class<? extends AbstractAppleMetaDataBox> cls) {
		String key = classToKeyMap.get(cls);
		if (key == null) {
		    try {
		    	AbstractAppleMetaDataBox box = cls.newInstance();
				key = (String)cls.getField("TYPE").get(box);
				classToKeyMap.put(cls, key);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return key;
	}

	private String listToString(List<String> actors) {
		return actors.toString().replaceAll("[\\[\\]]","");
	}

	private List<String> stringToList(String values) {
		List<String> list = new ArrayList<String>();
		if (values != null)
			for (String value : values.split(", ?"))
				list.add(value.trim());
		return list;
	}
}
