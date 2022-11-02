import cinemas.Cinema;
import cinemas.Cineplex;
import interfaces.Displayable;
import movies.Movie;
import movies.MovieTimeSlot;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BookingHistory implements Displayable {
    private String transactionID;
    private int numOfTickets;
    private double totalCost;
    //private MovieGoer movieGoer;
    private MovieTimeSlot timeSlot;
    public void setTransactionID(){ //need to add cinema code (not done yet)
        //LocalDate date = LocalDate.now();
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        transactionID = timeSlot.getCineplex().getCode() + dateTime.format(formatter);
        System.out.println(transactionID);
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public void setNumOfTickets(int numOfTickets) {
        this.numOfTickets = numOfTickets;
    }

    public void setTimeSlot(MovieTimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    public void display(){
        System.out.println("Transaction ID: "+ transactionID);
        System.out.println("Total Cost: " + totalCost);
        timeSlot.display();
        System.out.println("---------------");
    }
}
