package com.cohort.qb.api.payment;

public class CreditCardProcessor implements PaymentProcessor {

	@Override
	public boolean pay(double amount) {
		System.out.println("Rs." + amount + " paid using credit card number ");
		System.out.println("Credit Card payment successful.");
		return true;
	}

}
