package entity.cinema;

import java.io.Serializable;

public class Cinema implements Serializable{
	private static final long serialVersionUID = 5462962575403177526L;
	private int id;
	private CinemaLayout cinemaLayout;
	private CinemaType cinemaType;
	
	public Cinema(int id, CinemaType cinemaType) {
		this.id = id;
		this.cinemaType = cinemaType;
		setCinemaLayout(this.cinemaType);
	}
	
	public void setCinemaLayout(CinemaType cinemaType) {
		this.cinemaLayout = new CinemaLayout(cinemaType);;
	}
	
	public int getID() {
		return this.id;
	}
	
	public CinemaLayout getCinemaLayout() {
		return this.cinemaLayout;
	}
		
	public CinemaType getCinemaType() {
		return this.cinemaType;
	}
}
