package boundary;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AdminMenu {
	
	public static void adminMenu() {
		int choice;
		boolean loggedIn = false;
		boolean toggle = true;
		Scanner sc = new Scanner(System.in);
		
		do {
			try {
				System.out.println("\n------------------- Admin Menu -------------------");
				if(!loggedIn) {
					System.out.println("(1) ----------------      Login");
				} else {
					System.out.println("(1) ----------------      Logout");
				}
				System.out.println("(2) ----------------      Edit movie listing");
				System.out.println("(3) ----------------      Edit cinema showtimes");
				System.out.println("(4) ----------------      Configure system settings");
				System.out.printf("\nOption: ");
						
				choice = sc.nextInt();
			
				switch (choice) {
					case 1:
					case 2:
						authenticate(loggedIn);
						sc.close();
						editMovieList();
					case 3:
						authenticate(loggedIn);
						sc.close();
						editCinemaMovie();
					case 4:
						authenticate(loggedIn);
						sc.close();
						systemConfig();
					default:
						System.out.println("Option does not exist. Please key in a valid option!");
				}
			} catch (InputMismatchException e) {
				sc.nextLine();
				System.out.println("Invalid input. Please make sure to enter an integer value!");
			}
			
		} while(toggle);	
	}
	
	private static void authenticate(boolean loggedIn) {
		if(!loggedIn) {
			System.out.println("Unauthorised access detected! Exiting admin menu...\n");
			MainMenu.run();
		}
	}
	
	private static void editMovieList() {
		
	}
	
	private static void editCinemaMovie() {
		
	}
	
	private static void systemConfig() {
	
	}
}
