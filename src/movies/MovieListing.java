package movies;
import java.util.ArrayList;
import interfaces.Displayable;

import java.util.Objects;
import java.util.Scanner;

public class MovieListing implements Displayable{
    private ArrayList<Movie> movieArrayList;
    MovieDataUpdater movieDataUpdater;
    public MovieListing(){
        movieDataUpdater = new MovieDataUpdater();
        this.movieArrayList = movieDataUpdater.readBin();
    }
    public void display(){
        System.out.println("""
                List movie options
                1) Now Showing
                2) Coming Soon
                3) Preview
                Select option:""");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();
        ShowingStatus status;
        switch(option){
            case 2 -> status = ShowingStatus.COMING_SOON;
            case 3 -> status = ShowingStatus.PREVIEW;
            default -> status = ShowingStatus.NOW_SHOWING; //if troll input just show Now_Showing
        }
        int i=0;
        ArrayList<Movie> tempArray = new ArrayList<>(); //store movie references for the movies in selected category
        for(Movie movie: movieArrayList){
            if(Objects.equals(status, movie.getShowingStatus())){
                tempArray.add(movie);
                System.out.println(++i + ") " + movie.getTitle());
            }
        }
        System.out.println("Select movie to view:");
        option = sc.nextInt();
        tempArray.get(option-1).printInfo();
    }
    protected Movie getMovie(String info){
        for(Movie movie: movieArrayList){
            if(Objects.equals(info, movie.getTitle())){
                return movie;
            }
        }
        return null;
    }
    protected ArrayList<String> getMovieTitles(){ // moviegoers not supposed to see timeslots for EndOfShowing and ComingSoon
        ArrayList<String> movieTitles = new ArrayList<>();
        for(Movie movie: movieArrayList){
            if(movie.getShowingStatus() != ShowingStatus.END_OF_SHOWING && movie.getShowingStatus() != ShowingStatus.COMING_SOON) movieTitles.add(movie.getTitle());
        }
        return movieTitles;
    }
    protected ArrayList<Movie> getMovieList(){
        return movieArrayList;
    }
    protected void updateMovieList(){
        movieDataUpdater.writeBin(movieArrayList);
    }
}
