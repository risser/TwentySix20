package com.twentysix20.lyrics.exception;

public class LyricHandlerVerificationException extends RuntimeException {
	private static final long serialVersionUID = -7539569392036066787L;

	public LyricHandlerVerificationException() {
	}

	public LyricHandlerVerificationException(String message) {
		super(message);
	}

	public LyricHandlerVerificationException(Throwable cause) {
		super(cause);
	}

	public LyricHandlerVerificationException(String message, Throwable cause) {
		super(message, cause);
	}
}
