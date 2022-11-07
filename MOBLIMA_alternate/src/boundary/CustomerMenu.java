package boundary;

import java.util.Scanner;
import control.customer.*;
import entity.Customer;
import interfaces.*;

public class CustomerMenu implements Menu{
	//private boolean accountInitialised;
	private Customer customer;
	public CustomerMenu(){
		customer = null;
	}
	public void start() {
		Scanner sc = new Scanner(System.in);
		int choice;
				
		do {
			System.out.println("\n------------------ Customer Menu -----------------");
			System.out.println("(1) ----------------      Show movie list");
			System.out.println("(2) ----------------      List top 5 movies");
			System.out.println("(3) ----------------      View movie timeslots");
			System.out.println("(4) ----------------      Book tickets");
			System.out.println("(5) ----------------      View booking history");
			System.out.println("(6) ----------------      Enter movie review");
			if(customer==null) System.out.println("(7) ----------------      Login");
			else System.out.println("(7) ----------------      Logout");
			System.out.println("(8) ----------------      Return to main menu");
			System.out.printf("\nOption: ");
			
			while(!sc.hasNextInt()) {
				System.out.println("Invalid input. Please enter an integer!");
				System.out.printf("\nOption: ");
				sc.next();
			}
			
			choice = sc.nextInt();
			
			switch(choice) {
				case 1 -> {
					Viewer movieListViewer = new MovieListViewer();
					movieListViewer.view();
				}
				case 2 -> {
					Viewer top5MovieViewer = new TopFiveMovieViewer();
					top5MovieViewer.view();
				}

				case 3 ->{
					Viewer movieTimeslotViewer = new MovieTimeslotViewer();
					movieTimeslotViewer.view();
				}

				case 4 ->{
					AccountManager manager = new CustomerAccountManager();
					while(customer == null){
						customer = manager.start();
						if(customer==null) System.out.println("Failed to initialise account! Try again!");
						else System.out.println("Account intialised!");
					}
					Controller movieTicketBooker = new MovieTicketBooker(customer);
					movieTicketBooker.start();
				}

				case 5 ->{
					Viewer bookingHistoryViewer = new BookingHistoryViewer(customer);
					bookingHistoryViewer.view();
				}

				case 6 ->{
					Controller movieReviewer = new MovieReviewer();
					movieReviewer.start();
				}

				case 7 ->{
					if(customer == null){
						AccountManager manager = new CustomerAccountManager();
						customer = manager.start();
						if(customer==null) System.out.println("Failed to initialise account!");
						else System.out.println("Account intialised!");
					}
					else{
						System.out.println("Account logged out!");
						customer = null;
					}
				}

				case 8 -> {
					System.out.println("Returning to main menu...");
					return;
				}
				default -> System.out.println("Option does not exist! Please input a valid choice!");
			}				
		} while (true);	
	}
}
