package com.cgg.data.exception;

public class NoRecordFoundException extends Exception {

	public NoRecordFoundException() {
	}

	public NoRecordFoundException(String message) {
		super(message);
	}

	public NoRecordFoundException(Throwable cause) {
		super(cause);
	}

	public NoRecordFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoRecordFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
