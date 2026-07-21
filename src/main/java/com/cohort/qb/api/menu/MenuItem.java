package com.cohort.qb.api.menu;

public class MenuItem extends MenuComponent {

	private double basePrice;

	public MenuItem(String name, double basePrice) {
		super(name);
		this.basePrice = basePrice;
	}

	@Override
	public double getPrice() {
		return basePrice;
	}

	@Override
	public int getItemCount() {
		return 1;
	}

	@Override
	public void print(int depth) {
		System.out.println("  ".repeat(depth) + "- " + name + " (Rs." + basePrice + ")");
	}

}
