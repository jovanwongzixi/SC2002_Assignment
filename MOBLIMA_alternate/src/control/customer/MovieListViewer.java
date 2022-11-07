package control.customer;

import control.datahandler.MovieDataHandler;
import entity.movie.Movie;
import interfaces.Displayer;
import interfaces.DataHandler;
import interfaces.Viewer;

import java.util.List;
import java.util.Scanner;

public class MovieListViewer implements Viewer {
    public MovieListViewer(){}
    @Override
    public void view() {
        DataHandler movieListRetriever = new MovieDataHandler();
        List<Movie> movieData = movieListRetriever.retrieve();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            Displayer movieListDisplayer = new MovieListDisplayer(movieData);
            movieListDisplayer.display();
            //displayMovieList(movieData);
            System.out.printf("\nInput option number to view movie details (-1 to return to customer menu): ");

            while(!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter an integer!");
                System.out.printf("Option: ");
                sc.next();
            }

            choice = sc.nextInt();

            if(choice > movieData.size()) {
                System.out.println("Option does not exist. Please key in a valid option!\n");
            } else if (choice == -1) {
                System.out.println("Returning to customer menu...");
                return;
            } else {
                Displayer movieDetailsDisplayer = new MovieDetailsDisplayer(movieData.get(choice-1));
                movieDetailsDisplayer.display();
                System.out.println("\nPress Enter to return to movie list...");
                if(sc.nextLine() != null)
                    sc.nextLine();
            }
        } while (true);
    }
}
