import java.io.*;
import java.time.*;
import java.time.format.*;
import java.util.*;
import static control.SerializeDB.*;
import entity.user.Admin;
import entity.movie.*;
import entity.Booking;
import entity.cinema.*;

public class DataInitializer {

	public static void initializeAdminData() {
		List<Admin> adminList = new ArrayList<Admin>();
		
		createNewFile("data/admin.dat");
		
		Admin a1 = new Admin("bigchungus","qwerty123456");
		Admin a2 = new Admin("chonkyboi","wasdpoi!@");
		Admin a3 = new Admin("epicgamer42069", "M0unT41nD3w_~");
		
		adminList.add(a1);
		adminList.add(a2);
		adminList.add(a3);
					
		try {
			FileOutputStream fos = new FileOutputStream("data/admin.dat");
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
		List<Review> rList1 = new ArrayList<Review>();
		List<Review> rList2 = new ArrayList<Review>();
		
		createNewFile("data/movie.dat");
		
		Movie m1 = new Movie("Black Adam", FilmRating.PG13, MovieType.REGULAR, true, "Action, Adventure, Fantasy",
				"Nearly 5,000 years after he was bestowed with the almighty powers of the Egyptian gods - "
				+ "and imprisoned just as quickly - Black Adam is freed from his earthly tomb, ready to unleash "
				+ "his unique form of justice on the modern world.", "Jaume Collet-Serra", mCast1, 0, rList1, 15000, 
				ShowingStatus.NOW_SHOWING);
		Movie m2 = new Movie("One Piece Film (RED)", FilmRating.PG13, MovieType.REGULAR, false, "Anime", "Uta - the most beloved "
				+ "singer in the world.\r\n" + "Her voice, which she sings with while concealing her true identity,\r\n" 
				+ "Has been described as “otherworldly.”\r\n" + "She will appear in public for the first time at a "
				+ "live concert.\r\n" + "As the venue fills with all kinds of Uta fans - excited pirates, the Navy watching "
				+ "closely, and the Straw Hats led by Luffy who simply came to enjoy her sonorous performance - the "
				+ "voice that the whole world has been waiting for is about to resound.", "Goro Taniguchi", mCast2, 0, rList2, 13000,
				ShowingStatus.NOW_SHOWING);
				
		movieList.add(m1);
		movieList.add(m2);
		
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
	
	public static void initializeCinemaShowtimeData() {
		List<Showtime> cinemaShowtimes = new ArrayList<Showtime>();
		
		createNewFile("data/cinema_showtime.dat");
		
		try {
			FileOutputStream fos = new FileOutputStream("data/cinema_showtime.dat");
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
		
		Cinema c1 = new Cinema(1,CinemaType.REGULAR);
		Cinema c2 = new Cinema(2,CinemaType.REGULAR);
		Cinema c3 = new Cinema(3,CinemaType.REGULAR);
		Cinema c4 = new Cinema(4,CinemaType.REGULAR);
		Cinema c5 = new Cinema(5,CinemaType.REGULAR);
		Cinema c6 = new Cinema(6,CinemaType.PLATINUM_MOVIE_SUITES);
		
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
		
		Cineplex cp1 = new Cineplex("City Hall", "CTH", cinemaData1);
		Cineplex cp2 = new Cineplex("AMK", "AMK", cinemaData2);
		Cineplex cp3 = new Cineplex("Holland Village", "HLV", cinemaData3);
		
		cineplexData.add(cp1);
		cineplexData.add(cp2);
		cineplexData.add(cp3);
		
		createNewFile("data/cineplex.dat");
		
		try {
			FileOutputStream fos = new FileOutputStream("data/cineplex.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(cineplexData);
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void initializeTicketPrices() {
		
		createNewFile("data/ticket_prices.dat");
		
		TicketPrice t1 = new TicketPrice('A',38);
		TicketPrice t2 = new TicketPrice('B',14.5);
		TicketPrice t3 = new TicketPrice('C',17);
		TicketPrice t4 = new TicketPrice('D',28);
		TicketPrice t5 = new TicketPrice('E',7);
		TicketPrice t6 = new TicketPrice('F',9);
		TicketPrice t7 = new TicketPrice('G',10);
		TicketPrice t8 = new TicketPrice('H',12);
		TicketPrice t9 = new TicketPrice('I',5);
		TicketPrice t10 = new TicketPrice('J',0.5);
		
		List<TicketPrice> priceList = new ArrayList<TicketPrice>(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10));
		
		try {
			FileOutputStream fos = new FileOutputStream("data/ticket_prices.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(priceList);
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void initializeHolidays() {
		
		createNewFile("data/holidays.dat");
		DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		
		//adding only x'mas and new year for now...
		List<LocalDate> holidayList = new ArrayList<LocalDate>(Arrays.asList(LocalDate.parse("24.12.2021", formatDate), 
				LocalDate.parse("25.12.2021", formatDate), LocalDate.parse("31.12.2021", formatDate), 
				LocalDate.parse("01.01.2022", formatDate)));
		
		try {
			FileOutputStream fos = new FileOutputStream("data/holidays.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(holidayList);
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void initializeBookingData() {
		List<Booking> bookingData = new ArrayList<Booking>();
		
		createNewFile("data/booking.dat");
		
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
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		initializeAdminData();
        initializeCineplexData();
        initializeMovieData();
        initializeCinemaShowtimeData();
        initializeTicketPrices();
        initializeHolidays();
        initializeBookingData();
    }
}
