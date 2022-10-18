package cinemas;
import java.util.ArrayList;
public class Cineplex {
    private String location;
    private ArrayList<Cinema> cinemaArray; //use ArrayList for array with dynamic size
    public Cineplex(String location){
        this.location = location;
        this.cinemaArray = new ArrayList<Cinema>();
    }
    public String getLocation(){
        return this.location;
    }
}
