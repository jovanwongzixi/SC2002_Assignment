package control.customer;

import control.datahandler.*;
import entity.Customer;
import entity.cinema.Seat;
import entity.cinema.SeatState;
import entity.movie.*;
import interfaces.*;

import java.util.List;

public class MovieTicketBooker implements Controller {
    private final Customer currentUser;
    public MovieTicketBooker(Customer currentUser){
        this.currentUser = currentUser;
    }
    public void start(){
        int section = 1;
        Movie movie = null;
        Timeslot ts = null;
        List<Seat> selectedSeats = null;
        List<Timeslot> timeslotList = null;
        do{
            switch(section){
                case 1:
                    DataHandler movieDataHandler = new MovieDataHandler();
                    MovieSelector movieSelector = new MovieSelector(movieDataHandler.retrieve());
                    movie = movieSelector.selectMovie();
                    if(movie==null) return;
                case 2:
                    DataHandler timeslotHandler = new TimeslotDataHandler();
                    timeslotList= timeslotHandler.retrieve();
                    TimeslotSelector timeSlotSelector = new TimeslotSelector(timeslotList);
                    ts = timeSlotSelector.selectTimeslot(movie);
                    if(ts == null){
                        section = 1;
                        break;
                    }
                case 3:
                    SeatSelector seatSelector = new SeatSelector();
                    selectedSeats = seatSelector.selectSeats(ts, movie);
                    if(selectedSeats == null){
                        section = 2;
                        break;
                    }
                case 4:
                    PaymentHandler paymentHandler = new PaymentHandler();
                    if(paymentHandler.ticketPayment(movie, ts, selectedSeats, currentUser)) confirmSeats(ts,timeslotList.indexOf(ts),selectedSeats);
                    return;
            }
        } while(true);
    }
    private void confirmSeats(Timeslot ts, int tsIndex, List<Seat> selectedSeats) {
        DataHandler timeslotDataHandler = new TimeslotDataHandler();
        List<Timeslot> movieTimeslots = timeslotDataHandler.retrieve();
        movieTimeslots.set(tsIndex, ts);
        for (Seat s : selectedSeats) {
            s.setSeatState(SeatState.TAKEN);
        }
        timeslotDataHandler.save(movieTimeslots);
    }
}
