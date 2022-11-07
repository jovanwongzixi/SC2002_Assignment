package boundary;
/*
 * No issues.
 */
import java.util.Scanner;
import control.admin.MovieListManager;
import interfaces.Menu;
import interfaces.MovieManager;

public class MovieListEditorMenu implements Menu{
	
	public void start() {
		MovieManager movieListManager = new MovieListManager();
		Scanner sc = new Scanner(System.in);
		int choice;
		
		do {
			System.out.println("\n------------- Movie List Editor Menu -------------");
			System.out.println("(1) ----------------      Add movie listing");
			System.out.println("(2) ----------------      Edit movie showing status");
			System.out.println("(3) ----------------      Remove movie listing");
			System.out.println("(4) ----------------      Return to previous menu");
			System.out.printf("\nOption: ");
			
			while(!sc.hasNextInt()) {
				System.out.println("Invalid input. Please enter an integer!");
				System.out.printf("\nOption: ");
				sc.next();
			}
			
			choice = sc.nextInt();

			switch (choice) {
				case 1 -> {
					movieListManager.add();
					return;
				}
				case 2 -> {
					movieListManager.edit();
					return;
				}
				case 3 -> {
					movieListManager.remove();
					return;
				}
				case 4 -> {
					System.out.println("Returning to previous menu...");
					return;
				}
				default -> System.out.println("Option does not exist! Please input a valid choice!");
			}
		} while (true);
	}
}
