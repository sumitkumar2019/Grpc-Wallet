package com.app.wallet.server.integration.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.app.wallet.config.ApplicationConfiguration;
import com.app.wallet.grpc.service.WalletGrpc;
import com.app.wallet.grpc.service.WalletOuterClass.BalanceRequest;
import com.app.wallet.grpc.service.WalletOuterClass.BalanceResponse;
import com.app.wallet.grpc.service.WalletOuterClass.CreateRequest;
import com.app.wallet.grpc.service.WalletOuterClass.CreateResponse;
import com.app.wallet.grpc.service.WalletOuterClass.TransactionRequest;
import com.app.wallet.grpc.service.WalletOuterClass.TransactionResponse;
import com.app.wallet.server.literals.WalletLiterals;
import com.app.wallet.server.service.WalletServerService;

import io.grpc.inprocess.InProcessChannelBuilder;
import io.grpc.inprocess.InProcessServerBuilder;
import io.grpc.testing.GrpcCleanupRule;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfiguration.class })
@TestPropertySource("classpath:application.properties")
public class WalletServerDBIntegrationTest implements WalletLiterals {

	@Autowired
	WalletServerService WalletServerService;

	private WalletGrpc.WalletBlockingStub blockingStub;

	/**
	 * Best practice to always clean up gRPC resources such as client channels,
	 * servers, and previously attached Contexts whenever they are no longer needed
	 */
	@Rule
	public final GrpcCleanupRule grpcCleanup = new GrpcCleanupRule();

	/**
	 * Set Up Test Resources: Server started and channel created for communication
	 * with blocking stub methods
	 **/
	@Before
	public void setUp() throws Exception {
		String serverName = InProcessServerBuilder.generateName();
		grpcCleanup.register(InProcessServerBuilder.forName(serverName).directExecutor().addService(WalletServerService)
				.build().start());
		blockingStub = WalletGrpc.newBlockingStub(
				grpcCleanup.register(InProcessChannelBuilder.forName(serverName).directExecutor().build()));

	}

