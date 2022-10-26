package entity.cinema;

import java.time.*;
import entity.movie.Movie;
import java.io.Serializable;

public class Showtime implements Serializable{
	private static final long serialVersionUID = -8242226099133439559L;
	private Movie movie;
	private Cineplex cineplex;
	private Cinema cinema;
	private LocalDate showDate;
	private LocalTime showTime;
	
	public Showtime(Movie movie, Cineplex cineplex, Cinema cinema, LocalDate showDate, LocalTime showTime) {
		this.movie = movie;
		this.cineplex = cineplex;
		this.cinema = cinema;
		this.showDate = showDate;
		this.showTime = showTime;
	}
	
	public void setCineplex(Cineplex cineplex) {
		this.cineplex = cineplex;
	}
	
	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}
	
	public void setShowDate(LocalDate showDate) {
		this.showDate = showDate;
	}
	
	public void setShowTime(LocalTime showTime) {
		this.showTime = showTime;
	}
	
	public Movie getMovie() {
		return this.movie;
	}
	
	public Cineplex getCineplex() {
		return this.cineplex;
	}
	
	public Cinema getCinema() {
		return this.cinema;
	}
	
	public LocalDate getShowDate() {
		return this.showDate;
	}
	
	public LocalTime getShowTime() {
		return this.showTime;
	}
	
	public boolean equals(Object o) {
		if (o instanceof Showtime) {
			Showtime s = (Showtime)o;
			return (getMovie().equals(s.getMovie()));
		}
		return false;
	}
}
