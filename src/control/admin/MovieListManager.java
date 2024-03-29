package control.admin;

import java.util.*;
import control.datahandler.MovieDataHandler;
import control.datahandler.TimeslotDataHandler;
import entity.movie.*;
import interfaces.DataHandler;
import interfaces.MovieManager;

public class MovieListManager implements MovieManager {
	
	public void add() {
		Scanner sc = new Scanner(System.in);
		DataHandler movieDataHandler = new MovieDataHandler();
		List<Movie> movieData = movieDataHandler.retrieve();
		List<Review> reviewList = new ArrayList<Review>();
		int choice, counter = 0;
		boolean isBlockbuster, is3D;
		FilmRating filmRating = null;
		ShowingStatus showingStatus = null;
		
		System.out.printf("Enter movie title: ");
		String movieName = sc.nextLine();
		
		System.out.println("Enter film rating: ");
		System.out.println("(1) ------ G");
		System.out.println("(2) ------ PG");
		System.out.println("(3) ------ PG13");
		System.out.println("(4) ------ NC16");
		System.out.println("(5) ------ M18");
		System.out.println("(6) ------ R21");
		do {
			System.out.printf("Option: ");
			
			while(!sc.hasNextInt()) {
				System.out.println("Invalid input. Please enter an integer!");
				System.out.printf("Option: ");
				sc.next();
			}
			
			choice = sc.nextInt();

			switch (choice) {
				case 1 -> filmRating = FilmRating.G;
				case 2 -> filmRating = FilmRating.PG;
				case 3 -> filmRating = FilmRating.PG13;
				case 4 -> filmRating = FilmRating.NC16;
				case 5 -> filmRating = FilmRating.M18;
				case 6 -> filmRating = FilmRating.R21;
				default -> System.out.println("Option does not exist! Please input a valid choice!");
			}
		} while (choice < 1 || choice > 6);
		
		System.out.println("Is it a 3D movie? (Y/N)");
		do {
			System.out.printf("Input: ");
			char bool = Character.toUpperCase(sc.next().charAt(0));
			if(bool == 'Y') {
				is3D = true;
				break;
			} else if(bool == 'N') {
				is3D = false;
				break;
			} else {
				System.out.println("Invalid input! Please try again!");
			}
		} while (true);
		
		System.out.println("Is it a blockbuster movie? (Y/N)");
		do {
			System.out.printf("Input: ");
			char bool = Character.toUpperCase(sc.next().charAt(0));
			if(bool == 'Y') {
				isBlockbuster = true;
				break;
			} else if(bool == 'N') {
				isBlockbuster = false;
				break;
			} else {
				System.out.println("Invalid input! Please try again!");
			}
		} while (true);
		sc.nextLine();
		
		System.out.printf("Enter movie genre: ");
		String movieGenre = sc.nextLine();
		
		System.out.printf("Enter synopsis: ");
		String synopsis = sc.nextLine();
		
		System.out.printf("Enter movie director: ");
		String director = sc.nextLine();

		System.out.println("Enter cast members (key 'X' to terminate): ");
		List<String> movieCast = new ArrayList<String>();
		String castMember = ""; 
		do {
			System.out.printf("Cast member " + (counter+1) + ": ");
			castMember = sc.nextLine();
			
			if (castMember.equals("X") && counter < 2) {
				System.out.println("Please key in a minimum of 2 cast members!");
				castMember = "";
			} else if(!castMember.equals("X")) {
				counter++;
				movieCast.add(castMember);
			}
		} while (!castMember.equals("X"));
		
		System.out.println("Enter showing status: ");
		System.out.println("(1) ------ Coming Soon");
		System.out.println("(2) ------ Preview");
		System.out.println("(3) ------ Now Showing");
		do {
			choice = 0;	
			System.out.printf("Option: ");
			
			while(!sc.hasNextInt()) {
				System.out.println("Invalid input. Please enter an integer!");
				System.out.printf("Option: ");
				sc.next();
			}
			
			choice = sc.nextInt();

			switch (choice) {
				case 1 -> showingStatus = ShowingStatus.COMING_SOON;
				case 2 -> showingStatus = ShowingStatus.PREVIEW;
				case 3 -> showingStatus = ShowingStatus.NOW_SHOWING;
				default -> System.out.println("Option does not exist! Please input a valid choice!");
			}
		} while(choice < 1 || choice > 3);
		
		Movie m =  new Movie(movieName, filmRating, is3D, isBlockbuster, movieGenre, synopsis, director, movieCast, 0, reviewList, 0, showingStatus);		
		movieData.add(m);
		
		movieDataHandler.save(movieData);
		System.out.println("Movie added to list! Returning to admin menu...");
	}
	
