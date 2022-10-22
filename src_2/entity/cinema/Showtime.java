package entity.cinema;
//copying over and will try to resolve different attribute types
import java.time.*;
import java.io.Serializable;

public class Showtime implements Serializable{
	private static final long serialVersionUID = -8242226099133439559L;
	private String title;
	private Cineplex cineplex;
	private Cinema cinema;
	private LocalDate showDate;
	private LocalTime showTime;
	
	public Showtime(String title, Cineplex cineplex, Cinema cinema, LocalDate showDate, LocalTime showTime) {
		this.title = title;
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
	
	public String getTitle() {
		return this.title;
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
}
