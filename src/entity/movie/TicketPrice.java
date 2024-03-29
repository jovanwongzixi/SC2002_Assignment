package entity.movie;

import interfaces.SerializedData;

public class TicketPrice implements SerializedData {
    private double price;
    public TicketPrice(double price){
        this.price = price;
    }
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
