package entity.movie;

import java.io.Serial;
import java.time.LocalDate;
import java.time.LocalTime;
import entity.cinema.Cinema;
import interfaces.Entity;
import interfaces.SerializedData;

public class Timeslot implements SerializedData, Entity {
	@Serial
	private static final long serialVersionUID =-6326284475932881679L;
	private String movieTitle;
	private String cineplex;
	private Cinema cinema;
	private LocalDate showDate;
	private LocalTime showTime;
	
	public Timeslot(String movieTitle, String cineplex, Cinema cinema, LocalDate showDate, LocalTime showTime) {
		this.movieTitle = movieTitle;
		this.cineplex = cineplex;
		this.cinema = cinema;
		this.showDate = showDate;
		this.showTime = showTime;
	}
	
	public void setCineplex(String cineplex) {
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
	
	public String getMovieTitle() {
		return this.movieTitle;
	}
	
	public String getCineplex() {
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
}
