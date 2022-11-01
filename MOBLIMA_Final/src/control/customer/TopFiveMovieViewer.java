package control.customer;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

import control.SerializeDB;
import entity.movie.Movie;
import entity.movie.Review;

public class TopFiveMovieViewer {
	public void view() {
		List<Boolean> sortFlag = SerializeDB.getFlags();
		List<Movie> movieData = new ArrayList<Movie>(); 
		Scanner sc = new Scanner(System.in);
		int choice, switcher = 0;
		
		if(sortFlag.get(0) && sortFlag.get(1)) {
			do {
				System.out.println("\n(1) ----------------      View top 5 movies by rating");
				System.out.println("(2) ----------------      View top 5 movies by ticket sales");
				System.out.println("(3) ----------------      Return to customer menu...");
				System.out.printf("\nOption: ");
				
				while(!sc.hasNextInt()) {
					System.out.println("Invalid input. Please enter an integer!");
					System.out.printf("Option: ");
					sc.next();
				}
						
				switcher = sc.nextInt();
				
				if (switcher > 3) {
					System.out.println("Option does not exist. Please key in a valid option!\n");
				} else if (switcher == 3) {
					System.out.println("Returning to customer menu...");
					return;
				}		
			} while (switcher < 1 || switcher > 3);
		} else if (sortFlag.get(0)) {
			switcher = 1;
		} else if (sortFlag.get(1)) {
			switcher = 2;
		}
		
		do {
			movieData = displayTop5MovieList(switcher);
			System.out.printf("\nInput option number to view movie details (-1 to return to customer menu): ");
			
			while(!sc.hasNextInt()) {
				System.out.println("Invalid input. Please enter an integer!");
				System.out.printf("Option: ");
				sc.next();
			}
					
			choice = sc.nextInt();
			
			if(choice > 5) {
				System.out.println("Option does not exist. Please key in a valid option!\n");
			} else if (choice == -1) {
				System.out.println("Returning to customer menu...");
				return;
			} else {
				displayMovieDetails(movieData, choice-1);
				System.out.printf("\nPress Enter to return to movie list...");
				if(sc.nextLine() != null)
					sc.nextLine();
			}			
		} while (true);
	}
	
	private List<Movie> displayTop5MovieList(int switcher) {	
		List<Movie> movieList = SerializeDB.getMovieList(), topRatingList = new ArrayList<Movie>(), 
				topTicketSalesList = new ArrayList<Movie>();
		
		Collections.sort(movieList, (m1, m2) -> (Double.compare(m2.getOverallRating(), m1.getOverallRating())));
		for (int i = 0; i < 5; i++) {
			topRatingList.add(movieList.get(i));
		}
		
		Collections.sort(movieList, (m1, m2) -> (m2.getTicketSales() - m1.getTicketSales()));
		for (int i = 0; i < 5; i++) {
			topTicketSalesList.add(movieList.get(i));
		}
		
		if (switcher == 1) {
			System.out.println("\n-------------------- Top 5 Movies by Rating -------------------");
			for(int index = 1; index <= 5; index++) {
				System.out.printf("(%d) ----------------	%s (Rating: %.1f)\n", index, topRatingList.get(index-1).getTitle(), topRatingList.get(index-1).getOverallRating());			
			}
			return topRatingList;
		} else {
			System.out.println("\n-------------------- Top 5 Movies by Ticket Sales -------------------");
			for(int index = 1; index <= 5; index++) {
				System.out.printf("(%d) ----------------	%s (Ticket sales: %d)\n", index, topTicketSalesList.get(index-1).getTitle(), topTicketSalesList.get(index-1).getTicketSales());	
			}
			return topTicketSalesList;
		}
	}
	
	private void displayMovieDetails(List<Movie> movieData, int index) {
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
