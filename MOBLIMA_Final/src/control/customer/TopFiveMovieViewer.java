package control.customer;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

import control.SerializeDB;
import entity.movie.Movie;
import entity.movie.Review;

public class TopFiveMovieViewer {
	public void view() {
		List<Movie> movieData = SerializeDB.getMovieList();
		Scanner sc = new Scanner(System.in);
		int choice;
			
		do {
			displayTop5MovieList();
			System.out.printf("\nInput option number to view movie details (-1 to return to customer menu): ");
			
			while(!sc.hasNextInt()) {
				System.out.println("Invalid input. Please enter an integer!");
				System.out.printf("Option: ");
				sc.next();
			}
					
			choice = sc.nextInt();
			
			if(choice > movieData.size()) {
				System.out.println("Option does not exist. Please key in a valid option!\n");
			} else if (choice == -1) {
				System.out.println("Returning to customer menu...");
				return;
			} else {
				displayMovieDetails(choice-1);
				System.out.println("\nPress Enter to return to movie list...");
				if(sc.nextLine() != null)
					sc.nextLine();
			}			
		} while (true);
	}
	
	private void displayTop5MovieList() {
		boolean sortFlag = SerializeDB.getFlags();
		List<Movie> movieData = SerializeDB.getMovieList();
		
		if (!sortFlag) {
			System.out.println("-------------------- Top 5 Movies by Rating -------------------");
			for(int index = 1; index <= 5; index++) {
				System.out.printf("(%d) ----------------	%s (Rating: %.1f)\n", index, movieData.get(index-1).getTitle(), movieData.get(index-1).getOverallRating());			
			}
		} else {
			System.out.println("-------------------- Top 5 Movies by Ticket Sales -------------------");
			for(int index = 1; index <= 5; index++) {
				System.out.printf("(%d) ----------------	%s (Ticket sales: %d)\n", index, movieData.get(index-1).getTitle(), movieData.get(index-1).getTicketSales());	
			}
		}
	}
	
	private void displayMovieDetails(int index) {
		List<Movie> movieData = SerializeDB.getMovieList();
		List<String> castList = movieData.get(index).getCast();
		List<Review> movieReviews = movieData.get(index).getReviews();
		String delimiter = ", ";
		
		System.out.printf("Title:\t\t\t%s\n", movieData.get(index).getTitle());
		System.out.printf("Film Rating:\t\t%s\n", movieData.get(index).getFilmRating());
		if (movieData.get(index).getIs3D()) {
			System.out.printf("Movie Type:\t\t3D\n");
		} else {
			System.out.printf("Movie Type:\t\tRegular 2D\n");
		}		
		System.out.printf("Genre:\t\t\t%s\n", movieData.get(index).getMovieGenre());
		String synopsis = movieData.get(index).getSynopsis();
		synopsis += "\n"; 
		synopsis = synopsis.replaceAll("(.{1,50})\\s+", "$1\n\t\t\t");
		System.out.printf("Synopsis:\t\t%s\n", synopsis);
		System.out.printf("Director:\t\t%s\n", movieData.get(index).getDirector());
		StringJoiner castJoiner = new StringJoiner(delimiter);
		castList.forEach(item -> castJoiner.add(item));	
		String castString = castJoiner.toString();
		castString += "\n";
		castString = castString.replaceAll("(.{1,50})\\s+", "$1\n\t\t\t");
		System.out.printf("Cast:\t\t\t%s\n", castString);
		System.out.printf("Showing Status:\t\t%s\n", movieData.get(index).getShowingStatus().getText());
		if (movieReviews.size() <= 1) {
			System.out.printf("Rating:\t\t\tNA\n");
		} else {
			System.out.printf("Rating:\t\t\t%.1f\n", movieData.get(index).getOverallRating());
		}
		if (movieReviews.size() == 0) {
			System.out.printf("Reviews:\t\tNA\n");
		} else {
			System.out.printf("Reviews: \t\t");
			for (Review r : movieReviews) {
				if (movieReviews.indexOf(r) == 0) {
					System.out.println(r.getNickname()+" ("+r.getRating()+") --------- (" +r.getDateTime().format(DateTimeFormatter.ofPattern("MMM dd uuuu HH:mm:ss"))+ ")");
				} else {
					System.out.println("\t\t\t"+r.getNickname()+" ("+r.getRating()+") --------- (" +r.getDateTime().format(DateTimeFormatter.ofPattern("MMM dd uuuu HH:mm:ss"))+ ")");
				}
				String reviewString = r.getContent();
				reviewString += "\n";
				reviewString = reviewString.replaceAll("(.{1,50})\\s+", "$1\n\t\t\t");
				System.out.printf("\t\t\t%s\n", reviewString);
			}		
		}		
	}
}
