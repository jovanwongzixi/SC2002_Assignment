package control;

import java.io.*;
import java.util.*;
import entity.user.Admin;
import cinemas.*;
import movies.*;

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
	public static List<Admin> getAdminList(String filename) {
		List<Admin> data = new ArrayList<Admin>();
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream(filename);
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
	public static List<Movie> getMovieList(String filename) {
		List<Movie> data = new ArrayList<Movie>();
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream(filename);
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
	public static List<Cineplex> getCineplexList(String filename) {
		List<Cineplex> data = new ArrayList<Cineplex>();
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream(filename);
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
	public static List<MovieTimeSlot> getMovieTimeSlotList(String filename) {
		List<MovieTimeSlot> data = new ArrayList<MovieTimeSlot>();
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream(filename);
			in = new ObjectInputStream(fis);
			data = (List<MovieTimeSlot>)in.readObject();
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
			FileOutputStream fos = new FileOutputStream("src/data/movie.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(movieList);
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void writeToMovieTimeSlotList(List<MovieTimeSlot> showtimeList) {
		try {
			FileOutputStream fos = new FileOutputStream("src/data/cinema_showtime.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(showtimeList);
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
