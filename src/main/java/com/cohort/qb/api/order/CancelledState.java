package com.cohort.qb.api.order;

public class CancelledState implements OrderState {

	@Override
	public void next(Order order) {
		System.out.println("Order cancelled, not further action required.");
	}

	@Override
	public void cancel(Order order, String reason) {
		System.out.println("Already cancelled");
	}

	@Override
	public String getStatus() {
		return "CANCELLED";
	}
}
