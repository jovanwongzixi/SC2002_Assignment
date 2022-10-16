package Cine;
enum SeatState{
    AVAILABLE,
    SELECTED,
    TAKEN
}
public class Seat {
    private int seatID;
    private char row;
    private int column;
    private SeatState seatState;

    public Seat(){

    }
    public int getSeatID() {
        return this.seatID;
    }

    public char getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public SeatState getSeatState() {
        return seatState;
    }
}


