package cinemas;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class CinemaLayoutUpdater {
    private HashMap<String, ArrayList<Seat>> layoutHashMap;
    public CinemaLayoutUpdater(){
        DeserialiseLayout();
    }
    protected void SerialiseLayout(){
        try{
            FileOutputStream fileOut = new FileOutputStream("src/data/LayoutData.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(layoutHashMap);
            out.close();
            fileOut.close();
            //System.out.println("Layout serialised!");
            //System.out.println(layoutHashMap.get("0").get(0).getSeatState());
        }
        catch(IOException e){
            throw new RuntimeException(e);
        }
    }
    protected void DeserialiseLayout(){
        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/data/LayoutData.ser"));
            layoutHashMap = (HashMap<String, ArrayList<Seat>>) in.readObject();
            //System.out.println("DESERIALISE LAYOUTTT");
            //for(String i: layoutHashMap.keySet()) System.out.println(i);
            in.close();
            /*int count=0;
            while(layoutHashMap!=null){
                layoutHashMap = (HashMap<String, ArrayList<Seat>>) in.readObject();
                count++;
            }
            System.out.println("Count: "+count);
            if(count>1) throw new RuntimeException();*/
        }
        catch(EOFException e){
            layoutHashMap = new HashMap<>();
        }
        catch (IOException e){
            layoutHashMap = new HashMap<>();
            //throw new RuntimeException(e);
        }
        catch(ClassNotFoundException e){
            System.out.println("Class not found!");
        }
    }
    protected ArrayList<Seat> getLayoutData(String slotID){
        if(layoutHashMap.get(slotID)!=null) return layoutHashMap.get(slotID);
        ArrayList<Seat> seats = new ArrayList<>();
        for(char i='A'; i<'G'; i++){
            for(int j=0; j<8; j++){
                seats.add(new Seat(i,j));
            }
        }
        layoutHashMap.put(slotID,seats);
        SerialiseLayout();
        return layoutHashMap.get(slotID);
    }
    protected void updateLayoutData(ArrayList<Seat> seats, String slotID){
        layoutHashMap.replace(slotID,seats);
        //System.out.println("SlotID: "+slotID);
        //for(String i: layoutHashMap.keySet()) System.out.println(i);
        //System.out.println(layoutHashMap.get(slotID).get(0).getSeatState());
        SerialiseLayout();
    }
}
