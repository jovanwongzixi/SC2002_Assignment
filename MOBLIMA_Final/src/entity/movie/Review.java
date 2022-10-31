package entity.movie;

import java.time.LocalDateTime;

import interfaces.SerializedData;

public class Review implements SerializedData{
	private String nickname;
	private LocalDateTime datetime;
	private String content;
	private double rating;
	
	public Review(String nickname, LocalDateTime datetime, String content, double rating) {
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
	
	public double getRating() {
		return this.rating;
	}
	
	/*
	@Override
	public boolean equals(Object o) {
		if (o instanceof Review) {
			Review re = (Review)o;
			return (getNickname().equals(re.getNickname()));
		}
		return false;
	}
	*/
}
