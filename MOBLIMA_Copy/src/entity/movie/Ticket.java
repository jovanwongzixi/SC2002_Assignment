package entity.movie;

import java.io.Serializable;
import entity.cinema.*;

public class Ticket implements Serializable{
	private static final long serialVersionUID = 8754821187119887618L;
	private Cineplex cineplex;
	private MovieType movieType;
	private boolean isBlockbuster;
	private CinemaType cinemaType;
	private AgeType ageType;
	private SeatType seatType;
	private boolean isSpecial;
	
	public Ticket(Cineplex cineplex, MovieType movieType, boolean isBlockbuster, CinemaType cinemaType, 
			AgeType ageType, SeatType seatType, boolean isSpecial) {
		
		this.cineplex = cineplex;
		this.movieType = movieType;
		this.isBlockbuster = isBlockbuster;
		this.cinemaType = cinemaType;
		this.ageType = ageType;
		this.seatType = seatType;
		this.isSpecial = isSpecial;
	}
	
	public Cineplex getCineplex() {
		return this.cineplex;
	}
	
	public MovieType getMovieType() {
		return this.movieType;
	}
	
	public boolean getIsBlockbuster() {
		return this.isBlockbuster;
	}
	
	public CinemaType getCinemaType() {
		return this.cinemaType;
	}
	
	public AgeType getAgeType() {
		return this.ageType;
	}
	
	public SeatType getSeatType() {
		return this.seatType;
	}
	
	public boolean getIsSpecial() {
		return this.isSpecial;
	}
}
