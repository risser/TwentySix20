package com.twentysix20.lyrics.exception;

public class LyricParsingException extends RuntimeException {
	private static final long serialVersionUID = -3960982611546295861L;

	public LyricParsingException() {
		super();
	}

	public LyricParsingException(String message, Throwable cause) {
		super(message, cause);
	}

	public LyricParsingException(String message) {
		super(message);
	}

	public LyricParsingException(Throwable cause) {
		super(cause);
	}
}
