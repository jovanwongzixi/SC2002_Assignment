package cinemas;

import java.util.ArrayList;
import java.util.Objects;

public class CineplexList {
    ArrayList<Cineplex> cineplexArray;
    public CineplexList(){
        CineplexReader cineplexReader = new CineplexReader();
        this.cineplexArray = cineplexReader.readFile();
    }
    /*public static void main(String[] args){
        CineplexList list = new CineplexList();
        for(Cineplex cineplex: list.cineplexArray){
            System.out.println(cineplex.getName());
        }
    }*/
    public Cineplex getCineplex(String cineplexName){
        for(Cineplex cineplex: this.cineplexArray){
            if(Objects.equals(cineplex.getName(), cineplexName)){
                return cineplex;
            }
        }
        return null; //consider throwing exception here
    }
}