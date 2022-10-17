package Movies;
import java.io.*;
import java.util.ArrayList;

public class MovieListingReader {
    protected ArrayList<Movie> readFile() throws IOException{
        ArrayList<Movie> movieArrayList = new ArrayList<>();
        try{
            FileReader frStream = new FileReader("src/data/MovieData.csv");
            BufferedReader brStream = new BufferedReader(frStream);
            String inputLine;
            while((inputLine = brStream.readLine())!=null){
                String[] values = inputLine.split(",");
                Movie movie = new Movie(values);
                movieArrayList.add(movie);
            }
            brStream.close();
        }
        catch(FileNotFoundException e){
            System.out.println( "Error opening the input file!"
                    + e.getMessage() );
        }
        return movieArrayList;
    }

}
