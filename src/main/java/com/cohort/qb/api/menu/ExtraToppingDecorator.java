package com.cohort.qb.api.menu;

public class ExtraToppingDecorator extends MenuItemDecorator {
	private double toppingPrice;
	private String toppingName;

	public ExtraToppingDecorator(MenuComponent wrappedItem, double toppingPrice, String toppingName) {
		super(wrappedItem);
		this.toppingPrice = toppingPrice;
		this.toppingName = toppingName;
	}

	@Override
	public double getPrice() {
		return wrappedItem.getPrice() + toppingPrice;
	}

	@Override
	public String getDescription() {
		return wrappedItem.getDescription() + " with " + toppingName;
	}

}
