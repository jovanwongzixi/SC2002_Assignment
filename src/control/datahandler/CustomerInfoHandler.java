package control.datahandler;

import entity.Customer;
import interfaces.User;
import interfaces.UserInfoHandler;

import java.io.*;
import java.util.HashMap;

public class CustomerInfoHandler implements UserInfoHandler {
    @Override
    public HashMap<String, Customer> retrieve() {
        HashMap<String, Customer> data = new HashMap<>();
        try {
            String fileName = "../bin/data/Customer.dat";
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fis);
            data = (HashMap<String, Customer>)in.readObject();
            in.close();
        } catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return data;
    }
    public <T extends User> void save(HashMap<String,T> inputMap){
        try {
            String fileName = "../bin/data/Customer.dat";
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(inputMap);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
