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
import interfaces.Entity;
import interfaces.SerializedData;

public class SerializeDB {

	public static List<Admin> getAdminList() {
		List<Admin> data = new ArrayList<Admin>();
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream("MOBLIMA_Final/bin/data/Admin.dat");
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
			fis = new FileInputStream("MOBLIMA_Final/bin/data/Movie.dat");
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
			fis = new FileInputStream("MOBLIMA_Final/bin/data/Cineplex.dat");
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
			fis = new FileInputStream("MOBLIMA_Final/bin/data/Timeslot.dat");
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
			fis = new FileInputStream("MOBLIMA_FINAL/bin/data/Booking.dat");
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
			FileOutputStream fos = new FileOutputStream("MOBLIMA_FINAL/bin/data/Movie.dat");
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
			FileOutputStream fos = new FileOutputStream("MOBLIMA_FINAL/bin/data/Timeslot.dat");
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
			FileOutputStream fos = new FileOutputStream("MOBLIMA_FINAL/bin/data/Booking.dat");
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
	public static <T extends SerializedData> List<T> getList(String className){
		List<T> data = new ArrayList<>();
		try {
			String fileName = "MOBLIMA_FINAL/bin/data/" + className + ".dat";
			FileInputStream fis = new FileInputStream(fileName);
			ObjectInputStream in = new ObjectInputStream(fis);
			data = (List<T>)in.readObject();
			in.close();
		} catch (IOException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return data;
	}
	public static <T extends SerializedData> void writeList(String className, List<T> inputList){
		try {
			String fileName = "MOBLIMA_FINAL/bin/data/" + className + ".dat";
			FileOutputStream fos = new FileOutputStream(fileName);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(inputList);
			//System.out.println(inputList.get(0).getClass());
			//System.out.println("Data index 0: " + inputList.get(0));
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static <T extends SerializedData> Map<String, T> getMap(String className){
		Map<String, T> data = new HashMap<>();
		try {
			String fileName = "MOBLIMA_FINAL/bin/data/" + className + ".dat";
			FileInputStream fis = new FileInputStream(fileName);
			ObjectInputStream in = new ObjectInputStream(fis);
			data = (Map<String, T>)in.readObject();
			in.close();
		} catch(IOException | ClassNotFoundException e){
			e.printStackTrace();
		}
		return data;
	}
	public static <T extends SerializedData> void writeMap(String className, Map<String,T> inputMap){
		try {
			String fileName = "MOBLIMA_FINAL/bin/data/" + className + ".dat";
			FileOutputStream fos = new FileOutputStream(fileName);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(inputMap);
			//System.out.println(inputList.get(0).getClass());
			//System.out.println("Data index 0: " + inputList.get(0));
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
