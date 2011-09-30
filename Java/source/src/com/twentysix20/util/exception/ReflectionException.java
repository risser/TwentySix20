package com.twentysix20.util.exception;

public class ReflectionException extends RuntimeException {
	private static final long serialVersionUID = 5034889081163383784L;

	public ReflectionException() {
        super();
    }

    public ReflectionException(String message) {
        super(message);
    }

    public ReflectionException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public ReflectionException(Throwable throwable) {
        super(throwable);
    }

}
