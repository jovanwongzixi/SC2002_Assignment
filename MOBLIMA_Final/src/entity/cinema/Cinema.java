package entity.cinema;

import java.util.*;
import interfaces.SerializedData;

public class Cinema implements SerializedData{
	private int id;
	private boolean isPlatinum;
	private int numOfRows;
	private int numOfCols;
	private List<List<Seat>> cinemaLayout;
	
	public Cinema(int id, boolean isPlatinum) {
		this.id = id;
		this.isPlatinum = isPlatinum;
		if(getIsPlatinum()) {
			this.numOfRows = 8;
			this.numOfCols = 8;
		} else {
			this.numOfRows = 8;
			this.numOfCols = 14;
		}
		this.cinemaLayout = new ArrayList<List<Seat>>();
		for (int i = 1; i <= this.numOfRows; i++) {
			List<Seat> seatRow = new ArrayList<Seat>();
			
			if (i <= this.numOfRows-2)
				for (int j = 1; j <= this.numOfCols; j++) {
					Seat s = new Seat(i, j, false, SeatState.AVAILABLE);
					seatRow.add(s);
			} else {
				for (int j = 1; j <= this.numOfCols; j+=2) {
					Seat s = new Seat(i, j, true, SeatState.AVAILABLE);
					seatRow.add(s);
				}
			}
			
			cinemaLayout.add(seatRow);
		}
	}
	
	public int getID() {
		return this.id;
	}
	
	public boolean getIsPlatinum() {
		return this.isPlatinum;
	}
	
	public int getNumOfRows() {
		return this.numOfRows;
	}
	
	public int getNumOfCols() {
		return this.numOfCols;
	}
	
	public List<List<Seat>> getCinemaLayout() {
		return this.cinemaLayout;
	}
}
