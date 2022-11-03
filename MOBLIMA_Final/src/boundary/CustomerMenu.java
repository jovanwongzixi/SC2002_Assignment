package boundary;

import java.util.Scanner;
import control.customer.*;
import interfaces.*;

public class CustomerMenu implements Menu{
	
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
			System.out.println("(7) ----------------      Return to main menu");
			System.out.printf("\nOption: ");
			
			while(!sc.hasNextInt()) {
				System.out.println("Invalid input. Please enter an integer!");
				System.out.printf("\nOption: ");
				sc.next();
			}
			
			choice = sc.nextInt();
			
			switch(choice) {
				case 1:
					Viewer movieListViewer = new MovieListViewer();
					movieListViewer.view();
					break;
				case 2:
					Viewer top5MovieViewer = new TopFiveMovieViewer();
					top5MovieViewer.view();
					break;
				case 3:
					Viewer movieTimeslotViewer = new MovieTimeslotViewer();
					movieTimeslotViewer.view();
					break;
				case 4:
					MovieTicketBooker movieTicketBooker = new MovieTicketBooker();
					movieTicketBooker.start();
					break;
				case 5:
					Viewer bookingHistoryViewer = new BookingHistoryViewer();
					bookingHistoryViewer.view();
					break;
				case 6:
					MovieReviewer movieReviewer = new MovieReviewer();
					movieReviewer.start();
					break;
				case 7:
					System.out.println("Returning to main menu...");
					return;
				default:
					System.out.println("Option does not exist! Please input a valid choice!");
			}				
		} while (true);	
	}
}
