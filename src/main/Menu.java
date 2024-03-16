package main;

import java.util.Scanner;

public class Menu {

	public static void main(String[] args) {
		// Console Menu Start and instantiate menu_input to create While Loop
		char menu_input = 'e';

		printTitle();
		while (menu_input != 'd') {
			switch (menuOptions()) {
			// Order
			case 'a':
				// TODO Create Order Method + store data from Orders?
				break;
			// Show Sales Report
			case 'b':
				// TODO Create Show Sales Report Method
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
		System.out.printf("%3s\n", "a) Order");
		System.out.printf("%3s\n", "b) Show Sales report");
		System.out.printf("%3s\n", "c) Update prices");
		System.out.printf("%3s\n", "d) Exit");
		System.out.print("Please select: ");

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
	
	
}
