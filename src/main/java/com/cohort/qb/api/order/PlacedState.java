package com.cohort.qb.api.order;

import com.cohort.qb.api.payment.PaymentProcessor;
import com.cohort.qb.api.payment.PaymentProcessorFactory;

public class PlacedState implements OrderState {

	@Override
	public void next(Order order) {
		PaymentProcessor processor = PaymentProcessorFactory.createProcessor(order.getPaymentMethod());
		boolean success = processor.pay(order.getTotalAmount());
		if (success)
			order.setState(new ConfirmedState());
		else
			order.cancel("Payment failed");
	}

	@Override
	public void cancel(Order order, String reason) {
		order.setState(new CancelledState());
	}

	@Override
	public String getStatus() {
		return "PLACED";
	}
}
