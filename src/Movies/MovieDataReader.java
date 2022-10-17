package Movies;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class MovieDataReader {
    protected Movie read(String ID){
        try{
            FileReader frStream = new FileReader("src/data/MovieData.csv");
            BufferedReader brStream = new BufferedReader(frStream);
            String inputLine=null;
            while((inputLine = brStream.readLine())!=null){
                String[] values = inputLine.split(",");
                Movie movie = new Movie(values);
                if(Objects.equals(movie.getID(), ID)){
                    brStream.close();
                    return movie;
                }
            }
            brStream.close();
        } catch(IOException e){
            System.out.println( "Error opening the input file!"
                    + e.getMessage() );
        }
        return null;
    }
}
