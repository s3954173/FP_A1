package main;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Menu {

	public static void main(String[] args) {
		// Console Menu Start and instantiate menu_input to create While Loop
		char menu_input = 'e';
		ArrayList<Order> orders = new ArrayList<Order>();
		Burrito burrito = new Burrito();
		Fries fries = new Fries();
		Soda soda = new Soda();
		Meal meal = new Meal();

		printTitle();
		while (menu_input != 'd') {
			switch (menuOptions()) {
			// Order
			case 'a':
				Order new_order = orderFood(burrito, fries, soda, meal);
				orders.add(new_order);
				
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
				System.out.println("Please only provide a single character option. a, b, c, d");
			}
		} while (true);

	}

	public static Order orderFood(Burrito burrito, Fries fries, Soda soda, Meal meal) {
		Order order = new Order();
		int user_choice = -1;

		// Move this variable somewhere else
		int cook_fries_counter = 0;

		Scanner menu_input = new Scanner(System.in);

		printOrderOptions();
		do {
			// Take user input and error handling for non-int inputs
			try {
				user_choice = menu_input.nextInt();

				switch (user_choice) {
				case 1:
					order.addItem(burrito);
					break;
				case 2:
					// TODO Fries code for warming tray
					order.addItem(fries);
					cook_fries_counter++;
					break;
				case 3:
					order.addItem(soda);
					break;
				case 4:
					// TODO add meal setter method
					order.addMeal(meal);
					break;
				case 5:
					break;
				default:
					System.out.println("Please provide an option between 1-5");
				}
			} catch (InputMismatchException e) {
				// Handle the case where the input is not an integer
				System.out.println("Please enter a valid integer between 1-5");
				menu_input.next(); // Clear the invalid input from the scanner
			}

		} while (user_choice != 5);
		
		// Finalise Order Details
		order.setTotalPrice();
		order.setTotalPrepTime(cook_fries_counter);
		order.printOrderTotal();
		int money = 0;
		
		// User enters in amount to pay for order
		do {
			try {
				System.out.print("Please enter money: ");
				money = menu_input.nextInt();
				
				// Resolve Order if payable
				if (money >= order.getTotalPrice()) {

					System.out.printf("Change returned $%d\n", (money - order.getTotalPrice()));
					System.out.printf("Time taken: %d minutes\n\n", order.getTotalPrepTime());
					
				}
				// Tell user to provide money enough to pay for order
				else {
					System.out.println("Sorry, that's not enough to pay for order\n");
				}
				
			} catch (InputMismatchException e) {
				// Handle the case where the input is not an integer
				System.out.println("Please enter a valid integer amount");
				menu_input.next(); // Clear the invalid input from the scanner
			}
		} while (money < order.getTotalPrice());

		return order;
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

}
