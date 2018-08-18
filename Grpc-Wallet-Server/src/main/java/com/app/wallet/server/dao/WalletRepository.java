package com.app.wallet.server.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.app.wallet.server.model.Wallet;

/**
 * @author Sumit Kumar: WalletRepository: This Repository Interface and its
 *         implementation at Persistence Layer contains Data access methods
 *
 */
public interface WalletRepository {

	Logger LOGGER = LoggerFactory.getLogger(WalletRepository.class);
	
	/**
	 * createWallet: This method will help to create a users wallet
	 * 
	 * @param wallet
	 * @return
	 */
	public int createWallet(Wallet wallet);

	/**
	 * updateWalletBalanceByUserId: This method will help to update a wallet balance
	 * as per user id
	 * 
	 * @param userId
	 * @param amount
	 */
	public void updateWalletBalanceByUserId(int userId, double amount);

	/**
	 * getWalletByUserId: This method retrieve a user wallet record as per user id
	 * 
	 * @param userId
	 * @param amount
	 */
	public Wallet getWalletByUserId(int userId);

}
