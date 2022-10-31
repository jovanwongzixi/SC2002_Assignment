package control;
/*
 * No issues.
 */
import java.io.*;
import java.time.LocalDate;
import java.util.*;
import entity.Admin;
import entity.Booking;
import entity.cinema.Cineplex;
import entity.movie.*;

public class SerializeDB {
	
	public static List<Admin> getAdminList() {
		List<Admin> data = new ArrayList<Admin>();
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream("data/admin_accounts.dat");
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
	
	public static List<Movie> getMovieList() {
		List<Movie> data = new ArrayList<Movie>();
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream("data/movies.dat");
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
	
	public static List<Timeslot> getMovieTimeslots() {
		List<Timeslot> data = new ArrayList<Timeslot>();
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream("data/movie_timeslots.dat");
			in = new ObjectInputStream(fis);
			data = (List<Timeslot>)in.readObject();
			in.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return data;
	}
	
	public static List<Double> getTicketPrices(){
		List<Double> data = new ArrayList<Double>();
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream("data/ticket_prices.dat");
			in = new ObjectInputStream(fis);
			data = (List<Double>)in.readObject();
			in.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return data;
	}
	
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
	
	public static boolean getFlags(){
		boolean data = false;
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream("data/flags.dat");
			in = new ObjectInputStream(fis);
			data = (boolean)in.readObject();
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
			FileOutputStream fos = new FileOutputStream("data/movies.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(movieList);
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void writeToMovieTimeslots(List<Timeslot> movieTimeslots) {
		try {
			FileOutputStream fos = new FileOutputStream("data/movie_timeslots.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(movieTimeslots);
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void writeToTicketPrices(List<Double> ticketPrices) {
		try {
			FileOutputStream fos = new FileOutputStream("data/ticket_prices.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(ticketPrices);
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
	
	public static void writeToHolidayList(List<LocalDate> holidays) {
		try {
			FileOutputStream fos = new FileOutputStream("data/holidays.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(holidays);
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void writeToFlag(boolean sortSwitch) {
		try {
			FileOutputStream fos = new FileOutputStream("data/flags.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(sortSwitch);
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
