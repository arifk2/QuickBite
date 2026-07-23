package com.cohort.qb.api.handler;

import com.cohort.qb.api.order.Order;

public class FraudCheckHandler extends ValidationHandler {

	@Override
	protected boolean check(Order order) {
		System.out.println("Running Fraud Check.");
		return true;
	}
}
