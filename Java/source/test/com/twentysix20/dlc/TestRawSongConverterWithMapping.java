package com.twentysix20.dlc;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.twentysix20.dlc.mapping.StringMapping;
import com.twentysix20.dlc.model.ArtistFactory;
import com.twentysix20.dlc.model.DiscFactory;
import com.twentysix20.dlc.model.PackFactory;
import com.twentysix20.dlc.model.Song;
import com.twentysix20.dlc.rawxml.RawSong;
import com.twentysix20.dlc.rawxml.RawSongConverter;


public class TestRawSongConverterWithMapping {
	@Before public void clearFactories() {
		DiscFactory.clear();
		ArtistFactory.clear();
		PackFactory.clear();
	}

	@Test public void withArtistMappings() {
		StringMapping map = new StringMapping();
		map.addMapping("T.Rex", "T. Rex");
		map.addMapping("P!nk", "Pink");

		RawSongConverter converter = new RawSongConverter();
		converter.setArtistMapping(map);
		
		Song song = converter.convert(rawSong("T.Rex"));
		assertEquals("T. Rex", song.getArtist().getName());
		
		Song song2 = converter.convert(rawSong("P!nk"));
		assertEquals("Pink", song2.getArtist().getName());
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