package movies;

import cinemas.Cineplex;
import cinemas.CineplexList;
import interfaces.Displayable;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class MovieTimeSlotList implements Displayable {
    private ArrayList<MovieTimeSlot> slots;
    private CineplexList cineplexList;
    public MovieTimeSlotList(){
        MovieTimeSlotReader reader = new MovieTimeSlotReader();
        this.slots = reader.readFile();
        this.cineplexList = new CineplexList();
    }
    public void display(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter movie title: ");
        String title = sc.nextLine();
        System.out.println("Enter date :");
        String date = sc.nextLine();
        System.out.println("Enter cineplex name: ");
        String cineplexName = sc.nextLine();
        //Checking for matching title, date for each cineplex (Could think of a way to sort for faster access time)
        //for(Cineplex cineplex : cineplexList.getCineplexArray()){
           // String cineplexName = cineplex.getName();
            System.out.println(cineplexName);
            for(MovieTimeSlot slot : slots){
                if(Objects.equals(slot.getCineplex().getName(), cineplexName)&& Objects.equals(slot.getMovie().getTitle(), title) && Objects.equals(slot.getDate(), date)) {
                    System.out.print(slot.getTime() + " ");
                }
            }
            System.out.println();
        //}
    }
    public MovieTimeSlot selectTimeSlot(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter cineplex name: ");
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
        }
        return null; //implement null exception handler here
    }
}
