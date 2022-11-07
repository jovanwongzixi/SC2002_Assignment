package control.customer;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

import control.SerializeDB;
import control.datahandler.FlagDataHandler;
import control.datahandler.MovieDataHandler;
import entity.Flag;
import entity.movie.Movie;
import entity.movie.Review;
import interfaces.DataHandler;
import interfaces.Displayer;
import interfaces.Viewer;

public class TopFiveMovieViewer implements Viewer {
	public void view() {
		DataHandler movieDataHandler = new MovieDataHandler();
		List<Movie> movieData= movieDataHandler.retrieve();
		DataHandler flagDataHandler = new FlagDataHandler();
		List<Flag> sortFlag = flagDataHandler.retrieve();
		Scanner sc = new Scanner(System.in);
		int choice, switcher = 0;
		
		if(sortFlag.get(0).getFlag() && sortFlag.get(1).getFlag()) {
			do {
				System.out.println("\n(1) ----------------      View top 5 movies by rating");
				System.out.println("(2) ----------------      View top 5 movies by ticket sales");
				System.out.println("(3) ----------------      Return to customer menu...");
				System.out.printf("\nOption: ");
				
				while(!sc.hasNextInt()) {
					System.out.println("Invalid input. Please enter an integer!");
					System.out.printf("Option: ");
					sc.next();
				}
						
				switcher = sc.nextInt();
				
				if (switcher > 3) {
					System.out.println("Option does not exist. Please key in a valid option!\n");
				} else if (switcher == 3) {
					System.out.println("Returning to customer menu...");
					return;
				}		
			} while (switcher < 1 || switcher > 3);
		} else if (sortFlag.get(0).getFlag()) {
			switcher = 1;
		} else if (sortFlag.get(1).getFlag()) {
			switcher = 2;
		}
		
		do {
			if(switcher == 1){
				Collections.sort(movieData, (m1, m2) -> (Double.compare(m2.getOverallRating(), m1.getOverallRating())));
			}
			else{
				Collections.sort(movieData, (m1, m2) -> (m2.getTicketSales() - m1.getTicketSales()));
			}
			Displayer topFiveMovieDisplayer = new TopFiveMovieDisplayer(movieData.subList(0,5),switcher);
			topFiveMovieDisplayer.display();
			System.out.printf("\nInput option number to view movie details (-1 to return to customer menu): ");
			
			while(!sc.hasNextInt()) {
				System.out.println("Invalid input. Please enter an integer!");
				System.out.printf("Option: ");
				sc.next();
			}
					
			choice = sc.nextInt();
			
			if(choice > 5) {
				System.out.println("Option does not exist. Please key in a valid option!\n");
			} else if (choice == -1) {
				System.out.println("Returning to customer menu...");
				return;
			} else {
				Displayer movieDetailsDisplayer = new MovieDetailsDisplayer(movieData.get(choice-1));
				movieDetailsDisplayer.display();
				System.out.printf("\nPress Enter to return to movie list...");
				if(sc.nextLine() != null)
					sc.nextLine();
			}			
		} while (true);
	}
}
