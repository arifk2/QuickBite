package com.cohort.qb.api.menu;

public class MenuItem extends MenuComponent {

	private double basePrice;
	private String description;

	public MenuItem(String name, double basePrice, String description) {
		super(name);
		this.basePrice = basePrice;
		this.description = description;
	}

	public MenuItem(String name, double basePrice) {
		this(name, basePrice, ""); 
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

	@Override
	public String getDescription() {
		return description;
	}

}
