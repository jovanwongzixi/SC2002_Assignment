package control.admin;
//CLEAN BUT MISSING METHOD SYSTEMCONFIG()
import java.util.*;
import control.*;
import entity.user.Admin;

public class AdminCommands {
	
	public static boolean authenticate(String userid, String password) {
		boolean flag_1 = false, flag_2 = false;
		int index;
		 	
		List<Admin> adminData = SerializeDB.getAdminList();
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
	
	@SuppressWarnings("resource")
	public static void editMovieList() {
		int choice;
		Scanner sc = new Scanner(System.in);
		MovieListEditor movieListEditor = new MovieListEditor();
		
		do {
			try {
				System.out.println("\n(1) ----------------      Create movie listing");
				System.out.println("(2) ----------------      Update movie listing");
				System.out.println("(3) ----------------      Remove movie listing");
				System.out.println("(4) ----------------      Return to previous menu");
				System.out.printf("\nOption: ");
			
				choice = sc.nextInt();
			
				switch(choice) {
					case 1:
						movieListEditor.create();
						return;
					case 2:
						movieListEditor.update();
						return;
					case 3:
						movieListEditor.remove();
						return;
					case 4:
						System.out.println("Returning to previous menu...");
						return;
					default:
						System.out.println("Option does not exist. Please key in a valid option!\n");
				}
			} catch(InputMismatchException ex) {
				sc.nextLine();
				System.out.println("Invalid input. Please choose a valid option!\n");
			}
		} while (true);
	}
	
	@SuppressWarnings("resource")
	public static void editCinemaShowtime() {
		int choice;
		Scanner sc = new Scanner(System.in);
		CinemaShowtimeEditor cinemaShowtimeEditor = new CinemaShowtimeEditor();
		
		do {
			try {
				System.out.println("\n(1) ----------------      Create movie showtime");
				System.out.println("(2) ----------------      Update movie showtime");
				System.out.println("(3) ----------------      Remove movie showtime");
				System.out.println("(4) ----------------      Return to previous menu");
				System.out.printf("\nOption: ");
			
				choice = sc.nextInt();
			
				switch(choice) {
					case 1:
						cinemaShowtimeEditor.create();
						return;
					case 2:
						cinemaShowtimeEditor.update();
						return;
					case 3:
						cinemaShowtimeEditor.remove();
						return;
					case 4:
						System.out.println("Returning to previous menu...");
						return;
					default:
						System.out.println("Option does not exist. Please key in a valid option!\n");	
				}
			} catch(InputMismatchException ex) {
				sc.nextLine();
				System.out.println("Invalid input. Please choose a valid option!\n");
			}
		} while(true);
	}
	
	@SuppressWarnings("resource")
	public static void editSystemConfig() {
		int choice;
		Scanner sc = new Scanner(System.in);
		SystemConfig systemConfig = new SystemConfig();
		
		do {
			try {
				System.out.println("\n(1) ----------------      Edit holidays");
				System.out.println("(2) ----------------      Edit ticket prices");
				System.out.println("(3) ----------------      Return to previous menu");
				System.out.printf("\nOption: ");
				
				choice = sc.nextInt();
				
				switch(choice) {
					case 1:
						systemConfig.editHolidays();
						return;
					case 2:
						systemConfig.editPrices();
						return;
					case 3:
						System.out.println("Returning to previous menu...");
						return;
					default:
						System.out.println("Option does not exist. Please key in a valid option!\n");	
				}
			} catch (InputMismatchException ex) {
				sc.nextLine();
				System.out.println("Invalid input. Please choose a valid option!\n");
			}
		} while (true);
	}
}
