package movies;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class CsvToBin {
    public static void main(String[] args){
        //timeSlotConverter();
        movieConverter();
    }
    private static void timeSlotConverter(){
        MovieTimeSlotReader reader = new MovieTimeSlotReader();
        ArrayList<MovieTimeSlot> array = reader.readFile();
        try{
            FileOutputStream fileOut = new FileOutputStream("src/data/movieTimeSlotCSV.dat");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(array);
            out.close();
            fileOut.close();
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
    }
    private static void movieConverter(){
        MovieDataUpdater reader = new MovieDataUpdater();
        ArrayList<Movie> array = reader.readFile();
        try{
            FileOutputStream fileOut = new FileOutputStream("src/data/movieCSV.dat");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(array);
            out.close();
            fileOut.close();
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
