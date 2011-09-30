package com.twentysix20.lyrics;

import java.io.IOException;

public interface LyricPageHandler {
	void fetchLyrics() throws IOException;

	String artist();
	String title();
	String lyrics();
	String errorMessage();
	long timing();

	String nameOfSite();
}
