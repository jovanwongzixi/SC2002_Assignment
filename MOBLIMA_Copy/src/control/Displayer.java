package control;

import java.time.format.DateTimeFormatter;
import java.util.*;
import entity.movie.*;
import entity.cinema.*;

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
			System.out.printf("Reviews: \t\t");
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
		Movie movie = movieData.get(index);
		int i = 0, count = 0;
		
		for (Showtime s : showtimeData) {
			if(s.getMovie().getTitle().equals(movie.getTitle())) {
				count++;
			}
		}
		
		if (count == 0)
			return count;
		else {
		
			System.out.printf("------------------ %s Timeslots ------------------\n", movie.getTitle());
		
			for (Showtime s : showtimeData) {
				if(s.getMovie().getTitle().equals(movie.getTitle())) {
					i++;
					System.out.printf("(%d) -------\t Cineplex %s at Cinema %d on %td %<tb %<tY at %tR\n", i, s.getCineplex().getName(),
							s.getCinema().getID(), s.getShowDate(), s.getShowTime());
				}
			}
		
			return i;
		}
	}
	
	public void displaySeatLayout(int movieIndex, int showIndex) {
		List<Movie> movieData = SerializeDB.getMovieList();
		List<Showtime> showtimeData = SerializeDB.getShowtimeList();
		List<Showtime> bufferArr = new ArrayList<Showtime>();
		Movie movie = movieData.get(movieIndex);
		
		for (Showtime s : showtimeData) {
			if(s.getMovie().getTitle().equals(movie.getTitle())) {
				bufferArr.add(s);
			}
		}
		
		bufferArr.get(showIndex).getCinema().getCinemaLayout().printLayout();
	}
	
	public void displaySeatLayoutUnserialized(Showtime s) {
		s.getCinema().getCinemaLayout().printLayout();
	}
}
