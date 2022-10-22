import java.io.*;
import java.util.*;
import static control.SerializeDB.*;
import entity.user.Admin;
import entity.movie.*;
import entity.cinema.*;

public class DataInitializer {

	public static void initializeAdminData() {
		List<Admin> adminList = new ArrayList<Admin>();
		
		createNewFile("src/data/admin.dat");
		
		Admin a1 = new Admin("bigchungus","qwerty123456");
		Admin a2 = new Admin("chonkyboi","wasdpoi!@");
		Admin a3 = new Admin("epicgamer42069", "M0unT41nD3w_~");
		
		adminList.add(a1);
		adminList.add(a2);
		adminList.add(a3);
					
		try {
			FileOutputStream fos = new FileOutputStream("src/data/admin.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(adminList);
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void initializeMovieData() {
		List<Movie> movieList = new ArrayList<Movie>();
		List<String> mCast1 = new ArrayList<String>(Arrays.asList("Dwayne Johnson", 
				"Aldis Hodge", "Pierce Brosnan", "Noah Centineo", "Sarah Shahi", "Marwan Kenzari", "Quintessa Swindell", 
				"Bodhi Sabongui"));
		List<String> mCast2 = new ArrayList<String>(Arrays.asList("Ikue Otani", "Kaori Nazuka", "Mayumi Tanaka", 
				"Akemi Okamura", "Kappei Yamaguchi", "Hiroaki Hirata"));
		
		createNewFile("src/data/movie.dat");
		
		Movie m1 = new Movie("Black Adam", FilmRating.PG13, MovieType.BLOCKBUSTER, "Action, Adventure, Fantasy",
				"Nearly 5,000 years after he was bestowed with the almighty powers of the Egyptian gods - "
				+ "and imprisoned just as quickly - Black Adam is freed from his earthly tomb, ready to unleash "
				+ "his unique form of justice on the modern world.", "Jaume Collet-Serra", mCast1, null, null, 
				ShowingStatus.NOW_SHOWING);
		Movie m2 = new Movie("One Piece Film (RED)", FilmRating.PG13, MovieType.REGULAR, "Anime", "Uta - the most beloved "
				+ "singer in the world.\r\n" + "Her voice, which she sings with while concealing her true identity,\r\n" 
				+ "Has been described as “otherworldly.”\r\n" + "She will appear in public for the first time at a "
				+ "live concert.\r\n" + "As the venue fills with all kinds of Uta fans - excited pirates, the Navy watching "
				+ "closely, and the Straw Hats led by Luffy who simply came to enjoy her sonorous performance - the "
				+ "voice that the whole world has been waiting for is about to resound.", "Goro Taniguchi", mCast2, null, null,
				ShowingStatus.NOW_SHOWING);
				
		movieList.add(m1);
		movieList.add(m2);
		
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
	
	public static void initializeCinemaShowtimeData() {
		List<Showtime> cinemaShowtimes = new ArrayList<Showtime>();
		
		createNewFile("src/data/cinema_showtime.dat");
		
		try {
			FileOutputStream fos = new FileOutputStream("src/data/cinema_showtime.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(cinemaShowtimes);
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void initializeCineplexData() {
		List<Cineplex> cineplexData = new ArrayList<Cineplex>();
		List<Cinema> cinemaData1 = new ArrayList<Cinema>();
		List<Cinema> cinemaData2 = new ArrayList<Cinema>();
		List<Cinema> cinemaData3 = new ArrayList<Cinema>();
		
		Cinema c1 = new Cinema(1,null,CinemaType.REGULAR);
		Cinema c2 = new Cinema(2,null,CinemaType.REGULAR);
		Cinema c3 = new Cinema(3,null,CinemaType.REGULAR);
		Cinema c4 = new Cinema(4,null,CinemaType.REGULAR);
		Cinema c5 = new Cinema(5,null,CinemaType.REGULAR);
		Cinema c6 = new Cinema(6,null,CinemaType.PLATINUM_MOVIE_SUITES);
		
		c1.setCinemaLayout();
		c2.setCinemaLayout();
		c3.setCinemaLayout();
		c4.setCinemaLayout();
		c5.setCinemaLayout();
		c6.setCinemaLayout();
		
		cinemaData1.add(c1);
		cinemaData1.add(c2);
		cinemaData1.add(c3);
		cinemaData1.add(c4);
		cinemaData1.add(c5);
		cinemaData1.add(c6);
		
		cinemaData2.add(c1);
		cinemaData2.add(c2);
		cinemaData2.add(c3);
		cinemaData2.add(c4);
		
		cinemaData3.add(c1);
		cinemaData3.add(c2);
		cinemaData3.add(c3);
		
		Cineplex cp1 = new Cineplex("City Hall", cinemaData1);
		Cineplex cp2 = new Cineplex("AMK", cinemaData2);
		Cineplex cp3 = new Cineplex("Holland Village", cinemaData3);
		
		cineplexData.add(cp1);
		cineplexData.add(cp2);
		cineplexData.add(cp3);
		
		createNewFile("src/data/cineplex.dat");
		
		try {
			FileOutputStream fos = new FileOutputStream("src/data/cineplex.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(cineplexData);
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		initializeAdminData();
        initializeCineplexData();
        initializeMovieData();
        initializeCinemaShowtimeData();
    }
}
