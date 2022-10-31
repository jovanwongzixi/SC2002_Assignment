package entity.cinema;

import java.util.List;
import interfaces.SerializedData;

public class Cineplex implements SerializedData{
	private String name;
	private String code;
	private List<Cinema> cinema;
	
	public Cineplex(String name, String code, List<Cinema> cinema) {
		this.name = name;
		this.code = code;
		this.cinema = cinema;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getCode() {
		return this.code;
	}
	
	public List<Cinema> getCinema(){
		return this.cinema;
	}
}