	/**
	 * Integration test with below provided test cases:
	 * 
	 * 0. Create a Wallet with userId 1
	 * 1. Make a withdrawal of USD 200 for user with id 1. Must return "insufficient_funds".
	 * 2. Make a deposit of USD 100 to user with id 1.
	 * 3. Check that all balances are correct
	 * 4. Make a withdrawal of USD 200 for user with id 1. Must return "insufficient_funds".
	 * 5. Make a deposit of EUR 100 to user with id 1.
	 * 6. Check that all balances are correct
	 * 7. Make a withdrawal of USD 200 for user with id 1. Must return "insufficient_funds".
	 * 8. Make a deposit of USD 100 to user with id 1.
	 * 9. Check that all balances are correct
	 * 10. Make a withdrawal of USD 200 for user with id 1. Must return "ok".
	 * 11. Check that all balances are correct.
	 * 12. Make a withdrawal of USD 200 for user with id 1. Must return "insufficient_funds".
	 **/
	@Test
	public void grpcServerDatabaseintegrationTest() {

		// 0. Create a Wallet with userId 1
		CreateResponse craeteResponse = blockingStub
				.createWallet(CreateRequest.newBuilder().setName("Test").setCurrency(USD).build());
		assertEquals(1, craeteResponse.getId());
		assertEquals(SUCCESS_RESPONSE, craeteResponse.getStatus());

		// 1. Make a withdrawal of USD 200 for user with id 1. Must return "insufficient_funds".
		TransactionResponse withDrawalresponse = blockingStub.withdraw(TransactionRequest.newBuilder()
				.setUserId(craeteResponse.getId()).setAmount(FIXED_WITHDRAWL_AMOUNT).setCurrency(USD).build());
		assertEquals(INSUFFICIENTFUND_ERROR, withDrawalresponse.getStatus());

		// 2. Make a deposit of USD 100 to user with id 1.
		TransactionResponse depositResponse = blockingStub.deposit(TransactionRequest.newBuilder()
				.setUserId(craeteResponse.getId()).setAmount(FIXED_DEPOSIT_AMOUNT).setCurrency(USD).build());
		assertEquals(SUCCESS_RESPONSE, depositResponse.getStatus());

		// 3. Check that all balances are correct
		BalanceResponse balanceResponse = blockingStub
				.balance(BalanceRequest.newBuilder().setUserId(craeteResponse.getId()).build());
		assertEquals(FIXED_DEPOSIT_AMOUNT, balanceResponse.getAmount(), DELTA);
		assertEquals(USD, balanceResponse.getCurrency());
		assertEquals(SUCCESS_RESPONSE, balanceResponse.getStatus());

		// 4. Make a withdrawal of USD 200 for user with id 1. Must return "insufficient_funds".
		TransactionResponse withDrawalresponse2 = blockingStub.withdraw(TransactionRequest.newBuilder()
				.setUserId(craeteResponse.getId()).setAmount(FIXED_WITHDRAWL_AMOUNT).setCurrency(USD).build());
		assertEquals(INSUFFICIENTFUND_ERROR, withDrawalresponse2.getStatus());

		// 5. Make a deposit of EUR 100 to user with id 1.
		TransactionResponse depositResponse2 = blockingStub.deposit(TransactionRequest.newBuilder()
				.setUserId(craeteResponse.getId()).setAmount(FIXED_DEPOSIT_AMOUNT).setCurrency(EUR).build());
		assertEquals(SUCCESS_RESPONSE, depositResponse2.getStatus());

		// 6. Check that all balances are correct
		BalanceResponse balanceResponse2 = blockingStub
				.balance(BalanceRequest.newBuilder().setUserId(craeteResponse.getId()).build());
		assertEquals(_100_EUR_TO_USD + FIXED_DEPOSIT_AMOUNT, balanceResponse2.getAmount(), DELTA);
		assertEquals(USD, balanceResponse2.getCurrency());
		assertEquals(SUCCESS_RESPONSE, balanceResponse2.getStatus());

		// 7. Make a withdrawal of USD 200 for user with id 1. Must return "insufficient_funds".
		TransactionResponse withDrawalresponse3 = blockingStub.withdraw(TransactionRequest.newBuilder()
				.setUserId(craeteResponse.getId()).setAmount(FIXED_WITHDRAWL_AMOUNT).setCurrency(USD).build());
		assertEquals(INSUFFICIENTFUND_ERROR, withDrawalresponse3.getStatus());

		// 8. Make a deposit of USD 100 to user with id 1.
		TransactionResponse depositResponse3 = blockingStub.deposit(TransactionRequest.newBuilder()
				.setUserId(craeteResponse.getId()).setAmount(FIXED_DEPOSIT_AMOUNT).setCurrency(USD).build());
		assertEquals(SUCCESS_RESPONSE, depositResponse3.getStatus());

		// 9. Check that all balances are correct
		BalanceResponse balanceResponse3 = blockingStub
				.balance(BalanceRequest.newBuilder().setUserId(craeteResponse.getId()).build());
		assertEquals(_100_EUR_TO_USD + FIXED_DEPOSIT_AMOUNT + FIXED_DEPOSIT_AMOUNT, balanceResponse3.getAmount(),
				DELTA);
		assertEquals(USD, balanceResponse3.getCurrency());
		assertEquals(SUCCESS_RESPONSE, balanceResponse3.getStatus());

		// 10. Make a withdrawal of USD 200 for user with id 1. Must return "ok".
		TransactionResponse withDrawalresponse4 = blockingStub.withdraw(TransactionRequest.newBuilder()
				.setUserId(craeteResponse.getId()).setAmount(FIXED_WITHDRAWL_AMOUNT).setCurrency(USD).build());
		assertEquals(SUCCESS_RESPONSE, withDrawalresponse4.getStatus());

		// 11. Check that all balances are correct
		BalanceResponse balanceResponse4 = blockingStub
				.balance(BalanceRequest.newBuilder().setUserId(craeteResponse.getId()).build());
		assertEquals(_100_EUR_TO_USD, balanceResponse4.getAmount(), DELTA);
		assertEquals(USD, balanceResponse4.getCurrency());
		assertEquals(SUCCESS_RESPONSE, balanceResponse4.getStatus());

		// 12. Make a withdrawal of USD 200 for user with id 1. Must return "insufficient_funds".
		TransactionResponse withDrawalresponse5 = blockingStub.withdraw(TransactionRequest.newBuilder()
				.setUserId(craeteResponse.getId()).setAmount(FIXED_WITHDRAWL_AMOUNT).setCurrency(USD).build());
		assertEquals(INSUFFICIENTFUND_ERROR, withDrawalresponse5.getStatus());

	}
}