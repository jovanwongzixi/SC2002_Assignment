package entity.cinema;

import java.util.*;
import java.io.Serializable;

public class CinemaLayout implements Serializable{
	private static final long serialVersionUID = -5060404325247259284L;
	private int numOfRows;
	private int numOfCols;
	private List<List<Seat>> seat2DArray;
	private CinemaType cinemaType;
	
	public CinemaLayout(CinemaType cinemaType) {
		seat2DArray = new ArrayList<List<Seat>>();
		this.cinemaType = cinemaType;
		if (this.cinemaType == CinemaType.REGULAR) {
			this.numOfRows = 8;
			this.numOfCols = 14;
		} else if (this.cinemaType == CinemaType.PLATINUM_MOVIE_SUITES) {
			this.numOfRows = 8;
			this.numOfCols = 8;
		}
		
		for (int i = 1; i <= this.numOfRows; i++) {
			List<Seat> seat1DArray = new ArrayList<Seat>();
			
			if (i <= this.numOfRows-2)
				for (int j = 1; j <= this.numOfCols; j++) {
					Seat s = new Seat(i, j, SeatType.SINGLE, SeatState.AVAILABLE);
					seat1DArray.add(s);
			} else {
				for (int j = 1; j <= this.numOfCols; j+=2) {
					Seat s = new Seat(i, j, SeatType.DOUBLE, SeatState.AVAILABLE);
					seat1DArray.add(s);
				}
			}
			
			seat2DArray.add(seat1DArray);
		}
	}
	
	public void printLayout() {
		if (this.cinemaType == CinemaType.REGULAR) {
			System.out.println("                                              Screen                                             ");
			System.out.println("                                       --------------------                                      ");
			System.out.println("     1  |  2  |  3  |  4  |    |  5  |  6  |  7  |  8  |  9  | 10  |    | 11  | 12  | 13  | 14  |");
			System.out.println("---------------------------    -------------------------------------    -------------------------");
		
			for (int i = 0; i < numOfRows; i++) {
				if (i < numOfRows-2) {
					System.out.println("  |     |     |     |     |    |     |     |     |     |     |     |    |     |     |     |     |");
				} else {
					System.out.println("  |           |           |    |           |           |           |    |           |           |");
				}
				System.out.printf("%c |", (char)(i+65));
			
				for (int j = 0; j < numOfCols; j++) {
					if (j == 4 || j == 10)
						System.out.printf("    |");
					if (i < numOfRows-2) {
						if (this.seat2DArray.get(i).get(j).getSeatState() == SeatState.AVAILABLE) {						
							if (j != numOfCols) {
								System.out.printf("     |");
							}
						} else if (this.seat2DArray.get(i).get(j).getSeatState() == SeatState.SELECTED) {
							if (j != numOfCols) {
								System.out.printf("  O  |");
							}
						} else {
							if (j != numOfCols) {
								System.out.printf("  X  |");
							}
						}
					} else {
						if (this.seat2DArray.get(i).get(j/2).getSeatState() == SeatState.AVAILABLE) {						
							if (j <= numOfCols-1) {
								System.out.printf("           |");
								j++;
							}
						} else if (this.seat2DArray.get(i).get(j/2).getSeatState() == SeatState.SELECTED) {
							if (j <= numOfCols-1) {
								System.out.printf("     O     |");
								j++;
							}
						} else {
							if (j <= numOfCols-1) {
								System.out.printf("     X     |");
								j++;
							}
						}
					}
				}
			
				if (i < numOfRows-2) {
					System.out.println("\n  |     |     |     |     |    |     |     |     |     |     |     |    |     |     |     |     |");
				} else {
					System.out.println("\n  |           |           |    |           |           |           |    |           |           |");
				}
			
				if (i != numOfRows-1) {
					System.out.println("---------------------------    -------------------------------------    -------------------------");
				}
			}		
		} else {
			System.out.println("                               Screen                             ");
			System.out.println("                        --------------------                      ");
			System.out.println("     1  |  2  |    |  3  |  4  |    |  5  |  6  |    |  7  |  8  |");
			System.out.println("---------------    -------------    -------------    -------------");
			
			for (int i = 0; i < numOfRows; i++) {
				if (i < numOfRows-2) {
					System.out.println("  |     |     |    |     |     |    |     |     |    |     |     |");
				} else {
					System.out.println("  |           |    |           |    |           |    |           |");
				}
				System.out.printf("%c |", (char)(i+65));
			
				for (int j = 0; j < numOfCols; j++) {
					if (j == 2 || j == 4 || j == 6)
						System.out.printf("    |");
					if (i < numOfRows-2) {
						if (this.seat2DArray.get(i).get(j).getSeatState() == SeatState.AVAILABLE) {						
							if (j != numOfCols) {
								System.out.printf("     |");
							}
						} else if (this.seat2DArray.get(i).get(j).getSeatState() == SeatState.SELECTED) {
							if (j != numOfCols) {
								System.out.printf("  O  |");
							}
						} else {
							if (j != numOfCols) {
								System.out.printf("  X  |");
							}
						}
					} else {
						if (this.seat2DArray.get(i).get(j/2).getSeatState() == SeatState.AVAILABLE) {						
							if (j <= numOfCols-1) {
								System.out.printf("           |");
								j++;
							}
						} else if (this.seat2DArray.get(i).get(j/2).getSeatState() == SeatState.SELECTED) {
							if (j <= numOfCols-1) {
								System.out.printf("     O     |");
								j++;
							}
						} else {
							if (j <= numOfCols-1) {
								System.out.printf("     X     |");
								j++;
							}
						}
					}
				}
			
				if (i < numOfRows-2) {
					System.out.println("\n  |     |     |    |     |     |    |     |     |    |     |     |");
				} else {
					System.out.println("\n  |           |    |           |    |           |    |           |");
				}
			
				if (i != numOfRows-1) {
					System.out.println("---------------    -------------    -------------    -------------");
				}
			}	
		
		}
		
		//if this.CinemaType == CinemaType.PLATINUM_MOVIE_SUITE
		
		System.out.println("\nX --------- Seat taken");
		System.out.println("O --------- Seat selected\n");
	}
	
	public List<List<Seat>> getSeat2DArray(){
		return this.seat2DArray;
	}
	
	public int getNumOfRows() {
		return this.numOfRows;
	}
	
	public int getNumOfCols() {
		return this.numOfCols;
	}
}