package com.twentysix20.media.thetvdb.model;

import java.util.List;

public class SeriesSearchResults {
	public List<Series> series;

	@Override
	public String toString() {
		return String.format("SeriesSearchResults=%s", series);
	}
}
