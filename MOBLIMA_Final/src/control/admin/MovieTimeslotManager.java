package control.admin;
/*
 * No issues.
 */
import java.time.*;
import java.util.*;
import control.SerializeDB;
import entity.cinema.*;
import entity.movie.*;

public class MovieTimeslotManager{

	public void add() {
		List<Timeslot> movieTimeslots = SerializeDB.getList("Timeslot");
		List<Movie> movieData = SerializeDB.getList("Movie");
		List<Cineplex> cineplexData = SerializeDB.getList("Cineplex");
		DateTimeManager datetimeManager = new DateTimeManager();
		Scanner sc = new Scanner(System.in);
		int choice, index = 0, cinemaInput;
		boolean cineplexMatch = false;
		
		if (movieData.size() == 0) {
			System.out.println("\nThere are no movies available! Returning to admin menu...");
			return;
		} else {
			System.out.println("\nChoose a movie to add timeslot:");
			for (int i = 0; i < movieData.size(); i++) {
				System.out.printf("(%d) ----------------	  %s\n", i+1, movieData.get(i).getTitle());
			}
			
			do {
				System.out.printf("\nOption: ");
				
				while(!sc.hasNextInt()) {
					System.out.println("Invalid input. Please enter an integer!");
					System.out.printf("Option: ");
					sc.next();
				}
					
				choice = sc.nextInt();
				
				if (choice >= 1 && choice <= movieData.size()) {
					index = choice-1; 
					break;
				}
				System.out.println("Option does not exist. Please key in a valid option!\n");
				
			} while (true);
		}
		
		String movieTitle = movieData.get(index).getTitle();
		sc.nextLine();
		
		do {
			index = -1;
			System.out.println("\nWhich cineplex will the movie be shown?");
			String cineplexInput = sc.nextLine();
			
			for (int i = 0; i < cineplexData.size(); i++) {
				if(cineplexData.get(i).getName().equals(cineplexInput)) {
					index = i;
					cineplexMatch = true;
				}
			}
			
			if(!cineplexMatch)
				System.out.println("The cineplex does not exist! Please try again!");
		} while (!cineplexMatch);
		
		String cineplexShown = cineplexData.get(index).getName();
		
		do {
			System.out.println("Which cinema will the movie be shown?");
			
			while(!sc.hasNextInt()) {
				System.out.println("Invalid input. Please enter an integer!");
				sc.next();
			}
			
			cinemaInput = sc.nextInt();
								
			if(cinemaInput >= 1 && cinemaInput <= cineplexData.get(index).getCinema().size()) {
				break;
			} else {
				System.out.println("The cinema does not exist! Please try again!");
			}										
		} while (true);
		
		Cinema cinemaShown = cineplexData.get(index).getCinema().get(cinemaInput-1);
		LocalDate dateOfShow = datetimeManager.addDate();
		LocalTime timeOfShow = datetimeManager.addTime();
		
		Timeslot ts = new Timeslot(movieTitle, cineplexShown, cinemaShown, dateOfShow, timeOfShow);
		movieTimeslots.add(ts);
		
		SerializeDB.writeList("Timeslot",movieTimeslots);
		System.out.println("Created showtime listing! Returning to admin menu...");
		return;
		
	}
	
	public void edit() {
		List<Timeslot> movieTimeslots = SerializeDB.getList("Timeslot");
		Scanner sc = new Scanner(System.in);
		int choice, index;
		
		if (movieTimeslots.size() == 0) {
			System.out.println("\nThere are no movie timeslots to remove! Returning to admin menu...");
			return;
		} else {
			System.out.println("\nChoose a movie timeslot to remove:");
			for (int i = 0; i < movieTimeslots.size(); i++) {
				System.out.printf("(%d) ----------------	%s\n",i+1,movieTimeslots.get(i).getMovieTitle());
				System.out.printf("\t\t\tCineplex/Cinema: %s / %d\n", movieTimeslots.get(i).getCineplex(), movieTimeslots.get(i).getCinema().getID());
				System.out.printf("\t\t\tMovie Timeslot: %td %<tb %<tY at %tR\n\n", movieTimeslots.get(i).getShowDate(), movieTimeslots.get(i).getShowTime());
			}	
			
			do {
				System.out.printf("\nOption: ");
				
				while(!sc.hasNextInt()) {
					System.out.println("Invalid input. Please enter an integer!");
					sc.next();
				}
									
				choice = sc.nextInt();
				
				if(choice >= 1 && choice <= movieTimeslots.size()) {
					index = choice-1;
					break;
				} else {
					System.out.println("The movie timeslot does not exist! Please try again!");
				}
			} while (true);
		}		
		sc.nextLine();
		
		do {
			choice = 0;
			System.out.println("\nWhat would you like to update?");
			System.out.println("(1) ----------------	  Location");
			System.out.println("(2) ----------------	  Timeslot");									
			System.out.printf("\nOption: ");
			
			while(!sc.hasNextInt()) {
				System.out.println("Invalid input. Please enter an integer!");
				sc.next();
			}
				
			choice = sc.nextInt();
			
			switch(choice) {
				case 1:
					movieTimeslots = updateLocation(index);
					break;
				case 2:
					movieTimeslots = updateTimeslot(index);
					break;
				default:
					System.out.println("Option does not exist. Please key in a valid option!\n");
			}
		} while (choice < 1 || choice > 2);
			
		SerializeDB.writeList("Timeslot",movieTimeslots);
		System.out.println("Updated showtime listing! Returning to admin menu...");
		return;
	}
	
