package entity.cinema;

import java.util.*;
import java.io.Serializable;

public class CinemaLayout implements Serializable{
	private static final long serialVersionUID = -5060404325247259284L;
	List<List<Seat>> cinemaLayout = new ArrayList<List<Seat>>();
	
	public CinemaLayout(List<List<Seat>> cinemaLayout) {
		this.cinemaLayout = cinemaLayout;
	}
	
	public List<List<Seat>> getCinemaLayout(){
		return this.cinemaLayout;
	}
}