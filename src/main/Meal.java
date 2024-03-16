package main;

public class Meal {
	private FoodItem[] items;
	private int discount;
	private float total_price;
	
	//CONSTRUCTOR
	public Meal() {
		// Instantiate Meal Items
		Burrito burrito = new Burrito();
        Fries fries = new Fries();
        Soda soda = new Soda();
        
        // Set Meal attributes
        this.items = new FoodItem[]{burrito, fries, soda};
		this.discount = 3;
		this.total_price = 10.5f;
	}
	
	// TODO: Create 2nd Constructor??
	
	// GETTERS
	public FoodItem[] getItems() {
        return this.items;
    } 
	
	public int getDiscount() {
        return this.discount;
    }
	
	public float getTotalPrice() {
        return total_price;
    }
	
	// SETTERS
	// TODO Add setter for items???
	
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	
	public void setTotalPrice() {
		// Loop through items, add total amount and apply discount
		float total_price = 0;
		
		for(FoodItem item:this.getItems()) {
			total_price += item.getPrice();
		};
		
		this.total_price = total_price - this.getDiscount();
	}

}
