package com.app.wallet.server.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.wallet.grpc.service.WalletGrpc;
import com.app.wallet.grpc.service.WalletOuterClass.BalanceRequest;
import com.app.wallet.grpc.service.WalletOuterClass.BalanceResponse;
import com.app.wallet.grpc.service.WalletOuterClass.CreateRequest;
import com.app.wallet.grpc.service.WalletOuterClass.CreateResponse;
import com.app.wallet.grpc.service.WalletOuterClass.TransactionRequest;
import com.app.wallet.grpc.service.WalletOuterClass.TransactionResponse;
import com.app.wallet.server.dao.WalletRepository;
import com.app.wallet.server.exception.InsufficientFundException;
import com.app.wallet.server.exception.UnknownCurrencyException;
import com.app.wallet.server.exception.UserWalletNotFoundException;
import com.app.wallet.server.helper.CurrencyExchangeHelper;
import com.app.wallet.server.literals.WalletLiterals;
import com.app.wallet.server.model.Wallet;

import io.grpc.stub.StreamObserver;

/**
 * @author Sumit Kumar: WalletServerService: This Class contains all the service
 *         methods overridden from its generated GRPC class
 */
@Service
public class WalletServerService extends WalletGrpc.WalletImplBase implements WalletLiterals {

	private static final Logger LOGGER = LoggerFactory.getLogger(WalletServerService.class);

	@Autowired
	private WalletRepository walletRepository;

	/**
	 * createWallet: This method contain the logic for wallet creation for an User
	 * 
	 * @param request
	 * @param responseObserver
	 */
	@Override
	public void createWallet(CreateRequest request, StreamObserver<CreateResponse> responseObserver) {
		LOGGER.info("Creating wallet for User:" + request.getName());
		CreateResponse createResponse = null;
		String currency = request.getCurrency();
		try {
			if (!currency.equals(EUR) && !currency.equals(GBP) && !currency.equals(USD)) {
				LOGGER.info("UnknownCurrencyException: Transcation cannot be completed : Currency other than EUR, GBP, USD");
				throw new UnknownCurrencyException(UNKNOWNCURRENCY_ERROR);
			}
			Wallet wallet = new Wallet();
			wallet.setBalance(request.getAmount());
			wallet.setCurrency(request.getCurrency());
			wallet.setName(request.getName());

			LOGGER.info("New wallet User with properites: name: " + request.getName() + " Currency: "
					+ request.getCurrency() + " Amount: " + request.getAmount());

			int walletId = walletRepository.createWallet(wallet);

			createResponse = CreateResponse.newBuilder().setId(walletId).setStatus(SUCCESS_RESPONSE)
					.build();

			LOGGER.info("Wallet Created Succesfully with User Id: " + walletId);
		} catch (UnknownCurrencyException unknownCurrencyException) {
			createResponse = CreateResponse.newBuilder().setStatus(UNKNOWNCURRENCY_ERROR).build();

		} finally {
			responseObserver.onNext(createResponse);
			responseObserver.onCompleted();

		}
	}

	/**
	 * deposit:This method contain the logic for deposit a particular amount in to
	 * an User Wallet
	 * 
	 * @param request
	 * @param responseObserver
	 */
	@Override
	public synchronized void deposit(TransactionRequest request, StreamObserver<TransactionResponse> responseObserver) {

		Integer userId = request.getUserId();
		double depositAmount = request.getAmount();
		String currency = request.getCurrency();

		LOGGER.info("Starting Deposit Service for Amount: " + depositAmount + " Currency: " + currency
				+ " for User Id: " + userId);

		TransactionResponse transactionResponse = null;
		try {
			if (userId.equals(null)) {
				LOGGER.info("UserWalletNotFoundException: Transcation cannot be completed : User Id not Found");
				throw new UserWalletNotFoundException(USERNOTFOUND_ERROR);
			}
			Wallet wallet = walletRepository.getWalletByUserId(userId);
			if (wallet.equals(null)) {
				LOGGER.info(
						"UserWalletNotFoundException: Transcation cannot be completed : Wallet not Found with userId:"
								+ userId);
				throw new UserWalletNotFoundException(USERNOTFOUND_ERROR);
			}

			String walletCurrency = wallet.getCurrency();
			double walletBalance = wallet.getBalance();

			if (!currency.equals(EUR) && !currency.equals(GBP) && !currency.equals(USD)) {
				LOGGER.info(
						"UnknownCurrencyException: Transcation cannot be completed : Currency other than EUR, GBP, USD");
				throw new UnknownCurrencyException(UNKNOWNCURRENCY_ERROR);
			}

			if (!currency.equals(walletCurrency)) {
				depositAmount = CurrencyExchangeHelper.getExchangedCurrencyAmount(depositAmount, walletCurrency,
						currency);
			}

			double totalAmount = walletBalance + depositAmount;
			LOGGER.info("Total Amount to be deposited: " + totalAmount);

			walletRepository.updateWalletBalanceByUserId(userId, totalAmount);

			transactionResponse = TransactionResponse.newBuilder().setStatus(SUCCESS_RESPONSE).build();

		} catch (InsufficientFundException fundException) {
			transactionResponse = TransactionResponse.newBuilder().setStatus(INSUFFICIENTFUND_ERROR).build();
		} catch (UnknownCurrencyException currencyException) {
			transactionResponse = TransactionResponse.newBuilder().setStatus(UNKNOWNCURRENCY_ERROR).build();
		} catch (UserWalletNotFoundException walletNotFoundException) {
			transactionResponse = TransactionResponse.newBuilder().setStatus(USERNOTFOUND_ERROR).build();
		} finally {
			responseObserver.onNext(transactionResponse);
			responseObserver.onCompleted();
			LOGGER.info("Wallet Deposit Response for User Id:" + userId + " Sent Successfully");
		}

	}

