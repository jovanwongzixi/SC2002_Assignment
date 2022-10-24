package control;

import java.io.*;
import java.util.*;
import entity.user.Admin;
import entity.cinema.*;
import entity.movie.Movie;

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
			fis = new FileInputStream("src/data/admin.dat");
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
			fis = new FileInputStream("src/data/movie.dat");
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
			fis = new FileInputStream("src/data/cineplex.dat");
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
			fis = new FileInputStream("src/data/cinema_showtime.dat");
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
	
	public static void writeToShowtimeList(List<Showtime> showtimeList) {
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
