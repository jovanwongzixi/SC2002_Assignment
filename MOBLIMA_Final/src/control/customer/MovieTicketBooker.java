package control.customer;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;
import control.SerializeDB;
import entity.Booking;
import entity.Holiday;
import entity.cinema.*;
import entity.movie.*;

public class MovieTicketBooker {

	public void start() {
		List<Movie> movieData = SerializeDB.getList("Movie");
		List<Movie> bookingList = new ArrayList<Movie>();
		Scanner sc = new Scanner(System.in);
		int choice;
		
		do {
			bookingList =  displayMovieList(movieData);
			System.out.printf("\nInput option number to view book movie ticket (-1 to return to customer menu): ");
			
			while(!sc.hasNextInt()) {
				System.out.println("Invalid input. Please enter an integer!");
				System.out.printf("Option: ");
				sc.next();
			}
					
			choice = sc.nextInt();
			
			if(choice > bookingList.size()) {
				System.out.println("Option does not exist. Please key in a valid option!\n");
			} else if (choice == -1) {
				System.out.println("Returning to customer menu...");
				return;
			} else {
				selectTimeslot(bookingList, choice-1);
				return;
			}	
		} while (true);
	}

	private void selectTimeslot(List<Movie> bookingList,int index) {
		Scanner sc = new Scanner(System.in);
		int choice;
		
		do {
			int ts_size = displayMovieTimeslots(bookingList, index);
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
				selectSeats(bookingList, index, choice-1);
				return;
			}
		} while (true);	
	}
	
	private void selectSeats(List<Movie> bookingList, int movieIndex, int showIndex) {
		Scanner sc = new Scanner(System.in);
		int choice, selection = 0, ts_index;
		List<Timeslot> movieTimeslots = SerializeDB.getList("Timeslot");
		List<Timeslot> bufferArr = new ArrayList<Timeslot>();
		List<Seat> selectedSeats = new ArrayList<Seat>();
		Timeslot timeslot = null;		
		Movie movie = bookingList.get(movieIndex);
		
		for (Timeslot ts : movieTimeslots) {
			if(ts.getMovieTitle().equals(movie.getTitle())) {
				bufferArr.add(ts);
			}
		}
		
		timeslot = bufferArr.get(showIndex);
		ts_index = movieTimeslots.indexOf(timeslot);
		
		do {
			displaySeatLayoutUnserialized(timeslot);
			System.out.println("(1) ----------------      Select seating");
			System.out.println("(2) ----------------      Remove selection");
			System.out.println("(3) ----------------      Proceed to payment");
			System.out.println("(4) ----------------      Return to previous menu");
			System.out.printf("\nOption: ");
			
			while(!sc.hasNextInt()) {
				System.out.println("Invalid input. Please enter an integer!");
				System.out.printf("Option: ");
				sc.next();
			}
			
			choice = sc.nextInt();
						
			switch (choice) {
				case 1: 
		            System.out.printf("Enter row (A-%c): ", (char)(timeslot.getCinema().getNumOfRows()-1 + 'A'));
		            char addRow = sc.next().charAt(0);
		            System.out.printf("Enter column (1-%d): ", timeslot.getCinema().getNumOfCols());
		            int addCol = sc.nextInt();
		            	
		            if(addRow+2-'A' < timeslot.getCinema().getNumOfRows()) {
		            	if (timeslot.getCinema().getCinemaLayout().get(addRow-'A').get(addCol-1).getSeatState() == SeatState.AVAILABLE) {
		            		timeslot.getCinema().getCinemaLayout().get(addRow-'A').get(addCol-1).setSeatState(SeatState.SELECTED);
		            		selection++;
		            		Seat s = new Seat(addRow+1-'A',addCol,false,SeatState.SELECTED);
		            		selectedSeats.add(s); 
		            		System.out.println("Successfully selected seat.");
		            	} else { 
		           			System.out.println("Seat has already been taken, please choose another seat."); 
		           		}
		           	} else {
		           		addCol = (addCol + addCol%2)/2;
		            	if (timeslot.getCinema().getCinemaLayout().get(addRow-'A').get(addCol-1).getSeatState() == SeatState.AVAILABLE) {
		           			timeslot.getCinema().getCinemaLayout().get(addRow-'A').get(addCol-1).setSeatState(SeatState.SELECTED);
		           			selection++;
		           			Seat s = new Seat(addRow+1-'A',addCol*2,true,SeatState.SELECTED);
		           			selectedSeats.add(s); 
		           			System.out.println("Successfully selected seat.");
		            	} else { 
		           			System.out.println("Seat has already been taken, please choose another seat."); 
		            	}
		            }
		            
		            break;
				case 2:
		            System.out.printf("Enter row (A-%c): ", (char)(timeslot.getCinema().getNumOfRows()-1 + 'A'));
		            char removeRow = sc.next().charAt(0);
		            System.out.printf("Enter column (1-%d): ", timeslot.getCinema().getNumOfCols());
		            int removeCol = sc.nextInt();
		            	
		            if(removeRow+2-'A' < timeslot.getCinema().getNumOfRows()) {
		            	if (timeslot.getCinema().getCinemaLayout().get(removeRow-'A').get(removeCol-1).getSeatState() == SeatState.SELECTED) {
		            		timeslot.getCinema().getCinemaLayout().get(removeRow-'A').get(removeCol-1).setSeatState(SeatState.AVAILABLE);
		            		selection--;
		            		for (Seat s : selectedSeats) {
		            			if (s.getSeatRow() == removeRow+1-'A' && s.getSeatCol() == removeCol) {
		            				selectedSeats.remove(s);	
		            			}
		            		}
		            		System.out.println("Successfully removed selected seat.");
		            	} else { 
		            		System.out.println("Seat is unable to be deselected, please choose the correct seat."); 
		            	}
		            } else {
		            	removeCol = (removeCol + removeCol%2)/2;
		            	if (timeslot.getCinema().getCinemaLayout().get(removeRow-'A').get(removeCol-1).getSeatState() == SeatState.SELECTED) {
		            		timeslot.getCinema().getCinemaLayout().get(removeRow-'A').get(removeCol-1).setSeatState(SeatState.AVAILABLE);
		            		selection--;
		            		for (Seat s : selectedSeats) {
		            			if (s.getSeatRow() == removeRow+1-'A' && s.getSeatCol() == removeCol*2) {
		            				selectedSeats.remove(s);	
		            			}
		            		}	
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
		            	ticketPayment(movie, timeslot, ts_index, selectedSeats);
		            	return;
		            }
				case 4:
		            System.out.println("Returning to previous menu...");
		            return;		            
				default:
		            System.out.println("Option does not exist. Please key in a valid option!\n");
			}						
		} while (true);
	}
	
	private void ticketPayment(Movie movie, Timeslot ts, int tsIndex, List<Seat> selectedSeats) {
		Scanner sc = new Scanner(System.in);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
		List<Cineplex> cineplexData = SerializeDB.getList("Cineplex");
		List<Timeslot> movieTimeslots = SerializeDB.getList("Timeslot");
		//List<LocalDate> holidays = SerializeDB.getHolidays();
		List<Holiday> holidays = SerializeDB.getList("Holiday");
		List<Booking> bookingData = SerializeDB.getList("Booking");
		List<Ticket> ticketList = new ArrayList<Ticket>();
		String name, emailAddress, mobileNum, cineplexCode = null;
		int choice;
		double ticketPrice = 0;
		boolean isSpecial = false;
		
		for(Cineplex c : cineplexData) {
			if (c.getName().equals(ts.getCineplex())) {
				cineplexCode = c.getCode();
			}
		}
		
		displaySeatLayoutUnserialized(ts);
		
		System.out.println("Enter your name: ");
		name = sc.nextLine();
		
		do {
			System.out.println("Enter your email address: ");
			emailAddress = sc.nextLine();
			
			if (!emailMatches(emailAddress)) {
				System.out.println("Invalid email address. Please try again!");
			}
		} while (!emailMatches(emailAddress));
		
		do {
			System.out.println("Enter your mobile number: ");
			mobileNum = sc.nextLine();
			if (!mobileMatches(mobileNum)) {
				System.out.println("Invalid mobile number. Please try again!");
			}
		} while (!mobileMatches(mobileNum));
		
		for (Holiday d : holidays) {
			if (ts.getShowDate().equals(d.getDate()) || ts.getShowDate().getDayOfWeek() == DayOfWeek.FRIDAY || ts.getShowDate().getDayOfWeek() == DayOfWeek.SATURDAY ||
					ts.getShowDate().getDayOfWeek() == DayOfWeek.SUNDAY) {
				isSpecial = true;
				break;
			}
		}
		
		
		for (Seat s : selectedSeats) {
			boolean toggle = true;
			do {
				boolean forChild = false;
				System.out.printf("Enter age group for seat %c%d:\n", (char)(s.getSeatRow()-1+'A'), s.getSeatCol());
				System.out.println("(1) ----------------      Senior Citizen (age 55 and above)");
				System.out.println("(2) ----------------      Adult");
				if (movie.getFilmRating() == FilmRating.G || movie.getFilmRating() == FilmRating.PG || movie.getFilmRating() == FilmRating.PG13) {
					System.out.println("(3) ----------------      Child (age 16 and below)");
					forChild = true;
				}
				System.out.printf("Option: ");
			
				while(!sc.hasNextInt()) {
					System.out.println("Invalid input. Please enter an integer!");
					System.out.printf("Option: ");
					sc.next();
				}
			
				choice = sc.nextInt();
				
				switch(choice) {
					case 1:
						ticketList.add(new Ticket(movie.getIs3D(), movie.getIsBlockbuster(), ts.getCinema().getIsPlatinum(),
								AgeGroup.SENIOR_CITIZEN, s.getIsDouble(), isSpecial));
						toggle = false;
						break;
					case 2:
						ticketList.add(new Ticket(movie.getIs3D(), movie.getIsBlockbuster(), ts.getCinema().getIsPlatinum(),
								AgeGroup.ADULT, s.getIsDouble(), isSpecial));
						toggle = false;
						break;
					case 3:
						if (forChild) {
							ticketList.add(new Ticket(movie.getIs3D(), movie.getIsBlockbuster(), ts.getCinema().getIsPlatinum(),
								AgeGroup.CHILD, s.getIsDouble(), isSpecial));
							toggle = false;
							break;
						}
						System.out.println("Option does not exist. Please key in a valid option!\n");
					default:
						System.out.println("Option does not exist. Please key in a valid option!\n");			
				}
			} while (toggle);
		}
		
		for (Ticket t : ticketList) {
			ticketPrice += calculatePrice(t);
		}
		
		do {
			System.out.printf("The ticket price is: %.2f. Would you like to confirm booking?\n", ticketPrice);
			System.out.println("(1) Yes");
			System.out.println("(2) No");
			System.out.printf("Option: ");
			
			while(!sc.hasNextInt()) {
				System.out.println("Invalid input. Please enter an integer!");
				System.out.printf("Option: ");
				sc.next();
			}
			
			choice = sc.nextInt();
				
			switch(choice) {
				case 1:
					PaymentHandler handler = new PaymentHandler();
					if (handler.start()) {
						LocalDateTime timeOfPurchase = LocalDateTime.now();
						String tID = cineplexCode + timeOfPurchase.format(formatter);
						Booking b = new Booking(name, emailAddress, mobileNum, ticketPrice,
								ts.getMovieTitle(), ts.getCineplex(), ts.getCinema().getID(), ts.getShowDate(),
								ts.getShowTime(), timeOfPurchase, tID);
						bookingData.add(b);

						confirmSeats(ts, selectedSeats);
						movieTimeslots.set(tsIndex, ts);
						SerializeDB.writeList("Timeslot", movieTimeslots);
						SerializeDB.writeList("Booking", bookingData);

						System.out.printf("The ticket has been successfully purchase. Your transaction ID is %s. Returning to previous menu...\n", tID);
						return;
					}
					else{
						System.out.println("Credit card invalid! Please try again!");
					}
				case 2:
						System.out.println("Returning to previous menu...");
						return;
				default:
						System.out.println("Option does not exist. Please key in a valid option!\n");

			}
			
		} while (true);
	}
	
	private List<Movie> displayMovieList(List<Movie> movieData) {
		List<Movie> bookingList = new ArrayList<Movie>();
		int index = 0;
		
		System.out.println("-------------------- Movie List -------------------");
		for(Movie m: movieData) {
			if (m.getShowingStatus() == ShowingStatus.PREVIEW || m.getShowingStatus() == ShowingStatus.NOW_SHOWING) {
				index++;
				bookingList.add(m);
				System.out.printf("(%d) ----------------	%s\n",index, m.getTitle());			
			}
		}
		return bookingList;
	}
	
	private int displayMovieTimeslots(List<Movie> bookingList, int index) {
		List<Timeslot> movieTimeslots = SerializeDB.getList("Timeslot");
		Movie movie = bookingList.get(index);
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
	
	private void displaySeatLayoutUnserialized(Timeslot ts) {
		
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
	}
	
	private boolean emailMatches(String emailAddress) {
	    return Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")
	      .matcher(emailAddress)
	      .matches();
	}
	
	private boolean mobileMatches(String mobileNum) {
		return Pattern.compile("[8-9][0-9]{7}").matcher(mobileNum).matches();
	}
	
	private double calculatePrice(Ticket ticket) {
		//List<Double> ticketPrices = SerializeDB.getTicketPrices();
		List<TicketPrice> ticketPrices = SerializeDB.getList("TicketPrice");
		double ticketPrice = 0;
		
		if (ticket.getIsSpecial()) {
			if(ticket.getIsPlatinum()) {
				ticketPrice += ticketPrices.get(0).getPrice();
			} else {
				if(!ticket.getIs3D()) {
					ticketPrice += ticketPrices.get(1).getPrice();
				} else {
					ticketPrice += ticketPrices.get(2).getPrice();
				}
			}
		} else {
			if(ticket.getIsPlatinum()) {
				ticketPrice += ticketPrices.get(3).getPrice();
			} else {
				if(ticket.getAgeGroup() == AgeGroup.SENIOR_CITIZEN) {
					ticketPrice += ticketPrices.get(8).getPrice();
				} else if (ticket.getAgeGroup() == AgeGroup.CHILD) { 
					if(!ticket.getIs3D()) {
						ticketPrice += ticketPrices.get(4).getPrice();
					} else {
						ticketPrice += ticketPrices.get(5).getPrice();
					}
				} else {
					if(!ticket.getIs3D()) {
						ticketPrice += ticketPrices.get(6).getPrice();
					} else {
						ticketPrice += ticketPrices.get(7).getPrice();
					}
				}
			}
		}
		
		if(ticket.getIsBlockbuster() == true) {
			ticketPrice += ticketPrices.get(9).getPrice();
		}
		
		if(ticket.getIsDouble()) {
			ticketPrice *= 2;
		}
		return ticketPrice;
	}
	
	public static void confirmSeats(Timeslot ts, List<Seat> selectedSeats) {
		for (Seat s : selectedSeats) {
			if(s.getSeatRow()+1 < ts.getCinema().getNumOfRows()) {
	    		ts.getCinema().getCinemaLayout().get(s.getSeatRow()-1).get(s.getSeatCol()-1).setSeatState(SeatState.TAKEN);
	    	} else {
	    		ts.getCinema().getCinemaLayout().get(s.getSeatRow()-1).get((s.getSeatCol()/2)-1).setSeatState(SeatState.TAKEN);
	    	}
		}
		
	}
}
