import cinemas.Seat;
import cinemas.SeatState;
import interfaces.Displayable;
import movies.MovieTicketPrice;
import movies.MovieTimeSlot;
import movies.MovieTimeSlotList;

import java.util.ArrayList;

public class TicketBooker {
    private MovieTicketPrice priceCalculator;
    private BookingHistoryList bookingHistoryList;
    public TicketBooker(Displayable bookingHistoryList){
        if(bookingHistoryList instanceof BookingHistoryList) this.bookingHistoryList = (BookingHistoryList) bookingHistoryList;
        priceCalculator = new MovieTicketPrice();
    }
    public void book(ArrayList<Seat> selectedSeats, MovieTimeSlot selectedTimeSlot){
        double totalPrice = 0;
        priceCalculator.setBlockbuster(false);
        priceCalculator.setDate(selectedTimeSlot.getShowDate());
        priceCalculator.setTime(selectedTimeSlot.getShowTime());
        priceCalculator.setIs3D(false);
        for(Seat seat : selectedSeats){
            selectedTimeSlot.updateLayout(seat, SeatState.TAKEN);
            totalPrice += priceCalculator.getModifiedPrice();
        }
        BookingHistory bookingHistory = new BookingHistory();
        bookingHistory.setTotalCost(totalPrice);
        bookingHistoryList.appendList(bookingHistory);
    }
}
