package com.cohort.qb.api.menu;

import java.util.List;

public abstract class MenuComponent {

	protected String name;

	protected MenuComponent(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public abstract double getPrice();

	public abstract int getItemCount();

	public abstract void print(int depth);

	public void add(MenuComponent component) {
		throw new UnsupportedOperationException(name + " is a leaf item - cannot add children to it");
	}

	public void remove(MenuComponent component) {
		throw new UnsupportedOperationException(name + " is a leaf item - cannot remove children from it");
	}

	public List<MenuComponent> getChildren() {
		throw new UnsupportedOperationException(name + " is a leaf item - has no children");
	}

}
