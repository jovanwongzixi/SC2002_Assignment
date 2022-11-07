package control.datahandler;

import entity.Holiday;
import interfaces.DataHandler;
import interfaces.SerializedData;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HolidayDataHandler implements DataHandler {
    public List<Holiday> retrieve(){
        List<Holiday> data = new ArrayList<>();
        try {
            String fileName = "MOBLIMA_alternate/bin/data/Movie.dat";
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fis);
            data = (List<Holiday>)in.readObject();
            in.close();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return data;
    }

    @Override
    public <T extends SerializedData> void save(List<T> holidayList) {
        try {
            String fileName = "MOBLIMA_alternate/bin/data/Holiday.dat";
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(holidayList);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
