package com.twentysix20.lyrics;

import java.io.IOException;

import com.twentysix20.util.html.InternetHtmlLoader;

abstract public class BaseLyricHandler implements LyricPageHandler {
	protected InternetHtmlLoader loader;
	protected String urlString;
	protected String pageData;
	protected String artist;
	protected String title;
	protected String lyrics;
	protected String errorMessage;
	protected long timing;
	protected String siteName;
	protected LyricParser lyricParser;
	protected ArtistTitleParser atParser;

	public BaseLyricHandler(String siteName, InternetHtmlLoader loader, String urlString) {
		this.siteName = siteName;
		this.loader = loader;
		this.urlString = urlString.replaceAll("%25","%");
		setupParsers();
	}

	@Override
	public void fetchLyrics() throws IOException {
		long startMillis = System.currentTimeMillis();
		pageData = loader.getHtmlPage(urlString);
		parseData();
		timing = System.currentTimeMillis() - startMillis;
	}

	protected void parseData() {
		artist = atParser.parseArtist(pageData);
		title = atParser.parseTitle(pageData);
		lyrics = lyricParser.parseLyrics(pageData, artist);
	}

	abstract protected void setupParsers();

	@Override
	public String artist() {
		assertPageLoaded();
		return artist;
	}

	@Override
	public String lyrics() {
		assertPageLoaded();
		return lyrics;
	}

	@Override
	public String title() {
		assertPageLoaded();
		return title;
	}

	@Override
	public String errorMessage() {
		assertPageLoaded();
		return errorMessage;
	}

	@Override
	public String nameOfSite() {
		assertPageLoaded();
		return siteName;
	}

	@Override
	public long timing() {
		assertPageLoaded();
		return timing;
	}

	private void assertPageLoaded() {
		if (pageData == null)
			throw new IllegalStateException("Page data must be fetched before lyric data can be accessed.");
	}
}