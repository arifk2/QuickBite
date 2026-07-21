package com.cohort.qb.api.menu;

public abstract class MenuItemDecorator extends MenuComponent {
	protected MenuComponent wrappedItem;

	protected MenuItemDecorator(MenuComponent wrappedItem) {
		super(wrappedItem.getName());
		this.wrappedItem = wrappedItem;
	}

	@Override
	public abstract double getPrice();

	@Override
	public int getItemCount() {
		return wrappedItem.getItemCount();
	}

	
	@Override
    public abstract String getDescription();
	
	@Override
	public void print(int depth) {
		System.out.println("  ".repeat(depth) + "- " + getDescription() + " (Rs." + getPrice() + ")");
	}

}
