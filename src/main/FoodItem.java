package main;

import java.util.Queue;

public abstract class FoodItem {
	private String name;
	private float price;
	private int prep_time;
	private int max_simultaneous_prep;
	
	// CONSTRUCTORS
	// Constructor with name and price parameter only
	public FoodItem(String name, float price) {
		this.name = name;
		this.price = price;
		this.prep_time = 0;
		this.max_simultaneous_prep = 1;
	}
	
	// Constructor with all attributes
	public FoodItem(String name, float price, int prepTime, int maxSimultaneousPrep) {
		this.name = name;
		this.price = price;
		this.prep_time = prepTime;
		this.max_simultaneous_prep = maxSimultaneousPrep;
	}
	

	// GETTERS
	public String getName() {
		return this.name;
	};
	
	public float getPrice() {
		return this.price;
	};
	
	public int getPrepTime() {
		return this.prep_time;
	};
	
	public float getMaxSimultaneousPrep() {
		return this.max_simultaneous_prep;
	};
	
	
	// SETTERS
	public void setName(String name) {
		this.name = name;
	};
	
	public void setPrice(float price) {
		// Check price is more than $0
		if(price >= 0) {
			this.price = price;
		} else {
			System.out.println("Price must be more than $0.00");
		};
	};
	
	public void setPrepTime(int prep_time) {
		if (prep_time < 0) {
			System.out.println("Prep time must be 0 minutes or more");
		} else {
			this.prep_time = prep_time;
		};
	}
	
	public void setMaxSimultaneousPrep(int max_simultaneous_prep) {
		if (prep_time < 1) {
			System.out.println("Max simultaneous prep must be 1 or more");
		} else {
			this.max_simultaneous_prep = max_simultaneous_prep;
		};
	}
}

class Burrito extends FoodItem {
	public Burrito() {
		super("Burrito", 7.0f, 9, 2);
		// $7, 9min prep time, prep 2 at same time
	};
	
	public Burrito(float price) {
		super("Burrito", price, 9, 2);

	};
	
}

class Fries extends FoodItem implements Kitchen{
	public Fries() {
		super("Fries", 4.0f, 8, 5);
		// $4, 8min prep time, prep 5 at same time
	};
	
	public Fries(float price) {
		super("Fries", price, 8, 5);
	};
	
	
	@Override
    public void prepFood() {
        // This method is replaced by the prepFries static method
    }
	
	public static int prepFries(FoodItem fries, Queue<FoodItem> warming_tray, Order order, int food_quantity) {
		int current_cook_counter = 0;
		
		// Check if warming tray has enough fries
		if (food_quantity > warming_tray.size()) {
			// Cook Fries and tally the amount of times fries need to be cooked
			current_cook_counter = (int) Math.ceil((double) food_quantity / 5);
			int fries_batch = current_cook_counter * 5;
			for (int i = 0; i < fries_batch; i++) {
				warming_tray.add(fries);
			}
			
			//Print out Cooking Fries
			System.out.println("Cooking fries; please be patient");
		}

		return current_cook_counter;
	};
}

class Soda extends FoodItem{
	public Soda() {
		super("Soda", 2.50f);
		// $2.50
	}
	
	public Soda(float price) {
		super("Soda", price);
	}
	
}