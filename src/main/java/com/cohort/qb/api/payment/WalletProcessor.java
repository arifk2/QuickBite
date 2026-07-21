package com.cohort.qb.api.payment;

public class WalletProcessor implements PaymentProcessor {

	@Override
	public boolean pay(double amount) {
		System.out.println("Deducting Rs." + amount + " from PayTM Wallet...");
		System.out.println("Wallet payment successful.");
		return true;
	}

}
