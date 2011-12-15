package com.twentysix20.dlc;

import com.twentysix20.dlc.model.ArtistFactory;
import com.twentysix20.dlc.model.DiscFactory;
import com.twentysix20.dlc.model.Genre;
import com.twentysix20.dlc.model.PackFactory;
import com.twentysix20.dlc.model.Song;
import com.twentysix20.dlc.rawxml.RawSong;

public class ObjectFactoryForTests {
	static public RawSong basicRawSong() {
		RawSong song = new RawSong();
		song.setId(1234);
		song.setTitle("Hum Hum");
		song.setArtist("Mr. Fake");
		song.setDisc("DLC");
		song.setPack("Fake Package 01");
		song.setYear("1980");
		song.setGenre("Metal");
		return song;
	}

	public static RawSong anotherRawSongSameEverything() {
		RawSong song = new RawSong();
		song.setId(321);
		song.setTitle("Dum Dum Monkey");
		song.setArtist("Mr. Fake");
		song.setDisc("DLC");
		song.setPack("Fake Package 01");
		song.setYear("1981");
		song.setGenre("Rock");
		return song;
	}

	public static Song basicSong() {
		Song song = new Song(1234, 
				"Hum Hum",
				ArtistFactory.getArtist("Mr. Fake"),
				DiscFactory.getDisc("DLC"),
				PackFactory.getPack("Fake Package 01"),
				1980,
				Genre.Metal);
		return song;
	}

	public static Song anotherSongSameEverything() {
		Song song = new Song(321, 
				"Dum Dum Monkey",
				ArtistFactory.getArtist("Mr. Fake"),
				DiscFactory.getDisc("DLC"),
				PackFactory.getPack("Fake Package 01"),
				1981,
				Genre.Rock);
		return song;
	}

	public static Song anotherSongDifferentEverything() {
		Song song = new Song(789, 
				"Dum Dum Monkey",
				ArtistFactory.getArtist("Mr. Crowley"),
				DiscFactory.getDisc("Rock Band 999"),
				PackFactory.getPack("Ozzy Rules 01"),
				1982,
				Genre.Metal);
		return song;
	}
}
