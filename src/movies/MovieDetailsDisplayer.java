package movies;
import interfaces.Displayable;
import java.util.Scanner;
public class MovieDetailsDisplayer implements Displayable {
    MovieListing listing;
    public MovieDetailsDisplayer(){}
    public MovieDetailsDisplayer(MovieListing movieListing){
        this.listing = movieListing;
    }
    public void display(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter movie title: ");
        //MovieListing listing= new MovieListing();
        listing.getMovie(sc.nextLine()).printInfo();
    }
}
