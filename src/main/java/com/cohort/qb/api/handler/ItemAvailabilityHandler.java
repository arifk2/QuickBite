package com.cohort.qb.api.handler;

import com.cohort.qb.api.order.Order;

public class ItemAvailabilityHandler extends ValidationHandler {

	@Override
	protected boolean check(Order order) {
		System.out.println("Check item availabilty..");
		return true; // talk to inventory service
	}

}
