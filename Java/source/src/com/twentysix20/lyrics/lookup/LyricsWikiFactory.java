package com.twentysix20.lyrics.lookup;

import com.twentysix20.lyrics.BaseLyricHandlerFactory;
import com.twentysix20.lyrics.LyricPageHandler;
import com.twentysix20.util.html.InternetHtmlLoader;

public class LyricsWikiFactory extends BaseLyricHandlerFactory {
	public static final String NAME = "LyricsWiki";

	public LyricsWikiFactory(InternetHtmlLoader loader) {
		super(loader);
	}

	@Override
	public LyricPageHandler getLyricPageHandler(String urlString) {
		if (urlString.contains("/Gracenote:"))
			return new LyricsWikiGracenoteHandler(NAME, loader, urlString);
		else
			return new LyricsWikiHandler(NAME, loader, urlString);
	}

	@Override
	public boolean matches(String url) {
		return url.matches("http://lyrics.wikia.com/.+:.+") && !url.startsWith("http://lyrics.wikia.com/Talk:");
	}

	@Override
	public boolean verify() {
		String verificationURL = "http://lyrics.wikia.com/Parliament:Come_In_Out_Of_The_Rain";
		String verificationLyricStart = "Happiness and peace";
		String verificationLyricEnd = "x2 til fade";
		String verificationArtist = "Parliament";
		String verificationTitle = "Come In Out Of The Rain";
		boolean standardVerified = verify(verificationURL, verificationLyricStart, verificationLyricEnd, verificationArtist, verificationTitle);
		verified = false; // weird, but it's because verify sets verified.  I should probably think of a nice way to do this, perhaps Strategy, but right now, I'm lazy.

		String graceNoteVerificationURL = "http://lyrics.wikia.com/Gracenote:Ray_Charles:Tell_Me_How_Do_You_Feel";
		String graceNoteVerificationLyricStart = "Tell me how do you feel";
		String graceNoteVerificationLyricEnd = "I wanna know, I wanna know";
		String graceNoteVerificationArtist = "Ray Charles";
		String graceNoteVerificationTitle = "Tell Me How Do You Feel";
		boolean graceNoteVerified = verify(graceNoteVerificationURL, graceNoteVerificationLyricStart, graceNoteVerificationLyricEnd, graceNoteVerificationArtist, graceNoteVerificationTitle);
		return standardVerified && graceNoteVerified;
	}

	@Override
	public String nameOfSite() {
		return NAME;
	}
}