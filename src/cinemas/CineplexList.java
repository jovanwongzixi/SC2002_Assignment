package cinemas;

import java.util.ArrayList;

public class CineplexList {
    ArrayList<Cineplex> cineplexArray;
    public CineplexList(){
        CineplexReader cineplexReader = new CineplexReader();
        this.cineplexArray = cineplexReader.readFile();
    }
}
