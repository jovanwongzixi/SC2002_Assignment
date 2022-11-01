//currently trying MovieGoer use MovieGoerApp not sure what im doing


import interfaces.User;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
public class MovieGoer implements User, Serializable {
    @Serial
    private static final long serialVersionUID = 123L;
    private String name;
    private int mobileNumber;
    private String emailAddress;
    private AgeType ageType;
    //private ArrayList<MovieTicket> purchasedTickets;
    //private BookingHistory history;

    public MovieGoer(){
        //getDetails(); Commented out for easier testing
        //purchasedTickets = new ArrayList<>();
    }
    @Override
    public void start(){
        //not in use
        Scanner sc = new Scanner(System.in);
    }
    public void setDetails(){
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
        System.out.println("Enter age:");
        int age = sc.nextInt();
        if (age<21) ageType = AgeType.CHILD;
        else if(age>=60) ageType = AgeType.SENIOR_CITIZEN;
        else ageType = AgeType.ADULT;
    }
    public String getName() {
        return name;
    }

    public int getMobileNumber(){
        return this.mobileNumber;
    }
    public String getEmailAddress(){
        return this.emailAddress;
    }
    public AgeType getAgeType(){
        return this.ageType;
    }
    public void display(){
        System.out.println("""
            Movie Goer Details
            ------------------""");
        System.out.println("Name: " + getName());
        System.out.println("Mobile number: " +getMobileNumber());
        System.out.println("Email address: "+getEmailAddress());
        System.out.println("Age Type: " + getAgeType());
        System.out.println("------------------");
    }/*
    public void addTicket(MovieTicket ticket){
        purchasedTickets.add(ticket);
    }
    public void getTicket(){
        System.out.println(purchasedTickets.get(0));
    }*/
}
