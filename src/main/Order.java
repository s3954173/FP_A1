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
	public void setTotalPrepTime() {
		
	}
	
	public void setTotalPrice() {
		// Loop through items and add the total prices
		float total_price = 0;
		for(FoodItem item:this.getItems()) {
			total_price += item.getPrice();
		}
		this.total_price = total_price;
	}

}
