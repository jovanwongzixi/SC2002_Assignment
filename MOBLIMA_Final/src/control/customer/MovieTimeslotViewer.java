package control.customer;

import java.util.*;
import control.SerializeDB;
import entity.cinema.*;
import entity.movie.*;
import interfaces.Viewer;

public class MovieTimeslotViewer implements Viewer{
	
	public void view() {
		List<Movie> movieData = SerializeDB.getList("Movie");
		Scanner sc = new Scanner(System.in);
		int choice;
		
		do {
			MovieListViewer movieListViewer = new MovieListViewer();
			movieListViewer.displayMovieList();
			System.out.printf("\nInput option number to view movie timeslots (-1 to return to customer menu): ");
			
			while(!sc.hasNextInt()) {
				System.out.println("Invalid input. Please enter an integer!");
				sc.next();
			}
					
			choice = sc.nextInt();
			
			if(choice > movieData.size()) {
				System.out.println("Option does not exist. Please key in a valid option!\n");
			} else if (choice == -1) {
				System.out.println("Returning to customer menu...");
				return;
			} else {
				viewSpecificTimeslot(choice-1);
			}			
		} while (true);
		
	}
	
	private void viewSpecificTimeslot(int index) {
		Scanner sc = new Scanner(System.in);
		int choice;
		
		do {
			int ts_size = displayMovieTimeslots(index);
			if (ts_size == 0) {
				System.out.println("There are no timeslots for the movie!");
				return;
			}
			System.out.printf("\nInput option number to view seats (-1 to return to previous menu): ");
			
			while(!sc.hasNextInt()) {
				System.out.println("Invalid input. Please enter an integer!");
				sc.next();
			}
			
			choice = sc.nextInt();
				
			if(choice > ts_size) {
				System.out.println("Option does not exist. Please key in a valid option!\n");
			} else if (choice == -1) {
				System.out.println("Returning to previous menu...");
				return;
			} else {
				viewSeats(index, choice-1);
			}
		} while (true);	
	}
	
	private void viewSeats(int movieIndex, int showIndex) {
		Scanner sc = new Scanner(System.in);
		displaySeatLayout(movieIndex, showIndex);
		
		System.out.println("Press Enter to return to movie timeslots...");
		if(sc.nextLine()!= null) {
			return;
		}
	}
	
	/*private void displayMovieList() {
		List<Movie> movieData = SerializeDB.getList("Movie");
		int index = 0;
		
		System.out.println("-------------------- Movie List -------------------");
		for(Movie m: movieData) {
			index++;
			System.out.printf("(%d) ----------------	%s\n",index, m.getTitle());			
		}
	}*/ //trying to use function from movieListViewer
	
	private int displayMovieTimeslots(int index) {
		List<Movie> movieData = SerializeDB.getList("Movie");
		List<Timeslot> movieTimeslots = SerializeDB.getList("Timeslot");
		Movie movie = movieData.get(index);
		int i = 0, count = 0;
		
		for (Timeslot ts : movieTimeslots) {
			if(ts.getMovieTitle().equals(movie.getTitle())) {
				count++;
			}
		}
		
		if (count == 0)
			return count;
				
		System.out.printf("------------------ %s Timeslots ------------------\n", movie.getTitle());
		
		for (Timeslot ts : movieTimeslots) {
			if (ts.getMovieTitle().equals(movie.getTitle())) {
				i++;
				System.out.printf("(%d) -------\t Cineplex %s at Cinema %d on %td %<tb %<tY at %tR\n", i, ts.getCineplex(),
					ts.getCinema().getID(), ts.getShowDate(), ts.getShowTime());
			}
		}	
		return i;
	}
	
