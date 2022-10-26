package control.admin;
//NOT SUPER CLEAN BUT FULLY FUNCTIONAL. DO NOT EDIT UNLESS EXCEPTION HANDLING AND BUG FIXING!!!!!
import java.util.*;
import control.SerializeDB;
import entity.cinema.Showtime;
import entity.movie.*;

public class MovieListEditor implements Writable{
	
	int counter = 0;

	@SuppressWarnings("resource")
	public void create() {
		Scanner sc = new Scanner(System.in);
		int choice;
		boolean toggle = true, isBlockbuster;
		FilmRating filmRating = null;
		MovieType movieType = null;
		ShowingStatus showingStatus = null;
		
		List<Movie> movieData = SerializeDB.getMovieList();
		List<Review> reviewList = new ArrayList<Review>();
		 
		System.out.printf("\nEnter movie title: ");
		String movieName = sc.nextLine();
		do {
			try {
				System.out.println("Enter film rating: ");
				System.out.println("(1) ------ G");
				System.out.println("(2) ------ PG");
				System.out.println("(3) ------ PG13");
				System.out.println("(4) ------ NC16");
				System.out.println("(5) ------ M18");
				System.out.println("(6) ------ R21");
				System.out.printf("Option: ");
						
				choice = sc.nextInt();
				
				switch (choice) {
					case 1: 
						filmRating = FilmRating.G;
						toggle = false;
						break;
					case 2: 
						filmRating = FilmRating.PG;
						toggle = false;
						break;
					case 3: 
						filmRating = FilmRating.PG13;
						toggle = false;
						break;
					case 4: 
						filmRating = FilmRating.NC16;
						toggle = false;
						break;
					case 5: 
						filmRating = FilmRating.M18;
						toggle = false;
						break;
					case 6: 
						filmRating = FilmRating.R21;
						toggle = false;
						break;
					default:
						System.out.println("Option does not exist. Please key in a valid option!\n");
				}
			} catch (InputMismatchException ex){
					sc.nextLine();
					System.out.println("Invalid input. Please choose a valid option!\n");
			}
		} while (toggle);
		
		toggle = true;		
		do {
			try {
				System.out.println("Enter movie type: ");
				System.out.println("(1) ------ 3D");
				System.out.println("(2) ------ Regular");
				System.out.printf("Option: ");
				
				choice = sc.nextInt();
				
				switch (choice) {
					case 1: 
						movieType = MovieType.THREE_D;
						toggle = false;
						break;
					case 2: 
						movieType = MovieType.REGULAR;
						toggle = false;
						break;
					default:
						System.out.println("Option does not exist. Please key in a valid option!\n");
				}
			} catch (InputMismatchException ex){
				sc.nextLine();
				System.out.println("Invalid input. Please choose a valid option!\n");
			}
		} while(toggle);

		do {
			System.out.println("Is it a blockbuster? (Y/N)");
			System.out.printf("Input: ");
			char bool = sc.next().charAt(0);
			if(bool == 'Y') {
				isBlockbuster = true;
				break;
			} else if(bool == 'N') {
				isBlockbuster = false;
				break;
			} else {
				System.out.println("Invalid input! Please try again!");
			}
		} while (true);
		
		sc.nextLine();
		System.out.printf("Enter movie genre: ");
		String movieGenre = sc.nextLine();
		System.out.printf("Enter synopsis: ");
		String synopsis = sc.nextLine();
		System.out.printf("Enter movie director: ");
		String director = sc.nextLine();

		System.out.println("Enter cast members (key X to terminate): ");
		List<String> movieCast = new ArrayList<String>();
		String castMember = ""; 
		
		do {
			System.out.printf("Cast member " + (counter+1) + ": ");
			castMember =  sc.nextLine();
			
			if (castMember.equals("X") && counter < 2) {
				System.out.println("Please key in a minimum of 2 cast members!");
				castMember = "";
			} else if(!castMember.equals("X")) {
				counter++;
				movieCast.add(castMember);
			}
		} while (!castMember.equals("X"));
		
		toggle = true;
		
		do {
			try {
				System.out.println("Enter showing status: ");
				System.out.println("(1) ------ Coming Soon");
				System.out.println("(2) ------ Preview");
				System.out.println("(3) ------ Now showing");
				System.out.printf("Option: ");
				
				choice = sc.nextInt();
				
				switch (choice) {
				case 1: 
					showingStatus = ShowingStatus.COMING_SOON;
					toggle = false;
					break;
				case 2: 
					showingStatus = ShowingStatus.PREVIEW;
					toggle = false;
					break;
				case 3: 
					showingStatus = ShowingStatus.NOW_SHOWING;
					toggle = false;
					break;
				default:
					System.out.println("Option does not exist. Please key in a valid option!\n");
				}
			} catch (InputMismatchException ex){
				sc.nextLine();
				System.out.println("Invalid input. Please choose a valid option!\n");
			}
		} while(toggle);
		
		Movie m =  new Movie(movieName, filmRating, movieType, isBlockbuster, movieGenre, synopsis, director, movieCast, 0, reviewList, 0, showingStatus);		
		movieData.add(m);
		
		SerializeDB.writeToMovieList(movieData);
		System.out.println("Movie added to list! Returning to admin menu...");
		return;
	}
	
