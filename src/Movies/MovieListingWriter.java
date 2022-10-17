package Movies;

import java.io.*;
import java.util.ArrayList;

public class MovieListingWriter {
    protected void write(ArrayList<String> movieArray) throws IOException {
        try{
            FileWriter fwStream = new FileWriter("src/Movies/MovieListingData.txt");
            BufferedWriter bwStream = new BufferedWriter(fwStream);
        }
        catch(FileNotFoundException e){

        }

    }
}
