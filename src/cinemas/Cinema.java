package cinemas;
import java.util.ArrayList;
public class Cinema{
    private int theatreNumber;
    private int numOfSeats; // if our layout is fixed we can have a fixed number of seats, so no need for setNumOfSeats
    private ArrayList<Seat> seatsArray;
    private CinemaLayout layout;
    private CinemaType cinemaType;
    public Cinema(int theatreNumber){
        this.theatreNumber = theatreNumber;
        this.seatsArray = new ArrayList<Seat>();
        //this.layout = new CinemaLayout();
    }
    public int getTheatreNumber(){
        return this.theatreNumber;
    }
    public int getNumOfSeats(){
        return this.numOfSeats;
    }
}
