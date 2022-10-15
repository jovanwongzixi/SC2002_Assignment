package boundary;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainMenu {

	public static void main(String[] args) {
		int choice;
		boolean toggle = true;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("===================================================");
		System.out.println("  __  __  ____  ____  _      _____ __  __          ");
		System.out.println(" |  \\/  |/ __ \\|  _ \\| |    |_   _|  \\/  |   /\\    ");
		System.out.println(" | \\  / | |  | | |_) | |      | | | \\  / |  /  \\   ");
		System.out.println(" | |\\/| | |  | |  _ <| |      | | | |\\/| | / /\\ \\  ");
		System.out.println(" | |  | | |__| | |_) | |____ _| |_| |  | |/ ____ \\ ");
		System.out.println(" |_|  |_|\\____/|____/|______|_____|_|  |_/_/    \\_\\");
		System.out.println("                                                   ");
		System.out.println("===================================================");
		System.out.println("");
		
		do {
			try {
				System.out.println("(1) ----------------      Admin access");
				System.out.println("(2) ----------------      Customer access");
				System.out.println("(3) ----------------      Quit application");
				System.out.printf("\nOption: ");
						
				choice = sc.nextInt();
			
				switch (choice) {
					case 1:
						AdminMenu.main(args);
						toggle = false;
						sc.close();
						break;
					case 2:
						CustomerMenu.main(args);
						toggle = false;
						sc.close();
						break;
					case 3:
						System.out.println("App terminated!");
						toggle = false;
						sc.close();
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
