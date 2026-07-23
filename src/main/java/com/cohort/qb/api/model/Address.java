package com.cohort.qb.api.model;

import java.util.concurrent.ThreadLocalRandom;

public class Address {
	private String line1;
	private String line2;
	private String city;
	private String state;
	private String pincode;

	public Address(String line1, String city, String state, String pincode) {
		this.line1 = line1;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
	}

	public Address(String line1, String city) {
		this(line1, city, "", "");
	}

	public String getLine1() {
		return line1;
	}

	public String getLine2() {
		return line2;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getPincode() {
		return pincode;
	}

	public void setLine2(String line2) {
		this.line2 = line2;
	}

	public double getDistanceFromRestaurantKm() {
		return 4.5 + ThreadLocalRandom.current().nextInt(1, 6);// adding random number 1 - to 5 for cache
	}

	@Override
	public String toString() {
		return "Address [line1=" + line1 + ", line2=" + line2 + ", city=" + city + ", state=" + state + ", pincode="
				+ pincode + "]";
	}

}