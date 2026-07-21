package com.cohort.qb.api.payment;

public class UPIProcessor implements PaymentProcessor {

	@Override
	public boolean pay(double amount) {
		System.out.println("Rs." + amount + " paid using UpiId");
		System.out.println("UPI payment successful.");
		return true;
	}

}
