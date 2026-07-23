package com.cohort.qb.api.order;

public class DeliveredState implements OrderState {

	@Override
	public void next(Order order) {
		System.out.println("Order already delivered.");
	}

	@Override
	public void cancel(Order order, String reason) {
		System.out.println("Order cannot - already delivered ");
	}

	@Override
	public String getStatus() {
		return "DELIVERED";
	}

}
