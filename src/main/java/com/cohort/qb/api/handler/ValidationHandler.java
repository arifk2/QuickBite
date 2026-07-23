package com.cohort.qb.api.handler;

import com.cohort.qb.api.order.Order;

public abstract class ValidationHandler {
	protected ValidationHandler nextHandler;
	
	public ValidationHandler setNext(ValidationHandler handler) {
		this.nextHandler = handler;
		return handler;
	}
	
	protected abstract boolean check(Order order);
	
	public boolean handle(Order order) {
        if (!check(order)) {
            return false; // this handler rejected - stop the chain
        }
        if (nextHandler != null) {
            return nextHandler.handle(order);
        }
        return true; // reached end of chain, all checks passed
    }
}
