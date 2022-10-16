package boundary;

import java.util.InputMismatchException;
import java.util.Scanner;
import static boundary.AdminMenu.*;
import control.EditMovieList;

public class MovieListingMenu {
	static Scanner sc;
	
	public static void movieListMenu() {
		int choice;
		boolean toggle = true;
		sc = new Scanner(System.in);
		
		do {
			try {
				System.out.println("\n--------------- Edit movie listing ---------------");
				System.out.println("(1) ----------------      Create move listing");
				System.out.println("(2) ----------------      Update move listing");
				System.out.println("(3) ----------------      Remove move listing");
				System.out.println("(4) ----------------      Exit to admin menu");
				
				System.out.printf("\nOption: ");
				
				choice = sc.nextInt();
				
				switch (choice) {
					case 1:
						toggle = false;
						EditMovieList.create();
						break;
					case 2:
						toggle = false;
						EditMovieList.update();
						break;
					case 3:
						toggle = false;
						EditMovieList.remove();
						break;
					case 4:
						toggle = false;
						adminMenu();
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
