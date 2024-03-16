package main;
import java.util.LinkedList;

class Order {
	private LinkedList<FoodItem> items;
	private int total_prep_time;
	private float total_price;
	
	// CONSTRUCTOR
	public Order() {
		this.items = new LinkedList<>();
		this.total_prep_time = 0;
		this.total_price = 0;
	}
	
	// GETTERS
	public LinkedList<FoodItem> getItems() {
        return items;
    } 
	
	public int getTotalPrepTime() {
        return total_prep_time;
    }
	
	public double getTotalPrice() {
        return total_price;
    }
	// SETTERS
	public void addItems(FoodItem item) {
		this.items.add(item);
	} 
	
	//TODO Calculate Total Prep time appropriately
	public void setTotalPrepTime(int cook_fries_counter) {
		int burrito_quantity = countItemQuantity("Burrito");
		
		// Checks which counter is higher to get the correct total prep time
		if (burrito_quantity > cook_fries_counter) {
			for (FoodItem item:this.getItems()) {
				if (item instanceof Burrito) {
					// Burrito Prep Time = Math.ceil(b/2) * 9 min
					this.total_prep_time = (int) Math.ceil(burrito_quantity/item.getMaxSimultaneousPrep()) * item.getPrepTime();
					break;
				}
			}
		} else {
			for (FoodItem item:this.getItems()) {
				if (item instanceof Fries) {
					// Fries Prep Time = cook_fries_counter * 8 min
					this.total_prep_time = (int) cook_fries_counter * item.getPrepTime();
					break;
				}
			}
		};
		
	}
	
	public void setTotalPrice() {
		// Loop through items and add the total prices
		float total_price = 0;
		for(FoodItem item:this.getItems()) {
			total_price += item.getPrice();
		};
		this.total_price = total_price;
	}
	
	// Custom method
	public int countItemQuantity(String item_name) {
		int quantity = 0;
		for (FoodItem item:this.getItems()) {
			if (item.getName().equals(item_name)) {
				quantity++;
			}
		};
		return quantity;
	}

}