	private void displaySeatLayout(int movieIndex, int showIndex) {
		List<Movie> movieData = SerializeDB.getList("Movie");
		List<Timeslot> movieTimeslots = SerializeDB.getList("Timeslot"), bufferArr = new ArrayList<Timeslot>();
		Movie movie = movieData.get(movieIndex);
		
		for (Timeslot ts : movieTimeslots) {
			if(ts.getMovieTitle().equals(movie.getTitle())) {
				bufferArr.add(ts);
			}
		}
		
		Cinema cinemaShown = bufferArr.get(showIndex).getCinema();
		
		if (!cinemaShown.getIsPlatinum()){
			System.out.println("                                              Screen                                             ");
			System.out.println("                                       --------------------                                      ");
			System.out.println("     1  |  2  |  3  |  4  |    |  5  |  6  |  7  |  8  |  9  | 10  |    | 11  | 12  | 13  | 14  |");
			System.out.println("---------------------------    -------------------------------------    -------------------------");
		
			for (int i = 0; i < cinemaShown.getNumOfRows(); i++) {
				if (i < cinemaShown.getNumOfRows()-2) {
					System.out.println("  |     |     |     |     |    |     |     |     |     |     |     |    |     |     |     |     |");
				} else {
					System.out.println("  |           |           |    |           |           |           |    |           |           |");
				}
				System.out.printf("%c |", (char)(i+65));
			
				for (int j = 0; j < cinemaShown.getNumOfCols(); j++) {
					if (j == 4 || j == 10)
						System.out.printf("    |");
					if (i < cinemaShown.getNumOfRows()-2) {
						if (cinemaShown.getCinemaLayout().get(i).get(j).getSeatState() == SeatState.AVAILABLE) {						
							if (j != cinemaShown.getNumOfCols()) {
								System.out.printf("     |");
							}
						} else if (cinemaShown.getCinemaLayout().get(i).get(j).getSeatState() == SeatState.SELECTED) {
							if (j != cinemaShown.getNumOfCols()) {
								System.out.printf("  O  |");
							}
						} else {
							if (j != cinemaShown.getNumOfCols()) {
								System.out.printf("  X  |");
							}
						}
					} else {
						if (cinemaShown.getCinemaLayout().get(i).get(j/2).getSeatState() == SeatState.AVAILABLE) {						
							if (j <= cinemaShown.getNumOfCols()-1) {
								System.out.printf("           |");
								j++;
							}
						} else if (cinemaShown.getCinemaLayout().get(i).get(j/2).getSeatState() == SeatState.SELECTED) {
							if (j <= cinemaShown.getNumOfCols()-1) {
								System.out.printf("     O     |");
								j++;
							}
						} else {
							if (j <= cinemaShown.getNumOfCols()-1) {
								System.out.printf("     X     |");
								j++;
							}
						}
					}
				}
			
				if (i < cinemaShown.getNumOfRows()-2) {
					System.out.println("\n  |     |     |     |     |    |     |     |     |     |     |     |    |     |     |     |     |");
				} else {
					System.out.println("\n  |           |           |    |           |           |           |    |           |           |");
				}
			
				if (i != cinemaShown.getNumOfRows()-1) {
					System.out.println("---------------------------    -------------------------------------    -------------------------");
				}
			}		
		} else {
			System.out.println("                               Screen                             ");
			System.out.println("                        --------------------                      ");
			System.out.println("     1  |  2  |    |  3  |  4  |    |  5  |  6  |    |  7  |  8  |");
			System.out.println("---------------    -------------    -------------    -------------");
			
			for (int i = 0; i < cinemaShown.getNumOfRows(); i++) {
				if (i < cinemaShown.getNumOfRows()-2) {
					System.out.println("  |     |     |    |     |     |    |     |     |    |     |     |");
				} else {
					System.out.println("  |           |    |           |    |           |    |           |");
				}
				System.out.printf("%c |", (char)(i+65));
			
				for (int j = 0; j < cinemaShown.getNumOfCols(); j++) {
					if (j == 2 || j == 4 || j == 6)
						System.out.printf("    |");
					if (i < cinemaShown.getNumOfRows()-2) {
						if (cinemaShown.getCinemaLayout().get(i).get(j).getSeatState() == SeatState.AVAILABLE) {						
							if (j != cinemaShown.getNumOfCols()) {
								System.out.printf("     |");
							}
						} else if (cinemaShown.getCinemaLayout().get(i).get(j).getSeatState() == SeatState.SELECTED) {
							if (j != cinemaShown.getNumOfCols()) {
								System.out.printf("  O  |");
							}
						} else {
							if (j != cinemaShown.getNumOfCols()) {
								System.out.printf("  X  |");
							}
						}
					} else {
						if (cinemaShown.getCinemaLayout().get(i).get(j/2).getSeatState() == SeatState.AVAILABLE) {						
							if (j <= cinemaShown.getNumOfCols()-1) {
								System.out.printf("           |");
								j++;
							}
						} else if (cinemaShown.getCinemaLayout().get(i).get(j/2).getSeatState() == SeatState.SELECTED) {
							if (j <= cinemaShown.getNumOfCols()-1) {
								System.out.printf("     O     |");
								j++;
							}
						} else {
							if (j <= cinemaShown.getNumOfCols()-1) {
								System.out.printf("     X     |");
								j++;
							}
						}
					}
				}
			
				if (i < cinemaShown.getNumOfRows()-2) {
					System.out.println("\n  |     |     |    |     |     |    |     |     |    |     |     |");
				} else {
					System.out.println("\n  |           |    |           |    |           |    |           |");
				}
			
				if (i != cinemaShown.getNumOfRows()-1) {
					System.out.println("---------------    -------------    -------------    -------------");
				}
			}	
		}
		
		System.out.println("\nX --------- Seat taken");
		System.out.println("O --------- Seat selected\n");
	}
}
