package com.cohort.qb.api.order;

public interface OrderState {

	void next(Order order);

	void cancel(Order order, String reason);

	String getStatus();
}
