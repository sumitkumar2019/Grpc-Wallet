package com.app.wallet.client;

import java.util.Map;
import java.util.Scanner;

import com.app.wallet.client.service.factory.ServiceFactory;

/**
 * @author Sumit Kumar: WalletClient: GRPC Wallet Client will have the below
 *         Feature: The wallet client should have the following CLI parameters:
 *         1. users (number of concurrent users emulated) 2.
 *         concurrent_threads_per_user (number of concurrent requests a user
 *         will make) 3. rounds_per_thread (number of rounds each thread is
 *         executing)
 */
public class WalletClient {

	// ---------------Main Entry Point for Client------------------

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {

		int[] userIds;
		
		// Take Input from console for Total Concurrent Users
		System.out.print("Enter Number Of Concurrent Users[minimum value 1]: ");
		Scanner numberOfConcurrentUserInput = new Scanner(System.in);
		int numberOfConcurrentUser = numberOfConcurrentUserInput.nextInt();
		
		if(numberOfConcurrentUser <= 0) {
			System.out.println("Please Enter Number more than 0");
			System.exit(0);
		}
		
		userIds = new int[numberOfConcurrentUser];

		Scanner userInput = new Scanner(System.in);
		// If Users already not register then register new users

		for (int i = 0; i < numberOfConcurrentUser; i++) {
			System.out.print("Enter New User "+ (i+1) + " Name [String value]:");
			String name = userInput.nextLine();
			if(name.isEmpty() || name == null) {
				System.out.println("Please Enter Name");
				System.exit(0);
			}
			System.out.print("Enter his Currency [USD/EUR/GBP] only" + (i+1) + ": ");
			String currency = userInput.nextLine();
			Map<Object, Object> responseMap = ServiceFactory.getWalletService().createWallet(name, 0.0, currency);
			if(responseMap.get("status").equals("Unknown Currency")) {
				System.out.println("Please Enter Correct Currency");
				System.exit(0);
			}
			System.out.println("Wallet Created succesfully for:" + name);
			userIds[i] = (Integer) responseMap.get("id");
		}

		System.out.println("users accepted...");

		// Once User Ids are available then Input per user number of requests
		System.out.print("Number Of Concurrent Requests Per User[Minimum value 1]: ");
		Scanner numberOfConcurrentRequestInput = new Scanner(System.in);
		int numberOfConcurrentRequest = numberOfConcurrentRequestInput.nextInt();
		if(numberOfConcurrentRequest <= 0) {
			System.out.println("Please Enter Correct Input");
			System.exit(0);
		}
		// Enter Rounds each user wants to execute
		System.out.print("Number Of Rounds Per Request[Minimum value 1]:");
		Scanner numberOfRoundsPerRequestsInput = new Scanner(System.in);
		int numberOfRoundsPerRequests = numberOfRoundsPerRequestsInput.nextInt();
		if(numberOfRoundsPerRequests <= 0) {
			System.out.println("Please Enter Correct Input");
			System.exit(0);
		}
		// Start all users threads
		for (int i = 0; i < numberOfConcurrentUser; i++) {
			Thread thread = new Thread(
					new User(userIds[i], numberOfConcurrentRequest, numberOfRoundsPerRequests));
			thread.start();

		}

	}

}
