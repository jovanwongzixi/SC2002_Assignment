package movies;
import java.util.ArrayList;
import interfaces.Displayable;

import java.util.Objects;
import java.util.Scanner;

public class MovieListing implements Displayable{
    private ArrayList<Movie> movieArrayList;
    public MovieListing(){
        MovieDataReader movieDataReader = new MovieDataReader();
        this.movieArrayList = movieDataReader.readFile();
    }
    public void display(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter showing status:");
        String showingStatus = sc.nextLine();
        for(Movie movie: movieArrayList){
            if(Objects.equals(showingStatus, movie.getShowingStatus().toString())){
                System.out.println(movie.getID() + ") " + movie.getTitle());
            }
        }
    }
}
