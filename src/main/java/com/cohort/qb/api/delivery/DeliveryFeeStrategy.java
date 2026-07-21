package com.cohort.qb.api.delivery;

import java.time.LocalTime;

public interface DeliveryFeeStrategy {
	double calculateFee(double distancekm, LocalTime time);
}
