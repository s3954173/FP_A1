package main;

import java.util.ArrayList;

public class Order extends Meal {
	private ArrayList<FoodItem> items;
	private ArrayList<Meal> meals;
	private int total_prep_time;
	private float total_price;

	// CONSTRUCTOR
	public Order() {
		this.items = new ArrayList<FoodItem>();
		this.meals = new ArrayList<Meal>();
		this.total_prep_time = 0;
		this.total_price = 0;
	}

	// GETTERS
	public ArrayList<FoodItem> getOrderItems() {
		return this.items;
	}

	public ArrayList<Meal> getMeals() {
		return this.meals;
	}

	public int getTotalPrepTime() {
		return this.total_prep_time;
	}

	public float getTotalPrice() {
		return this.total_price;
	}
	
	
	public int getOrderItemsSize() {
		return this.getOrderItems().size();
	}
	
	public int getMealItemsSize() {
		return this.getMeals().size();
	}
	
	// Return total price for a specific food item
	public float getItemTotalPrice(String food_name) {
		float total_item_price = 0;
		for (FoodItem item: getOrderItems()) {
			if (item.getName().equals(food_name)) {
				total_item_price += item.getPrice();
			}
		}
		
		return total_item_price;
	} 
	
	// Return total price of the meals array
	public float getMealsTotalPrice() {
		float total_meals_price = 0;
		if (getMealItemsSize() > 0) {
			total_meals_price = getMealItemsSize() * this.getMeals().get(0).getTotalPrice();
		}
		return total_meals_price; 
		
	}

	// SETTERS
	public void addItem(FoodItem item) {
		this.items.add(item);
	}

	public void addMeal(Meal meal) {
		this.meals.add(meal);
	}

	public void setTotalPrepTime(int cook_fries_counter, FoodItem burrito, FoodItem fries) {
		int burritos_to_prep =  countItemQuantity("Burrito") + getMealItemsSize();
		int burrito_cook_counter =  (int) Math.ceil((double) burritos_to_prep / burrito.getMaxSimultaneousPrep());
		
		// Checks which counter is higher to get the correct total prep time
		if (burrito_cook_counter >= cook_fries_counter) {
			this.total_prep_time = burrito_cook_counter	* burrito.getPrepTime();
	
		} else {
			this.total_prep_time = cook_fries_counter * fries.getPrepTime();
		};

	}

	public void setTotalPrice() {
		float total_price = 0f;

		// FoodItem Loop
		for (FoodItem item : this.getOrderItems()) {
			total_price += item.getPrice();
		};

		// Meals Loop
		for (Meal meal : this.getMeals()) {
			total_price += meal.getTotalPrice();
		};

		this.total_price = total_price;
	}

	// ADDITIONAL METHODS
	
	public int countItemQuantity(String item_name) {
		int quantity = 0;

		// FoodItem Loop to find specific FoodItem
		for (FoodItem item : this.getOrderItems()) {
			if (item.getName().equals(item_name)) {
				quantity++;
			}
		};

		// Add quantity of meals to item quantity
//		quantity += getMealItemsSize();

		return quantity;
	}

	public void printOrderTotal() {
		// Show order info with inclusion of meal
		int[] items_quantity = { countItemQuantity("Burrito"),
				countItemQuantity("Fries"), countItemQuantity("Soda"), getMealItemsSize() };

		String[] item_names = { "Burrito", "Fries", "Soda", "Meal" };

		// Print out "Total for x, x, x, x is $xx.xx
		System.out.print("Total for ");
		// Loop through to print each food item in Order if it exists.
		for (int i = 0; i < 4; i++) {

			if (items_quantity[i] > 0) {
				System.out.printf("%d %s, ", items_quantity[i], item_names[i]);
			}

		}
		System.out.printf("is $%.2f\n ", this.getTotalPrice());
	}
	
}
