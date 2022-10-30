import cinemas.*;
import interfaces.Displayable;
import movies.MovieTimeSlot;
import movies.MovieTimeSlotList;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class SeatSelector {
    private MovieTimeSlotList movieTimeSlotList;
    private ArrayList<Seat> selectedSeats;
    private MovieTimeSlot selectedTimeSlot;
    /*public SeatSelector(MovieTimeSlotList movieTimeSlotList){
        this.movieTimeSlotList = movieTimeSlotList;
        this.selectedSeats = new ArrayList<>();
        this.selectedTimeSlot = null;
    }*/
    public SeatSelector(Displayable movieTimeSlotList){
        if(movieTimeSlotList instanceof MovieTimeSlotList) this.movieTimeSlotList = (MovieTimeSlotList) movieTimeSlotList;
        this.selectedSeats = new ArrayList<>();
        this.selectedTimeSlot = null;
    }
    /*public SeatSelector(){
        this.movieTimeSlotList = new MovieTimeSlotList();
    }*/
    public void showSeats(){
        MovieTimeSlot timeSlot = movieTimeSlotList.selectTimeSlot();
        timeSlot.showCinemaLayout();
    }
    public void selectSeat(){
        boolean selected = false;
        Scanner sc = new Scanner(System.in);
        MovieTimeSlot timeSlot = movieTimeSlotList.selectTimeSlot();
        while(selectedTimeSlot!=null && !Objects.equals(selectedTimeSlot,timeSlot)){
            System.out.println("Only seats from same timeslot can be selected!");
            timeSlot = movieTimeSlotList.selectTimeSlot();
        }
        ArrayList<Seat> seats = timeSlot.getLayout().getSeats();
        while(!selected){
            System.out.println("Select row (A-F):");
            char row = Character.toUpperCase(sc.nextLine().charAt(0));
            System.out.println("Select column (0-7):");
            int column = sc.nextInt();
            sc.nextLine();
            for(Seat seat : seats){
                if(seat.getRow()==row && seat.getColumn()==column && seat.getSeatState()==SeatState.AVAILABLE){
                    timeSlot.updateLayout(seat,SeatState.SELECTED);
                    selectedSeats.add(seat);
                    selectedTimeSlot = timeSlot;
                    selected = true;
                    System.out.println("Seat selected!");
                    break;
                }
            }
            //failed to select seat;
            if(!selected)System.out.println("Seat not available. Select new seat!");
        }
    }

    public ArrayList<Seat> getSelectedSeats() {
        return selectedSeats;
    }

    public MovieTimeSlot getSelectedTimeSlot() {
        return selectedTimeSlot;
    }
}
