package control.customer;

import entity.movie.Movie;
import entity.movie.Review;
import interfaces.Displayer;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.StringJoiner;

public class MovieDetailsDisplayer implements Displayer {
    private final Movie movie;
    public MovieDetailsDisplayer(Movie movie){
        this.movie = movie;
    }
    public void display(){
        List<String> castList = movie.getCast();
        List<Review> movieReviews = movie.getReviews();
        String delimiter = ", ";

        System.out.printf("Title:\t\t\t%s\n", movie.getTitle());
        System.out.printf("Film Rating:\t\t%s\n", movie.getFilmRating());
        if (movie.getIs3D()) {
            System.out.printf("Movie Type:\t\t3D\n");
        } else {
            System.out.printf("Movie Type:\t\tRegular 2D\n");
        }
        System.out.printf("Genre:\t\t\t%s\n", movie.getMovieGenre());
        String synopsis = movie.getSynopsis();
        synopsis += "\n";
        synopsis = synopsis.replaceAll("(.{1,50})\\s+", "$1\n\t\t\t");
        System.out.printf("Synopsis:\t\t%s\n", synopsis);
        System.out.printf("Director:\t\t%s\n", movie.getDirector());
        StringJoiner castJoiner = new StringJoiner(delimiter);
        castList.forEach(item -> castJoiner.add(item));
        String castString = castJoiner.toString();
        castString += "\n";
        castString = castString.replaceAll("(.{1,50})\\s+", "$1\n\t\t\t");
        System.out.printf("Cast:\t\t\t%s\n", castString);
        System.out.printf("Showing Status:\t\t%s\n", movie.getShowingStatus().getText());
        if (movieReviews.size() <= 1) {
            System.out.printf("Rating:\t\t\tNA\n");
        } else {
            System.out.printf("Rating:\t\t\t%.1f\n", movie.getOverallRating());
        }
        if (movieReviews.size() == 0) {
            System.out.printf("Reviews:\t\tNA\n");
        } else {
            System.out.printf("Reviews: \t\t");
            for (Review r : movieReviews) {
                if (movieReviews.indexOf(r) == 0) {
                    System.out.println(r.getNickname()+" ("+r.getRating()+") --------- (" +r.getDateTime().format(DateTimeFormatter.ofPattern("MMM dd uuuu HH:mm:ss"))+ ")");
                } else {
                    System.out.println("\t\t\t"+r.getNickname()+" ("+r.getRating()+") --------- (" +r.getDateTime().format(DateTimeFormatter.ofPattern("MMM dd uuuu HH:mm:ss"))+ ")");
                }
                String reviewString = r.getContent();
                reviewString += "\n";
                reviewString = reviewString.replaceAll("(.{1,50})\\s+", "$1\n\t\t\t");
                System.out.printf("\t\t\t%s\n", reviewString);
            }
        }
    }
}

