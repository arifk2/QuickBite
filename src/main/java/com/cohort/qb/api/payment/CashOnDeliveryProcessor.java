package com.cohort.qb.api.payment;

public class CashOnDeliveryProcessor implements PaymentProcessor {

	@Override
	public boolean pay(double amount) {
		System.out.println("Rs." + amount + " marked as Cash on Delivery - to be collected at drop-off.");
		return true;
	}
}

