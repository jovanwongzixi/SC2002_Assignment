package entity.cinema;
//copying everything in this to my cineplex.java
import java.util.*;
import java.io.Serializable;

public class Cineplex implements Serializable{
	private static final long serialVersionUID = 4L;
	private String name;
	private List<Cinema> cinema;
	
	public Cineplex(String name, List<Cinema> cinema) {
		this.name = name;
		this.cinema = cinema;
	}
	
	public String getName() {
		return this.name;
	}
	
	public List<Cinema> getCinema(){
		return this.cinema;
	}
}
