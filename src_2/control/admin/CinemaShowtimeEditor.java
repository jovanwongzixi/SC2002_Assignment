package control.admin;

import java.util.*;

import boundary.AdminUI;
import control.SerializeDB;
import java.time.*;
import java.time.format.*;
import entity.cinema.*;
import entity.movie.Movie;

public class CinemaShowtimeEditor implements Writable{
	
	@SuppressWarnings("resource")
	public void create() {
		Scanner sc = new Scanner(System.in);
		int choice, index;
		String showMovie;
		LocalDate showDate;
		LocalTime showTime;
		boolean toggle_flag1 = true, toggle_flag2 = true, toggle_flag3 = true, toggle_flag4 = true, toggle_flag5 = true;
		
		List<Showtime> showtimeData = SerializeDB.getShowtimeList("src/data/cinema_showtime.dat");
		List<Movie> movieData = SerializeDB.getMovieList("src/data/movie.dat");
		List<Cineplex> cineplexData = SerializeDB.getCineplexList("src/data/cineplex.dat");
		List<String> movieNames = new ArrayList<String>();
		List<String> cineplexNames = new ArrayList<String>();
		
		for(Movie m : movieData) {
			movieNames.add(m.getTitle());
		}
		
		for (Cineplex cp :cineplexData) {
			cineplexNames.add(cp.getName());
		}
		
		if (movieNames.size() == 0) {
			System.out.println("There are no movies to update!");
		} else {
			System.out.println("Choose a movie to add showtime slot:");
			
			do {
				try {				
					for (int i = 0; i < movieNames.size(); i++) {
						System.out.printf("(%d) ----------------	  %s\n", i+1, movieNames.get(i));
					}
					
					choice = sc.nextInt();
					
					if (choice >= 1 && choice <= movieNames.size()) {
						toggle_flag1 = false;
						index = choice-1;
						showMovie = movieNames.get(index);
						sc.nextLine();
						
						do {	
							System.out.println("Which cineplex will the movie be shown?");
							String cineplexInput = sc.nextLine();
							
							if(cineplexNames.contains(cineplexInput)) {
								toggle_flag2 = false;
								index = cineplexNames.indexOf(cineplexInput);
								
								Cineplex showCineplex = cineplexData.get(index);
								
								do {
									try {
										System.out.println("Which cinema will the movie be shown?");
									
										int cinemaInput = sc.nextInt();
									
										if(cinemaInput >= 1 && cinemaInput <= cineplexData.get(index).getCinema().size()) {	
											toggle_flag3 = false;
											Cinema showCinema = cineplexData.get(index).getCinema().get(cinemaInput-1);
											
											do {
												try {
													showDate = addDate();
													
													do {
														try {
															showTime = addTime();
															toggle_flag5 = false;
															
															Showtime st = new Showtime(showMovie, showCineplex, showCinema, showDate, showTime);
															showtimeData.add(st);
															
														} catch (DateTimeParseException ex) {
															System.out.println("Invalid input. Please use the following format: dd.MM.yyyy!");
														}
													} while (toggle_flag5);
													
													toggle_flag4 = false;
															
												} catch (DateTimeParseException ex) {
													System.out.println("Invalid input. Please use the following format: dd.MM.yyyy!");
												}
											} while(toggle_flag4);
														
										} else {
											System.out.println("The cinema does not exist! Please try again!");
										}								
									} catch (InputMismatchException ex) {
										sc.nextLine();
										System.out.println("Invalid input. Please choose a valid option!\n");
									} 	
									
								} while (toggle_flag3);														
							} else {
								System.out.println("The cineplex does not exist! Please try again!");
							}
							
						} while (toggle_flag2);
					} else {
						System.out.println("Option does not exist. Please key in a valid option!\n");
					}
					
				} catch (InputMismatchException ex) {
					sc.nextLine();
					System.out.println("Invalid input. Please choose a valid option!\n");
				} 
				
			} while (toggle_flag1);
		}
		
		SerializeDB.writeToShowtimeList(showtimeData);
		System.out.println("Created showtime listing! Returning to admin menu...");
		AdminUI.run();
	}
	
