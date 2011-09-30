package com.twentysix20.lyrics;

public interface LyricHandlerFactory {
	boolean matches(String url);
	boolean verify();
	String nameOfSite();

	LyricPageHandler getLyricPageHandler(String urlString);
}
