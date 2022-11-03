package entity;

import java.io.Serial;
import java.time.*;

import interfaces.Entity;
import interfaces.SerializedData;

public class Booking implements SerializedData, Entity {
	@Serial
	private static final long serialVersionUID =-137031443563589353L;
	private String name;
	private String emailAddress;
	private String mobileNum;
	private double ticketPrice;
	private String movieTitle;
	private String cineplex;
	private int cinemaID;
	private LocalDate dateShow;
	private LocalTime timeShow;
	private LocalDateTime timeOfPurchase;
	private String tID;
	
	public Booking(String name,
				String emailAddress,
				String mobileNum, 
				double ticketPrice,
				String movieTitle,
				String cineplex,
				int cinemaID,
				LocalDate dateShow,
				LocalTime timeShow,
				LocalDateTime timeOfPurchase, 
				String tID) {
		
		this.name = name;
		this.emailAddress = emailAddress;
		this.mobileNum = mobileNum;
		this.ticketPrice = ticketPrice;
		this.movieTitle = movieTitle;
		this.cineplex = cineplex;
		this.cinemaID = cinemaID;
		this.dateShow = dateShow;
		this.timeShow = timeShow;
		this.timeOfPurchase = timeOfPurchase;
		this.tID = tID;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getEmailAddress() {
		return this.emailAddress;
	}
	
	public String getMobileNum() {
		return this.mobileNum;
	}
	
	public double getTicketPrice() {
		return this.ticketPrice;
	}
	
	public String getMovieTitle() {
		return this.movieTitle;
	}
	
	public String getCineplex() {
		return this.cineplex;
	}
	
	public int getCinemaID() {
		return this.cinemaID;
	}
	
	public LocalDate getDateShow() {
		return this.dateShow;
	}
	
	public LocalTime getTimeShow() {
		return this.timeShow;
	}
	
	public LocalDateTime getTimeOfPurchase() {
		return this.timeOfPurchase;
	}
	
	public String getTID() {
		return this.tID;
	}
}
