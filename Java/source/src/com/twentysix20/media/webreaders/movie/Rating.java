package com.twentysix20.media.webreaders.movie;

import static com.twentysix20.media.webreaders.movie.RatingSystem.MPAA;
import static com.twentysix20.media.webreaders.movie.RatingSystem.US_TV;

import com.twentysix20.util.StringUtil;

public enum Rating {
	TV_Y(US_TV,100),
	TV_Y7(US_TV,200),
	TV_G(US_TV,300),
	TV_PG(US_TV,400),
	TV_14(US_TV,500),
	TV_MA(US_TV,600),
	G(MPAA,100),
	PG(MPAA,200),
	PG_13(MPAA,300),
	R(MPAA,400),
	NC_17(MPAA,500),
	UNRATED(MPAA,-1),
	NR(MPAA,0);
	
	//{"mpaa|Unrated|???|", "Unrated"},

	private int itunesValue;
	private RatingSystem ratingSystem;

	private Rating(RatingSystem ratingSystem, int itunesValue) {
		this.ratingSystem = ratingSystem;
		this.itunesValue = itunesValue;
	}

	@Override
	public String toString() {
		return this.name().replace('_', '-');
	}

	static public Rating fromString(String s) {
		return Rating.valueOf(s.replace('-', '_'));
	}

	public String toITunesBoxString() {
		if (this == UNRATED) {
			return "mpaa|Unrated|???|"; 
		} else {
			return ratingSystem.toString() + "|" +
				toString() + "|" + 
				StringUtil.padLeft(Integer.toString(itunesValue), 3, '0') + "|";
		}
		
	}
}
