package control.admin;

import java.util.*;
import control.SerializeDB;
import entity.Admin;

public class Authenticator {

	public static boolean authenticate(String userid, String password) {
		SerializeDB serializer = new SerializeDB();		 	
		List<Admin> adminData = serializer.getAdminList();
			
		for (Admin a : adminData) {
			if (a.getUserID().equals(userid))
				if (a.getPassword().equals(password)) 
					return true;
		}
		return false;
	}
}
