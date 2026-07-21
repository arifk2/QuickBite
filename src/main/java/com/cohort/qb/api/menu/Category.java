package com.cohort.qb.api.menu;

import java.util.ArrayList;
import java.util.List;

public class Category extends MenuComponent {

	private List<MenuComponent> children;
	private String description;

	public Category(String name, String desciption) {
		super(name);
		this.children = new ArrayList<>();
		this.description = desciption;
	}

	public Category(String name) {
		this(name, "");
		this.children = new ArrayList<>();
	}

	@Override
	public void add(MenuComponent menuComponent) {
		children.add(menuComponent);
	}

	@Override
	public void remove(MenuComponent menuComponent) {
		children.remove(menuComponent);
	}

	@Override
	public double getPrice() {
		return children.stream().mapToDouble(MenuComponent::getPrice).sum();
	}

	@Override
	public void print(int depth) {
		System.out.println("  ".repeat(depth) + "+ " + name);
		for (MenuComponent child : children) {
			child.print(depth + 1);
		}
	}

	public int getItemCount() {
		return children.stream().mapToInt(MenuComponent::getItemCount).sum();
	}

	@Override
	public String getDescription() {
		return description;
	}

	/**
	 * to be deleted
	 * 
	 * @param itemName
	 * @return
	 */
	public MenuComponent find(String itemName) {
		for (MenuComponent child : children) {
			if (child.getName().equalsIgnoreCase(itemName)) {
				return child;
			}
			if (child instanceof Category) {
				MenuComponent found = ((Category) child).find(itemName);
				if (found != null) {
					return found;
				}
			}
		}
		return null; // not found in this branch
	}

}
