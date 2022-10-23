package control.admin;

import java.util.*;
import control.*;
import entity.user.Admin;

public class AdminCommands {
	
	public static boolean authenticate(String userid, String password) {
		boolean flag_1 = false, flag_2 = false;
		int index;
		 	
		List<Admin> adminData = SerializeDB.getAdminList("src/data/admin.dat");
		List<String> userids = new ArrayList<String>();
		List<String> passwords = new ArrayList<String>();
			
		for (Admin a : adminData) {
			userids.add(a.getUserID());
			passwords.add(a.getPassword());
		}
			
		if(userids.contains(userid)) {
			flag_1 = true;
			index = userids.indexOf(userid);
			flag_2 = passwords.get(index).equals(password);
		}			
		return flag_1 && flag_2;
	}
	 
	public static boolean login(String userid, String password) {
		boolean authenticated = authenticate(userid, password);
		
		if(!authenticated) {
			System.out.println("Invalid username/password! Please try again");
			return false;
		} else {
			System.out.println("Successfully logged in!");
			System.out.println("Welcome, " + userid);
			return true;
		}
	}
	
	public static void editMovieList() {
		int choice;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		MovieListEditor movieListEditor = new MovieListEditor();
		
		try {
			System.out.println("\n(1) ----------------      Create movie listing");
			System.out.println("(2) ----------------      Update movie listing");
			System.out.println("(3) ----------------      Remove movie listing");
			System.out.printf("\nOption: ");
			
			choice = sc.nextInt();
			
			switch(choice) {
				case 1:
					movieListEditor.create();
					break;
				case 2:
					movieListEditor.update();
					break;
				case 3:
					movieListEditor.remove();
					break;
				default:
					System.out.println("Option does not exist. Please key in a valid option!\n");
			}
		} catch(InputMismatchException ex) {
			sc.nextLine();
			System.out.println("Invalid input. Please choose a valid option!\n");
		}
	}
	
	public static void editCinemaShowtime() {
		int choice;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		MovieTimeSlotEditor cinemaShowtimeEditor = new MovieTimeSlotEditor();
		
		try {
			System.out.println("\n(1) ----------------      Create movie showtime");
			System.out.println("(2) ----------------      Update movie showtime");
			System.out.println("(3) ----------------      Remove movie showtime");
			System.out.printf("\nOption: ");
			
			choice = sc.nextInt();
			
			switch(choice) {
				case 1:
					cinemaShowtimeEditor.create();
					break;
				case 2:
					cinemaShowtimeEditor.update();
					break;
				case 3:
					cinemaShowtimeEditor.remove();
					break;
				default:
					System.out.println("Option does not exist. Please key in a valid option!\n");	
			}
		} catch(InputMismatchException ex) {
			sc.nextLine();
			System.out.println("Invalid input. Please choose a valid option!\n");
		}
	}
}
