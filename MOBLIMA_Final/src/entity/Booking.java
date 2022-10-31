package entity;

import java.time.LocalDateTime;
import entity.movie.Ticket;
import interfaces.SerializedData;

public class Booking implements SerializedData{
	private String name;
	private String emailAddress;
	private String mobileNum;
	private Ticket ticket;
	private LocalDateTime timeOfPurchase;
	private String tID;
	
	public Booking(String name, String emailAddress, String mobileNum, Ticket ticket, LocalDateTime timeOfPurchase, String tID) {
		this.name = name;
		this.emailAddress = emailAddress;
		this.mobileNum = mobileNum;
		this.ticket = ticket;
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
	
	public Ticket getTicket() {
		return this.ticket;
	}
	
	public LocalDateTime getTimeOfPurchase() {
		return this.timeOfPurchase;
	}
	
	public String getTID() {
		return this.tID;
	}
}
