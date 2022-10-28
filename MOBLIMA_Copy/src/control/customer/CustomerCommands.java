package control.customer;

import java.util.*;
import control.*;

public class CustomerCommands {
	
	@SuppressWarnings("resource")
	public static void showMovieList() {
		Scanner sc = new Scanner(System.in);
		Displayer displayer = new Displayer();
		int index, choice;
		
		do {
			try {
				index = displayer.displayMovieList();
				System.out.println();
				System.out.printf("Input option number to view movie details (-1 to return to customer menu): ");
				
				choice = sc.nextInt();
				
				if(choice > index) {
					System.out.println("Option does not exist. Please key in a valid option!\n");
				} else if (choice == -1) {
					System.out.println("Returning to customer menu...");
					return;
				} else {
					MovieController.viewMovieDetails(choice-1);
				}
			} catch (InputMismatchException ex) {
				sc.nextLine();
				System.out.println("Invalid input. Please choose a valid option!\n");
			}
		} while (true);
	}
	
	@SuppressWarnings("resource")
	public static void showMovieTimeslot() {
		Scanner sc = new Scanner(System.in);
		Displayer displayer = new Displayer();
		int index, choice;
		
		do {
			try {
				index = displayer.displayMovieList();
				System.out.println();
				System.out.printf("Input option number to view available timeslots (-1 to return to customer menu): ");
				
				choice = sc.nextInt();
				
				if(choice > index) {
					System.out.println("Option does not exist. Please key in a valid option!\n");
				} else if (choice == -1) {
					System.out.println("Returning to customer menu...");
					return;
				} else {
					MovieController.viewSpecificTimeslot(choice-1);	
				}
			} catch (InputMismatchException ex) {
				sc.nextLine();
				System.out.println("Invalid input. Please choose a valid option!\n");
			}
		} while (true);
	}
	
	@SuppressWarnings("resource")
	public static void bookMovieTicket() {
		Scanner sc = new Scanner(System.in);
		Displayer displayer = new Displayer();
		int index, choice;
		
		do {
			try {
				index = displayer.displayBookingMovieList();
				System.out.println();
				System.out.printf("Input option number to view available timeslots (-1 to return to customer menu): ");
				
				choice = sc.nextInt();
				
				if(choice > index) {
					System.out.println("Option does not exist. Please key in a valid option!\n");
				} else if (choice == -1) {
					System.out.println("Returning to customer menu...");
					return;
				} else {
					MovieController.bookSpecificTimeslot(choice-1);	
				}
			} catch (InputMismatchException ex) {
				sc.nextLine();
				System.out.println("Invalid input. Please choose a valid option!\n");
			}
		} while (true);
	}
	
	@SuppressWarnings("resource")
	public static void inputMovieReview() {
		Scanner sc = new Scanner(System.in);
		Displayer displayer = new Displayer();
		int index, choice;
		
		do {
			try {
				index = displayer.displayMovieList();				
				System.out.println();
				System.out.printf("Choose movie to review (-1 to return to customer menu): ");
				
				choice = sc.nextInt();
				
				if(choice > index) {
					System.out.println("Option does not exist. Please key in a valid option!\n");
				} else if (choice == -1) {
					System.out.println("Returning to customer menu...");
					return;
				} else {
					MovieController.enterReview(choice-1);
					break;
				}
			} catch  (InputMismatchException ex) {
				sc.nextLine();
				System.out.println("Invalid input. Please choose a valid option!\n");
			}
		} while (true);		
	}
}
