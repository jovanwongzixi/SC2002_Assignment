import java.util.Scanner;
public class MovieGoer extends User{
    private String name;
    private int mobileNumber;
    private String emailAddress;
    @Override
    public void start(){
        getDetails();
        System.out.println("""
                MovieGoer options menu
                1) List movies
                2) View movie details
                3) View movie timeslots
                4) View/select seats
                5) Book ticket
                6) View booking history
                7) Enter review
                8) Quit
                """);
        Scanner sc = new Scanner(System.in);
        int option;
        do{
            System.out.println("Enter option:");
            option = sc.nextInt();
            switch(option){
                case 1 -> listMovies();
                case 2 -> viewMovieDetails();
            }
        } while(option<7);


    }
    private void getDetails(){
        Scanner sc = new Scanner(System.in);
        System.out.println("MovieGoer Details\n"+
                "-------------------------");
        System.out.println("Enter name:");
        name = sc.nextLine();
        System.out.println("Enter mobile number:");
        mobileNumber = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter email address:");
        emailAddress = sc.nextLine();
    }
    private void listMovies(){

    }
    private void viewMovieDetails(){

    }
}
