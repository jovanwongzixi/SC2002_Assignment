package control.customer;

import control.datahandler.MovieDataHandler;
import entity.movie.Movie;
import entity.movie.ShowingStatus;
import interfaces.DataHandler;
import interfaces.Displayer;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class MovieSelector {
    private List<Movie> bookingList;
    public MovieSelector(List<Movie> bookingList){
        this.bookingList = bookingList;
    }
    protected Movie selectMovie(){
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            //DataHandler movieDataHandler = new MovieDataHandler();
            //List<Movie> bookingList = movieDataHandler.retrieve();
            bookingList.removeIf(movie -> Objects.equals(movie.getShowingStatus(), ShowingStatus.COMING_SOON));
            Displayer movieListDisplayer = new MovieListDisplayer(bookingList);
            movieListDisplayer.display();
            System.out.printf("\nInput option number to view book movie ticket (-1 to return to customer menu): ");

            while(!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter an integer!");
                System.out.printf("Option: ");
                sc.next();
            }

            choice = sc.nextInt();

            if(choice > bookingList.size()) {
                System.out.println("Option does not exist. Please key in a valid option!\n");
            } else if (choice == -1) {
                System.out.println("Returning to customer menu...");
                return null;
            } else {
                return bookingList.get(choice-1);
            }
        } while (true);
    }
}
