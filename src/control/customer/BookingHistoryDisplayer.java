package control.customer;

import entity.Booking;
import interfaces.Displayer;

import java.util.List;

public class BookingHistoryDisplayer implements Displayer {
    private final List<Booking> bookingData;
    public BookingHistoryDisplayer(List<Booking> bookingList){
        this.bookingData = bookingList;
    }
    public void display(){
        System.out.println("\n----------------- Booking History ----------------");
        if(bookingData.isEmpty())System.out.println("No booking history found!");
        else {
            for (Booking b : bookingData) {
            System.out.printf("""
                            Booking %s for %s:
                            Cineplex / Cinema: %s / %d
                            Movie timeslot: %td %<tb %<tY %tR
                            Price: %.2f
                            -------------------------------------
                            """,
                    b.getTID(), b.getMovieTitle(), b.getCineplex(), b.getCinemaID(),
                    b.getDateShow(), b.getTimeShow(), b.getTicketPrice());
            }
        }

    }
}
