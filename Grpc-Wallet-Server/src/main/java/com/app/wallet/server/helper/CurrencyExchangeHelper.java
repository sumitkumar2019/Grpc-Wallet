package com.app.wallet.server.helper;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.money.convert.CurrencyConversion;
import javax.money.convert.MonetaryConversions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Sumit Kumar: CurrencyExchangeHelper: This Helper Class will help to
 *         exchange the currency amount from one country to another country
 * 
 *
 */
public class CurrencyExchangeHelper {

	public static final Logger LOGGER = LoggerFactory.getLogger(CurrencyExchangeHelper.class);
	/**
	 * getExchangedCurrencyAmount: This method will help to exchange the currency
	 * amount from one country to another country
	 * 
	 * @param amount
	 * @param currentCurrency
	 * @param requiredCurrency
	 * @return
	 */
	public static double getExchangedCurrencyAmount(double amount, String currentCurrency, String requiredCurrency) {
		LOGGER.info("Currency Conversion started from " + currentCurrency + " to " + requiredCurrency);
		
		//Get required currency conversion
		CurrencyConversion convertedCurrency = MonetaryConversions.getConversion(requiredCurrency);
		
		//Set and create Current Monetary Amount
		MonetaryAmount monetaryAmount = Monetary.getDefaultAmountFactory().setCurrency(currentCurrency).setNumber(amount).create();
		
		//Converting from one monetary currency to required currency 
		monetaryAmount = monetaryAmount.with(convertedCurrency);
		
		//Converting required converted monetary amount to double value scaling up to two decimal places
		double convertedAmount = new BigDecimal(monetaryAmount.getNumber().doubleValue()).setScale(2, RoundingMode.HALF_UP).doubleValue();

		LOGGER.info("Currency Converted to " + requiredCurrency + " Successfully");
		return convertedAmount;
				
	}

}
