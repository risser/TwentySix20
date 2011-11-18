package com.twentysix20.dlc.rawxml;

public class RawSong {
	private int id;
	private String artist;
	private String title;
	private String genre;
	private String year;
	private String pack;
	private String disc;
	private String purchased;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getPack() {
		return pack;
	}
	public void setPack(String pack) {
		this.pack = pack;
	}
	public String getDisc() {
		return disc;
	}
	public void setDisc(String disc) {
		this.disc = disc;
	}
	public String getPurchased() {
		return purchased;
	}
	public void setPurchased(String purchased) {
		this.purchased = purchased;
	}
	@Override
	public String toString() {
		return "RawSong [artist=" + artist + ", disc=" + disc + ", genre="
				+ genre + ", id=" + id + ", pack=" + pack + ", title=" + title
				+ ", year=" + year + "]";
	}
}
