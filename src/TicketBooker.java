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
    public void book(SeatSelector seatSelector, MovieGoer user){
        MovieTimeSlot selectedTimeSlot = seatSelector.getSelectedTimeSlot();
        if(selectedTimeSlot==null){
            System.out.println("No seats selected for booking!");
            return;
        }
        double totalPrice = 0;
        priceCalculator.setBlockbuster(false);
        priceCalculator.setDate(selectedTimeSlot.getShowDate());
        priceCalculator.setTime(selectedTimeSlot.getShowTime());
        priceCalculator.setIs3D(false);
        for(Seat seat : seatSelector.getSelectedSeats()){
            selectedTimeSlot.updateLayout(seat, SeatState.TAKEN);
            totalPrice += priceCalculator.getModifiedPrice();
        }
        BookingHistory bookingHistory = new BookingHistory();
        bookingHistory.setTotalCost(totalPrice);
        bookingHistory.setTimeSlot(selectedTimeSlot);
        bookingHistoryList.appendList(bookingHistory);
        //clear selected seats and timeslot
        seatSelector.getSelectedSeats().clear();
        seatSelector.setSelectedTimeSlot(null);
    }
}