	@SuppressWarnings("resource")
	public void update() {
		int choice;
		boolean toggle = true;
		Scanner sc = new Scanner(System.in);
		List<Movie> movieData = SerializeDB.getMovieList();
		List<Showtime> showtimeData = SerializeDB.getShowtimeList();
		List<String> movieNames = new ArrayList<String>();
			
		for(Movie m : movieData) {
			movieNames.add(m.getTitle());
		}
		
		if (movieNames.size() == 0) {
			System.out.println("There are no movies to update!");
		} else {
			System.out.println("Choose a movie to update showing status:");
			
			do {
				try {
					for (int i = 0; i < movieNames.size(); i++) {
						System.out.printf("(%d) ----------------	  %s\n", i+1, movieNames.get(i));
					}
					System.out.printf("Option: ");
					
					choice = sc.nextInt();
					
					if (choice < 1 || choice > movieNames.size()) {
						System.out.println("Option does not exist. Please key in a valid option!\n");
					} else {
						toggle = false;
						int index = choice-1;

						do {
							try {
								System.out.println("Enter new showing status: ");
								System.out.println("(1) ------ Coming Soon");
								System.out.println("(2) ------ Preview");
								System.out.println("(3) ------ Now Showing");
								System.out.println("(4) ------ End of Showing");
								System.out.printf("Option: ");
												
								choice = sc.nextInt();
												
								switch(choice) {
									case 1: 
										movieData.get(index).setShowingStatus(ShowingStatus.COMING_SOON);
										SerializeDB.writeToMovieList(movieData);
										System.out.println("Showing status updated. Returning to admin menu...");
										return;
									case 2: 
										movieData.get(index).setShowingStatus(ShowingStatus.PREVIEW);
										SerializeDB.writeToMovieList(movieData);
										System.out.println("Showing status updated. Returning to admin menu...");
										return;
									case 3: 
										movieData.get(index).setShowingStatus(ShowingStatus.NOW_SHOWING);
										SerializeDB.writeToMovieList(movieData);
										System.out.println("Showing status updated. Returning to admin menu...");
										return;
									case 4: 
										movieData.get(index).setShowingStatus(ShowingStatus.END_OF_SHOWING);
										System.out.println("Showing status updated. Returning to admin menu...");
										System.out.println("Since movie is marked as end of showing, movie will be removed.");
										for (int i = 0; i<showtimeData.size(); i++) {
											if (showtimeData.get(i).getMovie().getTitle().equals(movieData.get(index).getTitle())) {
												showtimeData.remove(showtimeData.get(i));
											}
										}
										movieData.remove(index);
										SerializeDB.writeToMovieList(movieData);
										SerializeDB.writeToShowtimeList(showtimeData);
										return;
									default:
										System.out.println("Option does not exist. Please key in a valid option!\n");
								}	
							} catch (InputMismatchException ex) {
								sc.nextLine();
								System.out.println("Invalid input. Please choose a valid option!\n");
							}
						} while (true);
					}		
				} catch (InputMismatchException ex) {
					sc.nextLine();
					System.out.println("Invalid input. Please choose a valid option!\n");
				}
			} while(toggle);
		}
	}
	
	@SuppressWarnings("resource")
	public void remove() {
		int choice;
		boolean toggle = true;
		Scanner sc = new Scanner(System.in);
		List<Movie> movieData = SerializeDB.getMovieList();	
		List<Showtime> showtimeData = SerializeDB.getShowtimeList();	
		List<String> movieNames = new ArrayList<String>();
		
		for(Movie m : movieData) {
			movieNames.add(m.getTitle());
		}
		
		if (movieNames.size() == 0) {
			System.out.println("There are no movies to remove!");
		} else {
			System.out.println("Choose a movie to remove:");
			
			do {
				try {
					for (int i = 0; i < movieNames.size(); i++) {
						System.out.printf("(%d) ----------------	  %s\n", i+1, movieNames.get(i));
					}
					System.out.printf("Option: ");
					
					choice = sc.nextInt();
					
					if (choice < 1 || choice > movieNames.size()) {
						System.out.println("Option does not exist. Please key in a valid option!\n");
					} else {
						toggle = false;
						for (int i = 0; i<showtimeData.size(); i++) {
							if (showtimeData.get(i).getMovie().getTitle().equals(movieData.get(choice-1).getTitle())) {
								showtimeData.remove(showtimeData.get(i));
							}
						}
						movieData.remove(choice-1);
						SerializeDB.writeToMovieList(movieData);
						SerializeDB.writeToShowtimeList(showtimeData);
						System.out.printf("Removed %s! Returning to admin menu...\n", movieNames.get(choice-1));
						return;
					}
				} catch (InputMismatchException ex) {
					sc.nextLine();
					System.out.println("Invalid input. Please choose a valid option!\n");
				}
				
			} while(toggle);
		}	
	}
}