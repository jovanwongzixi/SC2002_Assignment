package control.datahandler;

import entity.Flag;
import entity.movie.TicketPrice;
import interfaces.DataHandler;
import interfaces.SerializedData;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FlagDataHandler implements DataHandler {
    public List<Flag> retrieve(){
        List<Flag> data = new ArrayList<>();
        try {
            String fileName = "MOBLIMA_alternate/bin/data/Flag.dat";
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fis);
            data = (List<Flag>)in.readObject();
            in.close();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return data;
    }

    @Override
    public <T extends SerializedData> void save(List<T> flagList) {
        try {
            String fileName = "MOBLIMA_alternate/bin/data/Flag.dat";
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(flagList);
            //System.out.println(inputList.get(0).getClass());
            //System.out.println("Data index 0: " + inputList.get(0));
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