	public void remove() {
		List<Timeslot> movieTimeslots = SerializeDB.getList("Timeslot");
		Scanner sc = new Scanner(System.in);
		int choice;
			
		if (movieTimeslots.size() == 0) {
			System.out.println("\nThere are no movie timeslots to remove! Returning to admin menu...");
			return;
		} else {
			System.out.println("\nChoose a movie timeslot to remove:");
			for (int i = 0; i < movieTimeslots.size(); i++) {
				System.out.printf("(%d) ----------------	%s\n",i+1,movieTimeslots.get(i).getMovieTitle());
				System.out.printf("\t\t\tCineplex/Cinema: %s / %d\n", movieTimeslots.get(i).getCineplex(), movieTimeslots.get(i).getCinema().getID());
				System.out.printf("\t\t\tMovie Timeslot: %td %<tb %<tY at %tR\n\n", movieTimeslots.get(i).getShowDate(), movieTimeslots.get(i).getShowTime());
			}	
			
			do {			
				System.out.printf("\nOption: ");
				
				while(!sc.hasNextInt()) {
					System.out.println("Invalid input. Please enter an integer!");
					sc.next();
				}
									
				choice = sc.nextInt();
					
				if(choice >= 1 && choice <= movieTimeslots.size()) {
					movieTimeslots.remove(choice-1);
					break;
				} else {
					System.out.println("The movie timeslot does not exist! Please try again!");
				}
			} while (true);
		}
		
		SerializeDB.writeList("Timeslot",movieTimeslots);
		System.out.println("Removed showtime listing! Returning to admin menu...");
		return;
	}
	
	private List<Timeslot> updateLocation(int index) {
		List<Timeslot> movieTimeslots = SerializeDB.getList("Timeslot");
		List<Cineplex> cineplexData = SerializeDB.getList("Cineplex");
		Scanner sc = new Scanner(System.in);
		int temp, cinemaInput;
		boolean cineplexMatch = false;
			
		do {
			temp = -1;
			System.out.println("\nWhich cineplex will the movie be shown?");
			String cineplexInput = sc.nextLine();
			
			for (int i = 0; i < cineplexData.size(); i++) {
				if(cineplexData.get(i).getName().equals(cineplexInput)) {
					temp = i;
					cineplexMatch = true;
				}
			}
			
			if(!cineplexMatch)
				System.out.println("The cineplex does not exist! Please try again!");
		} while (!cineplexMatch);
		
		String cineplexShown = cineplexData.get(temp).getName();
		
		do {
			System.out.println("Which cinema will the movie be shown?");
			
			while(!sc.hasNextInt()) {
				System.out.println("Invalid input. Please enter an integer!");
				sc.next();
			}
			
			cinemaInput = sc.nextInt();
								
			if(cinemaInput >= 1 && cinemaInput <= cineplexData.get(temp).getCinema().size()) {
				break;
			} else {
				System.out.println("The cinema does not exist! Please try again!");
			}										
		} while (true);
		
		Cinema cinemaShown = cineplexData.get(temp).getCinema().get(cinemaInput-1);
								
		movieTimeslots.get(index).setCineplex(cineplexShown);	
		movieTimeslots.get(index).setCinema(cinemaShown);
														
		return movieTimeslots;
	}
	
	private List<Timeslot> updateTimeslot(int index) {
		DateTimeManager datetimeManager = new DateTimeManager();
		List<Timeslot> movieTimeslots = SerializeDB.getList("Timeslot");
		

		LocalDate showDate = datetimeManager.addDate();
		LocalTime showTime = datetimeManager.addTime();
					
		movieTimeslots.get(index).setShowDate(showDate);
		movieTimeslots.get(index).setShowTime(showTime);
						
		return movieTimeslots;
	}
}
