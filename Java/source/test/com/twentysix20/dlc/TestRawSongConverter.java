package com.twentysix20.dlc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

import org.junit.Before;
import org.junit.Test;

import com.twentysix20.dlc.model.ArtistFactory;
import com.twentysix20.dlc.model.DiscFactory;
import com.twentysix20.dlc.model.Genre;
import com.twentysix20.dlc.model.PackFactory;
import com.twentysix20.dlc.model.Song;
import com.twentysix20.dlc.rawxml.RawSong;
import com.twentysix20.dlc.rawxml.RawSongConverter;


public class TestRawSongConverter {
	@Before public void clearFactories() {
		DiscFactory.clear();
		ArtistFactory.clear();
		PackFactory.clear();
	}

	@Test public void songGetsArtist() {
		Song song = new RawSongConverter().convert(ObjectFactoryForTests.basicRawSong());
		assertSame(ArtistFactory.getArtist("Mr. Fake"), song.getArtist());
	}

	@Test public void songGetsDisc() {
		Song song = new RawSongConverter().convert(ObjectFactoryForTests.basicRawSong());
		assertSame(DiscFactory.getDisc("DLC"), song.getDisc());
	}

	@Test public void songGetsPack() {
		Song song = new RawSongConverter().convert(ObjectFactoryForTests.basicRawSong());
		assertSame(PackFactory.getPack("Fake Package 01"), song.getPack());
	}

	@Test public void songGetsYear() {
		Song song = new RawSongConverter().convert(ObjectFactoryForTests.basicRawSong());
		assertEquals(Integer.valueOf(1980), song.getYear());
	}

	@Test public void allAlphaYearResultsInNull() {
		RawSong rawSong = new RawSong();
		rawSong.setId(1234);
		rawSong.setTitle("Monster");
		rawSong.setArtist("Lady Gaga");
		rawSong.setDisc("DLC");
		rawSong.setPack("Lady Gaga 01");
		rawSong.setYear("The ");
		rawSong.setGenre("Pop-Rock");
		Song song = new RawSongConverter().convert(rawSong);
		assertEquals(null, song.getYear());
	}

	@Test public void songGetsGenre() {
		Song song = new RawSongConverter().convert(ObjectFactoryForTests.basicRawSong());
		assertEquals(Genre.Metal, song.getGenre());
	}

	@Test public void artistOnSong() {
		Song song = new RawSongConverter().convert(ObjectFactoryForTests.basicRawSong());

		assertSame(ArtistFactory.getArtist("Mr. Fake"), song.getArtist());
		assertSame(song, song.getArtist().getSongs().iterator().next());
	}

	@Test public void differentSongsAreDifferent() {
		Song song = new RawSongConverter().convert(ObjectFactoryForTests.basicRawSong());
		Song song2 = new RawSongConverter().convert(ObjectFactoryForTests.anotherRawSongSameEverything());

		assertNotSame(song, song2);
		assertFalse(song.equals(song2));
	}

	@Test public void sameArtistHasAnotherSong() {
		Song song = new RawSongConverter().convert(ObjectFactoryForTests.basicRawSong());
		new RawSongConverter().convert(ObjectFactoryForTests.anotherRawSongSameEverything());

		assertEquals(2, song.getArtist().getSongs().size());
	}

	@Test public void packOnSong() {
		Song song = new RawSongConverter().convert(ObjectFactoryForTests.basicRawSong());

		assertSame(PackFactory.getPack("Fake Package 01"), song.getPack());
		assertSame(song, song.getPack().getSongs().iterator().next());
	}

	@Test public void samePackHasAnotherSong() {
		Song song = new RawSongConverter().convert(ObjectFactoryForTests.basicRawSong());
		new RawSongConverter().convert(ObjectFactoryForTests.anotherRawSongSameEverything());

		assertEquals(2, song.getPack().getSongs().size());
	}

	@Test public void discOnSong() {
		Song song = new RawSongConverter().convert(ObjectFactoryForTests.basicRawSong());

		assertSame(DiscFactory.getDisc("DLC"), song.getDisc());
		assertSame(song, song.getDisc().getSongs().iterator().next());
	}

	@Test public void sameDiscHasAnotherSong() {
		Song song = new RawSongConverter().convert(ObjectFactoryForTests.basicRawSong());
		new RawSongConverter().convert(ObjectFactoryForTests.anotherRawSongSameEverything());

		assertEquals(2, song.getDisc().getSongs().size());
	}
}