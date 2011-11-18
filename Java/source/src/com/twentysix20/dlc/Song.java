package com.twentysix20.dlc;

public class Song {
	private final int id;
	private final String title;
	private final Artist artist;
	private final Disc disc;
	private final Pack pack;
	private Integer year;
	private Genre genre;

	public Song(int id, String title, Artist artist, Disc disc, Pack pack, Integer year, Genre genre) {
		super();
		this.id = id;
		this.title = title;
		this.artist = artist;
		this.disc = disc;
		this.pack = pack;
		this.year = year;
		this.genre = genre;
	}

	public int getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + title.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Song other = (Song) obj;
		if (id != other.id)
			return false;
		return title.equals(other.title);
	}

	@Override
	public String toString() {
		return "Song [(" + id + ") " + artist.getName() + " - " + title
				+ ", year=" + year + ", genre=" + genre 
				+ ", disc=" + disc.getName() + ", pack=" + pack.getName() + "]";
	}

	public Artist getArtist() {
		return artist;
	}

	public Disc getDisc() {
		return disc;
	}

	public Pack getPack() {
		return pack;
	}

	public Integer getYear() {
		return year;
	}

	public Genre getGenre() {
		return genre;
	}
}
