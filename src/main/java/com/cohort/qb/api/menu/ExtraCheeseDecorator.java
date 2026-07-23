package com.cohort.qb.api.menu;

public class ExtraCheeseDecorator extends MenuItemDecorator {
	private double cheesePrice;

	public ExtraCheeseDecorator(MenuComponent wrappedItem, double cheesePrice) {
		super(wrappedItem);
		this.cheesePrice = cheesePrice;
	}

	@Override
	public double getPrice() {
		return wrappedItem.getPrice() + cheesePrice;
	}

	@Override
	public String getDescription() {
		return wrappedItem.getDescription() + " with Extra Cheese" + "(Rs." + cheesePrice + ")";
	}

}
