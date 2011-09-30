package com.twentysix20.media.webreaders.movie;

public enum RatingSystem {
	MPAA,
	US_TV;

	@Override
	public String toString() {
		return this.name().toLowerCase().replace('_', '-');
	}

	static public RatingSystem fromString(String s) {
		return RatingSystem.valueOf(s.replace('-', '_').toUpperCase());
	}
}
