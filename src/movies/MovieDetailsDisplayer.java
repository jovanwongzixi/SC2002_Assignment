package movies;
import interfaces.Displayable;
import java.util.Scanner;
public class MovieDetailsDisplayer implements Displayable {
    public void display(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter movie ID: ");
        MovieListing listing= new MovieListing();
        listing.getMovie(sc.nextLine()).printInfo();
    }
}
