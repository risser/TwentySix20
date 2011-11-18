package com.twentysix20.dlc.model;

public enum Genre {
	Alternative,
	Blues,
	Classic_Rock,
	Country,
	Emo,
	Glam,
	Grunge,
	Hip_Hop_Rap ("Hip Hop/Rap"),
	Indie_Rock,
	Metal,
	New_Wave,
	Novelty,
	Nu_Metal,
	Other,
	Pop_Dance_Electronic ("Pop/Dance/Electronic"),
	Pop_Rock ("Pop/Rock"),
	Prog,
	Progressive,
	Punk,
	Reggae_Ska ("Reggae/Ska"),
	RnB_Soul_Funk ("R&B/Soul/Funk"),
	Rock,
	Southern_Rock,
	Urban,
	None;

	private String description;

	private Genre() {
		this.description = null;
	}

	private Genre(String description) {
		this.description = description;
	}

	public String toString() {
		return (description == null ? super.toString().replace('_', ' ') : description);
	}

	static public Genre fromString(String string) {
		return valueOf(string.replace('&','n').replaceAll("[^A-Za-z]","_"));
	}
}
