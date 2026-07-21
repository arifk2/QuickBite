package com.cohort.qb.api.menu;

import java.util.ArrayList;
import java.util.List;

public class Category extends MenuComponent {

	private List<MenuComponent> children;

	public Category(String name) {
		super(name);
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

}