	public void edit() {
		DataHandler movieDataHandler = new MovieDataHandler();
		List<Movie> movieData = movieDataHandler.retrieve();
		DataHandler timeslotDataHandler = new TimeslotDataHandler();
		List<Timeslot> movieTimeslots = timeslotDataHandler.retrieve();
		Scanner sc = new Scanner(System.in);
		int choice, index = 0;
		
		if(movieData.size() == 0) {
			System.out.println("\nThere are no movies to update! Returning to admin menu...");
			return;
		} else {
			System.out.println("\nChoose a movie to update showing status:");
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
			
		System.out.println("\nEnter new showing status: ");
		System.out.println("(1) ------ Coming Soon");
		System.out.println("(2) ------ Preview");
		System.out.println("(3) ------ Now Showing");
		System.out.println("(4) ------ End of Showing");
		do {
			System.out.printf("\nOption: ");
				
			while(!sc.hasNextInt()) {
				System.out.println("Invalid input. Please enter an integer!");
				System.out.printf("Option: ");
				sc.next();
			}
					
			choice = sc.nextInt();

			switch (choice) {
				case 1 -> {
					movieData.get(index).setShowingStatus(ShowingStatus.COMING_SOON);
					movieDataHandler.save(movieData);
					System.out.println("Showing status updated. Returning to admin menu...");
					return;
				}
				case 2 -> {
					movieData.get(index).setShowingStatus(ShowingStatus.PREVIEW);
					movieDataHandler.save(movieData);
					System.out.println("Showing status updated. Returning to admin menu...");
					return;
				}
				case 3 -> {
					movieData.get(index).setShowingStatus(ShowingStatus.NOW_SHOWING);
					movieDataHandler.save(movieData);
					System.out.println("Showing status updated. Returning to admin menu...");
					return;
				}
				case 4 -> {
					movieData.get(index).setShowingStatus(ShowingStatus.END_OF_SHOWING);
					System.out.println("Showing status updated. Since movie is marked as end of showing, movie will be removed.");
					for (int i = 0; i < movieTimeslots.size(); i++) {
						if (movieTimeslots.get(i).getMovieTitle().equals(movieData.get(index).getTitle())) {
							movieTimeslots.remove(movieTimeslots.get(i));
						}
					}
					movieData.remove(index);
					movieDataHandler.save(movieData);
					timeslotDataHandler.save(movieTimeslots);
					System.out.println("Movie removed. Returning to admin menu...");
					return;
				}
				default -> System.out.println("Option does not exist. Please key in a valid option!\n");
			}
		} while (true);
	}

	public void remove() {
		DataHandler movieDataHandler = new MovieDataHandler();
		List<Movie> movieData = movieDataHandler.retrieve();
		DataHandler timeslotDataHandler = new TimeslotDataHandler();
		List<Timeslot> movieTimeslots = timeslotDataHandler.retrieve();
		Scanner sc = new Scanner(System.in);
		int choice, index = 0;
		
		if (movieData.size() == 0) {
			System.out.println("\nThere are no movies to remove! Returning to admin menu...");
			return;
		} else {
			System.out.println("\nChoose a movie to remove:");
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
		
		for (int i = 0; i<movieTimeslots.size(); i++) {
			if (movieTimeslots.get(i).getMovieTitle().equals(movieData.get(index).getTitle())) {
				movieTimeslots.remove(movieTimeslots.get(i));
			}
		}
		
		movieData.remove(index);
		movieDataHandler.save(movieData);
		timeslotDataHandler.save(movieTimeslots);
		System.out.printf("Movie removed! Returning to admin menu...\n");
	}
}
