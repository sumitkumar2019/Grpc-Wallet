package com.app.wallet.server.exception;

public class UserWalletNotFoundException extends RuntimeException{

	public UserWalletNotFoundException(String message) {
		super(message);
	}
}
