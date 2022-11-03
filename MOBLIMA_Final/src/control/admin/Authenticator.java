package control.admin;
/*
 * No issues.
 */
import java.util.*;
import control.SerializeDB;
import entity.Admin;

public class Authenticator {

	public static boolean authenticate(String userid, String password) {		 	
		List<Admin> adminData = SerializeDB.getList("Admin");
			
		for (Admin a : adminData) {
			if (a.getUserID().equals(userid))
				if (a.getPassword().equals(password)) 
					return true;
		}
		return false;
	}
}
