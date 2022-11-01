import interfaces.Displayable;

import java.util.ArrayList;

public class BookingHistoryList implements Displayable {
    private ArrayList<BookingHistory> bookingList;
    public BookingHistoryList(){
        bookingList = new ArrayList<>();
    }
    public void appendList(BookingHistory bookingHistory){
        bookingHistory.setTransactionID();
        bookingList.add(bookingHistory);
    }
    public void display(){
        if(bookingList.isEmpty()){
            System.out.println("Booking History is empty!");
            return;
        }
        for(BookingHistory history : bookingList){
            history.display();
        }
    }
}
