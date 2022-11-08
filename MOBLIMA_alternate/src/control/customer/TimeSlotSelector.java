package control.customer;

import entity.movie.Movie;
import entity.movie.Timeslot;
import interfaces.Displayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class TimeslotSelector {
    private final List<Timeslot> movieTimeslots;
    public TimeslotSelector(List<Timeslot> movieTimeslots){
        this.movieTimeslots = movieTimeslots;
    }
    protected Timeslot selectTimeslot(Movie movie){
        Scanner sc = new Scanner(System.in);
        int choice;
        //DataHandler timeslotDataHandler = new TimeslotDataHandler();
        //List<Timeslot> movieTimeslots = timeslotDataHandler.retrieve();
        List<Timeslot> selectedMovieTimeslots = new ArrayList<>();
        for(Timeslot ts : movieTimeslots){
            if(Objects.equals(ts.getMovieTitle(), movie.getTitle())) selectedMovieTimeslots.add(ts);
        }
        Displayer movieTimeslotDisplayer = new TimeslotListDisplayer(selectedMovieTimeslots);
        do {
            movieTimeslotDisplayer.display();
            System.out.printf("\nInput option number to view seats (-1 to return to previous menu): ");

            while(!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter an integer!");
                sc.next();
            }

            choice = sc.nextInt();

            if(choice > movieTimeslots.size()) {
                System.out.println("Option does not exist. Please key in a valid option!\n");
            } else if (choice == -1) {
                System.out.println("Returning to previous menu...");
                return null;
            } else {
                return selectedMovieTimeslots.get(choice-1);
            }
        } while (true);
    }
}
