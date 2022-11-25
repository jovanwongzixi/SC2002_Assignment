package control.datahandler;

import entity.movie.Timeslot;
import interfaces.DataHandler;
import interfaces.SerializedData;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TimeslotDataHandler implements DataHandler {
    public List<Timeslot> retrieve(){
        List<Timeslot> data = new ArrayList<>();
        try {
            String fileName = "../bin/data/Timeslot.dat";
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fis);
            data = (List<Timeslot>)in.readObject();
            in.close();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return data;
    }

    public <T extends SerializedData> void save(List<T> timeslotList){
        try {
            String fileName = "../bin/data/Timeslot.dat";
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(timeslotList);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
