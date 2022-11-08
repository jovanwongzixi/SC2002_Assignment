package control.customer;

import entity.cinema.Seat;
import entity.cinema.SeatState;
import entity.movie.Movie;
import entity.movie.Timeslot;
import interfaces.Displayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SeatSelector {
    protected List<Seat> selectSeats(Timeslot timeslot, Movie movie) {
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

            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter an integer!");
                System.out.printf("Option: ");
                sc.next();
            }

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.printf("Enter row (A-%c): ", (char) (timeslot.getCinema().getNumOfRows() - 1 + 'A'));
                    char addRow = sc.next().charAt(0);
                    System.out.printf("Enter column (1-%d): ", timeslot.getCinema().getNumOfCols());
                    int addCol = sc.nextInt();

                    if (addRow + 2 - 'A' < timeslot.getCinema().getNumOfRows()) {
                        Seat s = timeslot.getCinema().getCinemaLayout().get(addRow - 'A').get(addCol - 1);
                        if (s.getSeatState() == SeatState.AVAILABLE) {
                            s.setSeatState(SeatState.SELECTED);
                            selection++;
                            selectedSeats.add(s);
                            System.out.println("Successfully selected seat.");
                        } else {
                            System.out.println("Seat has already been taken, please choose another seat.");
                        }
                    } else {
                        addCol = (addCol + addCol % 2) / 2;
                        Seat s = timeslot.getCinema().getCinemaLayout().get(addRow - 'A').get(addCol - 1);
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
                    System.out.printf("Enter row (A-%c): ", (char) (timeslot.getCinema().getNumOfRows() - 1 + 'A'));
                    char removeRow = sc.next().charAt(0);
                    System.out.printf("Enter column (1-%d): ", timeslot.getCinema().getNumOfCols());
                    int removeCol = sc.nextInt();

                    if (removeRow + 2 - 'A' < timeslot.getCinema().getNumOfRows()) {
                        Seat s = timeslot.getCinema().getCinemaLayout().get(removeRow - 'A').get(removeCol - 1);
                        if (s.getSeatState() == SeatState.SELECTED) {
                            s.setSeatState(SeatState.AVAILABLE);
                            selection--;
                            selectedSeats.remove(s);
                            System.out.println("Successfully removed selected seat.");
                        } else {
                            System.out.println("Seat is unable to be deselected, please choose the correct seat.");
                        }
                    } else {
                        final int removeDoubleCol = (removeCol + removeCol % 2) / 2;
                        Seat s = timeslot.getCinema().getCinemaLayout().get(removeRow - 'A').get(removeDoubleCol - 1);
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
                        //ticketPayment(movie, timeslot, ts_index, selectedSeats);
                        return selectedSeats;
                    }
                case 4:
                    System.out.println("Returning to previous menu...");
                    return null;
                default:
                    System.out.println("Option does not exist. Please key in a valid option!\n");
            }
        } while (true);
    }
}
