package control.admin;

import java.time.LocalDate;
import java.util.*;
import control.SerializeDB;
import entity.movie.Movie;

public class SystemManager {
	
	public void editHolidays() {
		DateTimeManager datetimeManager = new DateTimeManager();
		List<LocalDate> holidays = SerializeDB.getHolidays();
		int choice;
		Scanner sc = new Scanner(System.in);
	
		do {
			System.out.println("\n(1) ----------------      Add new holiday");
			System.out.println("(2) ----------------      Return to system config");
			System.out.printf("\nOption: ");
				
			while(!sc.hasNextInt()) {
				System.out.println("Invalid input. Please enter an integer!");
				sc.next();
			}
				
			choice = sc.nextInt();
			
			switch(choice) {
				case 1:
					LocalDate holidayDate = datetimeManager.addDate();
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
		} while (true);
	}
	
	public void editPrices() {
		int choice;
		Scanner sc = new Scanner(System.in);
		List<Double> ticketPrices = SerializeDB.getTicketPrices();
		
		do {
			System.out.println("\n-------------- Edit Ticket Prices --------------");
			System.out.printf("(1) ----- Group A (Fri-Sun, PH for Platinum Members):\t\t%.2f\n", ticketPrices.get(0));
			System.out.printf("(2) ----- Group B (Fri-Sun, PH for Regular Movies):\t\t%.2f\n", ticketPrices.get(1));
			System.out.printf("(3) ----- Group C (Fri-Sun, PH for 3D Movies):\t\t\t%.2f\n", ticketPrices.get(2));
			System.out.printf("(4) ----- Group D (Mon-Thu for Platinum Members):\t\t%.2f\n", ticketPrices.get(3));
			System.out.printf("(5) ----- Group E (Mon-Thu for Child - Regular Movies):\t\t%.2f\n", ticketPrices.get(4));
			System.out.printf("(6) ----- Group F (Mon-Thu for Child - 3D):\t\t\t%.2f\n", ticketPrices.get(5));
			System.out.printf("(7) ----- Group G (Mon-Thu for Adult - Regular Movies):\t\t%.2f\n", ticketPrices.get(6));
			System.out.printf("(8) ----- Group H (Mon-Thu for Adult - 3D):\t\t\t%.2f\n", ticketPrices.get(7));
			System.out.printf("(9) ----- Group I (Mon-Thu for Senior Citizens):\t\t%.2f\n", ticketPrices.get(8));
			System.out.printf("(10) ----- Blockbuster Quantum:\t\t\t\t\t%.2f\n", ticketPrices.get(9));
			System.out.printf("(11) ----- Return to system config\n");				
			System.out.printf("\nOption: ");
				
			while(!sc.hasNextInt()) {
				System.out.println("Invalid input. Please enter an integer!");
				sc.next();
			}
			
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
				ticketPrices.set(choice-1, newPrice);
				SerializeDB.writeToTicketPrices(ticketPrices);
				System.out.println("Price updated!");			
			}			
		} while (true);
	}
	
	public void configureTopFive() {
		List<Movie> movieList = SerializeDB.getMovieList();
		List<Movie> topRatingList = new ArrayList<Movie>();
		List<Movie> topTicketSalesList = new ArrayList<Movie>();
		List<Boolean> sortFlag = SerializeDB.getFlags();
		Scanner sc = new Scanner(System.in);
		int choice;
		
		Collections.sort(movieList, (m1, m2) -> (Double.compare(m2.getOverallRating(), m1.getOverallRating())));
		for (int i = 0; i < 5; i++) {
			topRatingList.add(movieList.get(i));
		}
		
		Collections.sort(movieList, (m1, m2) -> (m2.getTicketSales() - m1.getTicketSales()));
		for (int i = 0; i < 5; i++) {
			topTicketSalesList.add(movieList.get(i));
		}
		System.out.println("--------------------- Current Top 5 Lists --------------------");
		if (sortFlag.get(0)) {
			System.out.println("\n-------------------- Top 5 Movies by Rating -------------------");
			for (int index = 1; index <= 5; index++) {
				System.out.printf("(%d) ----------------	%s (Rating: %.1f)\n", index, topRatingList.get(index-1).getTitle(), topRatingList.get(index-1).getOverallRating());			
			}
		}
		if (sortFlag.get(1)) {
			System.out.println("\n-------------------- Top 5 Movies by Ticket Sales -------------------");
			for(int index = 1; index <= 5; index++) {
				System.out.printf("(%d) ----------------	%s (Ticket sales: %d)\n", index, topTicketSalesList.get(index-1).getTitle(), topTicketSalesList.get(index-1).getTicketSales());	
			}
		}

		do {
			if (sortFlag.get(0)) {
				System.out.println("\n(1) ----------------      Hide top 5 movies by rating");
			} else {
				System.out.println("\n(1) ----------------      Show top 5 movies by rating");
			}
			if (sortFlag.get(1)) {
				System.out.println("(2) ----------------      Hide top 5 movies by ticket sales");
			} else {
				System.out.println("(2) ----------------      Show top 5 movies by ticket sales");
			}
			System.out.println("(3) ----------------      Return to system config");
			System.out.printf("\nOption: ");
				
			while(!sc.hasNextInt()) {
				System.out.println("Invalid input. Please enter an integer!");
				sc.next();
			}
				
			choice = sc.nextInt();
			
			switch(choice) {
				case 1:
					sortFlag.set(0, !sortFlag.get(0));
					SerializeDB.writeToFlag(sortFlag);
					System.out.println("Configured!");
					break;
				case 2:
					sortFlag.set(1, !sortFlag.get(1));
					SerializeDB.writeToFlag(sortFlag);
					System.out.println("Configured!");
					break;
				case 3:
					System.out.println("Returning to system config...");
					return;
				default:
					System.out.println("Option does not exist. Please key in a valid option!\n");	
			}
		} while (true);
		
	}
}
