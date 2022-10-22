package entity.cinema;
//copying everything other than layout part
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cinema implements Serializable{
	private static final long serialVersionUID = 5462962575403177526L;
	private int id;
	private CinemaLayout cinemaLayout;
	private CinemaType cinemaType;
	
	public Cinema(int id, CinemaLayout cinemaLayout, CinemaType cinemaType) {
		this.id = id;
		this.cinemaLayout = cinemaLayout;
		this.cinemaType = cinemaType;
	}
	
	public void setCinemaLayout() {
		List<List<Seat>> cinemaArray = new ArrayList<List<Seat>>();
		
		for (int i = 1; i <= 8; i++) {
			List<Seat> seatArray = new ArrayList<Seat>();
			
			for (int j = 1; j <= 15; j++) {
				Seat s = new Seat(i,j,SeatState.AVAILABLE);
				seatArray.add(s);				
			}
			
			cinemaArray.add(seatArray);
		}
		
		CinemaLayout c = new CinemaLayout(cinemaArray);
		this.cinemaLayout = c;
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
