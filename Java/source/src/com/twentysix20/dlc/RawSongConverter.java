package com.twentysix20.dlc;

import com.twentysix20.dlc.rawxml.RawSong;

public class RawSongConverter {

	private ArtistMapping artistMapping;

	public Song convert(RawSong basicRawSong) {
		Artist artist = ArtistFactory.getArtist(artist(basicRawSong));
		Pack pack = PackFactory.getPack(pack(basicRawSong));
		Disc disc = DiscFactory.getDisc(disc(basicRawSong));
		Integer intYear = year(basicRawSong);
		Genre genre = genre(basicRawSong);

		Song song = new Song(basicRawSong.getId(), basicRawSong.getTitle(), artist, disc, pack, intYear, genre);

		artist.addSong(song);
		pack.addSong(song);
		disc.addSong(song);

		return song;
	}

	private Genre genre(RawSong basicRawSong) {
		Genre genre = Genre.fromString(basicRawSong.getGenre());
		return genre;
	}

	private Integer year(RawSong basicRawSong) {
		Integer intYear = null;
		if (basicRawSong.getYear().matches("(19|20)[0-9][0-9]"))
			intYear = Integer.valueOf(basicRawSong.getYear());
		else
			System.err.println("Bad Year for '"+basicRawSong.getTitle()+"': '"+basicRawSong.getYear()+"'");
		return intYear;
	}

	private String disc(RawSong basicRawSong) {
		return basicRawSong.getDisc();
	}

	private String pack(RawSong basicRawSong) {
		return basicRawSong.getPack();
	}

	private String artist(RawSong basicRawSong) {
		String artist = basicRawSong.getArtist();
		if (artistMapping != null)
			artist = artistMapping.map(artist);
		return artist;
	}

	public void addArtistMapping(ArtistMapping artistMapping) {
		this.artistMapping = artistMapping;
	}
}
