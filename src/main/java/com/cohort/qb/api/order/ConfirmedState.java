package com.cohort.qb.api.order;

public class ConfirmedState implements OrderState {

	@Override
	public void next(Order order) {
		order.setState(new PreparingState());
	}

	@Override
	public void cancel(Order order, String reason) {
		order.setState(new CancelledState());

	}

	@Override
	public String getStatus() {
		return "CONFIRMED";
	}

}
