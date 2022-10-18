package cinemas;
import java.util.ArrayList;
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
