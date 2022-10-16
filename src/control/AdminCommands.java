package control;

import java.util.ArrayList;
import boundary.MovieListingMenu;
import entity.user.Admin;

public class AdminCommands {

	 public static boolean authenticate(String userid, String password) {
		 boolean flag_1 = false, flag_2 = false;
		 int index;
		 	
		ArrayList<Admin> adminData = SerializeDB.getAdminList("src/data/admin.dat");
		ArrayList<String> userids = new ArrayList<String>();
		ArrayList<String> passwords = new ArrayList<String>();
			
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
	
	public static void movieListMenu() {
		MovieListingMenu.movieListMenu();
	}
	
	public static void editCinemaMovie() {
		
	}
	
	public static void systemConfig() {
		
	}
}
