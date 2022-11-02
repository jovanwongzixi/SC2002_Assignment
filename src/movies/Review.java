package movies;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Review implements Serializable{
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

}