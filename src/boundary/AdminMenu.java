package boundary;

import java.util.InputMismatchException;
import java.util.Scanner;
import static control.AdminCommands.*;

public class AdminMenu {	
	static boolean loggedIn = false;
	static Scanner sc;
		
	public static void adminMenu() {
		int choice;
		boolean toggle = true;
		sc = new Scanner(System.in);
		
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
					System.out.println("(5) ----------------      Return to main menu");
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
							MainMenu.run();
						}
						break;
					case 2:
						if(!loggedIn) {
							System.out.println("Unauthorised access detected! Exiting admin menu...\n");
							toggle = false;
							MainMenu.run();
						} else {
							toggle = false;
							editMovieList();
						}
						break;
					case 3:
						if(!loggedIn) {
							System.out.println("Unauthorised access detected! Exiting admin menu...\n");
							toggle = false;
							MainMenu.run();
						} else {
							toggle = false;
							editCinemaMovie();
						}
						break;
					case 4:
						if(!loggedIn) {
							System.out.println("Unauthorised access detected! Exiting admin menu...\n");
							toggle = false;
							MainMenu.run();
						} else {
							toggle = false;
							systemConfig();
						}
						break;
					case 5:
						if(!loggedIn) {
							System.out.println("Returning to main menu...\n");
							toggle = false;
							MainMenu.run();
						} else {
							System.out.println("Option does not exist. Please key in a valid option!\n");
						}
						break;
					default:
						System.out.println("Option does not exist. Please key in a valid option!\n");
				}
			} catch (InputMismatchException e) {
				sc.nextLine();
				System.out.println("Invalid input. Please make sure to enter an integer value!\n");
			}
			
		} while(toggle);	
	}
}
