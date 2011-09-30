package com.twentysix20.lyrics;

import com.twentysix20.lyrics.lookup.AZLyricFactory;
import com.twentysix20.lyrics.lookup.JustSomeLyricsFactory;
import com.twentysix20.lyrics.lookup.Lyrics007Factory;
import com.twentysix20.lyrics.lookup.LyricsManiaFactory;
import com.twentysix20.lyrics.lookup.LyricsModeFactory;
import com.twentysix20.lyrics.lookup.LyricsVIPFactory;
import com.twentysix20.lyrics.lookup.LyricsWikiFactory;
import com.twentysix20.lyrics.lookup.MetroLyricsFactory;
import com.twentysix20.lyrics.lookup.STLyricsFactory;
import com.twentysix20.lyrics.lookup.Sing365Factory;
import com.twentysix20.lyrics.lookup.SongMeaningsFactory;
import com.twentysix20.lyrics.lookup.TuneWikiFactory;
import com.twentysix20.lyrics.lookup.UULyricsFactory;
import com.twentysix20.util.html.InternetHtmlLoader;

public class VerifyAll {

	public static void main(String[] args) throws Exception {
		System.setProperty("http.agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
 
		new SongMeaningsFactory(new InternetHtmlLoader()).verify();
		new UULyricsFactory(new InternetHtmlLoader()).verify();
		new LyricsModeFactory(new InternetHtmlLoader()).verify();
		new Sing365Factory(new InternetHtmlLoader()).verify();
		new MetroLyricsFactory(new InternetHtmlLoader()).verify();
		new AZLyricFactory(new InternetHtmlLoader()).verify();
		new STLyricsFactory(new InternetHtmlLoader()).verify();
		new Lyrics007Factory(new InternetHtmlLoader()).verify();
		new LyricsManiaFactory(new InternetHtmlLoader()).verify();
		new LyricsWikiFactory(new InternetHtmlLoader()).verify();
		new LyricsVIPFactory(new InternetHtmlLoader()).verify();
		new JustSomeLyricsFactory(new InternetHtmlLoader()).verify();

		new TuneWikiFactory(new InternetHtmlLoader()).verify();
	}
}