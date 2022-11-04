package control.customer;

import control.SerializeDB;
import entity.Customer;

import java.util.Map;
import java.util.Scanner;

public class CustomerAccountManager {
    public void getAccount(){
        Map<String, Customer> customers = SerializeDB.getMap("Customer");

    }
    public void createAccount(){

    }
    public void inputDetails(){
        /*Scanner sc = new Scanner(System.in);
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
        else ageType = AgeType.ADULT;*/
    }
}
