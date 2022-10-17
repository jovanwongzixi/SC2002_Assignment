package Movies;

import interfaces.Displayable;

import java.awt.*;
import java.util.Scanner;

//import com.opencsv.CSVReader;
public class Movie implements Displayable {
    private String ID;
    private String title;
    private ShowingStatus showingStatus;
    private String synopsis;
    private String director;
    private String cast;
    private String reviewerRating;
    private String filmRating;
    private MovieType movieType;
    public Movie(){}
    public Movie(String[] values){
        this.ID = values[0];
        this.title = values[1];
        setShowingStatus(values[2]);
        this.synopsis = values[3];
        this.director = values[4];
        this.cast = values[5];
        this.reviewerRating = values[6];
        this.filmRating = values[7];
    }
    private void setShowingStatus(String showingStatus){
        ShowingStatus[] allShowingStatuses = ShowingStatus.values();
        for(ShowingStatus status : allShowingStatuses){
            if(status.toString().equals(showingStatus)){
                this.showingStatus = status;
                return;
            }
        }
    }
    public ShowingStatus getShowingStatus() {
        return this.showingStatus;
    }
    public String getTitle() {
        return this.title;
    }
    public String getID() {
        return this.ID;
    }
    public String getDirector() {
        return director;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public String getCast() {
        return cast;
    }

    public String getFilmRating() {
        return filmRating;
    }

    public String getReviewerRating() {
        return reviewerRating;
    }

    public void display(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter movie ID: ");
        MovieDataReader movieDataReader = new MovieDataReader();
        Movie movie = movieDataReader.read(sc.nextLine());
        System.out.println("ID: " + movie.getID());
        System.out.println("Title: "+ movie.getTitle());
        System.out.println("Showing Status: " + movie.getShowingStatus().toString());
        System.out.println("Synopsis: "+ movie.getSynopsis());
        System.out.println("Director: "+ movie.getDirector());
        System.out.println("Cast: "+ movie.getCast());
        System.out.println("Film Rating: "+ movie.getFilmRating());
        System.out.println("Reviewer Rating: "+movie.getReviewerRating());
    }
}
