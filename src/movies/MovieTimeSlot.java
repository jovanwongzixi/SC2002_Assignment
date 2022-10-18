package movies;
import cinemas.Cinema;
import cinemas.Cineplex;
import cinemas.CineplexList;

public class MovieTimeSlot {
    private String ID;
    private String time;
    private String date;
    private Movie movie;
    private Cinema cinema;
    private Cineplex cineplex;
    //private CinemaLayout layout //each movietimeslot should have its own layout(what seats are taken)
    public MovieTimeSlot(String[] values){
        this.ID = values[0];

    }
    private void setMovie(){
        this.movie = new Movie(); // function not complete
    }
    private void setCineplex(String cineplexName){
        CineplexList cineplexList= new CineplexList();
        this.cineplex = cineplexList.getCineplex(cineplexName);
    }
    public void print(){

    }
}
