package control.customer;

import java.time.LocalDateTime;
import java.util.*;
import control.SerializeDB;
import entity.movie.*;

public class MovieReviewer {
	
	public void start() {
		List<Movie> movieData = SerializeDB.getList("Movie");
		Scanner sc = new Scanner(System.in);
		int choice;
		
		do {
			displayMovieList();				
			System.out.printf("\nChoose movie to review (-1 to return to customer menu): ");
			
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
				enterReview(choice-1);
				break;
			}
		} while (true);		
	}
	
	private void displayMovieList() {
		List<Movie> movieData = SerializeDB.getList("Movie");
		int index = 0;
		
		System.out.println("-------------------- Movie List -------------------");
		for(Movie m: movieData) {
			index++;
			System.out.printf("(%d) ----------------	%s\n",index, m.getTitle());			
		}
	}
	
	private void enterReview(int index) {
		Scanner sc = new Scanner(System.in);
		List<Movie> movieData = SerializeDB.getList("Movie");
		List<Review> movieReviews = new ArrayList<Review>();
		String nickname, content;
		double rating = 0;
		LocalDateTime datetime;
		
		movieReviews = movieData.get(index).getReviews();
		
		System.out.printf("Enter nickname: ");
		nickname = sc.nextLine();
		do {
			System.out.printf("Enter rating (from 1-5): ");
			while(!sc.hasNextInt()) {
				System.out.println("Invalid input. Please enter an integer!");
				sc.next();
			}
			rating = sc.nextInt();
				
			if (rating >= 1 && rating <= 5) {
				break;
			} else {
				System.out.println("Please key in a valid rating!\n");
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
		//SerializeDB.writeToMovieList(movieData);
		SerializeDB.writeList("Movie",movieData);
		System.out.println("Review added! Returning to customer menu...");
		return;
	}
}
