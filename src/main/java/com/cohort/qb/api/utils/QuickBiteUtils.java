package com.cohort.qb.api.utils;

import java.time.LocalTime;

import com.cohort.qb.api.delivery.DeliveryFeeStrategy;
import com.cohort.qb.api.delivery.DistanceBasedFeeStrategy;
import com.cohort.qb.api.delivery.PeakHourSurgeStrategy;
import com.cohort.qb.api.handler.DeliveryZoneHandler;
import com.cohort.qb.api.handler.FraudCheckHandler;
import com.cohort.qb.api.handler.ItemAvailabilityHandler;
import com.cohort.qb.api.handler.PaymentPreCheckHandler;
import com.cohort.qb.api.handler.ValidationHandler;
import com.cohort.qb.api.menu.Category;
import com.cohort.qb.api.menu.ExtraCheeseDecorator;
import com.cohort.qb.api.menu.MenuComponent;
import com.cohort.qb.api.menu.MenuItem;
import com.cohort.qb.api.menu.PackagingDecorator;
import com.cohort.qb.api.order.Order;

public class QuickBiteUtils {

	private QuickBiteUtils() {
	}

	public static Category buildSampleMenu() {
		Category root = new Category("Menu");
		Category starters = new Category("Starters");
		Category vegStarters = new Category("Veg Starters");

		vegStarters.add(new MenuItem("Paneer Tikka", 220.0, "Grilled cottage cheese cubes"));
		vegStarters.add(new MenuItem("Veg Manchurian", 180.0, "Indo-Chinese fried veg balls"));

		starters.add(vegStarters);
		root.add(starters);

		// --- Main Course ---
		Category mainCourse = new Category("Main Course");
		Category vegMainCourse = new Category("Veg Main Course");
		Category nonVegMainCourse = new Category("Non-Veg Main Course");

		vegMainCourse.add(new MenuItem("Paneer Butter Masala", 260.0, "Cottage cheese in rich tomato-butter gravy"));
		vegMainCourse.add(new MenuItem("Dal Makhani", 210.0, "Slow-cooked black lentils with butter and cream"));
		vegMainCourse.add(new MenuItem("Veg Biryani", 240.0, "Basmati rice layered with mixed vegetables and spices"));

		nonVegMainCourse.add(new MenuItem("Butter Chicken", 320.0, "Chicken in creamy tomato-butter gravy"));
		nonVegMainCourse.add(new MenuItem("Chicken Biryani", 290.0, "Basmati rice layered with spiced chicken"));

		mainCourse.add(vegMainCourse);
		mainCourse.add(nonVegMainCourse);
		root.add(mainCourse);

		return root;
	}

	public static MenuComponent customizeItem(Category menu, String itemName) {
		MenuComponent base = menu.find(itemName);
		MenuComponent withCheese = new ExtraCheeseDecorator(base, 30.0);
		MenuComponent withPackaging = new PackagingDecorator(withCheese, "Eco-box", 10.0);
		return withPackaging;
	}

	public static ValidationHandler buildValidationChain() {
		ValidationHandler availability = new ItemAvailabilityHandler();
		ValidationHandler zone = new DeliveryZoneHandler();
		ValidationHandler payment = new PaymentPreCheckHandler();
		ValidationHandler fraud = new FraudCheckHandler();

		availability.setNext(zone).setNext(payment).setNext(fraud);

		return availability; // head of the chain
	}

	public static DeliveryFeeStrategy selectFeeStrategy() {
		int hour = LocalTime.now().getHour();
		if ((hour >= 12 && hour <= 14) || (hour >= 19 && hour <= 21)) {
			return new PeakHourSurgeStrategy();
		}
		return new DistanceBasedFeeStrategy();
	}

	public static void printOrderHistory(Order order) {
		System.out.println("\n--- Order History (" + order.getOrderId() + ") ---");
		order.getHistory().forEach(System.out::println);
	}

}