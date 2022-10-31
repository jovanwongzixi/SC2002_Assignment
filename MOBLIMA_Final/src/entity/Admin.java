package entity;

import interfaces.SerializedData;

public class Admin implements SerializedData{
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
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Admin) {
			Admin a = (Admin)o;
			return (getUserID().equals(a.getUserID()));
		}
		return false;
	}
}
