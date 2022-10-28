package entity.movie;

import java.io.Serializable;

public class TicketPrice implements Serializable{
	private static final long serialVersionUID = 2888646272856667326L;
	private char group;
	private double price;
	
	public TicketPrice(char group, double price) {
		this.group = group;
		this.price = price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public char getGroup() {
		return this.group;
	}
	
	public double getPrice() {
		return this.price;
	}
}
