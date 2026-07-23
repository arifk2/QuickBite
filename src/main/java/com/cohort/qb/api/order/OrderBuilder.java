package com.cohort.qb.api.order;

import java.util.ArrayList;
import java.util.List;

import com.cohort.qb.api.menu.MenuComponent;
import com.cohort.qb.api.model.Address;
import com.cohort.qb.api.model.Customer;

public class OrderBuilder {
	private List<MenuComponent> items = new ArrayList<>();
	private Customer customer;
	private Address deliveryAddress;
	private String paymentMethod;

	public OrderBuilder setItems(List<MenuComponent> items) {
		this.items = items;
		return this;
	}

	public OrderBuilder addItem(MenuComponent item) {
		this.items.add(item);
		return this;
	}

	public OrderBuilder setCustomer(Customer customer) {
		this.customer = customer;
		return this;
	}

	public OrderBuilder setAddress(Address deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
		return this;
	}

	public OrderBuilder setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
		return this;
	}

	public Order build() {
		this.validateMandatoryFields();
		String orderId = OrderIdGenerator.getInstance().generateOrderId();

		return new Order(orderId, items, customer, deliveryAddress, paymentMethod);
	}

	private void validateMandatoryFields() {
		List<String> missing = new ArrayList<>();

		if (items == null || items.isEmpty()) {
			missing.add("items");
		}
		if (customer == null) {
			missing.add("customer");
		}
		if (deliveryAddress == null) {
			missing.add("deliveryAddress");
		}
		if (paymentMethod == null || paymentMethod.isBlank()) {
			missing.add("paymentMethod");
		}
		if (!missing.isEmpty()) {
			throw new IllegalStateException(
					"Cannot build Order - missing mandatory field(s): " + String.join(", ", missing));
		}
	}
}