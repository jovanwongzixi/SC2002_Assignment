package boundary;

import java.util.Scanner;
import control.admin.SystemManager;
import interfaces.Menu;

public class SystemMenu implements Menu{
	
	public void start() {
		SystemManager systemManager = new SystemManager();
		int choice;
		Scanner sc = new Scanner(System.in);
		
		do {
			System.out.println("\n----------------- System Config ------------------");
			System.out.println("(1) ----------------      Edit holidays");
			System.out.println("(2) ----------------      Edit ticket prices");
			System.out.println("(3) ----------------	  Configure top 5 movies");
			System.out.println("(4) ----------------      Return to previous menu");
			System.out.printf("\nOption: ");
			
			while(!sc.hasNextInt()) {
				System.out.println("Invalid input. Please enter an integer!");
				System.out.printf("\nOption: ");
				sc.next();
			}
			
			choice = sc.nextInt();

			switch (choice) {
				case 1 -> systemManager.editHolidays();
				case 2 -> systemManager.editPrices();
				case 3 -> systemManager.configureTopFive();
				case 4 -> {
					System.out.println("Returning to previous menu...");
					return;
				}
				default -> System.out.println("Option does not exist. Please key in a valid option!\n");
			}
		} while (true);
	}
}
