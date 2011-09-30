package com.twentysix20.media.tvboxer.boxes;

import com.coremedia.iso.IsoFileConvenienceHelper;
import com.coremedia.iso.boxes.apple.AppleGenericBox;
import com.coremedia.iso.boxes.apple.AppleNameBox;
import com.twentysix20.media.webreaders.movie.Rating;

public class AppleMediaRatingBox extends MP4GenericBox {
	public static final String RATING_MEANING = "com.apple.iTunes";
	public static final String RATING_BOX_NAME = "iTunEXTC";

	public AppleMediaRatingBox(AppleGenericBox x) {
		super(x);
	}

	public AppleMediaRatingBox(Rating rating) {
		super(RATING_BOX_NAME, rating.toITunesBoxString(), 1);
		setRating(rating);
	}

	public void setRating(Rating rating) {
		setData(rating.toITunesBoxString(), 1);
	}

	public Rating getRating() {
		String data = getData();
		String rating = data.split("\\|")[1];
		return Rating.fromString(rating);
	}

	public static boolean isTheTypeOf(AppleGenericBox x) {
		AppleNameBox nameBox = (AppleNameBox)IsoFileConvenienceHelper.get(x, "name");
		return RATING_BOX_NAME.equals(nameBox.getName());
	}
}