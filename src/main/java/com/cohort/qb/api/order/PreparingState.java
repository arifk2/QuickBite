package com.cohort.qb.api.order;

public class PreparingState implements OrderState {

	@Override
	public void next(Order order) {
		order.setState(new OutForDeliveryState());
	}

	@Override
	public void cancel(Order order, String reason) {
		System.out.println("Cannot cancel order - already preparing");
	}

	@Override
	public String getStatus() {
		return "PREPARING";
	}

}
