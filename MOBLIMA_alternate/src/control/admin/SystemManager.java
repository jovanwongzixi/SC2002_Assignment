package control.admin;

import java.time.LocalDate;
import java.util.*;
import control.SerializeDB;
import control.customer.TopFiveMovieDisplayer;
import control.datahandler.*;
import entity.Flag;
import entity.Holiday;
import entity.movie.Movie;
import entity.movie.TicketPrice;
import interfaces.DataHandler;
import interfaces.Displayer;

public class SystemManager {
	
	public void editHolidays() {
		DateTimeManager datetimeManager = new DateTimeManager();
		DataHandler holidayDataHandler = new HolidayDataHandler();
		List<Holiday> holidays = holidayDataHandler.retrieve();
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

			switch (choice) {
				case 1 -> {
					LocalDate holidayDate = datetimeManager.addDate();
					Holiday holiday = new Holiday();
					holiday.setDate(holidayDate);
					holidays.add(holiday);
					holidayDataHandler.save(holidays);
					System.out.println("Holiday added! Returning to system config...");
					return;
				}
				case 2 -> {
					System.out.println("Returning to system config...");
					return;
				}
				default -> System.out.println("Option does not exist. Please key in a valid option!\n");
			}
		} while (true);
	}
	
	public void editPrices() {
		int choice;
		Scanner sc = new Scanner(System.in);
		DataHandler ticketPriceDataHandler = new TicketPriceDataHandler();
		List<TicketPrice> ticketPrices = ticketPriceDataHandler.retrieve();
		Displayer ticketPriceDisplayer = new TicketPriceDisplayer(ticketPrices);
		do {
			ticketPriceDisplayer.display();

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
				ticketPrices.get(choice-1).setPrice(newPrice);
				ticketPriceDataHandler.save(ticketPrices);
				System.out.println("Price updated!");
			}			
		} while (true);
	}
	
	public void configureTopFive() {
		DataHandler movieDataHandler = new MovieDataHandler();
		List<Movie> movieList = movieDataHandler.retrieve();
		List<Movie> topRatingList = new ArrayList<Movie>();
		List<Movie> topTicketSalesList = new ArrayList<Movie>();
		DataHandler flagDataHandler = new FlagDataHandler();
		List<Flag> sortFlag = flagDataHandler.retrieve();
		Scanner sc = new Scanner(System.in);
		int choice;

		movieList.sort((m1, m2) -> (Double.compare(m2.getOverallRating(), m1.getOverallRating())));
		for (int i = 0; i < 5; i++) {
			topRatingList.add(movieList.get(i));
		}
		
		movieList.sort((m1, m2) -> (m2.getTicketSales() - m1.getTicketSales()));
		for (int i = 0; i < 5; i++) {
			topTicketSalesList.add(movieList.get(i));
		}
		System.out.println("--------------------- Current Top 5 Lists --------------------");
		if (sortFlag.get(0).getFlag()) {
			System.out.println("\n-------------------- Top 5 Movies by Rating -------------------");
			for (int index = 1; index <= 5; index++) {
				System.out.printf("(%d) ----------------	%s (Rating: %.1f)\n", index, topRatingList.get(index-1).getTitle(), topRatingList.get(index-1).getOverallRating());			
			}
		}
		if (sortFlag.get(1).getFlag()) {
			System.out.println("\n-------------------- Top 5 Movies by Ticket Sales -------------------");
			for(int index = 1; index <= 5; index++) {
				System.out.printf("(%d) ----------------	%s (Ticket sales: %d)\n", index, topTicketSalesList.get(index-1).getTitle(), topTicketSalesList.get(index-1).getTicketSales());	
			}
		}

		do {
			if (sortFlag.get(0).getFlag()) {
				System.out.println("\n(1) ----------------      Hide top 5 movies by rating");
			} else {
				System.out.println("\n(1) ----------------      Show top 5 movies by rating");
			}
			if (sortFlag.get(1).getFlag()) {
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

			switch (choice) {
				case 1 -> {
					sortFlag.get(0).inverseFlag();
					flagDataHandler.save(sortFlag);
					System.out.println("Configured!");
				}
				case 2 -> {
					sortFlag.get(1).inverseFlag();
					flagDataHandler.save(sortFlag);
					System.out.println("Configured!");
				}
				case 3 -> {
					System.out.println("Returning to system config...");
					return;
				}
				default -> System.out.println("Option does not exist. Please key in a valid option!\n");
			}
		} while (true);
		
	}
}
