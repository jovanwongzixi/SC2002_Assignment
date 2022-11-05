package control.customer;

import control.SerializeDB;
import entity.Customer;
import interfaces.Handler;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class CustomerAccountManager implements Handler {
    private Customer currentUser;
    public CustomerAccountManager(){
        currentUser = null;
    }
    public Customer start(){
        Scanner sc = new Scanner(System.in);
        int options=0;
        do {
            System.out.println("""
                    ------Customer Account Menu-------
                    1) Login
                    2) Create account
                    3) Guest Account
                    """);
            options = sc.nextInt();
            switch (options){
                case 1 -> getAccount();
                case 2 -> createAccount();
                case 3 -> inputDetails();
            }
        }while(options>3 || options<1);
        return currentUser;
    }
    public void getAccount(){
        Scanner sc = new Scanner(System.in);
        Map<String, Customer> customers = SerializeDB.getMap("Customer");
        System.out.println("Enter email address:");
        String email = sc.nextLine();
        System.out.println("Enter mobile number:");
        String number = sc.nextLine();
        try{
            if(Objects.equals(customers.get(email).getMobileNumber(), number)) currentUser = customers.get(email);
        }
        catch (NullPointerException e){
            System.out.println("Invalid account!");
        }
        //return currentUser;
    }
    public void createAccount(){
        inputDetails();
        Map<String, Customer> customers = SerializeDB.getMap("Customer");
        if(customers==null) customers = new HashMap<>();
        customers.put(currentUser.getEmailAddress(),currentUser);
        SerializeDB.writeMap("Customer", customers);
        //return currentUser;
    }
    public void inputDetails(){
        currentUser = new Customer();
        currentUser.setDetails();
    }
}
