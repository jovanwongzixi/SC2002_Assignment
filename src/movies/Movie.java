package movies;

import interfaces.Displayable;
//cast part converted from String to list of string,
//potential problem to settle

import java.io.Serializable;
import java.util.List;

//import com.opencsv.CSVReader;
public class Movie implements Serializable {
    private static final long serialVersionUID = 2L;
    //private String ID;
    private String title;
    private ShowingStatus showingStatus;
    private String synopsis;
    private String director;
    private List<String> cast;
    private Double overallRating;
    private FilmRating filmRating;
    private MovieType movieType;
    private String movieGenre;
    private List<MovieReview> reviews;
    public Movie(String title,
                 FilmRating filmRating,
                 MovieType movieType,
                 String movieGenre,
                 String synopsis,
                 String director,
                 List<String> cast,
                 Double overallRating,
                 List<MovieReview> reviews,
                 ShowingStatus showingStatus) {

        this.title = title;
        this.filmRating = filmRating;
        this.movieType = movieType;
        this.movieGenre = movieGenre;
        this.synopsis = synopsis;
        this.director = director;
        this.cast = cast;
        this.overallRating = overallRating;
        this.reviews = reviews;
        this.showingStatus = showingStatus;
    }
    /*public Movie(String ID){
        MovieDataReader movieDataReader = new MovieDataReader();
        String[] values = movieDataReader.readFile(ID);
        this.ID = values[0];
        this.title = values[1];
        setShowingStatus(values[2]);
        this.synopsis = values[3];
        this.director = values[4];
        this.cast = values[5];
        this.reviewerRating = values[6];
        this.filmRating = values[7];
    }*/
    public Movie(String[] values){
        //this.ID = values[0];
        this.title = values[1];
        setShowingStatus1(values[2]);
        this.synopsis = values[3];
        this.director = values[4];
        //this.cast = values[5];
        this.filmRating = FilmRating.valueOf(values[6]);
        this.overallRating = Double.parseDouble(values[7]);
    }
    private void setShowingStatus1(String showingStatus){
        ShowingStatus[] allShowingStatuses = ShowingStatus.values();
        for(ShowingStatus status : allShowingStatuses){
            if(status.toString().equals(showingStatus)){
                this.showingStatus = status;
                return;
            }
        }
    }
    public void setFilmRating(FilmRating filmRating) {
        this.filmRating = filmRating;
    }

    public void setMovieType(MovieType movieType) {
        this.movieType = movieType;
    }

    public void setShowingStatus(ShowingStatus showingStatus) {
        this.showingStatus = showingStatus;
    }

    public ShowingStatus getShowingStatus() {
        return this.showingStatus;
    }
    public String getTitle() {
        return this.title;
    }
    /*public String getID() {
        return this.ID;
    }*/
    public String getDirector() {
        return director;
    }

    public String getSynopsis() {
        return synopsis;
    }

    /*public String getCast() {
        return cast;
    }*/
    public List<String> getCast(){
        return this.cast;
    }

    public FilmRating getFilmRating() {
        return filmRating;
    }

    public Double getOverallRating() {
        return overallRating;
    }
    public MovieType getMovieType() {
        return this.movieType;
    }
    public List<MovieReview> getReviews(){
        return this.reviews;
    }
    public String getMovieGenre() {
        return this.movieGenre;
    }
    public void printInfo(){
        //System.out.println("ID: " + this.getID());
        System.out.println("Title: "+ this.getTitle());
        System.out.println("Showing Status: " + this.getShowingStatus().toString());
        System.out.println("Synopsis: "+ this.getSynopsis());
        System.out.println("Director: "+ this.getDirector());
        System.out.println("Cast: "+ this.getCast());
        System.out.println("Film Rating: "+ this.getFilmRating());
        System.out.println("Reviewer Rating: "+this.getOverallRating());
    }
}
