package com.cohort.qb.api.model;

public class Address {
	private String address;
	private String state;

	public Address(String address, String state) {
		this.address = address;
		this.state = state;
	}

	public String getAddress() {
		return address;
	}

	public String getState() {
		return state;
	}

}
