package control.customer;

import java.util.*;
import java.util.regex.Pattern;
import control.SerializeDB;
import entity.Booking;
import entity.Customer;
import interfaces.Viewer;

public class BookingHistoryViewer implements Viewer{
	private Customer customer;
	public BookingHistoryViewer(Customer currentUser){
		customer = currentUser;
	}
	public void view() {
		List<Booking> bookingData = SerializeDB.getList("Booking");
		Scanner sc = new Scanner(System.in);
		String mobileNum;
		int counter = 0;
		if(customer != null) mobileNum = customer.getMobileNumber();
		else {
			do {
				System.out.println("Enter your mobile number: ");
				mobileNum = sc.nextLine();
				if (!mobileMatches(mobileNum)) {
					System.out.println("Invalid mobile number. Please try again!");
				}
			} while (!mobileMatches(mobileNum));
		}
		System.out.println("\n----------------- Booking History ----------------");
		for (Booking b : bookingData) {
			if(b.getMobileNum().equals(mobileNum)) {
				counter++;		
				System.out.printf("Booking %s for %s:\n"
						+ "Cineplex / Cinema: %s / %d\n"
						+ "Movie timeslot: %td %<tb %<tY %tR\n"
						+ "Price: %.2f\n"
						+ "-------------------------------------\n",
						b.getTID(), b.getMovieTitle(), b.getCineplex(), b.getCinemaID(),
						b.getDateShow(), b.getTimeShow(), b.getTicketPrice());
			}
		}
		
		if(counter == 0) {
			System.out.println("No booking history found!");
		} else {
			System.out.println("\nPress Enter to return to movie list...");
			sc.nextLine();
		}
	}
	
	private boolean mobileMatches(String mobileNum) {
		return Pattern.compile("[8-9][0-9]{7}").matcher(mobileNum).matches();
	}
}
