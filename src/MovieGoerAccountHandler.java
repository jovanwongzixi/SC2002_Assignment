import movies.Movie;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MovieGoerAccountHandler {
    private HashMap<String, MovieGoer> movieGoers; //use email address as key value for O(1) access
    public MovieGoerAccountHandler(){
        retrieveData();
    }
    public boolean addAccount(MovieGoer user){
        if(movieGoers==null) movieGoers = new HashMap<>();
        if(movieGoers.get(user.getEmailAddress())== null) {
            movieGoers.put(user.getEmailAddress(), user);
            try {
                FileOutputStream fileOut = new FileOutputStream("src/data/MovieGoer.dat");
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(movieGoers);
                out.close();
                fileOut.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return true;
        }
        else return false;
    }
    public MovieGoer getAccount(String email, int number){
        MovieGoer user = null;
        if(movieGoers.get(email)!=null && movieGoers.get(email).getMobileNumber()==number) user = movieGoers.get(email);
        return user;
    }
    private void retrieveData(){
        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/data/MovieGoer.dat"));
            movieGoers = (HashMap<String, MovieGoer>) in.readObject();
            in.close();
        } catch (ClassNotFoundException | IOException e) {
            movieGoers = new HashMap<>();
        }
    }
}
