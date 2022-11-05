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
			movieListViewer.displayMovieList(movieData);
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
		List<Movie> movieData = SerializeDB.getList("Movie");
		List<Timeslot> movieTimeslots = SerializeDB.getList("Timeslot"), bufferArr = new ArrayList<>();
		do {
			int ts_size = displayMovieTimeslots(movieData,index);
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
				Movie movie = movieData.get(index);
				for (Timeslot ts : movieTimeslots) {
					if(ts.getMovieTitle().equals(movie.getTitle())) {
						bufferArr.add(ts);
					}
				}
				Timeslot ts = bufferArr.get(choice-1);
				viewSeats(ts);
			}
		} while (true);	
	}
	
	private void viewSeats(Timeslot ts) {
		Scanner sc = new Scanner(System.in);
		SeatLayoutDisplayer seatLayoutDisplayer = new SeatLayoutDisplayer();
		seatLayoutDisplayer.display(ts);
		//displaySeatLayout(ts);
		
		System.out.println("Press Enter to return to movie timeslots...");
		/*if(sc.nextLine()!= null) {
			return;
		}*/
		sc.nextLine();
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
	
	protected int displayMovieTimeslots(List<Movie> movieData, int index) {
		//List<Movie> movieData = SerializeDB.getList("Movie");
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
	//trying to use seatlayout displayer
	/*
	private void displaySeatLayout(Timeslot ts) {
		List<Movie> movieData = SerializeDB.getList("Movie");
		List<Timeslot> movieTimeslots = SerializeDB.getList("Timeslot"), bufferArr = new ArrayList<Timeslot>();
		Movie movie = movieData.get(movieIndex);
		
		for (Timeslot ts : movieTimeslots) {
			if(ts.getMovieTitle().equals(movie.getTitle())) {
				bufferArr.add(ts);
			}
		}
		
		//Cinema cinemaShown = bufferArr.get(showIndex).getCinema();
		Cinema cinemaShown = ts.getCinema();
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
	}*/
}
