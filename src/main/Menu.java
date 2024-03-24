package main;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;

public class Menu {

	public static void main(String[] args) {
		// Instantiate FoodItem, Meal, Orders and Warming Tray objects
		char menu_input = 'e';
		FoodItem burrito = new Burrito();
		FoodItem fries = new Fries();
		FoodItem soda = new Soda();
		Meal meal = new Meal();
		Queue<FoodItem> warming_tray = new LinkedList<FoodItem>();
		SalesReport sales_report = new SalesReport();

		// Console Menu Start and instantiate menu_input to create While Loop
		printTitle();
		while (menu_input != 'd') {
			switch (menuOptions()) {
			// Order
			case 'a':
				sales_report.addOrder(orderFood(warming_tray, burrito, fries, soda, meal));
				break;
			// Show Sales Report
			case 'b':

				sales_report.printSalesReport(warming_tray.size());
				System.exit(0);
				break;
			// Update prices
			case 'c':
				Object updated_item = updatePrice(burrito, fries, soda, meal);
				
				if (updated_item instanceof FoodItem) {
					Meal new_meal = new Meal();
					if (updated_item instanceof Burrito) {
						burrito = (FoodItem) updated_item;
						new_meal.updateItem(burrito);
					}
					else if (updated_item instanceof Fries){
						fries = (FoodItem) updated_item;
						new_meal.updateItem(fries);

					}
					else if (updated_item instanceof Soda){
						soda = (FoodItem) updated_item;
						new_meal.updateItem(soda);
					}
					new_meal.setTotalPrice();
					meal = new_meal;
					System.out.printf("The unit price of Meals has also been updated in response.\n New meal price is: $%.2f\n", new_meal.getTotalPrice());
				}
				
				else if (updated_item instanceof Meal) {
					meal = (Meal) updated_item;
				}
				break;
			
			// Exit (End Program)
			case 'd':
				System.out.println("Thank you for using Burrito King!");
				System.exit(0);
			}
		}

	}

	// Static Methods for Console print outs

	public static char menuOptions() {
		// Create Scanner Object and show menu Options
		Scanner menu_input = new Scanner(System.in);
		printMenuOptions();

		// Infinite loop to convert String to char upon valid input + error handling
		do {
			// Grab user menu input choice
			String user_choice = menu_input.next();
			menu_input.nextLine();

			// Check input is single character and is options a,b,c,d
			if (user_choice.length() == 1) {
				switch (user_choice) {
				case "a", "b", "c", "d":
					char choice_char = user_choice.charAt(0);
					return choice_char;
				default:
					System.out.println("Please provide one of the options. a, b, c, d");
					break;
				}
			} else {
				System.out.print("Please only provide a single character option. a, b, c, d");
			}

		} while (true);

	}

	public static Order orderFood(Queue<FoodItem> warming_tray, FoodItem burrito, FoodItem fries, FoodItem soda,
			Meal meal) {

		Order order = new Order();
		int user_choice = -1;
		boolean has_ordered = false;

		// Move this variable somewhere else
		int cook_fries_counter = 0;

		Scanner menu_input = new Scanner(System.in);

		do {
			printOrderOptions();
			// Take user input and error handling for non-int inputs
			try {
				user_choice = menu_input.nextInt();
				int food_quantity = 0;
				// TODO Optimise code
				switch (user_choice) {
				case 1:
					food_quantity = chooseFoodAmount(burrito.getName());
					for (int i = 0; i < food_quantity; i++) {
						order.addItem(burrito);
					}
					has_ordered = true;

					break;
				case 2:
					food_quantity = chooseFoodAmount(fries.getName());

					// Will either cook new fries or grab fries the warming tray
					cook_fries_counter += Fries.prepFries(fries, warming_tray, order, food_quantity);

					// Add fries from warming_tray
					for (int i = 0; i < food_quantity; i++) {
						warming_tray.remove(fries);
						order.addItem(fries);
					}
					System.out.printf("%d serves of fries left for next order\n", warming_tray.size());
					has_ordered = true;

					break;
				case 3:
					food_quantity = chooseFoodAmount(soda.getName());
					for (int i = 0; i < food_quantity; i++) {
						order.addItem(soda);
					}
					has_ordered = true;

					break;
				case 4:
					food_quantity = chooseFoodAmount("Meal");

					// Cook new fries if warming tray doesn't have enough
					cook_fries_counter += Fries.prepFries(fries, warming_tray, order, food_quantity);

					// Add meal to order and remove fries from warming tray
					for (int i = 0; i < food_quantity; i++) {
						warming_tray.remove(fries);
						order.addMeal(meal);
					}
					System.out.printf("%d serves of fries left for next order\n\n", warming_tray.size());
					has_ordered = true;

					break;
				case 5:
					if (!has_ordered) {
						System.out.println("Please order at least 1 item.");
					}
					break;
				default:
					System.out.println("Please provide an option between 1-5");
				}
			} catch (InputMismatchException e) {
				// Handle the case where the input is not an integer
				System.out.println("Please enter a valid integer");
				menu_input.next(); // Clear the invalid input from the scanner
			}
			
			System.out.println();

		} while (user_choice != 5 && !has_ordered);

		// Finalise Order Details
		order.setTotalPrice();
		order.setTotalPrepTime(cook_fries_counter, burrito, fries);
		order.printOrderTotal();
		float money = 0f;

		// User enters in amount to pay for order
		do {
			try {
				System.out.print("Please enter money: ");
				money = menu_input.nextFloat();

				// Resolve Order if payable
				if (money >= order.getTotalPrice()) {

					System.out.printf("Change returned $%.2f\n", (money - order.getTotalPrice()));
					System.out.printf("Time taken: %d minutes\n\n", order.getTotalPrepTime());

				}
				// Tell user to provide money enough to pay for order
				else {
					System.out.println("Sorry, that's not enough to pay for order\n");
				}

			} catch (InputMismatchException e) {
				// Handle the case where the input is not a number
				System.out.println("Please enter a valid dollar amount");
				menu_input.next(); // Clear the invalid input from the scanner
			}
		} while (money < order.getTotalPrice());

		return order;
	}

