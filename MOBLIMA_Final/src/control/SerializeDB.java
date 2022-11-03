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
			fis = new FileInputStream("MOBLIMA_Final/bin/data/admin_accounts.dat");
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
			fis = new FileInputStream("MOBLIMA_Final/bin/data/movies.dat");
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
			fis = new FileInputStream("MOBLIMA_Final/bin/data/cineplex.dat");
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
			fis = new FileInputStream("MOBLIMA_Final/bin/data/movie_timeslots.dat");
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
			fis = new FileInputStream("MOBLIMA_Final/bin/data/ticket_prices.dat");
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
			fis = new FileInputStream("MOBLIMA_FINAL/bin/data/holidays.dat");
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
			fis = new FileInputStream("MOBLIMA_FINAL/bin/data/booking.dat");
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
	
	public static List<Boolean> getFlags(){
		List<Boolean> data = new ArrayList<Boolean>();
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream("MOBLIMA_FINAL/bin/data/flags.dat");
			in = new ObjectInputStream(fis);
			data = (List<Boolean>)in.readObject();
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
			FileOutputStream fos = new FileOutputStream("MOBLIMA_FINAL/bin/data/movies.dat");
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
			FileOutputStream fos = new FileOutputStream("MOBLIMA_FINAL/bin/data/movie_timeslots.dat");
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
			FileOutputStream fos = new FileOutputStream("MOBLIMA_FINAL/bin/data/ticket_prices.dat");
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
			FileOutputStream fos = new FileOutputStream("MOBLIMA_FINAL/bin/data/booking.dat");
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
			FileOutputStream fos = new FileOutputStream("MOBLIMA_FINAL/bin/data/holidays.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(holidays);
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void writeToFlag(List<Boolean> sortSwitch) {
		try {
			FileOutputStream fos = new FileOutputStream("MOBLIMA_FINAL/bin/data/flags.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(sortSwitch);
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
<<<<<<< Updated upstream
	public static <T> List<T> getList(String className){
		List<T> data = new ArrayList<>();
		String fileName = "MOBLIMA_FINAL/bin/data" + className + ".dat";
		try {
=======
	public static <T> void writeToFile(String className, List<T> inputList){
		try {
			String fileName = "MOBLIMA_FINAL/bin/data/" + className + ".dat";
			FileOutputStream fos = new FileOutputStream(fileName);
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			oos.writeObject(inputList);
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static <T> List<T> getList(String className){
		List<T> data = new ArrayList<>();
		 //fis = null;
		 //in = null;
		try {
			String fileName = "MOBLIMA_FINAL/bin/data/" + className + ".dat";
>>>>>>> Stashed changes
			FileInputStream fis = new FileInputStream(fileName);
			ObjectInputStream in = new ObjectInputStream(fis);
			data = (List<T>)in.readObject();
			in.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return data;
	}
<<<<<<< Updated upstream
	public static <T> void writeToFile(String className, List<T> inputList){
		String fileName = "MOBLIMA_FINAL/bin/data" + className + ".dat";
		try {
			FileOutputStream fos = new FileOutputStream(fileName);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(inputList);
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
=======
>>>>>>> Stashed changes
}
