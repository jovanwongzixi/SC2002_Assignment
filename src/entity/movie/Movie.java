package entity.movie;

import java.io.Serializable;
import java.util.ArrayList;


public class Movie implements Serializable{
	private static final long serialVersionUID = 3L;
	private int id;
	private String title;
	private ShowingStatus showingStatus;
	private FilmRating filmRating;
	private MovieType movieType;
	private MovieGenre movieGenre;
	private String sypnosis;
	private String director;
	private ArrayList<String> cast;
	private float overallRating;
	private ArrayList<Review> reviews;
	
	/*
	 * Insert constructor here
	 */
	
	/*
	 * Insert functions here
	 */
}
