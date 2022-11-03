package entity.cinema;

import java.io.Serial;
import java.util.List;

import interfaces.Entity;
import interfaces.SerializedData;

public class Cineplex implements SerializedData, Entity {
	@Serial
	private static final long serialVersionUID = -1270572564909922591L;
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
