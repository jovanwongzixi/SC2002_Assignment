package control.customer;

import java.util.*;
import boundary.CustomerUI;
import control.Displayer;

public class CustomerCommands {
	
	@SuppressWarnings("resource")
	public static void showMovieList() {
		Scanner sc = new Scanner(System.in);
		Displayer displayer = new Displayer();
		int index, choice;
		boolean toggle = true;
		
		do {
			try {
				index = displayer.displayMovieList();
				System.out.println();
				System.out.printf("Input option number to view movie details (-1 to return to customer menu): ");
				
				choice = sc.nextInt();
				
				if(choice > index) {
					System.out.println("Option does not exist. Please key in a valid option!\n");
				} else if (choice == -1) {
					toggle = false;
					System.out.println("Returning to customer menu...");
					CustomerUI.run();
				} else {
					toggle = false;
					viewMovieDetails(choice);
				}
			} catch (InputMismatchException ex) {
				sc.nextLine();
				System.out.println("Invalid input. Please choose a valid option!\n");
			}
		} while (toggle);
	}
	
	@SuppressWarnings("resource")
	public static void viewMovieDetails(int choice) {
		Scanner sc = new Scanner(System.in);
		Displayer displayer = new Displayer();
		displayer.displayMovieDetails(choice-1);
		
		System.out.println("Press Enter to return to movie list...");
		if(sc.nextLine()!= null) {
			showMovieList();
		}
	}
}
