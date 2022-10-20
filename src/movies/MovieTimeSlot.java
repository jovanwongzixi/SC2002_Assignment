package movies;
import cinemas.*;

public class MovieTimeSlot {
    private String slotID;
    private String date;
    private String time;
    private Movie movie;
    private Cineplex cineplex;
    private Cinema cinema;
    private CinemaLayout layout; //each movietimeslot should have its own layout(what seats are taken)
    public MovieTimeSlot(String[] values){
        this.slotID = values[0];
        this.date = values[1];
        this.time = values[2];
        setMovie(values[3]);
        setCineplex(values[4]);
        //setCinema();
        this.layout = new CinemaLayout();
    }
    private void setMovie(String movieTitle){
        MovieListing listing = new MovieListing();
        this.movie = listing.getMovie(movieTitle);
    }

    public Movie getMovie() {
        return movie;
    }
    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    private void setCineplex(String cineplexName){
        CineplexList cineplexList= new CineplexList();
        this.cineplex = cineplexList.getCineplex(cineplexName);
    }

    public Cineplex getCineplex() {
        return cineplex;
    }

    public void print(){

    }
    public void showCinemaLayout(){
        layout.printLayout();
    }
    public CinemaLayout getLayout() {
        return layout;
    }
}