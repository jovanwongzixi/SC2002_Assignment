package movies;

import cinemas.Cineplex;
import cinemas.CineplexList;
import interfaces.Displayable;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;

public class MovieTimeSlotList implements Displayable {
    private ArrayList<MovieTimeSlot> slots;
    private ArrayList<MovieTimeSlot> tempList;
    private MovieListing movieListing;
    private CineplexList cineplexList;
    public MovieTimeSlotList(Displayable movieListing){
        if(movieListing instanceof MovieListing) this.movieListing = (MovieListing) movieListing;
        MovieTimeSlotReader reader = new MovieTimeSlotReader();
        this.slots = reader.readBin();
        for(MovieTimeSlot slot : slots) slot.setLayout();
        this.cineplexList = new CineplexList();
        tempList = new ArrayList<>();
    }
    private void selectMovieAndCineplex(){
        Scanner sc = new Scanner(System.in);
        int option, i=0;
        ArrayList<String> movieTitles = movieListing.getMovieTitles();
        for(String movieTitle : movieTitles){
            System.out.println(++i + ") " + movieTitle);
        }
        System.out.println("Select movie: ");
        option = sc.nextInt();
        String title = movieTitles.get(option-1);

        ArrayList<String> cineplexNames = cineplexList.getCineplexNames();
        i = 0;
        for(String cineplexName : cineplexNames){
            System.out.println(++i + ") " + cineplexName);
        }
        System.out.println("Select cineplex: ");
        option = sc.nextInt();
        String cineplex = cineplexNames.get(option-1);

        for(MovieTimeSlot slot : slots){
            if(Objects.equals(slot.getCineplex().getName(), cineplex) && Objects.equals(slot.getMovie().getTitle(), title)) tempList.add(slot);
        }

    }
    private void selectDate(){
        Scanner sc = new Scanner(System.in);
        int option, i=0;
        HashSet<LocalDate> datesSet = new HashSet<>();
        ArrayList<LocalDate> datesList = new ArrayList<>();
        for(MovieTimeSlot slot : tempList){
            LocalDate date = slot.getShowDate();
            if(!datesSet.contains(date)){
                datesSet.add(date);
                System.out.println(++i + ") " + date + " "+date.getDayOfWeek());
                datesList.add(date);
            }
        }
        System.out.println("Select date :");
        option = sc.nextInt();
        tempList.removeIf(slot -> !Objects.equals(slot.getShowDate(), datesList.get(option - 1)));
    }
    public void display(){
        Scanner sc = new Scanner(System.in);
        tempList.clear();
        selectMovieAndCineplex();
        selectDate();
        if(!tempList.isEmpty()){
            System.out.println("Movie: "+ tempList.get(0).getTitle());
            System.out.println("Cineplex: "+ tempList.get(0).getCineplex().getName());
            System.out.print("Showtime(s): ");
            for(MovieTimeSlot slot: tempList){
                System.out.print(slot.getShowTime() + " ");
            }
            System.out.println();
        }
        else{
            System.out.println("No timeslot");
        }
        //Checking for matching title, date for each cineplex (Could think of a way to sort for faster access time)
        //for(Cineplex cineplex : cineplexList.getCineplexArray()){
           // String cineplexName = cineplex.getName();
        /*
        System.out.println();
            System.out.println(cineplexName);
            for(MovieTimeSlot slot : slots){
                if(Objects.equals(slot.getCineplex().getName(), cineplexName)&& Objects.equals(slot.getMovie().getTitle(), title) && Objects.equals(slot.getDate(), date)) {
                    System.out.print(slot.getTime() + " ");
                }
            }
            System.out.println();*/
        //}
    }
    public MovieTimeSlot selectTimeSlot(){
        Scanner sc = new Scanner(System.in);
        int option, i=0;
        tempList.clear();
        selectMovieAndCineplex();
        selectDate();
        if(!tempList.isEmpty()){
            for(MovieTimeSlot slot: tempList){
                System.out.println(++i + ") " + slot.getShowTime());
            }
            System.out.println("Select time: ");
            option = sc.nextInt();
            return tempList.get(option-1);
        }
        else{
            System.out.println("No timeslot");
        }
        /*System.out.println("Enter cineplex name: ");
        String cineplexName = sc.nextLine();
        System.out.println("Enter movie title: ");
        String title = sc.nextLine();
        System.out.println("Enter date :");
        String date = sc.nextLine();
        System.out.println("Enter time :");
        String time = sc.nextLine();
        for(MovieTimeSlot slot : slots){
            if(Objects.equals(slot.getCineplex().getName(), cineplexName)&& Objects.equals(slot.getMovie().getTitle(), title) && Objects.equals(slot.getDate(), date) && Objects.equals(slot.getTime(), time)) {
                return slot;
            }
        }*/
        return null; //implement null exception handler here
    }
}