	public static int chooseFoodAmount(String name) {
		Scanner quantity_input = new Scanner(System.in);
		int quantity = 0;
		do {
			switch (name) {
			case "Fries":
				System.out.print("\nHow many serves of fries would you like to buy:");
				break;
			default:
				System.out.printf("\nHow many %ss would you like to buy: ", name);
				break;
			}

			try {
				quantity = quantity_input.nextInt();
				if (quantity < 1) {
					System.out.println("Please enter a valid quantity of 1 or more");
				}
			} catch (InputMismatchException e) {
				// Handle the case where the input is not an integer
				System.out.println("Please enter a valid integer amount");
				quantity_input.next(); // Clear the invalid input from the scanner
			}

		} while (quantity < 1);

		return quantity;
	}

	// Menu Options Print Methods
	public static void printTitle() {
		System.out.println("=".repeat(20));
		System.out.println("Burrito King");
		System.out.println("=".repeat(20));
	}

	public static void printMenuOptions() {
		System.out.println("");
		System.out.printf("%-3s\n", "a) Order");
		System.out.printf("%-3s\n", "b) Show Sales report");
		System.out.printf("%-3s\n", "c) Update prices");
		System.out.printf("%-3s\n", "d) Exit");
		System.out.print("Please select: ");
	}

	public static void printOrderOptions() {
		System.out.println("");
		System.out.println("> Select the food item");
		System.out.printf("%-3s\n", "1. Burrito");
		System.out.printf("%-3s\n", "2. Fries");
		System.out.printf("%-3s\n", "3. Soda");
		System.out.printf("%-3s\n", "4. Meal");
		System.out.printf("%-3s\n", "5. No More");
		System.out.print("Please select: ");
	}

	public static void printUpdateOptions() {
		System.out.println("> Select the food item to update the price");
		System.out.printf("%-3s\n", "1. Burrito");
		System.out.printf("%-3s\n", "2. Fries");
		System.out.printf("%-3s\n", "3. Soda");
		System.out.printf("%-3s\n", "4. Meal");
		System.out.printf("%-3s\n", "5. Exit");
		System.out.print("Please select: ");
	}

	public static Object updatePrice(FoodItem burrito, FoodItem fries, FoodItem soda, Meal meal) {
		// TODO: Remove new Object()
//		Object updated_item = new Object();

		int user_choice = -1;

		Scanner menu_input = new Scanner(System.in);
		printUpdateOptions();

		do {
			// Take user input and error handling for non-int inputs
			try {
				user_choice = menu_input.nextInt();
				// TODO Optimise code
				switch (user_choice) {
				case 1:
					System.out.print("Please enter new price: ");
					FoodItem updated_burrito = new Burrito(menu_input.nextFloat());
					System.out.printf("The unit price of burrito is updated to $%.2f\n", updated_burrito.getPrice());
					return updated_burrito;

				case 2:
					System.out.print("Please enter new price: ");
					FoodItem updated_fries = new Fries(menu_input.nextFloat());
					System.out.printf("The unit price of fries is updated to $%.2f\n", updated_fries.getPrice());
					return updated_fries;

				case 3:
					System.out.print("Please enter new price: ");
					FoodItem updated_soda = new Soda(menu_input.nextFloat());
					System.out.printf("The unit price of soda is updated to $%.2f\n", updated_soda.getPrice());
					return updated_soda;
					
				case 4:
					System.out.print("Please enter new discount amount to be applied: ");
					Meal updated_meal = new Meal(menu_input.nextFloat());
					System.out.printf("The discount price of meal is updated to $%.2f\n", updated_meal.getDiscount());
					return updated_meal;
					
				case 5:
					return null;
					
				default:
					System.out.println("Please provide an option between 1-5");
					break;
				}
			} catch (InputMismatchException e) {
				// Handle the case where the input is not an integer
				System.out.println("Please enter a valid integer");
				menu_input.next(); // Clear the invalid input from the scanner
			}

		} while ((user_choice < 1) && (user_choice > 5));
		
		return null;

	}


}
