package control.datahandler;

import entity.Customer;
import interfaces.SerializedData;
import interfaces.UserInfoHandler;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class CustomerInfoHandler implements UserInfoHandler {
    @Override
    public HashMap<String, Customer> retrieve() {
        HashMap<String, Customer> data = new HashMap<>();
        try {
            String fileName = "bin/data/Customer.dat";
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fis);
            data = (HashMap<String, Customer>)in.readObject();
            in.close();
        } catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return data;
    }
    public <T extends SerializedData> void save(HashMap<String,T> inputMap){
        try {
            String fileName = "bin/data/Customer.dat";
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(inputMap);
            //System.out.println(inputList.get(0).getClass());
            //System.out.println("Data index 0: " + inputList.get(0));
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
