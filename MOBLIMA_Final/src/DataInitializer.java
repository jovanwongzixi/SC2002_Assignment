import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import control.SerializeDB;
import entity.*;
import entity.movie.*;
import entity.cinema.*;

public class DataInitializer {
	//driver for initializer
	public static void main(String[] args) throws FileNotFoundException, IOException {
		DataInitializer dataInit = new DataInitializer();
		//dataInit.initializeAdminData();
        //dataInit.initializeMovieData();
        //dataInit.initializeCineplexData();
        //dataInit.initializeMovieTimeslotData();
        //dataInit.initializeTicketPrices();
        //dataInit.initializeHolidays();
        //dataInit.initializeBookingData();
        //dataInit.initializeFlags();
		//SerializeDB.writeMap("Customer", new HashMap<String, Customer>());
    }
	//initialize 3 administrative accounts
	private void initializeAdminData() {
		List<Admin> adminList = new ArrayList<Admin>();
				
		Admin a1 = new Admin("bigchungus","qwerty123456");
		Admin a2 = new Admin("chonkyboi","wasdpoi!@");
		Admin a3 = new Admin("epicgamer42069", "M0unT41nD3w_~");
		
		adminList.add(a1); adminList.add(a2); adminList.add(a3);
					
		try {
			FileOutputStream fos = new FileOutputStream("MOBLIMA_Final/bin/data/admin_accounts.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(adminList);
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//initialize 5 movies
	private void initializeMovieData() {
		List<Movie> movieList = new ArrayList<Movie>();
		List<String> mCast1 = new ArrayList<String>(Arrays.asList("Dwayne Johnson", 
				"Aldis Hodge", "Pierce Brosnan", "Noah Centineo", "Sarah Shahi", "Marwan Kenzari", "Quintessa Swindell", 
				"Bodhi Sabongui"));
		List<String> mCast2 = new ArrayList<String>(Arrays.asList("Ikue Otani", "Kaori Nazuka", "Mayumi Tanaka", 
				"Akemi Okamura", "Kappei Yamaguchi", "Hiroaki Hirata"));
		List<String> mCast3 = new ArrayList<String>(Arrays.asList("Donnie Yen", "Han Xue", "Tang Xu", "Jia Bing", "Hou Tianlai", 
				"Xu Guangyu", "Yuan Jinhui", "Cai Xin", "Lin Chenhan", "Hu Ming"));
		List<String> mCast4 = new ArrayList<String>(Arrays.asList("Justin Cheung", "Hedwig Tam", "Jennifer Yu", "Lam Yiu Sing", "Raymond Chiu"));
		List<String> mCast5 = new ArrayList<String>(Arrays.asList("Jesse T. Usher", "Sosie Bacon", "Kyle Gallner", "Robin Weigert"));
		List<Review> rList1 = new ArrayList<Review>(), rList2 = new ArrayList<Review>(), rList3 = new ArrayList<Review>(), 
				rList4 = new ArrayList<Review>(), rList5 = new ArrayList<Review>();
		
		
		Movie m1 = new Movie("Black Adam", FilmRating.PG13, false, true, "Action, Adventure, Fantasy",
				"Nearly 5,000 years after he was bestowed with the almighty powers of the Egyptian gods - "
				+ "and imprisoned just as quickly - Black Adam is freed from his earthly tomb, ready to unleash "
				+ "his unique form of justice on the modern world.", "Jaume Collet-Serra", mCast1, 0, rList1, 22000, ShowingStatus.NOW_SHOWING);
		Movie m2 = new Movie("One Piece Film (RED)", FilmRating.PG13, false, false, "Anime", "Uta - the most beloved "
				+ "singer in the world.\r\n" + "Her voice, which she sings with while concealing her true identity,\r\n" 
				+ "Has been described as “otherworldly.”\r\n" + "She will appear in public for the first time at a "
				+ "live concert.\r\n" + "As the venue fills with all kinds of Uta fans - excited pirates, the Navy watching "
				+ "closely, and the Straw Hats led by Luffy who simply came to enjoy her sonorous performance - the "
				+ "voice that the whole world has been waiting for is about to resound.", "Goro Taniguchi", mCast2, 0, rList2, 13000,
				ShowingStatus.NOW_SHOWING);
		Movie m3 = new Movie("Come Back Home", FilmRating.PG, false, false, "Drama, Disaster",
				"In the cold winter, a group of Shenzhen tourist families take a trip to the northeast Changbai Mountain. "
				+ "It was originally intended to be a happy and harmonious holiday, but due to the negligence of his father, "
				+ "an 8-year-old boy is unfortunately lost. The parents seek assistance from the relevant local authorities, "
				+ "and the search and rescue operation begins immediately. The golden rescue time of 24 hours passes, followed "
				+ "by the routine safety limit of 48 hours, but the child is still nowhere to be found. Even if there is little hope, "
				+ "the father and the search and rescue teams will not give up…", "Lo Chi Leung", mCast3, 0, rList3, 15000, 
				ShowingStatus.NOW_SHOWING);
		Movie m4 = new Movie("Hell Hole", FilmRating.NC16, true, false, "Horror", "This is a tale about love, loss, suffering and karma. "
				+ "A loving mother makes a death pact with a spirit, sacrificing herself to save her son. Years later, the son is bullied "
				+ "in a medical school and dies tragically. Now both mother and son have been reunited as vengeful spirits and are back "
				+ "to exact revenge on those who have wronged them.", "Sam Loh", mCast4, 0, rList4, 8000,
				ShowingStatus.NOW_SHOWING);
		Movie m5 = new Movie("Smile", FilmRating.M18, false, false, "Horror, Mystery, Thriller",
				"After witnessing a bizarre, traumatic incident involving a patient, Dr. Rose Cotter (Sosie Bacon) "
				+ "starts experiencing frightening occurrences that she can't explain. As an overwhelming terror begins "
				+ "taking over her life, Rose must confront her troubling past in order to survive and escape her horrifying "
				+ "new reality.", "Parker Finn", mCast5, 0, rList5, 10000, ShowingStatus.NOW_SHOWING);
				
		movieList.add(m1); movieList.add(m2); movieList.add(m3); movieList.add(m4); movieList.add(m5);
		
		try {
			FileOutputStream fos = new FileOutputStream("MOBLIMA_Final/bin/data/movies.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(movieList);
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//initialize 3 cineplexes
	private void initializeCineplexData() {
		List<Cineplex> cineplexData = new ArrayList<Cineplex>();
		List<Cinema> cinemaData1 = new ArrayList<Cinema>();
		List<Cinema> cinemaData2 = new ArrayList<Cinema>();
		List<Cinema> cinemaData3 = new ArrayList<Cinema>();
		
		Cinema c1 = new Cinema(1,false);
		Cinema c2 = new Cinema(2,false);
		Cinema c3 = new Cinema(3,false);
		Cinema c4 = new Cinema(4,false);
		Cinema c5 = new Cinema(5,true);
		Cinema c6 = new Cinema(6,true);
		
		cinemaData1.add(c1); cinemaData1.add(c2); cinemaData1.add(c3); cinemaData1.add(c4); cinemaData1.add(c5); cinemaData1.add(c6);
		cinemaData2.add(c1); cinemaData2.add(c2); cinemaData2.add(c3); cinemaData2.add(c4);
		cinemaData3.add(c1); cinemaData3.add(c2); cinemaData3.add(c3);
		
		Cineplex cp1 = new Cineplex("City Hall", "CTH", cinemaData1);
		Cineplex cp2 = new Cineplex("AMK", "AMK", cinemaData2);
		Cineplex cp3 = new Cineplex("Holland Village", "HLV", cinemaData3);
		
		cineplexData.add(cp1); cineplexData.add(cp2); cineplexData.add(cp3);
				
		try {
			FileOutputStream fos = new FileOutputStream("MOBLIMA_Final/bin/data/cineplex.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(cineplexData);
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//no timeslots initialized
	private void initializeMovieTimeslotData() {
		List<Timeslot> movieTimeslots = new ArrayList<Timeslot>();
		
		try {
			FileOutputStream fos = new FileOutputStream("MOBLIMA_Final/bin/data/movie_timeslots.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(movieTimeslots);
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//initialize prices for all customer groups
	private void initializeTicketPrices() {
		List<Double> priceList = new ArrayList<Double>(Arrays.asList(38.0, 14.5, 17.0, 28.0, 7.0, 9.0, 10.0, 12.0, 5.0, 0.5));
		List<TicketPrice> priceList1 = new ArrayList<>();
		for(Double price : priceList){
			TicketPrice ticketPrice = new TicketPrice();
			ticketPrice.setPrice(price);
			priceList1.add(ticketPrice);
		}
		try {
			FileOutputStream fos = new FileOutputStream("MOBLIMA_Final/bin/data/ticket_prices.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(priceList);
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		SerializeDB.writeList("TicketPrice", priceList1);
	}
	//initialize X'Mas and New Year holidays and eves
	private void initializeHolidays() {
		DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd.MM.yyyy");		
		//adding only x'mas and new year for now...
		List<LocalDate> holidayList = new ArrayList<LocalDate>(Arrays.asList(LocalDate.parse("24.12.2021", formatDate), 
				LocalDate.parse("25.12.2021", formatDate), LocalDate.parse("31.12.2021", formatDate), 
				LocalDate.parse("01.01.2022", formatDate)));
		List<Holiday> holidayList1 = new ArrayList<>();
		for(LocalDate d : holidayList){
			Holiday holiday = new Holiday();
			holiday.setDate(d);
			holidayList1.add(holiday);
		}
		try {
			FileOutputStream fos = new FileOutputStream("MOBLIMA_Final/bin/data/holidays.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(holidayList);
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		SerializeDB.writeList("Holiday",holidayList1);
	}
	//no booking initialized
	private void initializeBookingData() {
		List<Booking> bookingData = new ArrayList<Booking>();
		
		try {
			FileOutputStream fos = new FileOutputStream("MOBLIMA_Final/bin/data/booking.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(bookingData);
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//flag data
	private void initializeFlags() {
		List<Boolean> sortSwitch = new ArrayList<Boolean>();
		
		sortSwitch.add(true);
		sortSwitch.add(true);
		List<Flag> sortFlag = new ArrayList<>();
		for(boolean b : sortSwitch){
			Flag flag = new Flag();
			flag.setFlag(true);
			sortFlag.add(flag);
		}

		try {
			FileOutputStream fos = new FileOutputStream("MOBLIMA_Final/bin/data/flags.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(sortSwitch);
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		SerializeDB.writeList("Flag",sortFlag);
	}
}
