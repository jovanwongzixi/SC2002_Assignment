//trying alternative implementation, not in use yet

import interfaces.Displayable;
import movies.MovieDetailsDisplayer;
import movies.MovieListing;
import movies.MovieTicket;
import movies.MovieTimeSlotList;

import java.util.Scanner;

public class MovieGoerApp {
    private MovieGoer currentUser;
    private Displayable movieListing;
    //private Displayable movieDetailsDisplayer;
    private Displayable movieTimeSlotList;
    private SeatSelector seatSelector;
    private Displayable bookingHistory;
    public MovieGoerApp(MovieGoer user){
        this.currentUser = user;
        this.movieListing = new MovieListing();
        //this.movieDetailsDisplayer = new MovieDetailsDisplayer(this.movieListing);
        this.movieTimeSlotList = new MovieTimeSlotList(this.movieListing);
        this.seatSelector = new SeatSelector(this.movieTimeSlotList);
        this.bookingHistory = new BookingHistory();
    }
    public void use(){
        Scanner sc = new Scanner(System.in);
        System.out.println("""
                MovieGoer options menu
                1) List movies
                2) View movie timeslots
                3) View seats
                4) Select seat
                5) Book ticket
                6) View booking history
                7) Enter review
                8) Quit
                """);
        int option;
        do{
            System.out.println("Enter option:");
            option = sc.nextInt();
            switch(option){
                case 1 -> listMovies();
                //case 2 -> viewMovieDetails();
                case 2 -> viewMovieTimeslots();
                case 3 -> viewSeats();
                case 4 -> selectSeats();
                case 5 -> bookTickets();
                case 6 -> viewBookingHistory();
                case 7 -> enterReview();
            }
        } while(option<8);
    }
    private void listMovies(){
        //Displayable movieListing = new MovieListing();
        movieListing.display();
    }
    /*private void viewMovieDetails(){
        //Displayable movieDetails = new MovieDetailsDisplayer();
        movieDetailsDisplayer.display();
    }*/
    private void viewMovieTimeslots(){
        //Displayable movieTimeSlots = new MovieTimeSlotList();
        movieTimeSlotList.display();
    }
    private void viewSeats(){
        seatSelector.showSeats();
    }
    private void selectSeats(){
        seatSelector.selectSeat();
    }
    private void bookTickets(){
        //currentUser.addTicket(new MovieTicket());
        //currentUser.getTicket();
    }
    private void viewBookingHistory(){
        //Displayable bookingHistory = new BookingHistory();
        //bookingHistory.display();
    }
    private void enterReview(){}
}
