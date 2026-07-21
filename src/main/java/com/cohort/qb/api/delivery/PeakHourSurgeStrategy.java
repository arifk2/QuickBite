package com.cohort.qb.api.delivery;

import java.time.LocalTime;

public class PeakHourSurgeStrategy implements DeliveryFeeStrategy {
	private static final double BASE_FEE = 20.0;
	private static final double PER_KM_RATE = 8.0;
	private static final double PEAK_HOUR_FEE = 1.5;

	private static final LocalTime LUNCH_PEAK_START = LocalTime.of(12, 30);
	private static final LocalTime LUNCH_PEAK_END = LocalTime.of(14, 0);
	private static final LocalTime DINNER_PEAK_START = LocalTime.of(19, 0);
	private static final LocalTime DINNER_PEAK_END = LocalTime.of(21, 30);

	@Override
	public double calculateFee(double distancekm, LocalTime time) {
		double baseCalculatedFee = BASE_FEE + (distancekm * PER_KM_RATE);

		if (isPeakHour(time)) {
			return baseCalculatedFee * PEAK_HOUR_FEE;
		}
		return baseCalculatedFee;
	}

	private boolean isPeakHour(LocalTime time) {
		boolean isLunchPeak = !time.isBefore(LUNCH_PEAK_START) && !time.isAfter(LUNCH_PEAK_END);
		boolean isDinnerPeak = !time.isBefore(DINNER_PEAK_START) && !time.isAfter(DINNER_PEAK_END);
		return isLunchPeak || isDinnerPeak;
	}
}
