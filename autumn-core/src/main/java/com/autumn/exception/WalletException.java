package com.autumn.exception;

public class WalletException extends RuntimeException {

	private static final long serialVersionUID = -4282042037952780697L;

	public WalletException(String message, Throwable cause) {
		super(message, cause);
	}

	public WalletException(String message) {
		super(message);
	}

	public WalletException(Throwable cause) {
		super(cause);
	}

}
