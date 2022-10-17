package Movies;
import java.io.IOException;
import java.util.ArrayList;
import interfaces.Displayable;

import java.util.Objects;
import java.util.Scanner;

public class MovieListing implements Displayable{
    //private String showingStatus;
    //private ArrayList<String> movieArray;
    private ArrayList<Movie> movieArrayList;
    public MovieListing(){}
    public void display(){
        read();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter showing status:");
        String showingStatus = sc.nextLine();
        for(Movie movie: movieArrayList){
            if(Objects.equals(showingStatus, movie.getShowingStatus().toString())){
                System.out.println(movie.getID() + ") " + movie.getTitle());
            }
        }
    }
    private void read() {
        MovieListingReader movieListingReader = new MovieListingReader();
        try {
            this.movieArrayList = movieListingReader.readFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
