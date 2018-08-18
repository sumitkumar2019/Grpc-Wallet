package com.app.wallet.server.exception;

public class InsufficientFundException extends RuntimeException{
	public InsufficientFundException(String message) {
		super(message);
	}
}
