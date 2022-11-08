import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import entity.*;
import entity.movie.*;
import entity.cinema.*;

public class DataInitializer {
	//driver for initializer
	public static void main(String[] args) throws FileNotFoundException, IOException {
		DataInitializer dataInit = new DataInitializer();
		dataInit.initializeAdminData();
        dataInit.initializeBookingData();
        dataInit.initializeCineplexData();
        dataInit.initializeCustomerData();
        dataInit.initializeFlagData();
        dataInit.initializeHolidayData();
        dataInit.initializeMovieData();
        dataInit.initializeTicketPriceData();
        //dataInit.initializeMovieTimeslotData();
    }
	//initialize 3 administrative accounts
	//MOBLIMA_alternate saves admin data as Hashmap
	private void initializeAdminData() {		
		Map<String, Admin> adminMap= new HashMap<>();
		Admin a1 = new Admin("bigchungus","qwerty123456");
		Admin a2 = new Admin("chonkyboi","wasdpoi!@");
		Admin a3 = new Admin("epicgamer42069", "M0unT41nD3w_~");
		adminMap.put(a1.getUserID(), a1); adminMap.put(a2.getUserID(), a2); adminMap.put(a3.getUserID(), a3);
					
		try {
			FileOutputStream fos = new FileOutputStream("../bin/data/Admin.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(adminMap);
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	//no booking initialized
	private void initializeBookingData() {
		List<Booking> bookingData = new ArrayList<Booking>();
			
		try {
			FileOutputStream fos = new FileOutputStream("../bin/data/Booking.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
				
			oos.writeObject(bookingData);
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//initialize 3 cineplexes
	private void initializeCineplexData() {
		List<Cineplex> cineplexData = new ArrayList<Cineplex>();
		List<Cinema> cinemaData1 = new ArrayList<Cinema>(), cinemaData2 = new ArrayList<Cinema>(), cinemaData3 = new ArrayList<Cinema>();
			
		Cinema c1 = new Cinema(1,false); Cinema c2 = new Cinema(2,false); Cinema c3 = new Cinema(3,false); Cinema c4 = new Cinema(4,false);
		Cinema c5 = new Cinema(1,true); Cinema c6 = new Cinema(2,true); Cinema c7 = new Cinema(3,true);
			
		cinemaData1.add(c5); cinemaData1.add(c6); cinemaData1.add(c7);
		cinemaData2.add(c1); cinemaData2.add(c2); cinemaData2.add(c3); cinemaData2.add(c4);
		cinemaData3.add(c1); cinemaData3.add(c2); cinemaData3.add(c3);
			
		Cineplex cp1 = new Cineplex("City Hall (Platinum)", "CTH", cinemaData1);
		Cineplex cp2 = new Cineplex("AMK", "AMK", cinemaData2);
		Cineplex cp3 = new Cineplex("Holland Village", "HLV", cinemaData3);
			
		cineplexData.add(cp1); cineplexData.add(cp2); cineplexData.add(cp3);
					
		try {
			FileOutputStream fos = new FileOutputStream("../bin/data/Cineplex.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
				
			oos.writeObject(cineplexData);
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//initialize customer data
	private void initializeCustomerData() {
		Map<String, Customer> customerMap= new HashMap<>();
		
		try {
			FileOutputStream fos = new FileOutputStream("../bin/data/Customer.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(customerMap);
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//initialize flag data
	private void initializeFlagData() {
		List<Flag> flagData = new ArrayList<Flag>();
		
		Flag flag1 = new Flag(true), flag2 = new Flag(true);
		flagData.add(flag1); flagData.add(flag2);

		try {
			FileOutputStream fos = new FileOutputStream("../bin/data/Flag.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
				
			oos.writeObject(flagData);
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//initialize X'Mas and New Year holidays and eves
	private void initializeHolidayData() {
		DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		List<Holiday> holidayData = new ArrayList<Holiday>();
		Holiday hol1 = new Holiday(LocalDate.parse("24.12.2022", formatDate)), hol2 = new Holiday(LocalDate.parse("25.12.2022", formatDate)),
				hol3 = new Holiday(LocalDate.parse("31.12.2022", formatDate)), hol4 = new Holiday(LocalDate.parse("01.01.2023", formatDate));
		holidayData.add(hol1); holidayData.add(hol2); holidayData.add(hol3); holidayData.add(hol4);
		
		try {
			FileOutputStream fos = new FileOutputStream("../bin/data/Holiday.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
				
			oos.writeObject(holidayData);
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
				"Bodhi Sabongui")),
				mCast2 = new ArrayList<String>(Arrays.asList("Ikue Otani", "Kaori Nazuka", "Mayumi Tanaka", 
				"Akemi Okamura", "Kappei Yamaguchi", "Hiroaki Hirata")),
				mCast3 = new ArrayList<String>(Arrays.asList("Donnie Yen", "Han Xue", "Tang Xu", "Jia Bing", "Hou Tianlai", 
				"Xu Guangyu", "Yuan Jinhui", "Cai Xin", "Lin Chenhan", "Hu Ming")),
				mCast4 = new ArrayList<String>(Arrays.asList("Justin Cheung", "Hedwig Tam", "Jennifer Yu", "Lam Yiu Sing", "Raymond Chiu")),
				mCast5 = new ArrayList<String>(Arrays.asList("Jesse T. Usher", "Sosie Bacon", "Kyle Gallner", "Robin Weigert")),
				mCast6 = new ArrayList<String>(Arrays.asList("Lupita Nyong'o" , "Danai Gurira" , "Angela Bassett", "Winston Duke", 
				"Letitia Wright" , "Florence Kasumba")),
				mCast7 = new ArrayList<String>(Arrays.asList("Hong Huifang", "Jung Dong-Hwan", "Kang Hyung Suk", "Yeo Jingoo")),
				mCast8 = new ArrayList<String>(Arrays.asList("Jack Neo", "Fattah Amin", "Zheng Ge Ping", "Vincent Ng", "Dato Rosyam Nor", 
				"Tien Hsin", "Zhu Hou Ren", "Henley Hii", "Pablo Amirul")),
				mCast9 = new ArrayList<String>(Arrays.asList("Yoshitsugu Matsuoka", "Inori Minase", "Ayana Taketatsu", "Ayane Sakura", 
						"Kana Hanazawa", "Miku Ito"));
		List<Review> rList1 = new ArrayList<Review>(), rList2 = new ArrayList<Review>(), rList3 = new ArrayList<Review>(), 
				rList4 = new ArrayList<Review>(), rList5 = new ArrayList<Review>(), rList6 = new ArrayList<Review>(),
				rList7 = new ArrayList<Review>(), rList8 = new ArrayList<Review>(), rList9 = new ArrayList<Review>();
		
		
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
				ShowingStatus.PREVIEW);
		Movie m4 = new Movie("Hell Hole (3D)", FilmRating.NC16, true, false, "Horror", "This is a tale about love, loss, suffering and karma. "
				+ "A loving mother makes a death pact with a spirit, sacrificing herself to save her son. Years later, the son is bullied "
				+ "in a medical school and dies tragically. Now both mother and son have been reunited as vengeful spirits and are back "
				+ "to exact revenge on those who have wronged them.", "Sam Loh", mCast4, 0, rList4, 8000,
				ShowingStatus.NOW_SHOWING);
		Movie m5 = new Movie("Smile", FilmRating.M18, false, false, "Horror, Mystery, Thriller",
				"After witnessing a bizarre, traumatic incident involving a patient, Dr. Rose Cotter (Sosie Bacon) "
				+ "starts experiencing frightening occurrences that she can't explain. As an overwhelming terror begins "
				+ "taking over her life, Rose must confront her troubling past in order to survive and escape her horrifying "
				+ "new reality.", "Parker Finn", mCast5, 0, rList5, 10000, ShowingStatus.NOW_SHOWING);
		Movie m6 = new Movie("Black Panther: Wakanda Forever (3D)", FilmRating.PG13, true, true, "Action, Adventure", "Queen Ramonda, "
				+ "Shuri, M'Baku, Okoye and the Dora Milaje fight to protect their nation from intervening world powers in the wake "
				+ "of King T'Challa's death. As the Wakandans strive to embrace their next chapter, the heroes must band together with "
				+ "Nakia and Everett Ross to forge a new path for their beloved kingdom.", "Ryan Coogler", mCast6, 0, rList6, 20000, 
				ShowingStatus.PREVIEW);
		Movie m7 = new Movie("Ajoomma", FilmRating.NC16, false, false, "Drama", "Produced by award-winning filmmaker Anthony Chen.\r\n"
				+ "\r\n" + "Auntie (Hong Huifang), is a middle-aged Singaporean woman who has dedicated the best years of her life to caring "
						+ "for her family. Now widowed with her grown up son, Sam (Shane Pow) about to fly the roost, Auntie is left to contend "
						+ "with a whole new identity beyond her roles of daughter, wife, and mother.\r\n"
				+ "\r\n" + "A solo trip to Korea becomes a wild adventure for Auntie, where she meets Kwon-Woo (Kang Hyung Suk), a young tour "
						+ "guide who can’t seem to get his life in order, and Jung Su (Jung Dong-Hwan), an elderly security guard. The trio embark "
						+ "on an unexpected roller coaster ride where hearts flutter and unlikely bonds are formed.\r\n"
				+ "\r\n" + "Inspired by the director’s mother, AJOOMMA is the story of a woman’s journey of self-discovery, where Auntie learns "
						+ "to embrace her new independent life with renewed confidence and panache.", "He Shuming", mCast7, 0, rList7, 5500,
						ShowingStatus.PREVIEW);
		Movie m8 = new Movie("Deleted", FilmRating.NC16, false, false, "Action, Crime, Drama", "The story starts with a Malaysian police detective "
				+ "- Chia Zhong Yi. In his desperate search for his daughter Hazel who was being kidnapped by child traffickers. He unintentionally "
				+ "caused grievous hurt to a male suspect in a moment of rashness. As a consequence of his actions, he was convicted and sentenced "
				+ "to 3 years in prison. Nevertheless, he never gave up hope in finding his daughter. Exploiting his status as an ex-convict, he "
				+ "infiltrated the crime syndicate and befriended a human trafficker Ghost, to find out about his daughter’s whereabouts.\r\n"
				+ "\r\n" + "On the other side of the fence, we have Vincent Yong who was part of the Singapore Police Force - Star Team. He leads "
						+ "a successful raid against Four Faced Buddha, but was unable to apprehend him and his son. Meanwhile, in order to gather "
						+ "a large quantity of human organs for trafficking, Four Faced Buddha instructed his son, a dangerous hacker who goes by "
						+ "the name of Saviour, to steal the medical records from all the major hospitals in the regions.\r\n"
				+ "\r\n" + "To escape from detection by the Interpol, Savior has remained hidden overseas, and it wasn’t until 3 years later that "
						+ "he was caught by the Malaysia Police Inspector Aron. Vincent was being ordered to extradite Savior back to Singapore and "
						+ "to persuade him to turn as a key prosecuting witness against Four Faced Buddha. At the same time, Ghost has been ordered "
						+ "by his boss Four Faced Buddha to rescue his son Savior during the extradition process. The loyalties of these few men "
						+ "are being severely tested. In an intense gun fight, Ghost was killed by Zhong Yi and his identity came under the "
						+ "suspicion of Vincent, creating conflicts between the two of them. In a wicked twist of fate, Vincent also accidentally "
						+ "discovers that his former Star Team instructor Jusuf whom he deeply respects has broken the law, and exploited the sting "
						+ "operation as a decoy to steal a smuggled heart to save his very own sick daughter.", "Ken Ng Lai Huat", mCast8, 0,
						rList8, 6900, ShowingStatus.NOW_SHOWING);
		Movie m9 = new Movie("The Quintessential Quintuplets", FilmRating.PG, false, false, "Anime", "Hired as a private tutor to the five "
				+ "identical quintuplets for the Nakano’s family, high school student Futaro Uesugi has led the quintuplets: Ichika, Nino, Miku, "
				+ "Yotsuba and Itsuki Nakano to a point where they can graduate and pursue their own dreams. At last, it is the final school "
				+ "festival and Futaro resolved to make this an occasion that they will not regret. He searched within himself for his feelings "
				+ "towards the quintuplets and invites them to meet him in the classroom for an important announcement…", "Masato Jinbo", mCast9, 0,
				rList9, 8800, ShowingStatus.NOW_SHOWING);
				
		movieList.add(m1); movieList.add(m2); movieList.add(m3); movieList.add(m4); movieList.add(m5);
		movieList.add(m6); movieList.add(m7); movieList.add(m8); movieList.add(m9);
		
		try {
			FileOutputStream fos = new FileOutputStream("../bin/data/Movie.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(movieList);
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//initialize prices for all customer groups
	private void initializeTicketPriceData() {
		List<TicketPrice> ticketPriceData = new ArrayList<TicketPrice>();
		TicketPrice groupA = new TicketPrice(38.0), groupB = new TicketPrice(14.5), groupC = new TicketPrice(17.0),
				groupD = new TicketPrice(28.0), groupE = new TicketPrice(7.0), groupF = new TicketPrice(9.0), groupG = new TicketPrice(10.0),
				groupH = new TicketPrice(12.0), groupJ = new TicketPrice(5.0), bbPrice = new TicketPrice(0.5);
		ticketPriceData.add(groupA); ticketPriceData.add(groupB); ticketPriceData.add(groupC); ticketPriceData.add(groupD);
		ticketPriceData.add(groupE); ticketPriceData.add(groupF); ticketPriceData.add(groupG); ticketPriceData.add(groupH);
		ticketPriceData.add(groupJ); ticketPriceData.add(bbPrice);
			
		try {
			FileOutputStream fos = new FileOutputStream("../bin/data/TicketPrice.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(ticketPriceData);
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//no timeslots initialized
	private void initializeMovieTimeslotData() {
		List<Timeslot> movieTimeslotData = new ArrayList<Timeslot>();
		
		try {
			FileOutputStream fos = new FileOutputStream("../bin/data/Timeslot.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(movieTimeslotData);
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
