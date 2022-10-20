import cinemas.*;
import interfaces.Displayable;
import movies.MovieTimeSlot;
import movies.MovieTimeSlotList;

import java.util.ArrayList;
import java.util.Scanner;

public class SeatSelector {
    private MovieTimeSlotList movieTimeSlotList;
    public SeatSelector(MovieTimeSlotList movieTimeSlotList){
        this.movieTimeSlotList = movieTimeSlotList;
    }
    public SeatSelector(){
        this.movieTimeSlotList = new MovieTimeSlotList();
    }
    public void showSeats(){
        MovieTimeSlot timeSlot = movieTimeSlotList.selectTimeSlot();
        timeSlot.showCinemaLayout();
    }
    public void selectSeat(){
        boolean selected = false;
        Scanner sc = new Scanner(System.in);
        MovieTimeSlot timeSlot = movieTimeSlotList.selectTimeSlot();
        ArrayList<Seat> seats = timeSlot.getLayout().getSeats();
        while(!selected){
            System.out.println("Select row: ");
            char row = sc.nextLine().charAt(0);
            System.out.println("Select column: ");
            int column = sc.nextInt();
            sc.nextLine();
            for(Seat seat : seats){
                if(seat.getRow()==row && seat.getColumn()==column && seat.getSeatState()==SeatState.AVAILABLE){
                    seat.setSeatState(SeatState.SELECTED);
                    selected = true;
                    break;
                }
            }
        }
    }
}
