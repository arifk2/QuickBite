package com.cohort.qb.api.order;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.cohort.qb.api.delivery.DeliveryFeeStrategy;
import com.cohort.qb.api.menu.MenuComponent;
import com.cohort.qb.api.model.Address;
import com.cohort.qb.api.model.Customer;
import com.cohort.qb.api.observer.OrderObserver;

public class Order {
	private final String orderId;
	private final List<MenuComponent> items;
	private final Customer customer;
	private final Address deliveryAddress;
	private final String paymentMethod;

	private OrderState currentState;
	private final List<OrderObserver> observers;
	private final List<String> history;
	private double deliveryFee;
	private String rejectionReason;

	public Order(String orderId, List<MenuComponent> items, Customer customer, Address deliveryAddress,
			String paymentMethod) {
		this.orderId = orderId;
		this.items = items;
		this.customer = customer;
		this.deliveryAddress = deliveryAddress;
		this.paymentMethod = paymentMethod;
		this.currentState = new PlacedState();
		this.deliveryFee = 0.0;
		this.observers = new ArrayList<>();
		this.history = new ArrayList<>();
		this.addHistory("Order placed with ID " + orderId);

	}

	public void addHistory(String event) {
		history.add(LocalDateTime.now() + " - " + event);
	}

	public List<String> getHistory() {
		return history;
	}

	public OrderState getCurrentState() {
		return currentState;
	}

	public List<OrderObserver> getObservers() {
		return observers;
	}

	public void setState(OrderState state) {
		this.currentState = state;
		this.addHistory("State changed to " + state.getStatus());
		this.notifyObservers(state.getStatus());
	}

	private void notifyObservers(String status) {
		for (OrderObserver observer : observers) {
			observer.update(this, status);
		}
	}

	public void next() {
		currentState.next(this);
	}

	public double getSubtotal() {
		return items.stream().mapToDouble(MenuComponent::getPrice).sum();
	}

	public double getTotalAmount() {
		return getSubtotal() + this.getTax() + deliveryFee;
	}

	public double getTax() {
		return getSubtotal() * PlatformConfig.getInstance().getTaxRate();
	}

	public void cancel(String reason) {
		this.rejectionReason = reason;
		currentState.cancel(this, reason);
	}

	public String getStatus() {
		return currentState.getStatus();
	}

	public void subscribe(OrderObserver observer) {
		observers.add(observer);
	}

	public String getOrderId() {
		return orderId;
	}

	public List<MenuComponent> getItems() {
		return items;
	}

	public Customer getCustomer() {
		return customer;
	}

	public Address getDeliveryAddress() {
		return deliveryAddress;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public double getDeliveryFee() {
		return deliveryFee;
	}

	public String getRejectionReason() {
		return rejectionReason;
	}

	public void applyDeliveryFeeStrategy(DeliveryFeeStrategy strategy) {
		double distanceKm = deliveryAddress.getDistanceFromRestaurantKm();
		this.deliveryFee = strategy.calculateFee(distanceKm, LocalTime.now());
		this.addHistory("Delivery fee calculated: Rs." + deliveryFee);
	}
}