	/**
	 * withdraw:This method contain the logic for withdrawing a particular amount in
	 * to an User Wallet
	 * 
	 * @param request
	 * @param responseObserver
	 */
	@Override
	public synchronized void withdraw(TransactionRequest request,
			StreamObserver<TransactionResponse> responseObserver) {
		Integer userId = request.getUserId();
		double withdrawalAmount = request.getAmount();
		String currency = request.getCurrency();
		TransactionResponse transactionResponse = null;
		LOGGER.info("Starting Withdrawl Service for Amount: " + withdrawalAmount + " Currency: " + currency
				+ " for User Id: " + userId);
		try {
			if (userId.equals(null)) {
				LOGGER.info("UserWalletNotFoundException: Transcation cannot be completed : User Id not Found");
				throw new UserWalletNotFoundException(USERNOTFOUND_ERROR);
			}
			Wallet wallet = walletRepository.getWalletByUserId(userId);
			if (wallet.equals(null)) {
				LOGGER.info("UserWalletNotFoundException: Transcation cannot be completed : Wallet not Found");
				throw new UserWalletNotFoundException(USERNOTFOUND_ERROR);
			}
			String walletCurrency = wallet.getCurrency();
			double walletBalance = wallet.getBalance();

			if (!currency.equals(EUR) && !currency.equals(GBP) && !currency.equals(USD)) {
				LOGGER.info(
						"UnknownCurrencyException: Transcation cannot be completed : Currency other than EUR, GBP, USD");
				throw new UnknownCurrencyException(UNKNOWNCURRENCY_ERROR);
			}

			if (!currency.equals(walletCurrency)) {
				withdrawalAmount = CurrencyExchangeHelper.getExchangedCurrencyAmount(withdrawalAmount, walletCurrency,
						currency);
			}

			if ((walletBalance < withdrawalAmount) || (walletBalance < 0)) {
				LOGGER.info("InsufficientFundException: Transcation cannot be completed : " + INSUFFICIENTFUND_ERROR);
				throw new InsufficientFundException(INSUFFICIENTFUND_ERROR);
			}
			double totalAmount = walletBalance - withdrawalAmount;
			LOGGER.info("Total Amount to be withdrawn: " + totalAmount);

			walletRepository.updateWalletBalanceByUserId(userId, totalAmount);

			transactionResponse = TransactionResponse.newBuilder().setStatus(SUCCESS_RESPONSE).build();

		} catch (InsufficientFundException insufficientFundException) {
			transactionResponse = TransactionResponse.newBuilder().setStatus(INSUFFICIENTFUND_ERROR).build();
		} catch (UnknownCurrencyException unknownCurrencyException) {
			transactionResponse = TransactionResponse.newBuilder().setStatus(UNKNOWNCURRENCY_ERROR).build();
		} catch (UserWalletNotFoundException walletNotFoundException) {
			transactionResponse = TransactionResponse.newBuilder().setStatus(USERNOTFOUND_ERROR).build();
		} finally {
			responseObserver.onNext(transactionResponse);
			responseObserver.onCompleted();
			LOGGER.info("Wallet Withdrawl Response for User Id:" + userId + " Sent Successfully");
		}
	}

	/**
	 * balance:This method contain the logic for getting balance of a particular
	 * User Wallet
	 * 
	 * @param request
	 * @param responseObserver
	 */
	@Override
	public synchronized void balance(BalanceRequest request, StreamObserver<BalanceResponse> responseObserver) {
		Integer userId = request.getUserId();
		BalanceResponse balanceResponse = null;
		LOGGER.info("Balance Service started for User Id: " + userId);
		try {
			if (userId.equals(null)) {
				LOGGER.info("UserWalletNotFoundException: Transcation cannot be completed : User Id not Found");
				throw new UserWalletNotFoundException(USERNOTFOUND_ERROR);
			}

			Wallet wallet = walletRepository.getWalletByUserId(request.getUserId());

			if (wallet.equals(null)) {
				LOGGER.info("UserWalletNotFoundException: Transcation cannot be completed : Wallet not Found");
				throw new UserWalletNotFoundException(USERNOTFOUND_ERROR);
			}

			balanceResponse = BalanceResponse.newBuilder().setStatus(SUCCESS_RESPONSE).setAmount(wallet.getBalance())
					.setCurrency(wallet.getCurrency()).build();

		} catch (UserWalletNotFoundException walletNotFoundException) {
			balanceResponse = BalanceResponse.newBuilder().setStatus(USERNOTFOUND_ERROR).build();
		} finally {
			responseObserver.onNext(balanceResponse);
			responseObserver.onCompleted();
			LOGGER.info("Balance Response for User Id:" + userId + " Sent Successfully");
		}

	}
}
