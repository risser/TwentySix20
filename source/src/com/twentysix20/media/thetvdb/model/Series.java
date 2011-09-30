package com.twentysix20.media.thetvdb.model;

public class Series {
	private String id;
	private String seriesid;
	private String language;
	private String SeriesName;
	private String FirstAired;
	private String banner;
	private String IMDB_ID;
	private String zap2it_id;
	private String Overview;

	public Series(String id, String seriesID, String language, String seriesName) {
		super();
		this.id = id;
		this.seriesid = seriesID;
		this.language = language;
		this.SeriesName = seriesName;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getSeriesID() {
		return seriesid;
	}
	public void setSeriesID(String seriesID) {
		this.seriesid = seriesID;
	}

	public String getSeriesName() {
		return SeriesName;
	}
	public void setSeriesName(String seriesName) {
		this.SeriesName = seriesName;
	}

	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}

	public String getFirstAired() {
		return FirstAired;
	}
	public void setFirstAired(String firstAired) {
		this.FirstAired = firstAired;
	}

	public String getBannerURL() {
		return banner;
	}
	public void setBannerURL(String bannerURL) {
		this.banner = bannerURL;
	}

	public String getImdbID() {
		return IMDB_ID;
	}
	public void setImdbID(String imdbID) {
		this.IMDB_ID = imdbID;
	}

	public String getZap2itID() {
		return zap2it_id;
	}

	public void setZap2itID(String zap2itID) {
		this.zap2it_id = zap2itID;
	}

	public String getOverview() {
		return Overview;
	}
	public void setOverview(String overview) {
		this.Overview = overview;
	}

	@Override
	public String toString() {
		return String.format(
				"Series [id=%s, seriesID=%s, seriesName=%s, firstAired=%s, language=%s, bannerURL='%s', imdbID=%s, zap2itID=%s, overview=%s]",
				id, seriesid, SeriesName, FirstAired, language, banner, IMDB_ID, zap2it_id, Overview);
	}
}