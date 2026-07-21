package com.cohort.qb.api.delivery;

import java.time.LocalTime;

public class DistanceBasedFeeStrategy implements DeliveryFeeStrategy {
	private static final double BASE_FEE = 20.0;
	private static final double PER_KM_RATE = 8.0;
	private static final double FREE_DELIVERY_THRESHOLD_KM = 4.0; // first 4 km free

	@Override
	public double calculateFee(double distancekm, LocalTime time) {
		double billableDistance = Math.max(0, distancekm - FREE_DELIVERY_THRESHOLD_KM);
		return BASE_FEE + (billableDistance * PER_KM_RATE);
	}

}
