package Movies;
import java.io.IOException;
import java.util.ArrayList;
import interfaces.Displayable;

public class MovieListing implements Displayable{
    private ArrayList<String> movieArray;
    /*public static void main(String[] args){
        MovieListing movieListing = new MovieListing();
        movieListing.display();
    }*/
    public void display(){
        MovieListingReader movieListingReader = new MovieListingReader();
        try {
            this.movieArray = movieListingReader.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for(String movie: movieArray){
            System.out.println(movie);
        }
    }
}
