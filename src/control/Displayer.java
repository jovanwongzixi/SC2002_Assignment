package control;

import java.util.*;
import movies.*;

public class Displayer {
	
	public int displayMovieList() {
		List<Movie> movieData = SerializeDB.getMovieList("src/data/movie.dat");
		int index = 0;
		
		System.out.println("------------------ Movie List -----------------");
		for(Movie m: movieData) {
			index++;
			System.out.printf("(%d) ----------------	%s\n",index, m.getTitle());			
		}
		return index;
	}
	
	public void displayMovieDetails(int index) {
		List<Movie> movieData = SerializeDB.getMovieList("src/data/movie.dat");
		List<String> castList = movieData.get(index).getCast();
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
		System.out.printf("Rating:\t\t\t%d\n", movieData.get(index).getOverallRating());
		System.out.printf("Reviews:\t\t%s\n", movieData.get(index).getReviews());			//reviews
		System.out.printf("Showing Status:\t\t%s\n", movieData.get(index).getShowingStatus().getText());
	}
}
