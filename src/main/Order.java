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

	// SETTERS
	public void addItem(FoodItem item) {
		this.items.add(item);
	}

	public void addMeal(Meal meal) {
		this.meals.add(meal);
	}

	// TODO Optimise Code
	public void setTotalPrepTime(int cook_fries_counter, FoodItem burrito, FoodItem fries) {
		int burrito_quantity = countItemQuantity("Burrito");
		System.out.println(burrito_quantity);
		
		//TODO Need to update it so that it doesn't check for the Burrito or Fries instance
		
		// Checks which counter is higher to get the correct total prep time
		if (burrito_quantity > cook_fries_counter) {

			// Burrito Prep Time = Math.ceil(b/2) * 9 min
			this.total_prep_time = (int) Math.ceil(
					(double) burrito_quantity / burrito.getMaxSimultaneousPrep())
					* burrito.getPrepTime();
	
		} else {
			// Fries Prep Time = cook_fries_counter * 8 min
			this.total_prep_time = cook_fries_counter * fries.getPrepTime();
			
		};

	}

	public void setTotalPrice() {
		float total_price = 0f;

		// FoodItem Loop
		for (FoodItem item : this.getOrderItems()) {
			total_price += item.getPrice();
		}
		;

		// Meals Loop
		for (Meal meal : this.getMeals()) {
			total_price += meal.getTotalPrice();
		};

		this.total_price = total_price;
	}

	// ADDITIONAL METHODS
	//TODO Create method to get array sizes of items and meals
	public int countItemQuantity(String item_name) {
		int quantity = 0;

		// FoodItem Loop to find specific FoodItem
		for (FoodItem item : this.getOrderItems()) {
			if (item.getName().equals(item_name)) {
				quantity++;
			}
		};

		// Add quantity of meals to item quantity
		quantity += this.getMeals().size();

		return quantity;
	}

	public void printOrderTotal() {
		// Show order info with inclusion of meal
		int meal_quantity = this.getMeals().size();
		int[] items_quantity = { countItemQuantity("Burrito") - meal_quantity,
				countItemQuantity("Fries") - meal_quantity, countItemQuantity("Soda") - meal_quantity, meal_quantity };

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
