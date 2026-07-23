package com.cohort.qb.api.model;

public class Customer {
	private String cutomerId;
	private String customerName;
	private String mobileNUmber;

	public Customer(String cutomerId, String customerName, String mobileNUmber) {
		this.cutomerId = cutomerId;
		this.customerName = customerName;
		this.mobileNUmber = mobileNUmber;
	}

	public String getCutomerId() {
		return cutomerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public String getMobileNUmber() {
		return mobileNUmber;
	}
}
