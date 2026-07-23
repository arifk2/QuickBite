package com.cohort.qb.api.observer;

import com.cohort.qb.api.order.Order;

public interface OrderObserver {
	void update(Order order, String status);
}
