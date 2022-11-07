package entity;

import interfaces.User;

import java.io.Serial;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Customer implements User {
    @Serial
    private static final long serialVersionUID = -4247603318904719990L;
    private String name;
    private String mobileNumber;
    private String emailAddress;
    //private AgeGroup ageType;
    public void setDetails(){
        Scanner sc = new Scanner(System.in);
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
