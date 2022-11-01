package movies;

import java.io.*;
import java.util.ArrayList;

public class MovieListingWriter {
    protected void write(ArrayList<String> movieArray) throws IOException {
        try{
            FileWriter fwStream = new FileWriter("data/MovieData.csv");
            BufferedWriter bwStream = new BufferedWriter(fwStream);
        }
        catch(FileNotFoundException e){
            throw new RuntimeException(e);
        }

    }
}
