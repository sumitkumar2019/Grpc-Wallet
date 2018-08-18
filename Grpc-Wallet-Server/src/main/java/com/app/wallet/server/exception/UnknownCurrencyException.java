package com.app.wallet.server.exception;

public class UnknownCurrencyException extends RuntimeException{

	public UnknownCurrencyException(String message){
		super(message);
	}
}
