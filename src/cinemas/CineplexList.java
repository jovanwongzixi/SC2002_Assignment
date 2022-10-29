package cinemas;

import interfaces.Displayable;

import java.util.ArrayList;
import java.util.Objects;

public class CineplexList implements Displayable {
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
    public ArrayList<String> getCineplexNames(){
        ArrayList<String> cineplexNames = new ArrayList<>();
        for(Cineplex cineplex : cineplexArray){
            cineplexNames.add(cineplex.getName());
        }
        return cineplexNames;
    }
    public int listSize(){
        return cineplexArray.size();
    }
    public void display(){
        System.out.println("Cineplex list:");
        for(int i=0; i< cineplexArray.size(); i++){
            System.out.println((i+1) + ") "+cineplexArray.get(i));
        }
    }
}