	@SuppressWarnings("resource")
	public void update() {
		Scanner sc = new Scanner(System.in);
		int choice;
		boolean toggle_flag1 = true, toggle_flag2 = true, toggle_flag3 = true, toggle_flag4 = true;
		
		List<Showtime> showtimeData = SerializeDB.getShowtimeList("src/data/cinema_showtime.dat");
		List<Cineplex> cineplexData = SerializeDB.getCineplexList("src/data/cineplex.dat");
		List<String> cineplexNames = new ArrayList<String>();
		
		for (Cineplex cp :cineplexData) {
			cineplexNames.add(cp.getName());
		}
		
		if (showtimeData.size() == 0) {
			System.out.println("There are no movie timeslots to update!");
		} else {
			System.out.println("Choose a movie timeslot to update:");
			do {
				try {
					for(int i = 0; i < showtimeData.size(); i++) {
						System.out.printf("(%d) ----------------	%s\n",i+1,showtimeData.get(i).getTitle());
						System.out.printf("Cineplex/Cinema: %s/%d\n",showtimeData.get(i).getCineplex().getName(),showtimeData.get(i).getCinema().getID());
						System.out.printf("Movie Timeslot: %td %<tb %<tY at %tR\n\n",showtimeData.get(i).getShowDate(),showtimeData.get(i).getShowTime());
					
						choice = sc.nextInt();
						int index = choice-1;
					
						if(choice >= 1 && choice <= showtimeData.size()) {
							toggle_flag1 = false;
							
							do {
								try {
									System.out.println("What would you like to update?");
									System.out.println("(1) ----------------	  Location");
									System.out.println("(2) ----------------	  Timeslot");
									
									choice = sc.nextInt();
									
									switch(choice) {
										case 1:
											toggle_flag2 = false;
											do {	
												System.out.println("Input new cinema");
												String cineplexInput = sc.nextLine();
												
												if(cineplexNames.contains(cineplexInput)) {
													toggle_flag3 = false;
													int j = cineplexNames.indexOf(cineplexInput);
													
													Cineplex showCineplex = cineplexData.get(j);
													
													do {
														try {
															System.out.println("Which cinema will the movie be shown?");
														
															int cinemaInput = sc.nextInt();
														
															if(cinemaInput >= 1 && cinemaInput <= cineplexData.get(j).getCinema().size()) {	
																toggle_flag4 = false;
																Cinema showCinema = cineplexData.get(j).getCinema().get(cinemaInput-1);
																
																showtimeData.get(index).setCineplex(showCineplex);	
																showtimeData.get(index).setCinema(showCinema);
																
															} else {
																System.out.println("The cinema does not exist! Please try again!");
															}								
														} catch (InputMismatchException ex) {
															sc.nextLine();
															System.out.println("Invalid input. Please choose a valid option!\n");
														}
													} while (toggle_flag4);
												} else {
													System.out.println("The cineplex does not exist! Please try again!");
												}
												
											} while (toggle_flag3);
											
										case 2:
											toggle_flag2 = false;
											do {
												try {
													LocalDate showDate = addDate();
													
													do {
														try {
															LocalTime showTime = addTime();
															toggle_flag4 = false;
															
															showtimeData.get(index).setShowDate(showDate);
															showtimeData.get(index).setShowTime(showTime);
															
														} catch (DateTimeParseException ex) {
															System.out.println("Invalid input. Please use the following format: dd.MM.yyyy!");
														}
													} while (toggle_flag4);
													
													toggle_flag3 = false;
															
												} catch (DateTimeParseException ex) {
													System.out.println("Invalid input. Please use the following format: dd.MM.yyyy!");
												}
											} while(toggle_flag3);
											
										default:
											System.out.println("Option does not exist. Please key in a valid option!\n");	
									}
								} catch (InputMismatchException ex) {
									sc.nextLine();
									System.out.println("Invalid input. Please choose a valid option!\n");
								}
							} while (toggle_flag2);
													
						} else {
							System.out.println("The movie timeslot does not exist! Please try again!");
						}
					}
				} catch (InputMismatchException ex) {
					sc.nextLine();
					System.out.println("Invalid input. Please choose a valid option!\n");
				}
			} while (toggle_flag1);
		}
		
		SerializeDB.writeToShowtimeList(showtimeData);
		System.out.println("Removed showtime listing! Returning to admin menu...");
		AdminUI.run();
	}
	
	@SuppressWarnings("resource")
	public void remove() {
		Scanner sc = new Scanner(System.in);
		int choice;
		boolean toggle = true;
		
		List<Showtime> showtimeData = SerializeDB.getShowtimeList("src/data/cinema_showtime.dat");
		
		if (showtimeData.size() == 0) {
			System.out.println("There are no movie timeslots to remove!");
		} else {
			System.out.println("Choose a movie timeslot to remove:");
			do {
				try {
					for(int i = 0; i < showtimeData.size(); i++) {
						System.out.printf("(%d) ----------------	%s\n",i+1,showtimeData.get(i).getTitle());
						System.out.printf("Cineplex/Cinema: %s/%d\n",showtimeData.get(i).getCineplex().getName(),showtimeData.get(i).getCinema().getID());
						System.out.printf("Movie Timeslot: %td %<tb %<tY at %tR\n\n",showtimeData.get(i).getShowDate(),showtimeData.get(i).getShowTime());
					
						choice = sc.nextInt();
					
						if(choice >= 1 && choice <= showtimeData.size()) {
							toggle = false;
							showtimeData.remove(choice-1);
						} else {
							System.out.println("The movie timeslot does not exist! Please try again!");
						}
					}
				} catch (InputMismatchException ex) {
					sc.nextLine();
					System.out.println("Invalid input. Please choose a valid option!\n");
				}
			} while (toggle);
		}
		
		SerializeDB.writeToShowtimeList(showtimeData);
		System.out.println("Removed showtime listing! Returning to admin menu...");
		AdminUI.run();
	}
	
	@SuppressWarnings("resource")
	public LocalDate addDate() throws DateTimeParseException{
		Scanner sc = new Scanner(System.in);
		System.out.println("Input show date (dd.MM.yyyy): ");
		String dateInput = sc.nextLine();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		return LocalDate.parse(dateInput, dtf);
	}
	
	@SuppressWarnings("resource")
	public LocalTime addTime() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Input show time (hh:mm): ");
		String timeInput = sc.nextLine();
		timeInput += ":00";
		return LocalTime.parse(timeInput, DateTimeFormatter.ISO_TIME);
	}
}
