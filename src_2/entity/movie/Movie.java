package entity.movie;
//copying over functions and adapting attribute types
import java.io.Serializable;
import java.util.*;

public class Movie implements Serializable{
	private static final long serialVersionUID = 2L;
	
	private String title;
	private FilmRating filmRating;
	private MovieType movieType;
	private String movieGenre;
	private String synopsis;
	private String director;
	private List<String> cast;
	private Double overallRating;
	private List<MovieReview> reviews;
	private ShowingStatus showingStatus;
	
	public Movie(String title,  
				FilmRating filmRating,
				MovieType movieType,
				String movieGenre,
				String synopsis,
				String director,
				List<String> cast,
				Double overallRating,
				List<MovieReview> reviews,
				ShowingStatus showingStatus) {
		
		this.title = title;
		this.filmRating = filmRating;
		this.movieType = movieType;
		this.movieGenre = movieGenre;
		this.synopsis = synopsis;
		this.director = director;
		this.cast = cast;
		this.overallRating = overallRating;
		this.reviews = reviews;
		this.showingStatus = showingStatus;
	}
	
	public void setFilmRating(FilmRating filmRating) {
		this.filmRating = filmRating;
	}
	
	public void setMovieType(MovieType movieType) {
		this.movieType = movieType;
	}
	
	public void setShowingStatus(ShowingStatus showingStatus) {
		this.showingStatus = showingStatus;
	}

	public String getTitle() {
		return this.title;
	}
	
	public FilmRating getFilmRating() {
		return this.filmRating;
	}
	
	public MovieType getMovieType() {
		return this.movieType;
	}
	
	public String getMovieGenre() {
		return this.movieGenre;
	}
	
	public String getSynopsis() {
		return this.synopsis;
	}
	
	public String getDirector() {
		return this.director;
	}
	
	public List<String> getCast(){
		return this.cast;
	}
	
	public Double getOverallRating() {
		return this.overallRating;
	}
	
	public List<MovieReview> getReviews(){
		return this.reviews;
	}
	
	public ShowingStatus getShowingStatus() {
		return this.showingStatus;
	}
}
