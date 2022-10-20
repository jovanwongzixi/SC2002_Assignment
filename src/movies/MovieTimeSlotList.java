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
        for(Cineplex cineplex : cineplexList.getCineplexArray()){
            String cineplexName = cineplex.getName();
            System.out.println(cineplexName);
            for(MovieTimeSlot slot : slots){
                if(Objects.equals(slot.getCineplex().getName(), cineplexName)&& Objects.equals(slot.getMovie().getTitle(), title) && Objects.equals(slot.getDate(), date)) {
                    System.out.print(slot.getTime() + " ");
                }
            }
            System.out.println();
        }
    }
}
