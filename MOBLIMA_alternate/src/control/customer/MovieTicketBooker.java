package control.customer;

import control.datahandler.*;
import entity.Booking;
import entity.Customer;
import entity.Holiday;
import entity.cinema.Cineplex;
import entity.cinema.Seat;
import entity.cinema.SeatState;
import entity.movie.*;
import interfaces.*;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class MovieTicketBooker implements Controller {
    private final Customer currentUser;
    public MovieTicketBooker(Customer currentUser){
        this.currentUser = currentUser;
    }
    public void start(){

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            DataHandler movieDataHandler = new MovieDataHandler();
            List<Movie> bookingList = movieDataHandler.retrieve();
            bookingList.removeIf(movie -> Objects.equals(movie.getShowingStatus(), ShowingStatus.COMING_SOON));
            Displayer movieListDisplayer = new MovieListDisplayer(bookingList);
            movieListDisplayer.display();
            System.out.printf("\nInput option number to view book movie ticket (-1 to return to customer menu): ");

            while(!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter an integer!");
                System.out.printf("Option: ");
                sc.next();
            }

            choice = sc.nextInt();

            if(choice > bookingList.size()) {
                System.out.println("Option does not exist. Please key in a valid option!\n");
            } else if (choice == -1) {
                System.out.println("Returning to customer menu...");
                return;
            } else {
                selectTimeslot(bookingList.get(choice-1));
                return;
            }
        } while (true);
    }
    private void selectTimeslot(Movie movie) {
        Scanner sc = new Scanner(System.in);
        int choice;
        DataHandler timeslotDataHandler = new TimeslotDataHandler();
        List<Timeslot> movieTimeslots = timeslotDataHandler.retrieve();
        List<Timeslot> selectedMovieTimeslots = new ArrayList<>();
        for(Timeslot ts : movieTimeslots){
            if(Objects.equals(ts.getMovieTitle(), movie.getTitle())) selectedMovieTimeslots.add(ts);
        }
        //movieTimeslots.removeIf(ts -> !Objects.equals(ts.getMovieTitle(), movie.getTitle()));
        Displayer movieTimeslotDisplayer = new TimeslotListDisplayer(selectedMovieTimeslots);
        do {
            movieTimeslotDisplayer.display();
            System.out.printf("\nInput option number to view seats (-1 to return to previous menu): ");

            while(!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter an integer!");
                sc.next();
            }

            choice = sc.nextInt();

            if(choice > movieTimeslots.size()) {
                System.out.println("Option does not exist. Please key in a valid option!\n");
            } else if (choice == -1) {
                System.out.println("Returning to previous menu...");
                return;
            } else {
                Timeslot ts = selectedMovieTimeslots.get(choice-1);
                selectSeats(ts, movieTimeslots.indexOf(ts), movie);
                return;
            }
        } while (true);
    }
    private void selectSeats(Timeslot timeslot, int ts_index, Movie movie) {
        Scanner sc = new Scanner(System.in);
        int choice, selection = 0;
        List<Seat> selectedSeats = new ArrayList<Seat>();

        do {
            Displayer seatDisplayer = new SeatDisplayer(timeslot);
            seatDisplayer.display();
            System.out.println("(1) ----------------      Select seating");
            System.out.println("(2) ----------------      Remove selection");
            System.out.println("(3) ----------------      Proceed to payment");
            System.out.println("(4) ----------------      Return to previous menu");
            System.out.printf("\nOption: ");

            while(!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter an integer!");
                System.out.printf("Option: ");
                sc.next();
            }

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.printf("Enter row (A-%c): ", (char)(timeslot.getCinema().getNumOfRows()-1 + 'A'));
                    char addRow = sc.next().charAt(0);
                    System.out.printf("Enter column (1-%d): ", timeslot.getCinema().getNumOfCols());
                    int addCol = sc.nextInt();

                    if(addRow+2-'A' < timeslot.getCinema().getNumOfRows()) {
                        Seat s = timeslot.getCinema().getCinemaLayout().get(addRow-'A').get(addCol-1);
                        if (s.getSeatState() == SeatState.AVAILABLE) {
                            s.setSeatState(SeatState.SELECTED);
                            selection++;
                            selectedSeats.add(s);
                            System.out.println("Successfully selected seat.");
                        } else {
                            System.out.println("Seat has already been taken, please choose another seat.");
                        }
                    } else {
                        addCol = (addCol + addCol%2)/2;
                        Seat s = timeslot.getCinema().getCinemaLayout().get(addRow-'A').get(addCol-1);
                        if (s.getSeatState() == SeatState.AVAILABLE) {
                            s.setSeatState(SeatState.SELECTED);
                            selection++;
                            selectedSeats.add(s);
                            System.out.println("Successfully selected seat.");
                        } else {
                            System.out.println("Seat has already been taken, please choose another seat.");
                        }
                    }

                    break;
                case 2:
                    System.out.printf("Enter row (A-%c): ", (char)(timeslot.getCinema().getNumOfRows()-1 + 'A'));
                    char removeRow = sc.next().charAt(0);
                    System.out.printf("Enter column (1-%d): ", timeslot.getCinema().getNumOfCols());
                    int removeCol = sc.nextInt();

                    if(removeRow+2-'A' < timeslot.getCinema().getNumOfRows()) {
                        Seat s = timeslot.getCinema().getCinemaLayout().get(removeRow-'A').get(removeCol-1);
                        if (s.getSeatState() == SeatState.SELECTED) {
                            s.setSeatState(SeatState.AVAILABLE);
                            selection--;
                            selectedSeats.remove(s);
                            System.out.println("Successfully removed selected seat.");
                        } else {
                            System.out.println("Seat is unable to be deselected, please choose the correct seat.");
                        }
                    } else {
                        final int removeDoubleCol = (removeCol + removeCol%2)/2;
                        Seat s = timeslot.getCinema().getCinemaLayout().get(removeRow-'A').get(removeDoubleCol-1);
                        if (s.getSeatState() == SeatState.SELECTED) {
                            s.setSeatState(SeatState.AVAILABLE);
                            selection--;
                            selectedSeats.remove(s);
                            System.out.println("Successfully removed selected seat.");
                        } else {
                            System.out.println("Seat is unable to be deselected, please choose the correct seat.");
                        }
                    }
                    break;
                case 3:
                    if (selection == 0) {
                        System.out.println("There are no seats selected! Please select a seat before payment!");
                        break;
                    } else {
                        ticketPayment(movie, timeslot, ts_index, selectedSeats);
                        return;
                    }
                case 4:
                    System.out.println("Returning to previous menu...");
                    return;
                default:
                    System.out.println("Option does not exist. Please key in a valid option!\n");
            }
        } while (true);
    }
    private void ticketPayment(Movie movie, Timeslot ts, int tsIndex, List<Seat> selectedSeats) {
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        DataHandler cineplexDataHandler = new CineplexDataHandler();
        List<Cineplex> cineplexData = cineplexDataHandler.retrieve();
        DataHandler holidayDataHandler = new HolidayDataHandler();
        List<Holiday> holidays = holidayDataHandler.retrieve();
        DataHandler bookingDataHandler = new BookingDataHandler();
        List<Booking> bookingData = bookingDataHandler.retrieve();

        List<Ticket> ticketList = new ArrayList<Ticket>();
        String cineplexCode = null;
        int choice;
        double ticketPrice = 0;
        boolean isSpecial = false;

        for(Cineplex c : cineplexData) {
            if (c.getName().equals(ts.getCineplex())) {
                cineplexCode = c.getCode();
            }
        }
        Displayer seatDisplayer = new SeatDisplayer(ts);
        seatDisplayer.display();

        for (Holiday d : holidays) {
            if (ts.getShowDate().equals(d.getDate()) || ts.getShowDate().getDayOfWeek() == DayOfWeek.FRIDAY || ts.getShowDate().getDayOfWeek() == DayOfWeek.SATURDAY ||
                    ts.getShowDate().getDayOfWeek() == DayOfWeek.SUNDAY) {
                isSpecial = true;
                break;
            }
        }


        for (Seat s : selectedSeats) {
            boolean toggle = true;
            do {
                boolean forChild = false;
                System.out.printf("Enter age group for seat %c%d:\n", (char)(s.getSeatRow()-1+'A'), s.getSeatCol());
                System.out.println("(1) ----------------      Senior Citizen (age 55 and above)");
                System.out.println("(2) ----------------      Adult");
                if (movie.getFilmRating() == FilmRating.G || movie.getFilmRating() == FilmRating.PG || movie.getFilmRating() == FilmRating.PG13) {
                    System.out.println("(3) ----------------      Child (age 16 and below)");
                    forChild = true;
                }
                System.out.printf("Option: ");

                while(!sc.hasNextInt()) {
                    System.out.println("Invalid input. Please enter an integer!");
                    System.out.printf("Option: ");
                    sc.next();
                }

                choice = sc.nextInt();

                switch(choice) {
                    case 1:
                        ticketList.add(new Ticket(movie.getIs3D(), movie.getIsBlockbuster(), ts.getCinema().getIsPlatinum(),
                                AgeGroup.SENIOR_CITIZEN, s.getIsDouble(), isSpecial));
                        toggle = false;
                        break;
                    case 2:
                        ticketList.add(new Ticket(movie.getIs3D(), movie.getIsBlockbuster(), ts.getCinema().getIsPlatinum(),
                                AgeGroup.ADULT, s.getIsDouble(), isSpecial));
                        toggle = false;
                        break;
                    case 3:
                        if (forChild) {
                            ticketList.add(new Ticket(movie.getIs3D(), movie.getIsBlockbuster(), ts.getCinema().getIsPlatinum(),
                                    AgeGroup.CHILD, s.getIsDouble(), isSpecial));
                            toggle = false;
                            break;
                        }
                        System.out.println("Option does not exist. Please key in a valid option!\n");
                    default:
                        System.out.println("Option does not exist. Please key in a valid option!\n");
                }
            } while (toggle);
        }
        TicketPriceCalculator calculator = new TicketPriceCalculator();
        for (Ticket t : ticketList) {
            ticketPrice += calculator.use(t);
        }

        do {
            System.out.printf("The ticket price is: %.2f. Would you like to confirm booking?\n", ticketPrice);
            System.out.println("(1) Yes");
            System.out.println("(2) No");
            System.out.printf("Option: ");

            while(!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter an integer!");
                System.out.printf("Option: ");
                sc.next();
            }

            choice = sc.nextInt();

            switch(choice) {
                case 1:
                    PaymentHandler handler = new PaymentHandler();
                    if (handler.start()) {
                        LocalDateTime timeOfPurchase = LocalDateTime.now();
                        String tID = cineplexCode + timeOfPurchase.format(formatter);
                        Booking b = new Booking(currentUser.getName(), currentUser.getEmailAddress(), currentUser.getMobileNumber(), ticketPrice,
                                ts.getMovieTitle(), ts.getCineplex(), ts.getCinema().getID(), ts.getShowDate(),
                                ts.getShowTime(), timeOfPurchase, tID);
                        bookingData.add(b);

                        confirmSeats(ts, tsIndex, selectedSeats);
                        bookingDataHandler.save(bookingData);

                        System.out.printf("The ticket has been successfully purchase. Your transaction ID is %s. Returning to previous menu...\n", tID);
                        return;
                    }
                    else{
                        System.out.println("Credit card invalid! Please try again!");
                    }
                case 2:
                    System.out.println("Returning to previous menu...");
                    return;
                default:
                    System.out.println("Option does not exist. Please key in a valid option!\n");
            }

        } while (true);
    }
    private void confirmSeats(Timeslot ts, int tsIndex, List<Seat> selectedSeats) {
        DataHandler timeslotDataHandler = new TimeslotDataHandler();
        List<Timeslot> movieTimeslots = timeslotDataHandler.retrieve();
        movieTimeslots.set(tsIndex, ts);
        for (Seat s : selectedSeats) {
            s.setSeatState(SeatState.TAKEN);
        }
        timeslotDataHandler.save(movieTimeslots);
    }
}
