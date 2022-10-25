package movies;

import java.time.LocalDate;

public class MovieTicketPrice {
    //private float price;
    private String date;
    private String time;
    private boolean is3D;
    private boolean isBlockbuster;
    //private float priceModifier; // To make changes to price based on 


    public MovieTicketPrice(String date, String time, String is3D, String isBlockbuster){
        this.date = date;
        this.time = time;
        this.is3D = Boolean.parseBoolean(is3D);
        this.isBlockbuster = Boolean.parseBoolean(isBlockbuster);
    }


    // Function to get the day of week as a number
    public int getTypeDay(String dateString){
        // 1 - Monday ... 7 - Sunday 

        LocalDate date = LocalDate.parse(dateString); // Parse dateString to LocalDate, but may need to change the MovieTimeSlot date format, e.g. "2018-05-05" (TBD)
        java.time.DayOfWeek day = date.getDayOfWeek();

        System.out.println("Day value is: " + day.getValue());
        return day.getValue();
    }


    // Function to check if it is a day/night session
    public int dayOrNightSession(String time){
        /*
         * Day --> 0 
         * Night --> 1
         */

        String[] timeVal = time.split(":");
        int hours = Integer.parseInt(timeVal[0]);

        System.out.println("Hours value is: " + Integer.parseInt(timeVal[0]));
        if (hours >= 18)
            return 1;
        else
            return 0;
    }


    // Function to get modified price based on day, time, type of movie
    public float getModifiedPrice(){

        /* 
         * Day of Week: 
         *     Monday - Friday --> Weekday price ($7)
         *     Weekends --> + $4
         * Time: After 6pm --> + $1.50
         * 3D --> Base price change to $11
         * Blockbuster(?) --> + $1
         */

        float price = 7;

        //System.out.println("3D value is: " + this.is3D);
        //System.out.println("Blockbuster value is: " + this.isBlockbuster);

        // If movie is 3D
        if (this.is3D)
            price = 11;
        // If movie is on weekends
        if (getTypeDay(this.date) > 5)
            price += 4;
        // If movie is a night session
        if (dayOrNightSession(this.time) == 1)
            price += 1.50;
        // If movie is a blockbuster
        if (this.isBlockbuster)
            price += 1;
        
        return price;
    }
}
