package com.cohort.qb.api.delivery;

import com.cohort.qb.api.model.Address;
import com.cohort.qb.api.model.DeliveryPartner;

public interface DeliveryPartnerService {
	DeliveryPartner findNearbyPartner(Address location);
}
