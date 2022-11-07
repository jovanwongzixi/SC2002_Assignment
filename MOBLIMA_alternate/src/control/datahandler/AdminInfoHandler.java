package control.datahandler;

import entity.Admin;
import interfaces.User;
import interfaces.UserInfoHandler;

import java.io.*;
import java.util.HashMap;

public class AdminInfoHandler implements UserInfoHandler {
    @Override
    public HashMap<String, Admin> retrieve() {
        HashMap<String, Admin> data = new HashMap<>();
        try {
            String fileName = "MOBLIMA_alternate/bin/data/Admin.dat";
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fis);
            data = (HashMap<String, Admin>)in.readObject();
            in.close();
        } catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public <T extends User> void save(HashMap<String, T> inputMap) {
        try {
            String fileName = "MOBLIMA_alternate/bin/data/Admin.dat";
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
