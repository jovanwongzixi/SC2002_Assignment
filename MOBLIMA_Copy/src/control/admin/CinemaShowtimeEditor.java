package control.admin;
//NOT SUPER CLEAN BUT FULLY FUNCTIONAL. DO NOT EDIT UNLESS EXCEPTION HANDLING AND BUG FIXING!!!!!
import java.util.*;
import control.SerializeDB;
import java.time.*;
import entity.cinema.*;
import entity.movie.Movie;

public class CinemaShowtimeEditor implements Writable{
	
	@SuppressWarnings("resource")
	public void create() {
		Scanner sc = new Scanner(System.in);
		DateTimeManipulator datetimeManip = new DateTimeManipulator();
		int choice, index = 0;
		Movie showMovie = null;
		Cineplex showCineplex = null;
		Cinema showCinema = null;
		LocalDate showDate = null;
		LocalTime showTime = null;
		boolean toggle = true;
		
		List<Showtime> showtimeData = SerializeDB.getShowtimeList();
		List<Movie> movieData = SerializeDB.getMovieList();
		List<Cineplex> cineplexData = SerializeDB.getCineplexList();
		List<String> cineplexNames = new ArrayList<String>();
		
		for (Cineplex cp :cineplexData) {
			cineplexNames.add(cp.getName());
		}
		
		if (movieData.size() == 0) {
			System.out.println("There are no movies to show!");
			System.out.println("Returning to admin menu...");
			return;
		} else {
			System.out.println("Choose a movie to add showtime slot:");
			
			do {
				try {				
					for (int i = 0; i < movieData.size(); i++) {
						System.out.printf("(%d) ----------------	  %s\n", i+1, movieData.get(i).getTitle());
					}
					
					System.out.printf("Option: ");
					choice = sc.nextInt();
					
					if (choice >= 1 && choice <= movieData.size()) {
						toggle = false;
						index = choice-1;
						showMovie = movieData.get(index);
						sc.nextLine();
					} else {
						System.out.println("Option does not exist. Please key in a valid option!\n");
					}
					
				} catch (InputMismatchException ex) {
					sc.nextLine();
					System.out.println("Invalid input. Please choose a valid option!\n");
				} 
				
			} while (toggle);
			
			toggle = true;
			do {	
				System.out.println("\nWhich cineplex will the movie be shown?");
				String cineplexInput = sc.nextLine();
							
				if(cineplexNames.contains(cineplexInput)) {
					toggle = false;
					index = cineplexNames.indexOf(cineplexInput);
								
					showCineplex = cineplexData.get(index);
				} else {
					System.out.println("The cineplex does not exist! Please try again!");
				}
				
			} while (toggle);	
			
			toggle = true;
			do {
				try {
					System.out.println("Which cinema will the movie be shown?");
									
					int cinemaInput = sc.nextInt();
									
					if(cinemaInput >= 1 && cinemaInput <= cineplexData.get(index).getCinema().size()) {	
						toggle = false;
						showCinema = cineplexData.get(index).getCinema().get(cinemaInput-1);
					} else {
						System.out.println("The cinema does not exist! Please try again!");
					}								
				} catch (InputMismatchException ex) {
					sc.nextLine();
					System.out.println("Invalid input. Please choose a valid option!\n");
				} 	
				
			} while (toggle);				
			
			showDate = datetimeManip.addDate();		
			showTime = datetimeManip.addTime();		
															
			Showtime st = new Showtime(showMovie, showCineplex, showCinema, showDate, showTime);
			showtimeData.add(st);				
		}
		
		SerializeDB.writeToShowtimeList(showtimeData);
		System.out.println("Created showtime listing! Returning to admin menu...");
		return;
	}
	
	@SuppressWarnings("resource")
	public void update() {
		Scanner sc = new Scanner(System.in);
		int choice;
		boolean toggle_flag1 = true, toggle_flag2 = true;
		
		List<Showtime> showtimeData = SerializeDB.getShowtimeList();
		List<Cineplex> cineplexData = SerializeDB.getCineplexList();
		List<String> cineplexNames = new ArrayList<String>();
		
		for (Cineplex cp :cineplexData) {
			cineplexNames.add(cp.getName());
		}
		
		if (showtimeData.size() == 0) {
			System.out.println("There are no movie timeslots to update!");
			System.out.println("Returning to admin menu...");
			return;
		} else {
			System.out.println("Choose a movie timeslot to update:");
			do {
				try {
					for(int i = 0; i < showtimeData.size(); i++) {
						System.out.printf("(%d) ----------------	%s\n",i+1,showtimeData.get(i).getMovie().getTitle());
						System.out.printf("\t\t\tCineplex/Cinema: %s/%d\n",showtimeData.get(i).getCineplex().getName(),showtimeData.get(i).getCinema().getID());
						System.out.printf("\t\t\tMovie Timeslot: %td %<tb %<tY at %tR\n\n",showtimeData.get(i).getShowDate(),showtimeData.get(i).getShowTime());
					}
					System.out.printf("Option: ");
						
					choice = sc.nextInt();
					int index = choice-1;
					
					if(choice >= 1 && choice <= showtimeData.size()) {
						toggle_flag1 = false;
						sc.nextLine();
							
						do {
							try {
								System.out.println("What would you like to update?");
								System.out.println("(1) ----------------	  Location");
								System.out.println("(2) ----------------	  Timeslot");									
								System.out.printf("Option: ");
									
								choice = sc.nextInt();
									
								switch(choice) {
									case 1:
										toggle_flag2 = false;
										showtimeData = updateLocation(index);
										break;
									case 2:
										toggle_flag2 = false;
										showtimeData = updateTimeslot(index);
										break;
									default:
										System.out.println("Option does not exist. Please key in a valid option!\n");	
								}
							} catch (InputMismatchException ex) {
								sc.nextLine();
								System.out.println("Invalid input. Please choose a valid option!\n");
							}
						} while (toggle_flag2);
													
					} else {
						System.out.println("The movie timeslot does not exist! Please try again!");
					}
				} catch (InputMismatchException ex) {
					sc.nextLine();
					System.out.println("Invalid input. Please choose a valid option!\n");
				}
			} while (toggle_flag1);
		}
		
		SerializeDB.writeToShowtimeList(showtimeData);
		System.out.println("Updated showtime listing! Returning to admin menu...");
		return;
	}
	
