package movies;
import cinemas.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class MovieTimeSlot implements Serializable {
    @Serial
    private static final long serialVersionUID = -8242226099133439559L;
    private String slotID;
    private LocalDate showDate;
    private LocalTime showTime;
    private Movie movie;
    private Cineplex cineplex;
    private Cinema cinema;
    private CinemaLayout layout; //each movietimeslot should have its own layout(what seats are taken)
    public MovieTimeSlot(String[] values){
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.slotID = values[0];
        this.showDate = LocalDate.parse(values[1],dateFormatter);
        this.showTime = LocalTime.parse(values[2]);
        setMovie(values[3]);
        setCineplex1(values[4]);
        //setCinema();
        this.layout = new CinemaLayout(slotID);
    }
    public MovieTimeSlot(String title, Cineplex cineplex, Cinema cinema, LocalDate showDate, LocalTime showTime) {
        setMovie(title);
        this.cineplex = cineplex;
        this.cinema = cinema;
        this.showDate = showDate;
        this.showTime = showTime;
    }
    private void setMovie(String movieTitle){
        MovieListing listing = new MovieListing();
        this.movie = listing.getMovie(movieTitle);
    }
    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }
    public void setCineplex(Cineplex cineplex) {
        this.cineplex = cineplex;
    }
    private void setCineplex1(String cineplexName){
        CineplexList cineplexList= new CineplexList();
        this.cineplex = cineplexList.getCineplex(cineplexName);
    }
    public void setShowDate(LocalDate showDate) {
        this.showDate = showDate;
    }

    public void setShowTime(LocalTime showTime) {
        this.showTime = showTime;
    }
    public Movie getMovie() {
        return movie;
    }
    public String getTitle(){return movie.getTitle();}
    public String getDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM");
        return showDate.format(formatter);
    }

    public String getTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return showTime.format(formatter);
    }
    public LocalDate getShowDate() {
        return this.showDate;
    }

    public LocalTime getShowTime() {
        return this.showTime;
    }
    public Cineplex getCineplex() {
        return cineplex;
    }
    public Cinema getCinema() {
        return this.cinema;
    }

    public void showCinemaLayout(){
        layout.printLayout();
    }
    public CinemaLayout getLayout() {
        return layout;
    }
    public void updateLayout(Seat seat, SeatState seatState){
        layout.update(seat, seatState, this.slotID);
    }
}