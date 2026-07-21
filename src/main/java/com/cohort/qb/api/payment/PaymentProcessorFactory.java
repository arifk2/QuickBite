package com.cohort.qb.api.payment;

public class PaymentProcessorFactory {

	// CREDIT_CARD, UPI, WALLET, COD;
	public static PaymentProcessor createProcessor(String method) {
		if (method == null)
			throw new IllegalArgumentException("Payment method cannot be null");

		return switch (method) {
		case "CREDIT_CARD" -> new CreditCardProcessor();
		case "UPI" -> new UPIProcessor();
		case "WALLET" -> new WalletProcessor();
		case "COD" -> new CashOnDeliveryProcessor();

		default -> throw new IllegalArgumentException("Unexpected value: " + method);
		};
	}
}
