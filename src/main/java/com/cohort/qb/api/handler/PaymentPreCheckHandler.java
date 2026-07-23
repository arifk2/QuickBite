package com.cohort.qb.api.handler;

import com.cohort.qb.api.order.Order;

public class PaymentPreCheckHandler extends ValidationHandler {

	@Override
	protected boolean check(Order order) {
		System.out.println("Pre-checking payment method: " + order.getPaymentMethod());
		return order.getPaymentMethod() != null;
	}

}
