package com.cohort.qb.api.handler;

import com.cohort.qb.api.order.Order;
import com.cohort.qb.api.order.PlatformConfig;

public class DeliveryZoneHandler extends ValidationHandler {

	@Override
	protected boolean check(Order order) {
		double radius = PlatformConfig.getInstance().getDeliveryRadiusKm();
		double distance = order.getDeliveryAddress().getDistanceFromRestaurantKm();
		System.out.println("Checking delivery zone (radius=" + radius + "km, distance=" + distance + "km)...");
		if (distance > radius) {
			order.cancel("Delivery address outside " + radius + " km radius");
			return false;
		}
		return true;
	}

}
