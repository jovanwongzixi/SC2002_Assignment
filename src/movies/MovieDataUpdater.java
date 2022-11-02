package movies;

import java.io.*;
import java.util.ArrayList;

public class MovieDataUpdater {
    //private ArrayList<Movie> movieArrayList;
    //public MovieDataUpdater(){
      //   movieArrayList = new ArrayList<>();
    //}
    //read all movies
    protected ArrayList<Movie> readFile(){
        ArrayList<Movie> movieArrayList = new ArrayList<>();
        try {
            FileReader frStream = new FileReader("src/data/MovieData.csv");
            BufferedReader brStream = new BufferedReader(frStream);
            String inputLine;
            brStream.readLine();
            while ((inputLine = brStream.readLine()) != null) {
                String[] values = inputLine.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"); // Reads csv input by splitting on commas outside quotes
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
    protected ArrayList<Movie> readBin(){
        ArrayList<Movie> movieArrayList = new ArrayList<>();
        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/data/movieCSV.dat"));
            movieArrayList = (ArrayList<Movie>) in.readObject();
            System.out.println(movieArrayList.get(0).getTitle());
            in.close();
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return movieArrayList;
    }
    protected void writeBin(ArrayList<Movie> movieArrayList){
        try{
            FileOutputStream fileOut = new FileOutputStream("src/data/movieCSV.dat");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(movieArrayList);
            out.close();
            fileOut.close();
            //System.out.println("Layout serialised!");
            //System.out.println(layoutHashMap.get("0").get(0).getSeatState());
        }
        catch(IOException e){
            throw new RuntimeException(e);
        }
    }
}
