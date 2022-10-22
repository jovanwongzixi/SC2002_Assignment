package entity.cinema;

import java.io.Serializable;

public class Seat implements Serializable{
	private static final long serialVersionUID = 3952420642623769714L;
	private int seatRow;
	private int seatNum;
	private SeatState seatState;
	
	public Seat (int seatRow, int seatNum, SeatState seatState){
		this.seatRow = seatRow;
		this.seatNum = seatNum;
		this.seatState = seatState;	
	}
	
	public int getSeatRow() {
		return this.seatRow;
	}
	
	public int getSeatNum() {
		return this.seatNum;
	}
	
	public SeatState getSeatState() {
		return this.seatState;
	}
}
