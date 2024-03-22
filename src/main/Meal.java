package main;

public class Meal {
	private FoodItem[] items;
	private float discount;
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
		this.total_price = (burrito.getPrice() + fries.getPrice() + soda.getPrice()) - getDiscount();
	}
	
	public Meal(float discount) {
		// Instantiate Meal Items
		Burrito burrito = new Burrito();
        Fries fries = new Fries();
        Soda soda = new Soda();
        
        // Set Meal attributes
        this.items = new FoodItem[]{burrito, fries, soda};
		this.discount = discount;
		this.total_price = (burrito.getPrice() + fries.getPrice() + soda.getPrice()) - getDiscount();
	}
	
	// GETTERS
	public FoodItem[] getItems() {
        return this.items;
    } 
	
	public float getDiscount() {
        return this.discount;
    }
	
	public float getTotalPrice() {
        return total_price;
    }
	
	// SETTERS
	public void setDiscount(float discount) {
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
	
	// MUTATOR
	public void updateItem(FoodItem item) {
		if (item instanceof Burrito) {
			this.getItems()[0] = item;
		}
		else if (item instanceof Fries) {
			this.getItems()[1] = item;
		}
		else if (item instanceof Soda) {
			this.getItems()[2] = item;
		}
	}

}
