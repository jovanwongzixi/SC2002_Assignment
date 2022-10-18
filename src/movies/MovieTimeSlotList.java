package movies;

import interfaces.Displayable;

import java.util.ArrayList;

public class MovieTimeSlotList implements Displayable {
    private ArrayList<MovieTimeSlot> slots;
    public MovieTimeSlotList(){
        MovieTimeSlotReader reader = new MovieTimeSlotReader();
        this.slots = reader.readFile();
    }
    public void display(){
        System.out.println("Enter movie ID: ");
        System.out.println("Enter date :");

    }
}
