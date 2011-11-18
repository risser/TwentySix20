package com.twentysix20.dlc;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.twentysix20.dlc.model.Artist;
import com.twentysix20.dlc.rawxml.DlcXmlReader;
import com.twentysix20.dlc.rawxml.RawSong;
import com.twentysix20.dlc.rawxml.RawSongContainer;
import com.twentysix20.dlc.rawxml.RawSongConverter;

public class DLCProcessor {

	private static final String XML_FILENAME = "c:\\temp\\dlc.xml";

	static public void main(String[] args) throws FileNotFoundException, IOException {
		DlcXmlReader reader = new DlcXmlReader(XML_FILENAME);
		RawSongContainer rawSongs = reader.readRawSongContainer();
		RawSongConverter converter = new RawSongConverter();
		DLCCollection dlc = new DLCCollection();
		for (RawSong rawSong : rawSongs.getSongs())
			dlc.addSong(converter.convert(rawSong));

		List<Artist> artists = dlc.sortedArtistList();
		for (Artist artist : artists)
			System.out.println(artist);
		
		System.out.println("\nDone.");
	}
}
