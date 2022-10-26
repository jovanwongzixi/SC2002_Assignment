package control.customer;

import java.time.LocalDateTime;
import java.util.*;
import control.*;
import entity.movie.*;

public class MovieController {
	
	@SuppressWarnings("resource" )
	public static void viewMovieDetails(int index) {
		Scanner sc = new Scanner(System.in);
		Displayer displayer = new Displayer();
		displayer.displayMovieDetails(index);
		
		System.out.println("Press Enter to return to movie list...");
		if(sc.nextLine()!= null) {
			return;
		}
	}
	
	@SuppressWarnings("resource")
	public static void viewSpecificTimeslot(int index) {
		Scanner sc = new Scanner(System.in);
		int choice;
		Displayer displayer = new Displayer();
		
		do {
			try {
				int show_index = displayer.displayMovieShowtime(index);
				if (show_index == 0) {
					System.out.println("There are no timeslots for the movie!");
					return;
				}
				
				System.out.println();
				System.out.printf("Input option number to view seats (-1 to return to previous menu): ");
				
				choice = sc.nextInt();
				
				if(choice > show_index) {
					System.out.println("Option does not exist. Please key in a valid option!\n");
				} else if (choice == -1) {
					System.out.println("Returning to previous menu...");
					return;
				} else {
					SeatController.viewSeats(index, choice-1);
				}
			} catch (InputMismatchException ex) {
				sc.nextLine();
				System.out.println("Invalid input. Please choose a valid option!\n");
			}
		} while (true);	
	}
	
	@SuppressWarnings("resource")
	public static void bookSpecificTimeslot(int index) {
		Scanner sc = new Scanner(System.in);
		int choice;
		Displayer displayer = new Displayer();
		
		do {
			try {
				int show_index = displayer.displayMovieShowtime(index);
				System.out.println();
				System.out.printf("Input option number to view seats (-1 to return to previous menu): ");
				
				choice = sc.nextInt();
				
				if(choice > show_index) {
					System.out.println("Option does not exist. Please key in a valid option!\n");
				} else if (choice == -1) {
					System.out.println("Returning to previous menu...");
					return;
				} else {
					SeatController.selectSeats(index, choice-1);
				}
			} catch (InputMismatchException ex) {
				sc.nextLine();
				System.out.println("Invalid input. Please choose a valid option!\n");
			}
		} while (true);	
	}
	
	@SuppressWarnings("resource")
	public static void enterReview(int index) {
		Scanner sc = new Scanner(System.in);
		List<Movie> movieData = SerializeDB.getMovieList();
		List<Review> movieReviews = new ArrayList<Review>();
		String nickname, content;
		double rating = 0;
		LocalDateTime datetime;
		
		movieReviews = movieData.get(index).getReviews();
		
		System.out.printf("Enter nickname: ");
		nickname = sc.nextLine();
		do {
			try {
				System.out.printf("Enter rating (from 1-5): ");
				rating = sc.nextInt();
				
				if (rating >= 1 && rating <= 5) {
					break;
				} else {
					System.out.println("Please key in a valid rating!\n");
				}				
			} catch (InputMismatchException ex) {
				sc.nextLine();
				System.out.println("Invalid input. Please choose a valid option!\n");
			}
		} while (true);
		sc.nextLine();
		
		System.out.println("Enter your review: ");
		content = sc.nextLine();
		datetime = LocalDateTime.now();
		
		Review r = new Review(nickname, datetime, content, rating);
		movieReviews.add(r);
		movieData.get(index).setReviews(movieReviews);
		
		if(movieReviews.size() > 1) {
			double overallRating = 0;
			for (Review re : movieReviews) {
				overallRating += re.getRating();
			}
			overallRating /= (double)movieReviews.size();
			movieData.get(index).setOverallRating(overallRating);
		}
		
		SerializeDB.writeToMovieList(movieData);
		System.out.println("Review added! Returning to customer menu...");
		return;
	}
}
