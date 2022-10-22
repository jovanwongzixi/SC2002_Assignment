package cinemas;


import java.io.Serializable;

public class Seat implements Serializable {
    //private int seatID;
    private char row;
    private int column;
    private SeatState seatState;

    public Seat(char row, int column){
        this.row = row;
        this.column = column;
        this.seatState = SeatState.AVAILABLE;
    }
    /*public int getSeatID() {
        return this.seatID;
    }*/

    public char getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public SeatState getSeatState() {
        return seatState;
    }

    public void setSeatState(SeatState seatState) {
        this.seatState = seatState;
    }
}


