package control.customer;

import java.util.*;
import java.util.regex.Pattern;
import control.datahandler.BookingDataHandler;
import entity.Booking;
import entity.Customer;
import interfaces.DataHandler;
import interfaces.Displayer;
import interfaces.Viewer;

public class BookingHistoryViewer implements Viewer{
	private final Customer customer;
	public BookingHistoryViewer(Customer currentUser){
		customer = currentUser;
	}
	public void view() {
		DataHandler bookingDataHandler = new BookingDataHandler();
		List<Booking> bookingData = bookingDataHandler.retrieve();
		Scanner sc = new Scanner(System.in);
		String mobileNum;
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
		String finalMobileNum = mobileNum;
		bookingData.removeIf(b->!b.getMobileNum().equals(finalMobileNum));
		Displayer bookingHistoryDisplayer = new BookingHistoryDisplayer(bookingData);
		bookingHistoryDisplayer.display();
		System.out.println("\nPress Enter to return to movie list...");
		sc.nextLine();
	}
	
	private boolean mobileMatches(String mobileNum) {
		return Pattern.compile("[8-9][0-9]{7}").matcher(mobileNum).matches();
	}
}
