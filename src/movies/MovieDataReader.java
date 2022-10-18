package movies;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class MovieDataReader {
    //read single movie
    /*
    protected String[] readFile(String ID){
        try{
            FileReader frStream = new FileReader("src/data/MovieData.csv");
            BufferedReader brStream = new BufferedReader(frStream);
            String inputLine=null;
            while((inputLine = brStream.readLine())!=null){
                String[] values = inputLine.split(",");
                if(Objects.equals(values[0], ID)){
                    brStream.close();
                    return values;
                }
            }
            brStream.close();
        } catch(IOException e){
            System.out.println( "Error opening the input file!"
                    + e.getMessage() );
        }
        return null;
    }*/
    //read all movies
    protected ArrayList<Movie> readFile(){
        ArrayList<Movie> movieArrayList = new ArrayList<>();
        try {
            FileReader frStream = new FileReader("src/data/MovieData.csv");
            BufferedReader brStream = new BufferedReader(frStream);
            String inputLine;
            brStream.readLine();
            while ((inputLine = brStream.readLine()) != null) {
                String[] values = inputLine.split(",");
                Movie movie = new Movie(values);
                movieArrayList.add(movie);
            }
            brStream.close();
        }
        catch(IOException e){
            throw new RuntimeException();
        }
        return movieArrayList;
    }
}
