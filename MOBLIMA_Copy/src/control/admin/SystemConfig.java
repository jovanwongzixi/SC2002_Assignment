package control.admin;

import java.util.*;
import java.time.LocalDate;
import control.SerializeDB;
import entity.movie.TicketPrice;

public class SystemConfig {

	@SuppressWarnings("resource")
	public void editHolidays() {
		DateTimeManipulator datetimeManip = new DateTimeManipulator();
		List<LocalDate> holidays = SerializeDB.getHolidays();
		int choice;
		Scanner sc = new Scanner(System.in);
	
		do {
			try {
				System.out.println("\n(1) ----------------      Add new holiday");
				System.out.println("(2) ----------------      Return to system config");
				System.out.printf("\nOption: ");
				
				choice = sc.nextInt();
			
				switch(choice) {
					case 1:
						LocalDate holidayDate = datetimeManip.addDate();
						holidays.add(holidayDate);
						SerializeDB.writeToHolidayList(holidays);
						System.out.println("Holiday added! Returning to system config...");
						return;
					case 2:
						System.out.println("Returning to system config...");
						return;
					default:
						System.out.println("Option does not exist. Please key in a valid option!\n");	
				}
			} catch (InputMismatchException ex) {
				sc.nextLine();
				System.out.println("Invalid input. Please choose a valid option!\n");
			}
		} while (true);
	}
	
	@SuppressWarnings("resource")
	public void editPrices() {
		int choice;
		Scanner sc = new Scanner(System.in);
		List<TicketPrice> ticketPrices = SerializeDB.getTicketPrices();
		do {
			try {
				System.out.println("\n-------------- Edit Ticket Prices --------------");
				System.out.printf("(1) ----- Group A (Fri-Sun, PH for Platinum Members):\t\t%.2f\n", ticketPrices.get(0).getPrice());
				System.out.printf("(2) ----- Group B (Fri-Sun, PH for Regular Movies):\t\t%.2f\n", ticketPrices.get(1).getPrice());
				System.out.printf("(3) ----- Group C (Fri-Sun, PH for 3D Movies):\t\t\t%.2f\n", ticketPrices.get(2).getPrice());
				System.out.printf("(4) ----- Group D (Mon-Thu for Platinum Members):\t\t%.2f\n", ticketPrices.get(3).getPrice());
				System.out.printf("(5) ----- Group E (Mon-Thu for Child - Regular Movies):\t\t%.2f\n", ticketPrices.get(4).getPrice());
				System.out.printf("(6) ----- Group F (Mon-Thu for Child - 3D):\t\t\t%.2f\n", ticketPrices.get(5).getPrice());
				System.out.printf("(7) ----- Group G (Mon-Thu for Adult - Regular Movies):\t\t%.2f\n", ticketPrices.get(6).getPrice());
				System.out.printf("(8) ----- Group H (Mon-Thu for Adult - 3D):\t\t\t%.2f\n", ticketPrices.get(7).getPrice());
				System.out.printf("(9) ----- Group I (Mon-Thu for Senior Citizens):\t\t%.2f\n", ticketPrices.get(8).getPrice());
				System.out.printf("(10) ----- Blockbuster Quantum:\t\t\t\t\t%.2f\n", ticketPrices.get(9).getPrice());
				System.out.printf("(11) ----- Return to system config\n");				
				System.out.printf("\nOption: ");
				
				choice = sc.nextInt();
				
				if (choice > 11 || choice < 1) {
					System.out.println("Option does not exist. Please key in a valid option!\n");	
				} else if (choice == 11) {
					System.out.println("Returning to system config...");
					return;
				} else {
					if (choice < 10) {
						System.out.printf("Input new price for Group %c: ",choice-1+'A');
					} else {
						System.out.printf("Input new blockbuster quantum: ");
					}
					while(!sc.hasNextDouble()) {
						System.out.println("Invalid input. Please enter a number!");
						sc.next();
					}
					double newPrice = sc.nextDouble();
					ticketPrices.get(choice-1).setPrice(newPrice);
					SerializeDB.writeToTicketPrices(ticketPrices);
					System.out.println("Price updated!");
					
				}			
			} catch (InputMismatchException ex) {
				sc.nextLine();
				System.out.println("Invalid input. Please choose a valid option!\n");
			}
		} while (true);
	}
}
