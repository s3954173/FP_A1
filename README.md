# FP_A1

# Key Classes
1. Menu (Main)
	- Serves as the console interface the user interacts with to complete the primary functions:
		a. Order food
			- Users can continuously select a food and the quantity to add to the order
			- Users can enter their money amount and will be able to see their order information including the items, total price, change and preparation time.
			- A user's order will be added to a Order ArrayList used for the sales report.
		b. Show sales report
		c. Update prices of food items
		d. Exit the program
	
	- Multiple static methods for different console outputs and user input checks in order to promote reusability and optimisation
	- Warming Tray created as a Queue data structure for Fries
		- Adds/removes Fries objects based upon orders and availability of fries.

2. FoodItem
	- Created as an abstract class with attributes name, price, preparation time and max number a food item can be prepared simultaneously
	- Burrito, Fries and Soda are concrete classes of FoodItem.
	- Fries implements and overrides prepFood() method from the Kitchen interface
		- Static method of prepFries() works to check when to cook fries based on the warming tray's availability of fries
		- Returns a counter used in calculating an order's preparation time
		
		
3. Meal
	- Created as an object containing attributes of a pre-defined array of FoodItems (burrito, fries, soda), discount and total price.
	- Total price calculated by the total value of all items and then apply the discount value
	- Serves as parent class to Order

4. Order extends Meal
	- Created as subclass of Meal. Contains attributes including  ArrayList of FoodItem, ArrayList of Meal, total preparation time, and total price.
	- FoodItem and Meal objects can be dynamically added to their respective ArrayLists.
	- Total preparation time calculated based upon whether preparing burritos or fries takes longer for that order.
	- Additional methods added:
		- countItemQuantity(item_name) helps to count individual FoodItems in the order and includes the meals.
		- printOrderTotal() shows the user what they've ordered and the total cost.
		

# Interfaces
1. Kitchen
	- Currently only has prepFood() method to allow Fries object to have a static prepFries() method
	- Can be expanded upon and implemented in other FoodItem objects in the future

# Validation
- All user inputs have validation and error handling depending on the required input

