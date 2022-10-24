package control.customer;

import java.time.LocalDateTime;
import java.util.*;
import boundary.CustomerUI;
import control.Displayer;
import control.SerializeDB;
import entity.movie.*;

public class CustomerCommands {
	
	@SuppressWarnings("resource")
	public static void showMovieList() {
		Scanner sc = new Scanner(System.in);
		Displayer displayer = new Displayer();
		int index, choice;
		boolean toggle = true;
		
		do {
			try {
				index = displayer.displayMovieList();
				System.out.println();
				System.out.printf("Input option number to view movie details (-1 to return to customer menu): ");
				
				choice = sc.nextInt();
				
				if(choice > index) {
					System.out.println("Option does not exist. Please key in a valid option!\n");
				} else if (choice == -1) {
					toggle = false;
					System.out.println("Returning to customer menu...");
					CustomerUI.run();
				} else {
					toggle = false;
					viewMovieDetails(choice);
				}
			} catch (InputMismatchException ex) {
				sc.nextLine();
				System.out.println("Invalid input. Please choose a valid option!\n");
			}
		} while (toggle);
	}
	
	@SuppressWarnings("resource")
	public static void showMovieTimeslot() {
		Scanner sc = new Scanner(System.in);
		Displayer displayer = new Displayer();
		int index, choice;
		boolean toggle = true;
		
		do {
			try {
				index = displayer.displayMovieList();
				System.out.println();
				System.out.printf("Input option number to view available timeslots (-1 to return to customer menu): ");
				
				choice = sc.nextInt();
				
				if(choice > index) {
					System.out.println("Option does not exist. Please key in a valid option!\n");
				} else if (choice == -1) {
					toggle = false;
					System.out.println("Returning to customer menu...");
					CustomerUI.run();
				} else {
					toggle = false;
					showSpecificTimeslot(choice);	
				}
			} catch (InputMismatchException ex) {
				sc.nextLine();
				System.out.println("Invalid input. Please choose a valid option!\n");
			}
		} while (toggle);
	}
	
	@SuppressWarnings("resource")
	public static void inputMovieReview() {
		Scanner sc = new Scanner(System.in);
		Displayer displayer = new Displayer();
		int index, choice;
		boolean toggle = true;
		
		do {
			try {
				index = displayer.displayMovieList();				
				System.out.println();
				System.out.printf("Choose movie to review (-1 to return to customer menu): ");
				
				choice = sc.nextInt();
				
				if(choice > index) {
					System.out.println("Option does not exist. Please key in a valid option!\n");
				} else if (choice == -1) {
					toggle = false;
					System.out.println("Returning to customer menu...");
					CustomerUI.run();
				} else {
					toggle = false;
					enterReview(choice);	
				}
			} catch  (InputMismatchException ex) {
				sc.nextLine();
				System.out.println("Invalid input. Please choose a valid option!\n");
			}
		} while (toggle);		
	}
	
	@SuppressWarnings("resource")
	public static void viewMovieDetails(int choice) {
		Scanner sc = new Scanner(System.in);
		Displayer displayer = new Displayer();
		displayer.displayMovieDetails(choice-1);
		
		System.out.println("Press Enter to return to movie list...");
		if(sc.nextLine()!= null) {
			showMovieList();
		}
	}
	
	@SuppressWarnings("resource")
	public static void showSpecificTimeslot(int choice) {
		Scanner sc = new Scanner(System.in);
		int storeIndex = choice;
		Displayer displayer = new Displayer();
		boolean toggle = true;
		
		do {
			try {
				int index = displayer.displayMovieShowtime(choice-1);
				System.out.println();
				System.out.printf("Input option number to view seats (-1 to return to previous menu): ");
				
				choice = sc.nextInt();
				
				if(choice > index) {
					System.out.println("Option does not exist. Please key in a valid option!\n");
				} else if (choice == -1) {
					toggle = false;
					showMovieTimeslot();
					System.out.println("Returning to previous menu...");
				} else {
					toggle = false;
					viewSeats(storeIndex, choice);
				}
			} catch (InputMismatchException ex) {
				sc.nextLine();
				System.out.println("Invalid input. Please choose a valid option!\n");
			}
		} while (toggle);	
	}
	
	@SuppressWarnings("resource")
	public static void viewSeats(int storeIndex, int choice) {
		Scanner sc = new Scanner(System.in);
		Displayer displayer = new Displayer();
		displayer.displaySeatLayout(choice-1);
		
		System.out.println("Press Enter to return to movie timeslots...");
		if(sc.nextLine()!= null) {
			showSpecificTimeslot(storeIndex);
		}
	}
	
	@SuppressWarnings("resource")
	public static void enterReview(int choice) {
		Scanner sc = new Scanner(System.in);
		List<Movie> movieData = SerializeDB.getMovieList();
		List<Review> movieReviews = new ArrayList<Review>();
		String nickname;
		double rating = 0;
		String content;
		LocalDateTime datetime;
		boolean toggle = true;
		
		movieReviews = movieData.get(choice-1).getReviews();
		
		System.out.printf("Enter nickname: ");
		nickname = sc.nextLine();
		do {
			try {
				System.out.printf("Enter rating (from 0-5): ");
				rating = sc.nextInt();
				
				if (rating >= 0 && rating <= 5) {
					toggle = false;
				} else {
					System.out.println("Please key in a valid rating!\n");
				}
				
			} catch (InputMismatchException ex) {
				sc.nextLine();
				System.out.println("Invalid input. Please choose a valid option!\n");
			}
		} while (toggle);
		sc.nextLine();
		
		System.out.println("Enter your review: ");
		content = sc.nextLine();
		datetime = LocalDateTime.now();
		
		Review r = new Review(nickname, datetime, content, rating);
		movieReviews.add(r);
		movieData.get(choice-1).setReviews(movieReviews);
		
		if(movieReviews.size() > 1) {
			double overallRating = 0;
			for (Review re : movieReviews) {
				overallRating += re.getRating();
			}
			overallRating /= (double)movieReviews.size();
			movieData.get(choice-1).setOverallRating(overallRating);
		}
		
		SerializeDB.writeToMovieList(movieData);
		System.out.println("Review added! Returning to customer menu...");
		CustomerUI.run();
	}
}
