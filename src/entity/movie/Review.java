package entity.movie;

import java.io.Serializable;
import java.time.LocalDateTime;

import entity.user.Admin;

public class Review implements Serializable{
	private static final long serialVersionUID = 2L;
	private String nickname;
	private LocalDateTime datetime;
	private String content;
	private int rating;
	
	public Review(String nickname, LocalDateTime datetime, String content, int rating) {
		this.nickname = nickname;
		this.datetime = datetime;
		this.content = content;
		this.rating = rating;
	}
	
	public String getNickname() {
		return this.nickname;
	}
	
	public LocalDateTime getDateTime() {
		return this.datetime;
	}
	
	public String getContent() {
		return this.content;
	}
	
	public int rating() {
		return this.rating;
	}
	
	public boolean equals(Object o) {
		if (o instanceof Review) {
			Review re = (Review)o;
			return (getNickname().equals(re.getNickname()));
		}
		return false;
	}

}
