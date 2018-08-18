package com.app.wallet.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User: User Thread will emulate required number of request per user on the
 * basis of input provided from console
 * 
 * @author Sumit Kumar: User:
 */
public class User implements Runnable {

	private static final Logger LOGGER = LoggerFactory.getLogger(User.class);

	private int numberOfConcurrentRequests;
	private int numberOfRoundsPerRequests;
	private int userId;

	public User(int userId, int numberOfConcurrentRequests, int numberOfRoundsPerRequests) {
		this.numberOfConcurrentRequests = numberOfConcurrentRequests;
		this.numberOfRoundsPerRequests = numberOfRoundsPerRequests;
		this.userId = userId;
		LOGGER.info("User Object has been created");
	}

	/**
	 * executeRequest: This method will create number of concurrent request thread
	 * per user as per the numberOfConcurrent request input provided from console
	 * 
	 * @param numberOfConcurrentRequests
	 */
	public void executeRequest(int numberOfConcurrentRequests) {
		for (int i = 0; i < numberOfConcurrentRequests; i++) {
			Thread thread = new Thread(new UserRequest(userId, numberOfRoundsPerRequests));
			thread.start();
			LOGGER.info("New User Request Thread started" + thread.getName());
		}
	}

	@Override
	public void run() {
		LOGGER.info("New User Thread started" + Thread.currentThread().getName());
		executeRequest(numberOfConcurrentRequests);
	}

}
