package com.twentysix20.media.tvboxer;

public enum ITunesMediaType {
	Movie(9),
	TV(10);

	private int value;

	private ITunesMediaType(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public String getValueAsString() {
		return Integer.toString(value);
	}

	static public ITunesMediaType fromString(String value) {
		if (value == null) return null;

		switch (Integer.parseInt(value)) {
		case 9:
			return Movie;
		case 10:
			return TV;
		}
		throw new IllegalArgumentException("No MediaType found for value '"+value+"'.");
	}
}
