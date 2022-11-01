package movies;

import java.time.LocalDate;


public class MovieTicketPrice {
    
    private String date;
    private String time;
    private boolean is3D;
    private boolean isBlockbuster;
    private AgeType ageType;


    public MovieTicketPrice(String date, String time, String is3D, String isBlockbuster, AgeType ageType){
        this.date = date;
        this.time = time;
        this.is3D = Boolean.parseBoolean(is3D);
        this.isBlockbuster = Boolean.parseBoolean(isBlockbuster);
        this.ageType = ageType;
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
        // [0] childPrice, [1] adultPrice, [2] seniorPrice, [3] child3DPrice, [4] adult3DPrice, [5] senior3DPrice, 
        // [6] weekendIncr, [7], nightIncr, [8] blockbusterIncr
        /* 
         * Day of Week: 
         *     Monday - Friday --> Weekday price
         *     Weekends --> weekendIncr
         * Time: After 6pm --> nightIncr
         * 3D --> Changed base price to [3] child3DPrice, [4] adult3DPrice or [5] senior3DPrice
         * Blockbuster(?) --> blockbusterIncr
         */

        MovieTicketPriceUpdater mvtpu = new MovieTicketPriceUpdater();
        String[] ticketModifiers = mvtpu.readTicketPricing();

        float price = Float.parseFloat(ticketModifiers[1]); // Adult price

        // Special pricing for child/seniors on weekdays (day session)
        if (this.ageType != AgeType.ADULT){
            if (getTypeDay(this.date) < 6 && dayOrNightSession(this.time) == 0){
                switch (this.ageType){
                    case CHILD -> {
                        price = Float.parseFloat(ticketModifiers[0]); // Child basic price
                        if (this.is3D) price = Float.parseFloat(ticketModifiers[3]); // Child 3D price
                    }
                    case SENIOR_CITIZEN -> {
                        price = Float.parseFloat(ticketModifiers[2]); // Seniors basic price
                        if (this.is3D) price = Float.parseFloat(ticketModifiers[5]); // Seniors 3D price
                    }
                }
            }
        }

        // If movie is 3D
        if (this.ageType == AgeType.ADULT && this.is3D)
            price = Float.parseFloat(ticketModifiers[4]); // Adult 3D price
        // If movie is on weekends
        if (getTypeDay(this.date) > 5)
            price += Float.parseFloat(ticketModifiers[6]); // Adult price + weekend session increment
        // If movie is a night session
        if (dayOrNightSession(this.time) == 1)
            price += Float.parseFloat(ticketModifiers[7]); // Adult price + night session increment
        // If movie is a blockbuster
        if (this.isBlockbuster)
            price += Float.parseFloat(ticketModifiers[8]); // Child/Adult/Senior price + blockbuster movie increment
        
        return price;
    }
}
