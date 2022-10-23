package boundary;

import java.util.*;
import static control.admin.AdminCommands.*;

public class AdminUI {
	
	static boolean loggedIn = false;
	
	public static void run() {
		
		int choice;
		boolean toggle = true;
		//boolean loggedIn = false;
		@SuppressWarnings("resource")
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
				if(!loggedIn) {
					System.out.println("(5) ----------------      Return to home");
				}
				System.out.printf("\nOption: ");
						
				choice = sc.nextInt();
				
				switch (choice) {
					case 1:
						if(!loggedIn) {
							System.out.printf("\nEnter user ID: ");
							String userid = sc.next();
							System.out.printf("Enter password: ");
							String password = sc.next();
					
							loggedIn = login(userid, password);
						} else {
							System.out.println("Successfully logged out! Returning to main menu...\n");
							loggedIn = false;
							toggle = false;
							HomeUI.run();
					}
					break;
				case 2:
					if(!loggedIn) {
						System.out.println("Unauthorised access detected! Exiting admin menu...\n");
						toggle = false;
						HomeUI.run();
					} else {
						toggle = false;
						editMovieList();
					}
					break;
				case 3:
					if(!loggedIn) {
						System.out.println("Unauthorised access detected! Exiting admin menu...\n");
						toggle = false;
						HomeUI.run();
					} else {
						toggle = false;
						editCinemaShowtime();
					}
					break;
				case 4:
					if(!loggedIn) {
						System.out.println("Unauthorised access detected! Exiting admin menu...\n");
						toggle = false;
						HomeUI.run();
					} else {
						toggle = false;
						//go to system config
					}
					break;
				case 5:
					if(!loggedIn) {
						System.out.println("Returning to main menu...\n");
						toggle = false;
						HomeUI.run();
					} else {
						System.out.println("Option does not exist. Please key in a valid option!\n");
					}
					break;
				default:
					System.out.println("Option does not exist. Please key in a valid option!\n");
			}
				
				
			} catch (InputMismatchException ex){
				sc.nextLine();
				System.out.println("Invalid input. Please choose a valid option!\n");
			}
		} while (toggle);
	}
}
