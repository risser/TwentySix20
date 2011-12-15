package com.twentysix20.dlc;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.twentysix20.dlc.mapping.IDMappingReader;
import com.twentysix20.dlc.mapping.StringMappingReader;
import com.twentysix20.dlc.model.Artist;
import com.twentysix20.dlc.model.Song;
import com.twentysix20.dlc.rawxml.DlcXmlReader;
import com.twentysix20.dlc.rawxml.RawSong;
import com.twentysix20.dlc.rawxml.RawSongContainer;
import com.twentysix20.dlc.rawxml.RawSongConverter;
import com.twentysix20.util.FileUtil;

public class DLCProcessor {

	private static final String XML_FILENAME = "c:\\temp\\dlc.xml";

	static public void main(String[] args) throws FileNotFoundException, IOException {
		String dataPath = FileUtil.getFullPathFileName(DLCProcessor.class, "data");

		DlcXmlReader reader = new DlcXmlReader(XML_FILENAME);
		RawSongContainer rawSongs = reader.readRawSongContainer();

		StringMappingReader artistMappingReader = new StringMappingReader(dataPath+"\\ArtistMapping.txt");
		IDMappingReader yearMappingReader = new IDMappingReader(dataPath+"\\YearMapping.txt");

		RawSongConverter converter = new RawSongConverter();
		converter.setArtistMapping(artistMappingReader.createMapping());
		converter.setYearMapping(yearMappingReader.createMapping());

		DLCCollection dlc = new DLCCollection();
		for (RawSong rawSong : rawSongs.getSongs()) {
			Song song = converter.convert(rawSong);
//System.out.println(song);			
			dlc.addSong(song);
		}

//		for (Artist artist : dlc.sortedArtistList())
//			System.out.println(artist);

		Map<Integer, List<Artist>> decadeArtistMap = dlc.sortedArtistsByDecade();
		for (int decade : decadeArtistMap.keySet()) {
			System.out.println(decade+"0's:");
			for (Artist artist : decadeArtistMap.get(decade))
				System.out.println(" - " + artist.getName());
		}
		
		System.out.println("\nDone.");
	}
}
