package main;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;

public class Menu {

	public static void main(String[] args) {
		// Instantiate FoodItem, Meal, Orders and Warming Tray objects
		char menu_input = 'e';
		ArrayList<Order> orders = new ArrayList<Order>();
		FoodItem burrito = new Burrito();
		FoodItem fries = new Fries();
		FoodItem soda = new Soda();
		Meal meal = new Meal();
		Queue<FoodItem> warming_tray = new LinkedList<FoodItem>();
		
		// Console Menu Start and instantiate menu_input to create While Loop
		printTitle();
		while (menu_input != 'd') {
			switch (menuOptions()) {
			// Order
			case 'a':
				orders.add(orderFood(warming_tray, burrito, fries, soda, meal));
				System.out.println(orders);
				break;
			// Show Sales Report
			case 'b':
				// TODO Create Show Sales Report Method
				System.exit(0);
				break;
			// Update prices
			case 'c':
				// TODO Create Update Prices Method
				break;
			// Exit (End Program)
			case 'd':
				System.out.println("Thank you for using Burrito King!");
				System.exit(0);
			}
		}

	}

	// Static Methods for Console print outs
	public static void printTitle() {
		System.out.println("=".repeat(20));
		System.out.println("Burrito King");
		System.out.println("=".repeat(20));
	}

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

	public static Order orderFood(Queue<FoodItem> warming_tray, FoodItem burrito, FoodItem fries, FoodItem soda, Meal meal) {


		Order order = new Order();
		int user_choice = -1;

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

					break;
				case 3:
					food_quantity = chooseFoodAmount(soda.getName());
					for (int i = 0; i < food_quantity; i++) {
						order.addItem(soda);
					}
					break;
				case 4:
					food_quantity = chooseFoodAmount("Meal");
					
					// Cook new fries if warming tray doesn't have enough
					cook_fries_counter += Fries.prepFries(fries, warming_tray, order, food_quantity);
					System.out.println("Counter: " + cook_fries_counter);

					// Add meal to order and remove fries from warming tray
					for (int i = 0; i < food_quantity; i++) {
			            warming_tray.remove(fries);
						order.addMeal(meal);
					}
					System.out.printf("%d serves of fries left for next order\n", warming_tray.size());

					break;
				case 5:
					break;
				default:
					System.out.println("Please provide an option between 1-5");
				}
			} catch (InputMismatchException e) {
				// Handle the case where the input is not an integer
				System.out.println("Please enter a valid integer");
				menu_input.next(); // Clear the invalid input from the scanner
			}

		} while (user_choice != 5);

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

	public static void printMenuOptions() {
		System.out.printf("%3s\n", "a) Order");
		System.out.printf("%3s\n", "b) Show Sales report");
		System.out.printf("%3s\n", "c) Update prices");
		System.out.printf("%3s\n", "d) Exit");
		System.out.print("Please select: ");
	}

	public static void printOrderOptions() {
		System.out.println("> Select the food item");
		System.out.printf("%3s\n", "1. Burrito");
		System.out.printf("%3s\n", "2. Fries");
		System.out.printf("%3s\n", "3. Soda");
		System.out.printf("%3s\n", "4. Meal");
		System.out.printf("%3s\n", "5. No More");
		System.out.print("Please select: ");
	}
	
//	public static int prepFries(FoodItem fries, Queue<FoodItem> warming_tray, Order order, int food_quantity) {
//		int current_cook_counter = 0;
//		
//		// Check if warming tray has enough fries
//		if (food_quantity > warming_tray.size()) {
//			// Cook Fries and tally the amount of times fries need to be cooked
//			current_cook_counter = (int) Math.ceil((double) food_quantity / 5);
//			int fries_batch = current_cook_counter * 5;
//			for (int i = 0; i < fries_batch; i++) {
//				warming_tray.add(fries);
//			}
//			
//			//Print out Cooking Fries
//			System.out.println("Cooking fries; please be patient");
//			System.out.printf("%d serves of fries left for next order\n", (warming_tray.size() - food_quantity));
//		}
//
//		return current_cook_counter;
//	};

}
