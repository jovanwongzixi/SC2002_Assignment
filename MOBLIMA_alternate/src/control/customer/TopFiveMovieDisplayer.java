package control.customer;

import entity.movie.Movie;
import interfaces.Displayer;

import java.util.List;

public class TopFiveMovieDisplayer implements Displayer {
    private final List<Movie> movieData;
    private final int switcher;
    public TopFiveMovieDisplayer(List<Movie> movieData, int switcher){
        this.switcher = switcher;
        this.movieData = movieData;
    }
    @Override
    public void display() {
        if (switcher == 1) {
            System.out.println("\n-------------------- Top 5 Movies by Rating -------------------");
            for(int index = 1; index <= 5; index++) {
                System.out.printf("(%d) ----------------	%s (Rating: %.1f)\n", index, movieData.get(index-1).getTitle(), movieData.get(index-1).getOverallRating());
            }
        } else {
            System.out.println("\n-------------------- Top 5 Movies by Ticket Sales -------------------");
            for(int index = 1; index <= 5; index++) {
                System.out.printf("(%d) ----------------	%s (Ticket sales: %d)\n", index, movieData.get(index-1).getTitle(), movieData.get(index-1).getTicketSales());
            }
        }
    }
}
