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
        System.out.println("""
                List movie options
                1) Now Showing
                2) Coming Soon
                3) Preview
                Select option:
                """);
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();
        ShowingStatus status;
        switch(option){
            case 2 -> status = ShowingStatus.COMING_SOON;
            case 3 -> status = ShowingStatus.PREVIEW;
            default -> status = ShowingStatus.NOW_SHOWING; //if troll input just show Now_Showing
        }
        for(Movie movie: movieArrayList){
            if(Objects.equals(status, movie.getShowingStatus())){
                System.out.println(movie.getID() + ") " + movie.getTitle());
            }
        }
    }
    protected Movie getMovie(String info){
        for(Movie movie: movieArrayList){
            if(Objects.equals(info, movie.getID())){
                return movie;
            }
        }
        return null;
    }
}
