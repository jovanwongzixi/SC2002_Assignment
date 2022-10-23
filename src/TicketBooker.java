import cinemas.Seat;
import cinemas.SeatState;
import movies.MovieTimeSlot;
import movies.MovieTimeSlotList;

import java.util.ArrayList;

public class TicketBooker {
    public void book(ArrayList<Seat> selectedSeats, MovieTimeSlot selectedTimeSlot){
        for(Seat seat : selectedSeats){
            selectedTimeSlot.updateLayout(seat, SeatState.TAKEN);
        }
    }
}
