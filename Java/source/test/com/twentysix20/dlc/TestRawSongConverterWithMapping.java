package com.twentysix20.dlc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

import org.junit.Before;
import org.junit.Test;

import com.twentysix20.dlc.rawxml.RawSong;


public class TestRawSongConverterWithMapping {
	@Before public void clearFactories() {
		DiscFactory.clear();
		ArtistFactory.clear();
		PackFactory.clear();
	}

	@Test public void withArtistMapping() {
		RawSongConverter converter = new RawSongConverter();
		converter.addArtistMapping(new ArtistMapping("T.Rex", "T. Rex"));
		
		Song song = converter.convert(rawSong("T.Rex"));
		assertEquals("T. Rex", song.getArtist().getName());
	}

///////////////////////////////////////////////////

	private RawSong rawSong(String artistName) {
		RawSong song = new RawSong();
		song.setId(1234);
		song.setTitle("Hum Hum");
		song.setArtist(artistName);
		song.setDisc("DLC");
		song.setPack("Fake Package 01");
		song.setYear("1980");
		song.setGenre("Metal");
		return song;
	}
}