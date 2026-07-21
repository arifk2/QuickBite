package com.cohort.qb.api.delivery;

import java.time.LocalTime;

public class FlatFeeStrategy implements DeliveryFeeStrategy {
	private static double FLATE_FEE = 40.00;

	@Override
	public double calculateFee(double distancekm, LocalTime time) {
		return FLATE_FEE;
	}

}
