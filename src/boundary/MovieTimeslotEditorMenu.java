package boundary;

import java.util.Scanner;
import control.admin.MovieTimeslotManager;
import interfaces.Menu;
import interfaces.MovieManager;

public class MovieTimeslotEditorMenu implements Menu{
	
	public void start() {
		MovieManager movieTimeslotManager = new MovieTimeslotManager();
		Scanner sc = new Scanner(System.in);
		int choice;
		
		do {
			System.out.println("\n----------- Movie Timeslot Editor Menu -----------");
			System.out.println("(1) ----------------      Add movie timeslot");
			System.out.println("(2) ----------------      Edit movie timeslot");
			System.out.println("(3) ----------------      Remove movie timeslot");
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
					movieTimeslotManager.add();
					return;
				}
				case 2 -> {
					movieTimeslotManager.edit();
					return;
				}
				case 3 -> {
					movieTimeslotManager.remove();
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
