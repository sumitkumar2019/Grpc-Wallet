package com.app.wallet.client;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.app.wallet.client.service.factory.ServiceFactory;

/**
 * @author Sumit Kumar: UserRequest: The User Request thread will execute
 *         different rounds on the basis of random selection of the rounds. Each
 *         Rounds perform a specific group of wallet operations like deposit,
 *         withdraw and balance etc. Each user request thread will execute a
 *         specific number of rounds on the basis of number of rounds provided
 *         for each user request.
 *
 */
public class UserRequest implements Runnable {

	private static final Logger LOGGER = LoggerFactory.getLogger(User.class);

	private int numberOfRoundsPerRequests;
	private char[] rounds = { 'A', 'B', 'C' };
	private int userId;

	public UserRequest(int userId, int numberOfRoundsPerRequests) {
		this.numberOfRoundsPerRequests = numberOfRoundsPerRequests;
		this.userId = userId;
		LOGGER.info("User Request Object Created");
	}

	/**
	 * executeRequest: This method will be executed different rounds of wallet
	 * operations based on the input for number of rounds provided by a User.
	 * 
	 * @param numberOfRoundsPerRequests
	 * @throws InterruptedException
	 */
	public void executeRequest(int numberOfRoundsPerRequests) throws InterruptedException {
		LOGGER.info("New User Request Thread started executing request:" + Thread.currentThread().getName());
		// Randomly Pick and execute Rounds
		for (int i = 0; i < numberOfRoundsPerRequests; i++) {
			Random randomizer = new Random();
			char round = rounds[(randomizer.nextInt(rounds.length))];
			ServiceFactory.getWalletService().executeRound(userId, round);
		}
		LOGGER.info("User Request Thread completed request:" + Thread.currentThread().getName());
	}

	@Override
	public void run() {
		try {
			executeRequest(numberOfRoundsPerRequests);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
