package control.datahandler;

import entity.Booking;
import interfaces.DataHandler;
import interfaces.SerializedData;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDataHandler implements DataHandler {
    @Override
    public List<Booking> retrieve() {
        List<Booking> data = new ArrayList<>();
        try {
            String fileName = "MOBLIMA_alternate/bin/data/Booking.dat";
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fis);
            data = (List<Booking>)in.readObject();
            in.close();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return data;
    }

    @Override
    public <T extends SerializedData> void save(List<T> bookingList) {
        try {
            String fileName = "MOBLIMA_alternate/bin/data/Booking.dat";
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(bookingList);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
