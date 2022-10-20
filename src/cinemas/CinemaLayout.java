package cinemas;

import java.util.ArrayList;

public class CinemaLayout {
    private ArrayList<Seat> seats;
    public CinemaLayout(){
        //let each cinema have 5 seats for now;
        this.seats = new ArrayList<>();
        for(int i=0; i<5; i++){
            Seat seat = new Seat('A', i+1);
            seats.add(seat);
        }
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
}
