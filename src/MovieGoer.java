//currently trying MovieGoer use MovieGoerApp not sure what im doing


import interfaces.User;
import movies.MovieDetailsDisplayer;
import movies.MovieListing;
import interfaces.Displayable;
import movies.MovieTicket;
import movies.MovieTimeSlotList;

import java.util.ArrayList;
import java.util.Scanner;
public class MovieGoer implements User {
    private String name;
    private int mobileNumber;
    private String emailAddress;
    private AgeType ageType;
    private ArrayList<MovieTicket> purchasedTickets;
    //private BookingHistory history;

    public MovieGoer(){
        //getDetails(); Commented out for easier testing
        purchasedTickets = new ArrayList<>();
    }
    @Override
    public void start(){
        Scanner sc = new Scanner(System.in);
        MovieGoerApp app = new MovieGoerApp(this);
        app.use();
        /*
        System.out.println("""
                MovieGoer options menu
                1) List movies
                2) View movie details
                3) View movie timeslots
                4) View/select seats
                5) Book ticket
                6) View booking history
                7) Enter review
                8) Quit
                """);
        //Scanner sc = new Scanner(System.in);
        int option;
        do{
            System.out.println("Enter option:");
            option = sc.nextInt();
            switch(option){
                case 1 -> listMovies();
                case 2 -> viewMovieDetails();
                case 3 -> viewMovieTimeslots();
                case 4 -> selectSeats();
                case 5 -> bookTickets();
                case 6 -> viewBookingHistory();
                case 7 -> enterReview();
            }
        } while(option<7);*/
    }
    private void getDetails(){
        Scanner sc = new Scanner(System.in);
        System.out.println("MovieGoer Details\n"+
                "-------------------------");
        System.out.println("Enter name:");
        name = sc.nextLine();
        System.out.println("Enter mobile number:");
        mobileNumber = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter email address:");
        emailAddress = sc.nextLine();
        System.out.println("Enter age:");
        int age = sc.nextInt();
        if (age<21) ageType = AgeType.CHILD;
        else if(age>=60) ageType = AgeType.SENIOR_CITIZEN;
        else ageType = AgeType.ADULT;
    }/*
    private void listMovies(){
        Displayable movieListing = new MovieListing();
        movieListing.display();
    }
    private void viewMovieDetails(){
        Displayable movieDetails = new MovieDetailsDisplayer();
        movieDetails.display();
    }
    private void viewMovieTimeslots(){
        Displayable movieTimeSlots = new MovieTimeSlotList();
        movieTimeSlots.display();
    }
    private void selectSeats(){

    }
    private void bookTickets(){
    }
    private void viewBookingHistory(){
        Displayable bookingHistory = new BookingHistory();
        bookingHistory.display();
    }
    private void enterReview(){}
    public String getName(){
        return this.name;
    }*/
    public int getMobileNumber(){
        return this.mobileNumber;
    }
    public String getEmailAddress(){
        return this.emailAddress;
    }
    public AgeType getAgeType(){
        return this.ageType;
    }
}
