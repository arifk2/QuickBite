package com.cohort.qb.api.order;

public class OutForDeliveryState implements OrderState {

	@Override
	public void next(Order order) {
		System.out.println("Order already delivered.");
	}

	@Override
	public void cancel(Order order, String reason) {
		System.out.println("Cannot cancel order already delivered.");
	}

	@Override
	public String getStatus() {
		return "OUT_FOR_DELIVERY";
	}

}
