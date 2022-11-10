package control.customer;

import entity.movie.Movie;
import interfaces.Displayer;

import java.util.List;

public class MovieListDisplayer implements Displayer {
    private final List<Movie> movieData;
    public MovieListDisplayer(List<Movie> movieList){
        this.movieData = movieList;
    }
    @Override
    public void display() {
        int index = 0;

        System.out.println("\n-------------------- Movie List -------------------");
        for(Movie m: movieData) {
            index++;
            System.out.printf("(%d) ----------------	%s\n",index, m.getTitle());
        }
    }
}
