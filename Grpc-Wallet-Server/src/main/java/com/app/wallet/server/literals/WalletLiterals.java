package com.app.wallet.server.literals;

import com.app.wallet.server.helper.CurrencyExchangeHelper;

/**
 * @author Sumit Kumar: WalletLiterals: This Interface contain all the
 *         application constant
 */
public interface WalletLiterals {

	// Currency Constants
	String EUR = "EUR";
	String GBP = "GBP";
	String USD = "USD";

	// Response Message Constants
	String USERNOTFOUND_ERROR = "User Id not available";
	String UNKNOWNCURRENCY_ERROR = "Unknown Currency";
	String INSUFFICIENTFUND_ERROR = "Insufficient Fund";
	String SERVER_ERROR = "Other Exceptions Occur at Server";
	String SUCCESS_RESPONSE = "OK";

	// Test Constants
	double DELTA = 1e-1;
	double FIXED_DEPOSIT_AMOUNT = 100;
	double FIXED_WITHDRAWL_AMOUNT = 200;
	double _100_EUR_TO_USD = CurrencyExchangeHelper.getExchangedCurrencyAmount(FIXED_DEPOSIT_AMOUNT, USD, EUR);

}
