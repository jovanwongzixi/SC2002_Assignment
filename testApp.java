package movies;

import java.util.ArrayList;

public class testApp {
    public static void main(String[] args) {
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
        float price;

        MovieTicketPrice movieTicketPrice = new MovieTicketPrice(date, time, is3D, isBlockbuster);
        System.out.println("Final price is: " + movieTicketPrice.getModifiedPrice());
        System.out.println("\n\n");


        // ================= CSVReader Test =================

        MovieDataReader mvdr = new MovieDataReader();
        ArrayList<Movie> movieArrayList = new ArrayList<>();
        movieArrayList = mvdr.readFile();

        MovieTimeSlotReader mvtsr = new MovieTimeSlotReader();
        ArrayList<MovieTimeSlot> movieTSArrayList = new ArrayList<>();
        movieTSArrayList = mvtsr.readFile();
       
        for (int i = 0; i < movieArrayList.size(); i++){
            movieArrayList.get(i).printInfo();
            movieTSArrayList.get(i).printInfo();
            MovieTicketPrice mvtp = new MovieTicketPrice(movieTSArrayList.get(i).getDate(), movieTSArrayList.get(i).getTime(), movieArrayList.get(i).getMovieType(), movieArrayList.get(i).getMovieBlockbuster());
            price = mvtp.getModifiedPrice();
            System.out.println("Final price = " + price + "\n\n");
        }
    }
}
