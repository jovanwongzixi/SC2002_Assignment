package cinemas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CineplexReader {
    protected ArrayList<Cineplex> readFile(){
        ArrayList<Cineplex> cineplexes = new ArrayList<>();
        try{
            FileReader frStream = new FileReader("src/data/Cineplex.csv");
            BufferedReader brStream = new BufferedReader(frStream);
            String inputLine;
            brStream.readLine();
            while ((inputLine = brStream.readLine()) != null) {
                String[] values = inputLine.split(",");
                Cineplex cineplex = new Cineplex(values);
                cineplexes.add(cineplex);
            }
        }
        catch(IOException e){
            throw new RuntimeException();
        }
        return cineplexes;
    }
}
