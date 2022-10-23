package boundary;

import java.util.*;

public class HomeUI {
		
	public static void run() {
		
		int choice;
		boolean toggle = true;
		@SuppressWarnings("resource")
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
				
				switch(choice) {
					case 1:
						toggle = false;
						AdminUI.run();
						break;
					case 2:
						toggle = false;
						CustomerUI.run();
						break;
					case 3:
						toggle = false;
						System.out.println("Thank you for using MOBLIMA! Have a nice day!");
						System.exit(0);
					default:
						System.out.println("The option does not exist. Please choose a valid option!\n");
				}				
			} catch (InputMismatchException ex) {
				sc.nextLine();
				System.out.println("Invalid input. Please choose a valid option!\n");
			}
		} while (toggle);
	}
}
