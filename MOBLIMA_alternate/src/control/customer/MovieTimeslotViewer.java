package control.customer;

import control.datahandler.MovieDataHandler;
import control.datahandler.TimeslotDataHandler;
import entity.movie.Movie;
import entity.movie.ShowingStatus;
import entity.movie.Timeslot;
import interfaces.Displayer;
import interfaces.DataHandler;
import interfaces.Viewer;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class MovieTimeslotViewer implements Viewer {
    public void view(){
        DataHandler movieDataHandler = new MovieDataHandler();
        List<Movie> movieData = movieDataHandler.retrieve();
        movieData.removeIf(m->Objects.equals(m.getShowingStatus(), ShowingStatus.COMING_SOON));
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            Displayer movieListDisplayer = new MovieListDisplayer(movieData);
            movieListDisplayer.display();
            System.out.printf("\nInput option number to view movie timeslots (-1 to return to customer menu): ");

            while(!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter an integer!");
                sc.next();
            }

            choice = sc.nextInt();

            if(choice > movieData.size()) {
                System.out.println("Option does not exist. Please key in a valid option!\n");
            } else if (choice == -1) {
                System.out.println("Returning to customer menu...");
                return;
            } else {
                Movie movie = movieData.get(choice-1);
                DataHandler timeslotDataHandler = new TimeslotDataHandler();
                List<Timeslot> movieTimeslots = timeslotDataHandler.retrieve();
                movieTimeslots.removeIf(ts -> !Objects.equals(ts.getMovieTitle(), movie.getTitle()));
                Displayer timeslotListDisplayer = new TimeslotListDisplayer(movieTimeslots);
                timeslotListDisplayer.display();
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
                    return;
                } else {
                    Timeslot ts = movieTimeslots.get(choice-1);
                    Displayer seatDisplayer = new SeatDisplayer(ts);
                    seatDisplayer.display();
                    System.out.println("Press Enter to return to movie lists...");
                    sc.nextLine();
                    sc.nextLine();
                }
            }
        } while (true);
    }
}
