package com.twentysix20.lyrics.lookup;

import com.twentysix20.lyrics.BaseLyricHandlerFactory;
import com.twentysix20.lyrics.LyricPageHandler;
import com.twentysix20.util.html.InternetHtmlLoader;

public class LyricsManiaFactory extends BaseLyricHandlerFactory {
	public static final String NAME = "LyricsMania";
	public static final String DaNCE_NAME = "LyricsMania (Dance)";

	public LyricsManiaFactory(InternetHtmlLoader loader) {
		super(loader);
	}

	@Override
	public LyricPageHandler getLyricPageHandler(String urlString) {
		if (urlString.contains("/dancelyrics/"))
			return new LyricsManiaDanceHandler(DaNCE_NAME, loader, urlString);
		else
			return new LyricsManiaHandler(NAME, loader, urlString);
	}

	@Override
	public boolean matches(String url) {
		return url.contains("lyricsmania.com") && url.contains("_lyrics_") && !url.contains("_album_lyrics_");
	}

	@Override
	public boolean verify() {
		String verificationURL = "http://www.lyricsmania.com/10,000_angels_lyrics_edie_brickell_and_the_new_bohemians.html";
		String verificationLyricStart = "I'm feeling feelings like I never felt before";
		String verificationLyricEnd = "the Lord my soul to keep";
		String verificationArtist = "Edie Brickell And The New Bohemians";
		String verificationTitle = "10,000 Angels";
		boolean standardVerified = verify(verificationURL, verificationLyricStart, verificationLyricEnd, verificationArtist, verificationTitle);
		verified = false; // weird, but it's because verify sets verified.  I should probably think of a nice way to do this, perhaps Strategy, but right now, I'm lazy.

		String danceLyricVerificationURL = "http://www.lyricsmania.com/dancelyrics/roger_sanchez_another_chance_lyrics_112.html";
		String danceLyricVerificationLyricStart = "If I had another chance tonight";
		String danceLyricVerificationLyricEnd = "If I had another chance tonight";
		String danceLyricVerificationArtist = "Roger Sanchez";
		String danceLyricVerificationTitle = "Another Chance";
		boolean danceLyricVerified = verify(danceLyricVerificationURL, danceLyricVerificationLyricStart, danceLyricVerificationLyricEnd, danceLyricVerificationArtist, danceLyricVerificationTitle);
		return standardVerified && danceLyricVerified;
	}

	@Override
	public String nameOfSite() {
		return NAME;
	}

	static public void main (String s[]) {
		new LyricsManiaFactory(new InternetHtmlLoader()).verify();
	}
}
