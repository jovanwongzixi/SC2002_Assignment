package control.datahandler;

import entity.movie.TicketPrice;
import interfaces.DataHandler;
import interfaces.SerializedData;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TicketPriceDataHandler implements DataHandler {
    public List<TicketPrice> retrieve(){
        List<TicketPrice> data = new ArrayList<>();
        try {
            String fileName = "bin/data/TicketPrice.dat";
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fis);
            data = (List<TicketPrice>)in.readObject();
            in.close();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return data;
    }

    @Override
    public <T extends SerializedData> void save(List<T> ticketPriceList) {
        try {
            String fileName = "bin/data/TicketPrice.dat";
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(ticketPriceList);
            //System.out.println(inputList.get(0).getClass());
            //System.out.println("Data index 0: " + inputList.get(0));
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
