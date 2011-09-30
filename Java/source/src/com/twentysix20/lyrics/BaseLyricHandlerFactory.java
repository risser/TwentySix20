package com.twentysix20.lyrics;

import java.io.IOException;

import com.twentysix20.lyrics.exception.LyricHandlerVerificationException;
import com.twentysix20.util.html.InternetHtmlLoader;

abstract public class BaseLyricHandlerFactory implements LyricHandlerFactory {
	protected InternetHtmlLoader loader;
	protected boolean verified = false;

	public BaseLyricHandlerFactory(InternetHtmlLoader loader) {
		this.loader = loader;
	}

	protected boolean verify(String verificationURL, String verificationLyricStart, String verificationLyricEnd,
			String verificationArtist, String verificationTitle) {
		if (verified) return true;

		System.out.println("Verifying "+nameOfSite()+"...");
		LyricPageHandler handler = getLyricPageHandler(verificationURL);
		try {
			handler.fetchLyrics();
		} catch (IOException e) {
			throw new LyricHandlerVerificationException("Couldn't verify "+handler.nameOfSite()+": IO Exception", e);
		}

		if (!handler.lyrics().startsWith(verificationLyricStart) ||
				!handler.lyrics().endsWith(verificationLyricEnd))
			throw new LyricHandlerVerificationException("Couldn't verify "+handler.nameOfSite()+": lyrics don't match:\n"+handler.lyrics());

		if (!handler.artist().equals(verificationArtist))
			throw new LyricHandlerVerificationException("Couldn't verify "+handler.nameOfSite()+": artist doesn't match '"+verificationArtist+"' (was "+handler.artist()+").");

		if (!handler.title().equals(verificationTitle))
			throw new LyricHandlerVerificationException("Couldn't verify "+handler.nameOfSite()+": title doesn't match '"+verificationTitle+"' (was "+handler.title()+").");

		verified = true;
		return true;
	}

}
