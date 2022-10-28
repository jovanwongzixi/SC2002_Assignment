package control.customer;

import java.util.*;
import java.util.regex.*;
import java.time.*;
import java.time.format.*;
import control.*;
import entity.Booking;
import entity.cinema.*;
import entity.movie.*;

public class BookingController {
	
	@SuppressWarnings("resource")
	public static void ticketPayment(int showIndex, Showtime s, Seat selectedSeat) {
		Scanner sc = new Scanner(System.in);
		Displayer displayer = new Displayer();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddhhmm");
		List<Showtime> showtimeData = SerializeDB.getShowtimeList();
		List<LocalDate> holidays = SerializeDB.getHolidays();
		List<Booking> bookingData = SerializeDB.getBookingList();
		Ticket showTicket;
		String name, emailAddress, mobileNum;
		AgeType ageType = null;
		int choice;
		boolean toggle = true, isSpecial = true;
		
		displayer.displaySeatLayoutUnserialized(s);
		
		System.out.println("Enter your name: ");
		name = sc.nextLine();
		
		do {
			System.out.println("Enter your email address: ");
			emailAddress = sc.nextLine();
			
			if (!emailMatches(emailAddress)) {
				System.out.println("Invalid email address. Please try again!");
			}
		} while (!emailMatches(emailAddress));
		
		do {
			System.out.println("Enter your mobile number: ");
			mobileNum = sc.nextLine();
			if (!mobileMatches(mobileNum)) {
				System.out.println("Invalid mobile number. Please try again!");
			}
		} while (!mobileMatches(mobileNum));
		
		do {
			try {
				System.out.println("Enter your age group: ");
				System.out.println("(1) ----------------      Child (age 18 and below");
				System.out.println("(2) ----------------      Senior Citizen (age 55 and above)");
				System.out.println("(3) ----------------      Adult");
				
				choice = sc.nextInt();
				
				switch(choice) {
					case 1:
						toggle = false;
						ageType = AgeType.CHILD;
						break;
					case 2:
						toggle = false;
						ageType = AgeType.SENIOR_CITIZEN;
						break;
					case 3:
						toggle = false;
						ageType = AgeType.ADULT;
						break;
					default:
						System.out.println("Option does not exist. Please key in a valid option!\n");			
				}
			} catch(InputMismatchException ex) {
				sc.nextLine();
				System.out.println("Invalid input. Please choose a valid option!\n");
			}
		} while (toggle);
		
		for (LocalDate d : holidays) {
			if (s.getShowDate().equals(d) || s.getShowDate().getDayOfWeek() == DayOfWeek.FRIDAY || s.getShowDate().getDayOfWeek() == DayOfWeek.SATURDAY ||
					s.getShowDate().getDayOfWeek() == DayOfWeek.SUNDAY ) {
				isSpecial = true;
			} else {
				isSpecial = false;
			}
		}
		
		showTicket = new Ticket(s.getCineplex(), s.getMovie().getMovieType(), s.getMovie().getIsBlockbuster(), s.getCinema().getCinemaType(),
					ageType, selectedSeat.getSeatType(), isSpecial);
			
		double ticketPrice = calculatePrice(showTicket);
		
		do {
			try {
				System.out.printf("The ticket price is: %.2f. Would you like to confirm booking?\n", ticketPrice);
				System.out.println("(1) Yes");
				System.out.println("(2) No");
				
				choice = sc.nextInt();
				
				switch(choice) {
					case 1:
						LocalDateTime timeOfPurchase = LocalDateTime.now();
						String tID = s.getCineplex().getCode() + timeOfPurchase.format(formatter);
						Booking b = new Booking(name, emailAddress, mobileNum, showTicket, timeOfPurchase, tID);
						bookingData.add(b);
						
						SeatController.confirmSeats(s, selectedSeat);
						showtimeData.set(showIndex, s);
						SerializeDB.writeToShowtimeList(showtimeData);
						SerializeDB.writeToBookingData(bookingData);
											
						System.out.printf("The ticket has been successfully purchase. Your transaction ID is %s. Returning to previous menu...\n", tID);
						return;
					case 2:
						System.out.println("Returning to previous menu...");
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
	
	public static boolean emailMatches(String emailAddress) {
	    return Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")
	      .matcher(emailAddress)
	      .matches();
	}
	
	public static boolean mobileMatches(String mobileNum) {
		return Pattern.compile("[8-9][0-9]{7}").matcher(mobileNum).matches();
	}
	
	public static double calculatePrice(Ticket ticket) {
		List<TicketPrice> ticketPrices = SerializeDB.getTicketPrices();
		double ticketPrice = 0;
		
		if (ticket.getIsSpecial() == true) {
			if(ticket.getCinemaType() == CinemaType.PLATINUM_MOVIE_SUITES) {
				ticketPrice += ticketPrices.get(0).getPrice();
			} else {
				if(ticket.getMovieType() == MovieType.REGULAR) {
					ticketPrice += ticketPrices.get(1).getPrice();
				} else {
					ticketPrice += ticketPrices.get(2).getPrice();
				}
			}
		} else {
			if(ticket.getCinemaType() == CinemaType.PLATINUM_MOVIE_SUITES) {
				ticketPrice += ticketPrices.get(3).getPrice();
			} else {
				if(ticket.getAgeType() == AgeType.SENIOR_CITIZEN) {
					ticketPrice += ticketPrices.get(8).getPrice();
				} else if (ticket.getAgeType() == AgeType.CHILD) { 
					if(ticket.getMovieType() == MovieType.REGULAR) {
						ticketPrice += ticketPrices.get(4).getPrice();
					} else {
						ticketPrice += ticketPrices.get(5).getPrice();
					}
				} else {
					if(ticket.getMovieType() == MovieType.REGULAR) {
						ticketPrice += ticketPrices.get(6).getPrice();
					} else {
						ticketPrice += ticketPrices.get(7).getPrice();
					}
				}
			}
		}
		
		if(ticket.getIsBlockbuster() == true) {
			ticketPrice += ticketPrices.get(9).getPrice();
		}
		
		if(ticket.getSeatType() == SeatType.DOUBLE) {
			ticketPrice *= 2;
		}
		return ticketPrice;
	}
}
