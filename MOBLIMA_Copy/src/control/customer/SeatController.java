package control.customer;

import java.util.*;
import control.*;
import entity.cinema.*;
import entity.movie.Movie;

public class SeatController {
	
	@SuppressWarnings("resource")
	public static void viewSeats(int movieIndex, int showIndex) {
		Scanner sc = new Scanner(System.in);
		Displayer displayer = new Displayer();
		displayer.displaySeatLayout(movieIndex, showIndex);
		
		System.out.println("Press Enter to return to movie timeslots...");
		if(sc.nextLine()!= null) {
			return;
		}
	}
	
	@SuppressWarnings("resource")
	public static void selectSeats(int movieIndex, int showIndex) {
		Scanner sc = new Scanner(System.in);
		Displayer displayer = new Displayer();
		int choice, selection = 0, a_index;
		List<Movie> movieData = SerializeDB.getMovieList();
		List<Showtime> showtimeData = SerializeDB.getShowtimeList();
		List<Showtime> bufferArr = new ArrayList<Showtime>();
		Seat selectedSeat = null;
		Showtime showtimeInfo = null;		
		Movie movie = movieData.get(movieIndex);
		
		for (Showtime s : showtimeData) {
			if(s.getMovie().getTitle().equals(movie.getTitle())) {
				bufferArr.add(s);
			}
		}
		
		showtimeInfo = bufferArr.get(showIndex);
		a_index = showtimeData.indexOf(showtimeInfo);
		
		do {
			try {
				displayer.displaySeatLayoutUnserialized(showtimeInfo);
				System.out.println("(1) ----------------      Select seating");
				System.out.println("(2) ----------------      Remove selection");
				System.out.println("(3) ----------------      Proceed to payment");
				System.out.println("(4) ----------------      Return to previous menu");
				System.out.printf("Option: ");
				
				choice = sc.nextInt();
						
				switch (choice) {
		            case 1: 
		            	if (selection >= 1) {
		            		System.out.println("You can only select one seat!");
		            		break;
		            	} else {
		            		System.out.printf("Enter row (A-%c): ", (char)(showtimeInfo.getCinema().getCinemaLayout().getNumOfRows()-1 + 'A'));
		            		char addRow = sc.next().charAt(0);
		            		System.out.printf("Enter column (1-%d): ", showtimeInfo.getCinema().getCinemaLayout().getNumOfCols());
		            		int addCol = sc.nextInt();
		            	
		            		if(addRow+2-'A' < showtimeInfo.getCinema().getCinemaLayout().getNumOfRows()) {
		            			if (showtimeInfo.getCinema().getCinemaLayout().getSeat2DArray().get(addRow-'A').get(addCol-1).getSeatState() == SeatState.AVAILABLE) {
		            				showtimeInfo.getCinema().getCinemaLayout().getSeat2DArray().get(addRow-'A').get(addCol-1).setSeatState(SeatState.SELECTED);
		            				selection++;
		            				selectedSeat = new Seat(addRow+1-'A',addCol,SeatType.SINGLE,SeatState.SELECTED);
		            				System.out.println("Successfully selected seat.");
		            			} else { 
		            				System.out.println("Seat has already been taken, please choose another seat."); 
		            			}
		            		} else {
		            			addCol = (addCol + addCol%2)/2;
		            			if (showtimeInfo.getCinema().getCinemaLayout().getSeat2DArray().get(addRow-'A').get(addCol-1).getSeatState() == SeatState.AVAILABLE) {
		            				showtimeInfo.getCinema().getCinemaLayout().getSeat2DArray().get(addRow-'A').get(addCol-1).setSeatState(SeatState.SELECTED);
		            				selection++;
		            				System.out.println("Successfully selected seat.");
		            				selectedSeat = new Seat(addRow+1-'A',addCol*2,SeatType.DOUBLE,SeatState.SELECTED);
		            			} else { 
		            				System.out.println("Seat has already been taken, please choose another seat."); 
		            			}
		            		}
		            	}
		            	break;
		            case 2:
		            	System.out.printf("Enter row (A-%c): ", (char)(showtimeInfo.getCinema().getCinemaLayout().getNumOfRows()-1 + 'A'));
		            	char removeRow = sc.next().charAt(0);
		            	System.out.printf("Enter column (1-%d): ", showtimeInfo.getCinema().getCinemaLayout().getNumOfCols());
		            	int removeCol = sc.nextInt();
		            	
		            	if(removeRow+2-'A' < showtimeInfo.getCinema().getCinemaLayout().getNumOfRows()) {
		            		if (showtimeInfo.getCinema().getCinemaLayout().getSeat2DArray().get(removeRow-'A').get(removeCol-1).getSeatState() == SeatState.SELECTED) {
		            			showtimeInfo.getCinema().getCinemaLayout().getSeat2DArray().get(removeRow-'A').get(removeCol-1).setSeatState(SeatState.AVAILABLE);
		            			selection--;
		            			selectedSeat = null;
		            			System.out.println("Successfully removed selected seat.");
		            		} else { 
		            			System.out.println("Seat is unable to be deselected, please choose the correct seat."); 
		            		}
		            	} else {
		            		removeCol = (removeCol + removeCol%2)/2;
		            		if (showtimeInfo.getCinema().getCinemaLayout().getSeat2DArray().get(removeRow-'A').get(removeCol-1).getSeatState() == SeatState.SELECTED) {
		            			showtimeInfo.getCinema().getCinemaLayout().getSeat2DArray().get(removeRow-'A').get(removeCol-1).setSeatState(SeatState.AVAILABLE);
		            			selection--;
		            			selectedSeat = null;	
		            			System.out.println("Successfully removed selected seat.");
		            		} else { 
		            			System.out.println("Seat is unable to be deselected, please choose the correct seat."); 
		            		}
		            	}
		            	break;
		            case 3:
		            	if (selection == 0) {
		            		System.out.println("There are no seats selected! Please select a seat before payment!");
		            		break;
		            	} else {
		            		BookingController.ticketPayment(a_index, showtimeInfo, selectedSeat);
		            		return;
		            	}
		            case 4:
		            	System.out.println("Returning to previous menu...");
		            	return;		            
		            default:
		            	System.out.println("Option does not exist. Please key in a valid option!\n");
				}						
			} catch (InputMismatchException ex) {
				sc.nextLine();
				System.out.println("Invalid input. Please choose a valid option!\n");
			}
		} while (true);
	}
	
	public static void confirmSeats(Showtime s, Seat selectedSeat) {		
		if(selectedSeat.getSeatRow()+1 < s.getCinema().getCinemaLayout().getNumOfRows()) {
    		s.getCinema().getCinemaLayout().getSeat2DArray().get(selectedSeat.getSeatRow()-1).get(selectedSeat.getSeatCol()-1).setSeatState(SeatState.TAKEN);
    	} else {
    		s.getCinema().getCinemaLayout().getSeat2DArray().get(selectedSeat.getSeatRow()-1).get((selectedSeat.getSeatCol()/2)-1).setSeatState(SeatState.TAKEN);
    	}
	}
}
