package com.cohort.qb.api.order;

/**
 * Singleton class - as this is configuration file
 */
public final class PlatformConfig {

	private static PlatformConfig instance;
	private volatile double taxRate;
	private volatile double deliveryRadiusKm;

	private PlatformConfig() {
		// don't delete
	};

	public static PlatformConfig getInstance() {
		if (instance == null) {
			synchronized (PlatformConfig.class) {
				if (instance == null) {
					instance = new PlatformConfig();
				}
			}
		}
		return instance;
	}

	public Object readResolve() {
		return getInstance();
	}

	/**
	 * Prevent from cloning
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException("Clone not possible..");
	}

	/**
	 * to set the tax rate externally
	 * 
	 * @param taxRate
	 */
	public synchronized void setTaxRate(double taxRate) {
		if (taxRate < 0 || taxRate > 1) {
			throw new IllegalArgumentException("Tax rate must be between 0 and 1");
		}
		this.taxRate = taxRate;
	}

	/**
	 * TO set the delivery radius
	 * 
	 * @param deliveryRadiusKm
	 */

	public synchronized void setDeliveryRadiusKm(double deliveryRadiusKm) {
		if (deliveryRadiusKm <= 0) {
			throw new IllegalArgumentException("Delivery radius must be positive");
		}
		this.deliveryRadiusKm = deliveryRadiusKm;
	}

	public double getTaxRate() {
		return taxRate;
	}

	public double getDeliveryRadiusKm() {
		return deliveryRadiusKm;
	}

}
