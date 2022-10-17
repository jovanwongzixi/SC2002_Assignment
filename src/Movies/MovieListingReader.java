package Movies;
import java.io.*;
import java.util.ArrayList;

public class MovieListingReader {
    public MovieListingReader(){
    }
    protected ArrayList<String> read() throws IOException{
        ArrayList<String> movieArray = new ArrayList<>();
        try{
            FileReader frStream = new FileReader("src/Movies/MovieListingData.txt");
            BufferedReader brStream = new BufferedReader(frStream);
            String inputLine=null;
            while((inputLine = brStream.readLine())!=null){
                movieArray.add(inputLine);
                //System.out.println(inputLine);
            }
            brStream.close();
        }
        catch(FileNotFoundException e){
            System.out.println( "Error opening the input file!"
                    + e.getMessage() );
        }
        return movieArray;
    }

}
