package com.app.wallet.server;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.app.wallet.server.service.WalletServerService;

import io.grpc.Server;
import io.grpc.ServerBuilder;

/**
 * @author Sumit Kumar : WalletServer: The Wallet Server Class is the Entry Point
 *         of the application its main responsibility is to configure the GRPC
 *         server and application. It has main method which will start the
 *         server and run the application.
 */
public class WalletServer {

	private Server server;

	private static final Logger LOGGER = LoggerFactory.getLogger(WalletServer.class);

	/**
	 * start: This method will configure GRPC server with its port and GRPC services
	 * using the Server builder classes
	 * 
	 * @throws IOException
	 */
	public void start() throws IOException {

		int port = 50051;
		
		LOGGER.info("Started Server Configuration");
		
		server = ServerBuilder.forPort(port).addService(getGRPCService()).build().start();
		
		LOGGER.info("GRPC Server started at Port: " + port);

		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				LOGGER.error("Stopping GRPC server..........");
				WalletServer.this.stop();
				LOGGER.error("GRPC Server Stopped...........");
			}

		});
	}

	/**
	 * stop: This method will shutdown the server
	 * @throws InterruptedException
	 */
	public void stop() {
		if (server != null) {
			server.shutdown();
		}
	}

	/**
	 * blockUntilShutdown: This method will keep server in alive state till it get explicitly terminated
	 * @throws InterruptedException
	 */
	private void blockUntilShutdown() throws InterruptedException {
		if (server != null) {
			server.awaitTermination();
		}
	}

	/**
	 * run: This Method will call to configure application context and start the
	 * Grpc server
	 */
	public static void run() {
		LOGGER.info("Starting Wallet Application");
		contextConfiguration();
		startGrpcServer();
	}

	/**
	 * contextConfiguration: This method will help to configure the application
	 * context after scanning all the configuration classes
	 * 
	 * @return applicationContext
	 */
	public static ApplicationContext contextConfiguration() {
		LOGGER.info("Started Configuring Application Context");
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		applicationContext.scan("com.app.wallet.config");
		applicationContext.refresh();
		LOGGER.info("Application Configured Successfully");
		return applicationContext;
	}

	/**
	 * getGRPCService: This method will provide Wallet GRPC service Object from
	 * application context
	 * 
	 * @return walletServerService Object
	 */
	public static WalletServerService getGRPCService() {
		LOGGER.info("Getting GRPC Server Service Object From Application Context");
		return contextConfiguration().getBean(WalletServerService.class);
	}

	/**
	 * startGrpcServer: This method will start server and await till its termination
	 */
	private static void startGrpcServer() {
		final WalletServer server = new WalletServer();
		try {
			LOGGER.info("Starting WalletGRPC Server");
			server.start();
			server.blockUntilShutdown();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * main: This method is entry point of the application
	 * 
	 * @param args
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws IOException, InterruptedException {
		WalletServer.run();
	}

}
