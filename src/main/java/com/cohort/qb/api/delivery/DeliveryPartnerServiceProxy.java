package com.cohort.qb.api.delivery;

import java.util.HashMap;
import java.util.Map;

import com.cohort.qb.api.model.Address;
import com.cohort.qb.api.model.DeliveryPartner;

public class DeliveryPartnerServiceProxy implements DeliveryPartnerService {

	private RealDeliveryPartnerService realService;
	private Map<String, DeliveryPartner> cache;

	public DeliveryPartnerServiceProxy(RealDeliveryPartnerService realService) {
		this.realService = realService;
		this.cache = new HashMap<>();
	}

	@Override
	public DeliveryPartner findNearbyPartner(Address location) {
		String key = location.getDistanceFromRestaurantKm() + "";
		if (cache.containsKey(key)) {
			System.out.println("Cache hit - returning cached partner.");
			return cache.get(key);
		}
		DeliveryPartner partner = realService.findNearbyPartner(location);
		cache.put(key, partner);
		return partner;
	}

	public DeliveryPartner reassignPartner(Address location, String userRole) {
		if (!"ADMIN".equalsIgnoreCase(userRole)) {
			throw new SecurityException("Only ops-admin can manually reassign a delivery partner");
		}
		return realService.findNearbyPartner(location);
	}
}
