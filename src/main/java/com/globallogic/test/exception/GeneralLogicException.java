package com.globallogic.test.exception;

public class GeneralLogicException extends RuntimeException {

	public GeneralLogicException() {
	}

	public GeneralLogicException(String message) {
		super(message);
	}

	public GeneralLogicException(Throwable cause) {
		super(cause);
	}

	public GeneralLogicException(String message, Throwable cause) {
		super(message, cause);
	}

	public GeneralLogicException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
