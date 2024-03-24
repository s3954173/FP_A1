package main;

import java.util.ArrayList;

public class SalesReport {
	private ArrayList<Order> orders;
	
	//CONSTRUCTOR
	public SalesReport() {
		this.orders = new ArrayList<Order>();
	};
	
	public SalesReport(ArrayList<Order> orders) {
		this.orders = orders;
	};
	
	

	// GETTERS
	public ArrayList<Order> getOrders() {
		return this.orders;
	};

	// SETTER
	public void addOrder(Order order) {
		this.getOrders().add(order);
	}

	// CUSTOM METHODS
	public int printItemInfo(String food_name) {
		int quantity = 0;
		float total_price = 0;

		for (Order order : getOrders()) {
			for (FoodItem item : order.getOrderItems()) {
				if (item.getName().equals(food_name)) {
					quantity++;
				}
			}
			total_price += order.getItemTotalPrice(food_name);
		}

        System.out.printf("%-10s%-5d $%.2f\n", food_name + ":", quantity, total_price);
		
		return quantity;

	}
	
	public int printMealInfo() {
		int quantity = 0;
		float total_price = 0;
		
		for (Order order : getOrders()) {
			quantity += order.getMealItemsSize();
			total_price += order.getMealsTotalPrice();
		}
        System.out.printf("%-10s%-5d $%.2f\n", "Meals:", quantity, total_price);
		
		return quantity;

	}
	
	public void printSalesReport(int unsold_fries) {
		int total_quantity = 0;
		float total_price = 0;
		
		// Leftover Fries
		System.out.printf("Unsold Serves of Fries: %d\n\n", unsold_fries);
		
		// Burritos, Fries, Soda, Meal Info
		total_quantity+=printItemInfo("Burrito");
		total_quantity+=printItemInfo("Fries");
		total_quantity+=printItemInfo("Soda");
		total_quantity+=printMealInfo();
		
		for (Order order: getOrders()) {
			total_price += order.getTotalPrice();
		}
		
		
		// Total Sales Quantity and Price
		System.out.println("-".repeat(25));
        System.out.printf("%-10s%-5d $%.2f\n", "", total_quantity, total_price);
		System.out.println("-".repeat(25));

	}

}
