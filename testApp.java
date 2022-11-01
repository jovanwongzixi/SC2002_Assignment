package movies;

import java.io.IOException;
import java.util.ArrayList;

public class testApp {
    public static void main(String[] args) throws IOException {
        /* 
         * Variables - date, time, is3D, isBlockbuster
         * - Day of Week: 
         *     Monday - Friday --> Weekday price ($7 base price)
         *     Weekends --> + $4
         * - Time: After 6pm --> + $1.50
         * - 3D --> Base price change to $11
         * - Blockbuster(?) --> + $1
         */

        // ================= Hardcode Test =================

        System.out.println("\n===============================================================");
        String date =  "2022-10-24"; // Found in MovieTimeSlot.csv
        String time = "18:59"; // Found in MovieTimeSlot.csv
        String is3D = "false"; // Found in MovieData.csv
        String isBlockbuster = "false"; // Found in MovieData.csv
        AgeType ageType = AgeType.ADULT;
        float price;

        MovieTicketPrice movieTicketPrice = new MovieTicketPrice(date, time, is3D, isBlockbuster, ageType);
        System.out.println("Final price is: " + movieTicketPrice.getModifiedPrice());
        System.out.println("\n\n");


        // ================= CSVReader Test =================
        // Reads MovieData.csv, MovieTimeSlot.csv to get movie information
        // Compares movie information with the price modifiers in MovieTicketPricing.csv
        // Assumes AgeType is passed in as a parameter into MovieTicketPrice
        // Retrieves results

        MovieDataReader mvdr = new MovieDataReader();
        ArrayList<Movie> movieArrayList = new ArrayList<>();
        movieArrayList = mvdr.readFile();

        MovieTimeSlotReader mvtsr = new MovieTimeSlotReader();
        ArrayList<MovieTimeSlot> movieTSArrayList = new ArrayList<>();
        movieTSArrayList = mvtsr.readFile();

       
        for (int i = 0; i < movieArrayList.size(); i++){
            movieArrayList.get(i).printInfo();
            movieTSArrayList.get(i).printInfo();
            MovieTicketPrice mvtp = new MovieTicketPrice(movieTSArrayList.get(i).getDate(), movieTSArrayList.get(i).getTime(), movieArrayList.get(i).getMovieType(), movieArrayList.get(i).getMovieBlockbuster(), ageType);
            price = mvtp.getModifiedPrice();
            System.out.println("Final price = " + price + "\n\n");
        }


        // ================= Read and Update Ticket Modifier CSV Test =================

        // MovieTicketPriceUpdater mvtpu = new MovieTicketPriceUpdater();
        // String[] ticketModifiers = mvtpu.readTicketPricing();

        // System.out.println("Child price is: " + Float.parseFloat(ticketModifiers[0]));
        // System.out.println("Adult price is: " + Float.parseFloat(ticketModifiers[1]));
        // System.out.println("Senior price is: " + Float.parseFloat(ticketModifiers[2]));
        // System.out.println("Child 3D price is: " + Float.parseFloat(ticketModifiers[3]));
        // System.out.println("Adult 3D price is: " + Float.parseFloat(ticketModifiers[4]));
        // System.out.println("Senior 3D price is: " + Float.parseFloat(ticketModifiers[5]));
        // System.out.println("Weekend session increment is: " + Float.parseFloat(ticketModifiers[6]));
        // System.out.println("Night session increment is: " + Float.parseFloat(ticketModifiers[7]));
        // System.out.println("Blockbuster movie increment is: " + Float.parseFloat(ticketModifiers[8]));

        // mvtpu.updateTicketPricing(8, "2");
        // ticketModifiers = mvtpu.readTicketPricing();
        
        // System.out.println("\n");
        // System.out.println("Child price is: " + Float.parseFloat(ticketModifiers[0]));
        // System.out.println("Adult price is: " + Float.parseFloat(ticketModifiers[1]));
        // System.out.println("Senior price is: " + Float.parseFloat(ticketModifiers[2]));
        // System.out.println("Child 3D price is: " + Float.parseFloat(ticketModifiers[3]));
        // System.out.println("Adult 3D price is: " + Float.parseFloat(ticketModifiers[4]));
        // System.out.println("Senior 3D price is: " + Float.parseFloat(ticketModifiers[5]));
        // System.out.println("Weekend session increment is: " + Float.parseFloat(ticketModifiers[6]));
        // System.out.println("Night session increment is: " + Float.parseFloat(ticketModifiers[7]));
        // System.out.println("Blockbuster movie increment is: " + Float.parseFloat(ticketModifiers[8]));
    }
}
