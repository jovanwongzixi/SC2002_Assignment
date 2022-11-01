package movies;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class MovieTicketPriceUpdater {

    private String fileName = "your MovieTicketPricing.csv filepath"; // Put filepath for MovieTicketPricing.csv
    private String modifierName = "childPrice,adultPrice,seniorPrice,child3DPrice,adult3DPrice,senior3DPrice,weekendIncr,nightIncr,blockbusterIncr";


    // Function to read each modifier from MovieTicketPricing data file
    // Public function to allow admin access
    public String[] readTicketPricing(){
        // [0] basePrice, [1] 3dprice, [2] nightIncr, [3] weekendIncr, [4] blockbusterIncr
        String[] values = new String[10];
        try{
            FileReader frStream = new FileReader(fileName); 
            BufferedReader brStream = new BufferedReader(frStream);
            String inputLine;
            brStream.readLine();
            while ((inputLine = brStream.readLine()) != null) {
                values = inputLine.split(",");
            }
        }
        catch(IOException e){
            throw new RuntimeException();
        }
        return values;
    }


    // Function to update each modifier from MovieTicketPricing data file
    // Public function to allow admin access
    public void updateTicketPricing(int typeModifier, String modifiedValue) throws IOException {

        String[] values = readTicketPricing();
        switch(typeModifier){
            case 0 -> values[0] = modifiedValue;
            case 1 -> values[1] = modifiedValue;
            case 2 -> values[2] = modifiedValue;
            case 3 -> values[3] = modifiedValue;
            case 4 -> values[4] = modifiedValue;
            case 5 -> values[5] = modifiedValue;
            case 6 -> values[6] = modifiedValue;
            case 7 -> values[7] = modifiedValue;
            case 8 -> values[8] = modifiedValue;
        }

        String valuesStr = String.join(",", values);
        ArrayList<String> dataLines = new ArrayList<String>(Arrays.asList(modifierName));
        dataLines.add(valuesStr);
        
        File csvOutputFile = new File(fileName);
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            dataLines.stream()
            .forEach(pw::println);
        }
    }
}
