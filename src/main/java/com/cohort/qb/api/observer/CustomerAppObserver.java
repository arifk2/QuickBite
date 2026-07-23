package com.cohort.qb.api.observer;

import com.cohort.qb.api.order.Order;

public class CustomerAppObserver implements OrderObserver {

	@Override
	public void update(Order order, String status) {
		System.out.println("[Customer App] Order " + order.getOrderId() + " is now " + status);
	}

}
