package boundary;

import java.util.Scanner;

import interfaces.Menu;

public class HomeMenu implements Menu{	
	
	public void start() {	
		Scanner sc = new Scanner(System.in);
		int choice;
		
		System.out.println("===================================================");
		System.out.println("  __  __  ____  ____  _      _____ __  __          ");
		System.out.println(" |  \\/  |/ __ \\|  _ \\| |    |_   _|  \\/  |   /\\    ");
		System.out.println(" | \\  / | |  | | |_) | |      | | | \\  / |  /  \\   ");
		System.out.println(" | |\\/| | |  | |  _ <| |      | | | |\\/| | / /\\ \\  ");
		System.out.println(" | |  | | |__| | |_) | |____ _| |_| |  | |/ ____ \\ ");
		System.out.println(" |_|  |_|\\____/|____/|______|_____|_|  |_/_/    \\_\\");
		System.out.println("                                                   ");
		System.out.println("===================================================");
		
		do {
			System.out.println("\n-------------------- Main Menu -------------------");
			System.out.println("(1) ----------------      Admin access");
			System.out.println("(2) ----------------      Customer access");
			System.out.println("(3) ----------------      Quit application");
			System.out.printf("\nOption: ");
			
			while(!sc.hasNextInt()) {
				System.out.println("Invalid input. Please enter an integer!");
				System.out.printf("\nOption: ");
				sc.next();
			}
			
			choice = sc.nextInt();
			
			switch(choice) {
				case 1:
					AdminMenu staffMenu = new AdminMenu();
					staffMenu.start();
					break;
				case 2:
					CustomerMenu moviegoerMenu = new CustomerMenu();
					moviegoerMenu.start();
					break;
				case 3:
					System.out.println("Thank you for using MOBLIMA! Have a nice day!");
					return;
				default:
					System.out.println("Option does not exist! Please input a valid choice!");
			}
		} while (true);
	}
}
