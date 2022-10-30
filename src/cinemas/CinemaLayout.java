package cinemas;

import java.io.Serializable;
import java.util.ArrayList;

public class CinemaLayout implements Serializable {
    private ArrayList<Seat> seats;
    //private CinemaLayoutUpdater updater;
    public CinemaLayout(String slotID){
        CinemaLayoutUpdater updater = new CinemaLayoutUpdater();
        //let each cinema have 5 seats for now;
        this.seats = updater.getLayoutData(slotID);
        /*for(int i=0; i<5; i++){
            Seat seat = new Seat('A', i+1);
            seats.add(seat);
        }*/
    }
    public void printLayout(){
        //printing seat info for now
        for(Seat seat: seats){
            System.out.printf("%c%d ",seat.getRow(),seat.getColumn());
            System.out.println(seat.getSeatState());
        }
    }
    public ArrayList<Seat> getSeats() {
        return seats;
    }
    public void update(Seat seat, SeatState seatState, String slotID){
        CinemaLayoutUpdater updater = new CinemaLayoutUpdater();
        seat.setSeatState(seatState);
        updater.updateLayoutData(this.seats, slotID);
    }
}
