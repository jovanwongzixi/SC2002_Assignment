package movies;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MovieTimeSlotReader {
    protected ArrayList<MovieTimeSlot> readFile(){
        ArrayList<MovieTimeSlot> slots = new ArrayList<>();
        try{
            FileReader frStream = new FileReader("src/data/MovieTimeSlot.csv");
            BufferedReader brStream = new BufferedReader(frStream);
            String inputLine;
            brStream.readLine();
            while ((inputLine = brStream.readLine()) != null) {
                String[] values = inputLine.split(",");
                MovieTimeSlot movieTimeSlot = new MovieTimeSlot(values);
                slots.add(movieTimeSlot);
            }
        }
        catch(IOException e){
            throw new RuntimeException();
        }
        return slots;
    }
}