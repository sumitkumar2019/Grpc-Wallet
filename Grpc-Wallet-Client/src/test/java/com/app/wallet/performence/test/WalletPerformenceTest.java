package com.app.wallet.performence.test;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.app.wallet.client.service.factory.ServiceFactory;

public class WalletPerformenceTest {
	private static final Logger logger = LoggerFactory.getLogger(WalletPerformenceTest.class.getName());
	private static final long DURATION_SECONDS = 60;

	public static void main(String[] args) throws Exception {
		ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
		 AtomicBoolean done = new AtomicBoolean();
		try {
			ServiceFactory.getWalletService().createWallet("test wallet", 0.0, "USD");
			scheduler.schedule(() -> done.set(true), DURATION_SECONDS, TimeUnit.SECONDS);
			doClientWork(done);
			countRPC();
		} finally {
			scheduler.shutdownNow();
		}
	}

	 private static void doClientWork(AtomicBoolean done) throws InterruptedException {
		 char[] rounds = { 'A', 'B', 'C' };
		 while (!done.get()) {
	    	Random randomizer = new Random();
			char round = rounds[(randomizer.nextInt(rounds.length))];
			ServiceFactory.getWalletService().executeRound(1, round);	    	
	    }
	  }
	
	private static void countRPC(){
		int rpcCount = ServiceFactory.getWalletService().getRPCCount();
		double qps = (double) rpcCount / DURATION_SECONDS;
		logger.info("Total RPC Count" + rpcCount);
		logger.info("Total RPCs/s" + qps);
	}
}
