package boundary;

import java.util.*;
import static control.customer.CustomerCommands.*;

public class CustomerUI {
	
	public static void run() {
		
		int choice;
		boolean toggle = true;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		do {
			try {
				System.out.println("\n------------------ Customer Menu -----------------");
				System.out.println("(1) ----------------      Show movie list");			//can use to list top 5 by overall reviews
				System.out.println("(2) ----------------      Search movie");
				System.out.println("(3) ----------------      View movie timeslots");
				System.out.println("(4) ----------------      Book tickets"); //-->select movie->select timeslot->view seats->select seats->tickettypepay...
				//subset of 4 System.out.println("(5) ----------------      Select cinema seating");
				System.out.println("(5) ----------------      View booking history");
				System.out.println("(6) ----------------      Enter movie review");
				System.out.println("(7) ----------------      Return to home");
				System.out.printf("\nOption: ");
				
				choice = sc.nextInt();
				
				switch(choice) {
					case 1:
						toggle = false;
						showMovieList();
						break;
					case 2:
						toggle = false;
						//idk if i want search
						break;
					case 3:
						toggle = false;
						showMovieTimeslot();
						break;
					case 4:
					case 5:
					case 6:
						toggle = false;
						inputMovieReview();
						break;
					case 7:
						System.out.println("Returning to main menu...\n");
						toggle = false;
						HomeUI.run();
						break;
					default:
						System.out.println("Option does not exist. Please key in a valid option!\n");
				}
				
			} catch (InputMismatchException ex) {
				sc.nextLine();
				System.out.println("Invalid input. Please choose a valid option!\n");
			}
		} while (toggle);
		
	}
}
