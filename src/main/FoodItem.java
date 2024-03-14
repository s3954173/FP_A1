package main;

abstract class FoodItem {
	private String name;
	private float price;
	private int prep_time = 0;
	private int max_simultaneous_prep = 1;
	
	// CONSTRUCTORS
	// Constructor with name and price parameter only
	public FoodItem(String name, float price) {
		this.name = name;
		this.price = price;
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
	
	// Setters methods below for flexibility
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


