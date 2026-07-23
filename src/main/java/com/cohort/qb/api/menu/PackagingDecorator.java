package com.cohort.qb.api.menu;

public class PackagingDecorator extends MenuItemDecorator {
	private double packagingCost;
	private String packagingDescription;

	public PackagingDecorator(MenuComponent wrappedItem, String packagingDescription, double packagingCost) {
		super(wrappedItem);
		this.packagingCost = packagingCost;
		this.packagingDescription = packagingDescription;
	}

	@Override
	public double getPrice() {
		return wrappedItem.getPrice() + packagingCost;
	}

	@Override
	public String getDescription() {
		return wrappedItem.getDescription() + ", " + packagingDescription + " packaging" + "(Rs." + packagingCost + ")";
	}
}
