package movies;

import interfaces.Displayable;

//import com.opencsv.CSVReader;
public class Movie {
    private String ID;
    private String title;
    private ShowingStatus showingStatus;
    private String synopsis;
    private String director;
    private String cast;
    private String reviewerRating;
    private String filmRating;
    private MovieType movieType;
    public Movie(){
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
    public void printInfo(){
        System.out.println("ID: " + this.getID());
        System.out.println("Title: "+ this.getTitle());
        System.out.println("Showing Status: " + this.getShowingStatus().toString());
        System.out.println("Synopsis: "+ this.getSynopsis());
        System.out.println("Director: "+ this.getDirector());
        System.out.println("Cast: "+ this.getCast());
        System.out.println("Film Rating: "+ this.getFilmRating());
        System.out.println("Reviewer Rating: "+this.getReviewerRating());
    }
}
