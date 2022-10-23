package cinemas;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/*
public class Cineplex {
    private String name;
    private ArrayList<Cinema> cinemaArray; //use ArrayList for array with dynamic size
    //Let Cinema be Cinema 1, 2, 3 etc.
    public Cineplex(String[] values){
        this.name = values[0];
        this.cinemaArray = new ArrayList<Cinema>(Integer.parseInt(values[1]));
    }
    public String getName(){
        return this.name;
    }
}
*/
public class Cineplex implements Serializable {
    private static final long serialVersionUID = 4L;
    private String name;
    private List<Cinema> cinemaArray;

    public Cineplex(String name, List<Cinema> cinema) {
        this.name = name;
        this.cinemaArray = cinema;
    }
    public Cineplex(String[] values){
        this.name = values[0];
        this.cinemaArray = new ArrayList<Cinema>(Integer.parseInt(values[1]));
    }



    public String getName() {
        return this.name;
    }

    public List<Cinema> getCinema(){
        return this.cinemaArray;
    }
}