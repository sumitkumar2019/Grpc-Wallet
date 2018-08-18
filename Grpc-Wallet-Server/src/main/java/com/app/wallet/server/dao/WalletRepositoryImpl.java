package com.app.wallet.server.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.wallet.server.model.Wallet;

/**
 * @author Sumit Kumar: WalletRepository: This Repository Implementation Class
 *         at Persistence Layer contains Data access methods
 *
 */
@Repository
@Transactional
public class WalletRepositoryImpl implements WalletRepository {

	@Autowired
	protected SessionFactory sessionFactory;

	/**
	 * createWallet: This method will help to create a users wallet
	 * 
	 * @param wallet
	 * @return userId
	 */
	@Override
	public int createWallet(Wallet wallet) {
		LOGGER.info("Creating Wallet for User: " + wallet);
		getCurrentSession().save(wallet);
		LOGGER.info("User Wallet Created Succesfully: " + wallet);
		return wallet.getUserId();
	}

	/**
	 * updateWalletBalanceByUserId: This method will help to update a wallet balance
	 * as per user id
	 * 
	 * @param userId
	 * @param amount
	 */
	@Override
	public void updateWalletBalanceByUserId(int userId, double balance) {
		LOGGER.info("Loading Wallet for User with Id: " + userId);
		Wallet wallet = (Wallet) getCurrentSession().get(Wallet.class, userId);
		wallet.setBalance(balance);
		LOGGER.info("Updating Wallet: " + wallet);
		getCurrentSession().saveOrUpdate(wallet);
		LOGGER.info("Wallet Updated Successfully: " + wallet);
	}

	/**
	 * getWalletByUserId: This method retrieve a user wallet record as per user id
	 * 
	 * @param userId
	 * @param amount
	 * @return wallet
	 */
	@Override
	public Wallet getWalletByUserId(int userId) {
		LOGGER.info("Getting Wallet for User with Id: " + userId);
		Wallet wallet = (Wallet) getCurrentSession().get(Wallet.class, userId);
		LOGGER.info("Wallet Returned Successfully: " + wallet);
		return wallet;
	}

	/**
	 * getCurrentSession: This method get current Hibernate session with DB
	 * 
	 * @return session
	 */
	protected Session getCurrentSession() {
		LOGGER.info("Getting DB Current Session");
		return sessionFactory.getCurrentSession();
	}

}
