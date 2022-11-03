package entity.movie;

import java.io.Serial;
import java.util.List;

import interfaces.Entity;
import interfaces.SerializedData;

public class Movie implements SerializedData, Entity {
	@Serial
	private static final long serialVersionUID =-622362950391507260L;
	private String title;
	private FilmRating filmRating;
	private boolean is3D;
	private boolean isBlockbuster;
	private String movieGenre;
	private String synopsis;
	private String director;
	private List<String> cast;
	private double overallRating;
	private List<Review> reviews;
	private int ticketSales;
	private ShowingStatus showingStatus;
	
	
	public Movie(String title,  
				FilmRating filmRating,
				boolean is3D,
				boolean isBlockbuster,
				String movieGenre,
				String synopsis,
				String director,
				List<String> cast,
				double overallRating,
				List<Review> reviews,
				int ticketSales,
				ShowingStatus showingStatus) {
		
		this.title = title;
		this.filmRating = filmRating;
		this.is3D = is3D;
		this.isBlockbuster = isBlockbuster;
		this.movieGenre = movieGenre;
		this.synopsis = synopsis;
		this.director = director;
		this.cast = cast;
		this.overallRating = overallRating;
		this.reviews = reviews;
		this.ticketSales = ticketSales;
		this.showingStatus = showingStatus;
	}
	
	public void setTicketSales(int ticketSales) {
		this.ticketSales = ticketSales;
	}
	
	public void setShowingStatus(ShowingStatus showingStatus) {
		this.showingStatus = showingStatus;
	}
	
	public void setReviews(List<Review> reviews){
		this.reviews = reviews;
	}
	
	public void setOverallRating(double overallRating) {
		this.overallRating = overallRating;
	}

	public String getTitle() {
		return this.title;
	}
	
	public FilmRating getFilmRating() {
		return this.filmRating;
	}
	
	public boolean getIs3D() {
		return this.is3D;
	}
	
	public boolean getIsBlockbuster() {
		return this.isBlockbuster;
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
	
	public double getOverallRating() {
		return this.overallRating;
	}
	
	public List<Review> getReviews(){
		return this.reviews;
	}
	
	public int getTicketSales() {
		return this.ticketSales;
	}
	
	public ShowingStatus getShowingStatus() {
		return this.showingStatus;
	}
}
