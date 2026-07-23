package com.cohort.qb.api;

import com.cohort.qb.api.delivery.DeliveryFeeStrategy;
import com.cohort.qb.api.delivery.DeliveryPartnerService;
import com.cohort.qb.api.delivery.DeliveryPartnerServiceProxy;
import com.cohort.qb.api.delivery.RealDeliveryPartnerService;
import com.cohort.qb.api.handler.ValidationHandler;
import com.cohort.qb.api.menu.Category;
import com.cohort.qb.api.menu.MenuComponent;
import com.cohort.qb.api.model.Address;
import com.cohort.qb.api.model.Customer;
import com.cohort.qb.api.model.DeliveryPartner;
import com.cohort.qb.api.observer.CustomerAppObserver;
import com.cohort.qb.api.observer.DeliveryPartnerAppObserver;
import com.cohort.qb.api.observer.RestaurantDashboardObserver;
import com.cohort.qb.api.order.Order;
import com.cohort.qb.api.order.OrderBuilder;
import com.cohort.qb.api.order.PlatformConfig;
import com.cohort.qb.api.utils.QuickBiteUtils;

public class QuickBiteDriver {
	public static void main(String[] args) {
		orderNow();
		System.out.println(">".repeat(30) + "" + "<".repeat(30));
	}

	public static void orderNow() {

		// Configuration building
		PlatformConfig.getInstance().setDeliveryRadiusKm(20);
		PlatformConfig.getInstance().setTaxRate(0.18);

		// Step 1: Browse menu (Composite)
		Category menu = QuickBiteUtils.buildSampleMenu();
		menu.print(0);
		System.out.println("Menu loaded: " + menu.getItemCount());

		// Step 2: Order Paneer Tikka with Customize item (Decorator)
		// 220(Panner Tikka) + 30(Extra Cheese) +10(box) +
		MenuComponent paneerTikka = QuickBiteUtils.customizeItem(menu, "Paneer Tikka");

		System.out.println(">".repeat(32) + "ORDER" + "<".repeat(32));
		System.out.println("\n" + paneerTikka.getName() + " with Customized item: " + paneerTikka.getDescription()
				+ " - Rs." + paneerTikka.getPrice());

		// Step 3: Register observers (Observer)
		CustomerAppObserver customerApp = new CustomerAppObserver();
		RestaurantDashboardObserver restaurantDash = new RestaurantDashboardObserver();
		DeliveryPartnerAppObserver deliveryApp = new DeliveryPartnerAppObserver();

		// Step 4: Build the order (Builder + Singleton for ID)
		Order order = new OrderBuilder().addItem(paneerTikka).setCustomer(new Customer("CUST123", "Arif", "9876543210"))
				.setAddress(new Address("221B Near Union Bank", "Delhi")).setPaymentMethod("UPI").build();

		order.subscribe(customerApp);
		order.subscribe(restaurantDash);
		order.subscribe(deliveryApp);

		System.out.println("\nOrder created: " + order.getOrderId());

		// Step 5: Run validation chain (Chain of Responsibility)
		ValidationHandler chain = QuickBiteUtils.buildValidationChain();
		boolean isValid = chain.handle(order);

		// Step 6: State transition based on validation outcome (State)
		if (!isValid) {
			System.out.println("\nOrder rejected: " + order.getRejectionReason());
			QuickBiteUtils.printOrderHistory(order);
			return;
		}

		// Step 6.5: Compute delivery fee (Strategy) BEFORE payment, so the amount
		// charged actually includes it. Based on delivery address/distance only
		DeliveryFeeStrategy feeStrategy = QuickBiteUtils.selectFeeStrategy();
		order.applyDeliveryFeeStrategy(feeStrategy);
		System.out.println("Delivery fee: Rs." + order.getDeliveryFee());
		System.out.println("Tax fee: Rs." + order.getTax());
		System.out.println("Total amount to be charged: Rs." + order.getTotalAmount());

		// Step 7: Charge payment (Factory Method) + move to CONFIRMED (State)
		// order.getTotalAmount() now correctly includes subtotal + tax + delivery fee
		order.next(); // PLACED -> CONFIRMED (PlacedState internally uses PaymentProcessorFactory)

		if (order.getStatus().equals("CANCELLED")) {
			System.out.println("\nOrder cancelled: " + order.getRejectionReason());
			QuickBiteUtils.printOrderHistory(order);
			return;
		}

		// Step 8: Restaurant marks PREPARING (State)
		order.next(); // CONFIRMED -> PREPARING

		// Step 9: Move to OUT_FOR_DELIVERY, look up delivery partner (Proxy)
		// Fee was already computed in Step 6.5 - this is purely partner assignment now
		order.next(); // PREPARING -> OUT_FOR_DELIVERY
		DeliveryPartnerService partnerService = new DeliveryPartnerServiceProxy(new RealDeliveryPartnerService());
		DeliveryPartner partner = partnerService.findNearbyPartner(order.getDeliveryAddress());
		System.out.println("Assigned delivery partner: " + partner.getName());

		// Step 11: Mark DELIVERED (State) - final notification + history
		order.next(); // OUT_FOR_DELIVERY -> DELIVERED

		QuickBiteUtils.printOrderHistory(order);
		System.out.println();
	}
}