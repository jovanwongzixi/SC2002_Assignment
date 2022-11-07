package entity;

import interfaces.SerializedData;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Customer implements SerializedData {
    private String name;
    private String mobileNumber;
    private String emailAddress;
    //private AgeGroup ageType;
    public void setDetails(){
        Scanner sc = new Scanner(System.in);
        /*System.out.println("MovieGoer Details\n"+
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
        if (age<21) ageType = AgeGroup.CHILD;
        else if(age>=60) ageType = AgeGroup.SENIOR_CITIZEN;
        else ageType = AgeGroup.ADULT;*/
        System.out.println("Enter your name: ");
        name = sc.nextLine();

        do {
            System.out.println("Enter your email address: ");
            emailAddress = sc.nextLine();

            if (!emailMatches(emailAddress)) {
                System.out.println("Invalid email address. Please try again!");
            }
        } while (!emailMatches(emailAddress));

        do {
            System.out.println("Enter your mobile number: ");
            mobileNumber = sc.nextLine();
            if (!mobileMatches(mobileNumber)) {
                System.out.println("Invalid mobile number. Please try again!");
            }
        } while (!mobileMatches(mobileNumber));
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getName() {
        return name;
    }


    private boolean mobileMatches(String mobileNum) {
        return Pattern.compile("[8-9][0-9]{7}").matcher(mobileNum).matches();
    }
    private boolean emailMatches(String emailAddress) {
        return Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")
                .matcher(emailAddress)
                .matches();
    }
}
