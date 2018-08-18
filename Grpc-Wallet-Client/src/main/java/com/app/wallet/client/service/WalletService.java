package com.app.wallet.client.service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.app.wallet.grpc.service.WalletGrpc;
import com.app.wallet.grpc.service.WalletOuterClass.BalanceRequest;
import com.app.wallet.grpc.service.WalletOuterClass.BalanceResponse;
import com.app.wallet.grpc.service.WalletOuterClass.CreateRequest;
import com.app.wallet.grpc.service.WalletOuterClass.CreateResponse;
import com.app.wallet.grpc.service.WalletOuterClass.TransactionRequest;
import com.app.wallet.grpc.service.WalletOuterClass.TransactionResponse;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

/**
 * @author Sumit Kumar: WalletService: The Wallet Service class sends Wallet
 *         service GRPC request to the Wallet GRPC Server.
 *
 */
public class WalletService{

	private static final Logger LOGGER = LoggerFactory.getLogger(WalletService.class);

	private final ManagedChannel channel;
	private final WalletGrpc.WalletBlockingStub blockingStub;
	private int rpcCount = 0;
	
	public WalletService(String host, int port) {
		this(ManagedChannelBuilder.forAddress(host, port).usePlaintext().build());
	}

	public WalletService(ManagedChannel channel) {
		this.channel = channel;
		blockingStub = WalletGrpc.newBlockingStub(channel);
	}

	/**
	 * createWallet: This method will call createWallet GRPC Service
	 * 
	 * @param name
	 * @param amount
	 * @param currency
	 * @return
	 */
	public Map<Object, Object> createWallet(String name, double amount, String currency) {
		LOGGER.info("Preparing Request for Creating Wallet for: " + name);
		CreateRequest request = CreateRequest.newBuilder().setName(name).setAmount(amount).setCurrency(currency)
				.build();
		CreateResponse response;
		try {
			LOGGER.info("Creating Wallet Request for: " + name);
			response = blockingStub.createWallet(request);
			rpcCount++;
			LOGGER.info("User" + name + " Wallet Created with Id: " + response.getId());
			Map<Object, Object> responseMap = new HashMap<>();
			responseMap.put("id", response.getId());
			responseMap.put("status", response.getStatus());
			return responseMap;
		} catch (StatusRuntimeException e) {
			LOGGER.info("Create Wallet-RPC failed due to: " + e.getStatus());
		}
		return null;

	}

	/**
	 * deposit: This method will call deposit GRPC Service
	 * 
	 * @param userId
	 * @param amount
	 * @param currency
	 */
	public void deposit(int userId, double amount, String currency) {
		LOGGER.info("Preparing Request for deposit amount in to Wallet for User Id: " + userId);
		TransactionRequest request = TransactionRequest.newBuilder().setUserId(userId).setAmount(amount)
				.setCurrency(currency).build();
		TransactionResponse response;
		try {
			LOGGER.info("Deposit Request to Wallet for User Id: " + userId);
			response = blockingStub.deposit(request);
			rpcCount++;
		} catch (StatusRuntimeException e) {
			LOGGER.info("Deposit-RPC failed due to: " + e.getStatus());
			return;
		}
		LOGGER.info("Deposit Status: " + response.getStatus());
	}

	/**
	 * withdraw: This method will call withdraw GRPC Service
	 * 
	 * @param userId
	 * @param amount
	 * @param currency
	 */
	public void withdraw(int userId, double amount, String currency) {
		LOGGER.info("Preparing Request for amount Withdrawal from Wallet for User Id: " + userId);
		TransactionRequest request = TransactionRequest.newBuilder().setUserId(userId).setAmount(amount)
				.setCurrency(currency).build();
		TransactionResponse response;
		try {
			LOGGER.info("Withdrawl Request to Wallet for User Id: " + userId);
			response = blockingStub.withdraw(request);
			rpcCount++;
		} catch (StatusRuntimeException e) {
			LOGGER.info("WithDraw - RPC failed due to: " + e.getStatus());
			return;
		}
		LOGGER.info("WithDrawl Status: " + response.getStatus());
	}

	/**
	 * balance:This method will call balance GRPC Service
	 * 
	 * @param userId
	 */
	public void balance(int userId) {
		LOGGER.info("Preparing Balance Request from Wallet for User Id: " + userId);
		BalanceRequest request = BalanceRequest.newBuilder().setUserId(userId).build();
		BalanceResponse response;
		try {
			LOGGER.info("Balance Request to Wallet for User Id: " + userId);
			response = blockingStub.balance(request);
			rpcCount++;
		} catch (StatusRuntimeException e) {
			LOGGER.info("RPC failed due to: " + e.getStatus());
			return;
		}
		LOGGER.info("Balance: " + response.getAmount() + " " + response.getCurrency());
	}

	/**
	 * executeRound: execute a round of wallet operations for a specific user
	 * 
	 * @throws InterruptedException
	 */
	public void executeRound(int userId, char round) throws InterruptedException {
		LOGGER.info("Thread:" + Thread.currentThread().getName() + " Round " + round + " getting Executed for User Id: "
				+ userId);
		switch (round) {
		case 'A':
			// 1.Deposit 100 USD
			deposit(userId, 100, "USD");
			// 2.Withdraw 200 USD
			withdraw(userId, 200, "USD");
			// 3.Deposit 100 EUR
			deposit(userId, 100, "EUR");
			// 4.Get Balance
			balance(userId);
			// 5.Withdraw 100 USD
			deposit(userId, 100, "USD");
			// 6.Get Balance
			balance(userId);
			// 7.Withdraw 100 USD
			deposit(userId, 100, "USD");
			break;

		case 'B':
			// 1.Withdraw 100 GBP
			withdraw(userId, 100, "GBP");
			// 2.Deposit 300 GPB
			deposit(userId, 300, "GBP");
			// 3.Withdraw 100 GBP
			withdraw(userId, 100, "GBP");
			// 4.Withdraw 100 GBP
			withdraw(userId, 100, "GBP");
			// 5.Withdraw 100 GBP
			withdraw(userId, 100, "GBP");
			break;

		case 'C':
			// 1.Get Balance
			balance(userId);
			// 2.Deposit 100 USD
			deposit(userId, 100, "USD");
			// 3.Deposit 100 USD
			deposit(userId, 100, "USD");
			// 4.Withdraw 100 USD
			withdraw(userId, 100, "USD");
			// 5.Depsoit 100 USD
			deposit(userId, 100, "USD");
			// 6.Get Balance
			balance(userId);
			// 7.Withdraw 200 USD
			withdraw(userId, 200, "USD");
			// 8.Get Balance
			balance(userId);
			break;
		default:
			break;
		}
		LOGGER.info(
				"Thread:" + Thread.currentThread().getName() + " Round " + round + " completed for User Id: " + userId);
	}

	/**
	 * shutdown: This method will shut down the channel
	 * 
	 * @throws InterruptedException
	 */
	public void shutdown() throws InterruptedException {
		channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
	}
	
	/**
	 * shutdown: This method will shut down the channel
	 * 
	 * @throws InterruptedException
	 */
	public int getRPCCount(){
		return rpcCount;
	}

}
