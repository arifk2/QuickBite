package com.cohort.qb.api.observer;

import com.cohort.qb.api.order.Order;

public class DeliveryPartnerAppObserver implements OrderObserver {

	@Override
	public void update(Order order, String status) {
		System.out.println("[Delivery Partner App] Order " + order.getOrderId() + " is now " + status);
	}
}
