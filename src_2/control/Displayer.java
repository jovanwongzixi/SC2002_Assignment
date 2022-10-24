package control;

import java.time.format.DateTimeFormatter;
import java.util.*;
import entity.movie.*;
import entity.cinema.Showtime;

public class Displayer {
	
	public int displayMovieList() {
		List<Movie> movieData = SerializeDB.getMovieList();
		int index = 0;
		
		System.out.println("-------------------- Movie List -------------------");
		for(Movie m: movieData) {
			index++;
			System.out.printf("(%d) ----------------	%s\n",index, m.getTitle());			
		}
		return index;
	}
	
	public void displayMovieDetails(int index) {
		List<Movie> movieData = SerializeDB.getMovieList();
		List<String> castList = movieData.get(index).getCast();
		List<Review> movieReviews = movieData.get(index).getReviews();
		String delimiter = ", ";
		
		System.out.printf("Title:\t\t\t%s\n", movieData.get(index).getTitle());
		System.out.printf("Film Rating:\t\t%s\n", movieData.get(index).getFilmRating());
		System.out.printf("Movie Type:\t\t%s\n", movieData.get(index).getMovieType().getText());
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
			System.out.printf("Reviews: \t\t", movieReviews);
			for (Review r : movieReviews) {
				if (movieReviews.indexOf(r) == 0) {
					System.out.println(r.getNickname()+" (" +r.getDateTime().format(DateTimeFormatter.ofPattern("MMM dd uuuu HH:mm:ss"))+ ")");
				} else {
					System.out.println("\t\t\t"+r.getNickname()+" (" +r.getDateTime().format(DateTimeFormatter.ofPattern("MMM dd uuuu HH:mm:ss"))+ ")");
				}
				String reviewString = r.getContent();
				reviewString += "\n";
				reviewString = reviewString.replaceAll("(.{1,50})\\s+", "$1\n\t\t\t");
				System.out.printf("\t\t\t%s\n", reviewString);
			}		
		}		
	}
	
	public int displayMovieShowtime(int index) {
		List<Movie> movieData = SerializeDB.getMovieList();
		List<Showtime> showtimeData = SerializeDB.getShowtimeList();
		String movieTitle = movieData.get(index).getTitle();
		int i = 0;
		
		System.out.printf("------------------ %s Timeslots ------------------\n", movieTitle);
		
		for (Showtime s : showtimeData) {
			if(s.getTitle().equals(movieTitle)) {
				i++;
				System.out.printf("(%d) -------\t Cineplex %s at Cinema %d on %td %<tb %<tY at %tR\n", i, s.getCineplex().getName(),
						s.getCinema().getID(), s.getShowDate(), s.getShowTime());
			}
		}
		
		return i;
	}
	
	public void displaySeatLayout(int index) {
		System.out.println("Placeholder lmao");						//function run checker
	}
}
