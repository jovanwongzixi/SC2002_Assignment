package entity;

import java.io.Serializable;

public class Admin implements Serializable{
	private static final long serialVersionUID = 1L;
	private String userid;
	private String password;
	
	public Admin(String userid, String password) {
		this.userid = userid;
		this.password = password;
	}
	
	public String getUserID() {
		return this.userid;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public boolean equals(Object o) {
		if (o instanceof Admin) {
			Admin a = (Admin)o;
			return (getUserID().equals(a.getUserID()));
		}
		return false;
	}
}
