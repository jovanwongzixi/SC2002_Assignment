package entity.cinema;

import java.io.Serializable;

public class Seat implements Serializable{
	private static final long serialVersionUID = 3952420642623769714L;
	private int seatRow;
	private int seatCol;
	private SeatType seatType;
	private SeatState seatState;
	
	public Seat (int seatRow, int seatCol, SeatType seatType, SeatState seatState){
		this.seatRow = seatRow;
		this.seatCol = seatCol;
		this.seatType = seatType;
		this.seatState = seatState;	
	}
	
	public void setSeatRow(int seatRow) {
		this.seatRow = seatRow;
	}
	
	public void setSeatCol(int seatCol) {
		this.seatCol = seatCol;
	}
	
	public void setSeatType(SeatType seatType) {
		this.seatType = seatType;
	}
	
	public void setSeatState(SeatState seatState) {
		this.seatState = seatState;
	}
	
	public int getSeatRow() {
		return this.seatRow;
	}
	
	public int getSeatCol() {
		return this.seatCol;
	}
	
	public SeatType getSeatType() {
		return this.seatType;
	}
	
	public SeatState getSeatState() {
		return this.seatState;
	}
}
