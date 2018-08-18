package com.app.wallet.client.service.factory;

import com.app.wallet.client.service.WalletService;

public class ServiceFactory {
	
	private static WalletService WALLET_SERVICE;
	
	private ServiceFactory() {}
	static{
		if(WALLET_SERVICE == null) {
			WALLET_SERVICE = new WalletService("localhost", 50051);
		}
	}
	
	public static WalletService getWalletService() {
		return WALLET_SERVICE;
	}

}