	@SuppressWarnings("resource")
	public void remove() {
		Scanner sc = new Scanner(System.in);
		int choice;
		boolean toggle = true;
		
		List<Showtime> showtimeData = SerializeDB.getShowtimeList();
		
		if (showtimeData.size() == 0) {
			System.out.println("There are no movie timeslots to remove!");
			System.out.println("Returning to admin menu...");
			return;
		} else {
			System.out.println("Choose a movie timeslot to remove:");
			do {
				try {
					for(int i = 0; i < showtimeData.size(); i++) {
						System.out.printf("(%d) ----------------	%s\n",i+1,showtimeData.get(i).getMovie().getTitle());
						System.out.printf("\t\t\tCineplex/Cinema: %s/%d\n",showtimeData.get(i).getCineplex().getName(),showtimeData.get(i).getCinema().getID());
						System.out.printf("\t\t\tMovie Timeslot: %td %<tb %<tY at %tR\n\n",showtimeData.get(i).getShowDate(),showtimeData.get(i).getShowTime());
					}	
					System.out.printf("Option: ");
						
					choice = sc.nextInt();
					
					if(choice >= 1 && choice <= showtimeData.size()) {
						toggle = false;
						showtimeData.remove(choice-1);
					} else {
						System.out.println("The movie timeslot does not exist! Please try again!");
					}
				} catch (InputMismatchException ex) {
					sc.nextLine();
					System.out.println("Invalid input. Please choose a valid option!\n");
				}
			} while (toggle);
		}
		
		SerializeDB.writeToShowtimeList(showtimeData);
		System.out.println("Removed showtime listing! Returning to admin menu...");
		return;
	}
	
	@SuppressWarnings("resource")
	public List<Showtime> updateLocation(int index) {
		Scanner sc = new Scanner(System.in);
		List<Showtime> showtimeData = SerializeDB.getShowtimeList();
		List<Cineplex> cineplexData = SerializeDB.getCineplexList();
		List<String> cineplexNames = new ArrayList<String>();
		boolean toggle_flag1 = true, toggle_flag2 = true;
		
		for (Cineplex cp :cineplexData) {
			cineplexNames.add(cp.getName());
		}
		
		do {	
			System.out.println("Input new cineplex:");
			String cineplexInput = sc.nextLine();
			
			if(cineplexNames.contains(cineplexInput)) {
				toggle_flag1 = false;
				int j = cineplexNames.indexOf(cineplexInput);
				
				Cineplex showCineplex = cineplexData.get(j);
				
				do {
					try {
						System.out.println("Which cinema will the movie be shown?");
					
						int cinemaInput = sc.nextInt();
					
						if(cinemaInput >= 1 && cinemaInput <= cineplexData.get(j).getCinema().size()) {	
							toggle_flag2 = false;
							Cinema showCinema = cineplexData.get(j).getCinema().get(cinemaInput-1);
							
							showtimeData.get(index).setCineplex(showCineplex);	
							showtimeData.get(index).setCinema(showCinema);
														
						} else {
							System.out.println("The cinema does not exist! Please try again!");
						}								
					} catch (InputMismatchException ex) {
						sc.nextLine();
						System.out.println("Invalid input. Please choose a valid option!\n");
					}
				} while (toggle_flag2);
			} else {
				System.out.println("The cineplex does not exist! Please try again!");
			}		
		} while (toggle_flag1);
		
		return showtimeData;
	}
	
	public List<Showtime> updateTimeslot(int index) {
		DateTimeManipulator datetimeManip = new DateTimeManipulator();
		List<Showtime> showtimeData = SerializeDB.getShowtimeList();
		

		LocalDate showDate = datetimeManip.addDate();
		LocalTime showTime = datetimeManip.addTime();
					
		showtimeData.get(index).setShowDate(showDate);
		showtimeData.get(index).setShowTime(showTime);
						
		return showtimeData;
	}
}
