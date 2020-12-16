package com.virtusa.buyit.exception;

public class BuyitException extends Exception {
	private String message;

	public BuyitException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String toString() {
		return "BuyitException [message=" + message + "]";
	}
}
