package control.datahandler;

import entity.cinema.Cineplex;
import interfaces.DataHandler;
import interfaces.SerializedData;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CineplexDataHandler implements DataHandler {
    public List<Cineplex> retrieve(){
        List<Cineplex> data = new ArrayList<>();
        try {
            String fileName = "MOBLIMA_alternate/bin/data/Cineplex.dat";
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fis);
            data = (List<Cineplex>)in.readObject();
            in.close();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return data;
    }

    @Override
    public <T extends SerializedData> void save(List<T> cineplexList) {
        try {
            String fileName = "MOBLIMA_alternate/bin/data/Cineplex.dat";
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(cineplexList);
            //System.out.println(inputList.get(0).getClass());
            //System.out.println("Data index 0: " + inputList.get(0));
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
