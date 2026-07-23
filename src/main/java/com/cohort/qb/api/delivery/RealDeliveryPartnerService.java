package com.cohort.qb.api.delivery;

import com.cohort.qb.api.model.Address;
import com.cohort.qb.api.model.DeliveryPartner;

public class RealDeliveryPartnerService implements DeliveryPartnerService {

	@Override
	public DeliveryPartner findNearbyPartner(Address location) {
		System.out.println("(slow lookup) Searching real-time partner database...");
		return new DeliveryPartner("Ravi Kumar");
	}
}
