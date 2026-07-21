package com.cohort.qb.api.order;

import java.util.concurrent.atomic.AtomicLong;

//Singleton class
public final class OrderIdGenerator {

	private static OrderIdGenerator instance;
	private final AtomicLong counter = new AtomicLong(0);

	private OrderIdGenerator() {
		// don't delete for singleton class
	}

	public static OrderIdGenerator getInstance() {
		if (instance == null) {
			synchronized (OrderIdGenerator.class) {
				if (instance == null) {
					instance = new OrderIdGenerator();
				}
			}
		}
		return instance;
	}

	public String generateOrderId() {
		long seq = counter.incrementAndGet(); // atomic, no need for synchronized here
		return "QB-" + System.currentTimeMillis() + "-" + seq;
	}

	/**
	 * Prevent from serialization
	 * 
	 * @return
	 */
	public Object readResolve() {
		return getInstance();
	}

	/**
	 * Prevent from cloning
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException("Clone not possible..");
	}

}
