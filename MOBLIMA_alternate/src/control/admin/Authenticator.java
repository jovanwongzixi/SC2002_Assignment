package control.admin;
/*
 * No issues.
 */
import java.util.*;
import control.datahandler.AdminInfoHandler;
import entity.Admin;
import interfaces.UserInfoHandler;

public class Authenticator {

	public static boolean authenticate(String userid, String password) {
		UserInfoHandler adminInfoHandler = new AdminInfoHandler();
		HashMap<String, Admin> adminData = adminInfoHandler.retrieve();
		return adminData.containsKey(userid) && Objects.equals(adminData.get(userid).getPassword(), password);
	}
}
