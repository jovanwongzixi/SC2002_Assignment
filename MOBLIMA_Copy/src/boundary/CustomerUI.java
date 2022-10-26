package boundary;

import java.util.*;
import static control.customer.CustomerCommands.*;

public class CustomerUI {
	
	public static void run() {
		
		int choice;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		do {
			try {
				System.out.println("\n------------------ Customer Menu -----------------");
				System.out.println("(1) ----------------      Show movie list");			//can use to list top 5 by overall reviews
				System.out.println("(2) ----------------      List Top 5 Movies");
				System.out.println("(3) ----------------      View movie timeslots");
				System.out.println("(4) ----------------      Book tickets");
				System.out.println("(5) ----------------      View booking history");
				System.out.println("(6) ----------------      Enter movie review");
				System.out.println("(7) ----------------      Return to home");
				System.out.printf("\nOption: ");
				
				choice = sc.nextInt();
				
				switch(choice) {
					case 1:
						showMovieList();
						break;
					case 2:
						//viewTopFive();
						break;
					case 3:
						showMovieTimeslot();
						break;
					case 4:
						bookMovieTicket();
						break;
					case 5:
						break;
					case 6:
						inputMovieReview();
						break;
					case 7:
						System.out.println("Returning to main menu...\n");
						return;
					default:
						System.out.println("Option does not exist. Please key in a valid option!\n");
				}				
			} catch (InputMismatchException ex) {
				sc.nextLine();
				System.out.println("Invalid input. Please choose a valid option!\n");
			}
		} while (true);	
	}
}
