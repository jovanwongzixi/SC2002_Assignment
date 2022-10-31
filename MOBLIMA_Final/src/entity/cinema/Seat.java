package entity.cinema;

import interfaces.SerializedData;

public class Seat implements SerializedData{
	private int seatRow;
	private int seatCol;
	private boolean isDouble;
	private SeatState seatState;
	
	public Seat (int seatRow, int seatCol, boolean isDouble, SeatState seatState){
		this.seatRow = seatRow;
		this.seatCol = seatCol;
		this.isDouble = isDouble;
		this.seatState = seatState;	
	}
	
	public void setSeatRow(int seatRow) {
		this.seatRow = seatRow;
	}
	
	public void setSeatCol(int seatCol) {
		this.seatCol = seatCol;
	}
	
	public void setSeatType(boolean isDouble) {
		this.isDouble = isDouble;
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
	
	public boolean getIsDouble() {
		return this.isDouble;
	}
	
	public SeatState getSeatState() {
		return this.seatState;
	}
}
