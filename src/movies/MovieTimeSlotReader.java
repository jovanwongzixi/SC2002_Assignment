package movies;

import java.io.*;
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
    protected ArrayList<MovieTimeSlot> readBin(){
        ArrayList<MovieTimeSlot> slots = new ArrayList<>();
        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/data/movieTimeSlotCSV.dat"));
            slots = (ArrayList<MovieTimeSlot>) in.readObject();
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return slots;
    }
}
