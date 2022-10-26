package control;

import java.io.*;
import java.util.*;
import java.time.*;
import entity.user.Admin;
import entity.Booking;
import entity.cinema.*;
import entity.movie.*;

public class SerializeDB {

	public static void createNewFile(String filename) {
		try {
			File file = new File(filename);
			file.createNewFile();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static List<Admin> getAdminList() {
		List<Admin> data = new ArrayList<Admin>();
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream("data/admin.dat");
			in = new ObjectInputStream(fis);
			data = (List<Admin>)in.readObject();
			in.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return data;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Movie> getMovieList() {
		List<Movie> data = new ArrayList<Movie>();
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream("data/movie.dat");
			in = new ObjectInputStream(fis);
			data = (List<Movie>)in.readObject();
			in.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return data;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Cineplex> getCineplexList() {
		List<Cineplex> data = new ArrayList<Cineplex>();
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream("data/cineplex.dat");
			in = new ObjectInputStream(fis);
			data = (List<Cineplex>)in.readObject();
			in.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return data;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Showtime> getShowtimeList() {
		List<Showtime> data = new ArrayList<Showtime>();
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream("data/cinema_showtime.dat");
			in = new ObjectInputStream(fis);
			data = (List<Showtime>)in.readObject();
			in.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return data;
	}
	
	@SuppressWarnings("unchecked")
	public static List<LocalDate> getHolidays(){
		List<LocalDate> data = new ArrayList<LocalDate>();
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream("data/holidays.dat");
			in = new ObjectInputStream(fis);
			data = (List<LocalDate>)in.readObject();
			in.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex ) {
			ex.printStackTrace();
		}
		return data;
	}
	
	@SuppressWarnings("unchecked")
	public static List<TicketPrice> getTicketPrices(){
		List<TicketPrice> data = new ArrayList<TicketPrice>();
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream("data/ticket_prices.dat");
			in = new ObjectInputStream(fis);
			data = (List<TicketPrice>)in.readObject();
			in.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return data;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Booking> getBookingList(){
		List<Booking> data = new ArrayList<Booking>();
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream("data/booking.dat");
			in = new ObjectInputStream(fis);
			data = (List<Booking>)in.readObject();
			in.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return data;
	}
	
	public static void writeToMovieList(List<Movie> movieList) {
		try {
			FileOutputStream fos = new FileOutputStream("data/movie.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(movieList);
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void writeToBookingData(List<Booking> bookingData) {
		try {
			FileOutputStream fos = new FileOutputStream("data/booking.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(bookingData);
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void writeToShowtimeList(List<Showtime> showtimeList) {
		try {
			FileOutputStream fos = new FileOutputStream("data/cinema_showtime.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(showtimeList);
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
