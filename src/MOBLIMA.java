import interfaces.User;

import java.util.Scanner;
public class MOBLIMA {
    private static User user;
    //private static ArrayList<String> userTypes;
    public static void main(String[] args){
        //userTypes = {};
        selectUserDomain();
        //user.start();
    }
    private static void selectUserDomain(){
        Scanner sc = new Scanner(System.in);
        System.out.println("""
                Welcome to MOBALIMA
                1) Staff
                2) MovieGoer""");
        int choice;
        do{
            System.out.println("Select domain (1 or 2)");
            choice = sc.nextInt();
        }while(choice<1 | choice >2);
        switch(choice){
            case 1 -> user = new CinemaStaff();
            case 2 -> {
                MovieGoerApp movieGoerApp = new MovieGoerApp();
                movieGoerApp.use();
            }
        }
    }
}
