package control.customer;

import entity.cinema.Cinema;
import entity.cinema.SeatState;
import entity.movie.Timeslot;
import interfaces.Displayer;

public class SeatDisplayer implements Displayer {
    private final Timeslot movieTimeslot;
    public SeatDisplayer(Timeslot movieTimeslot){
        this.movieTimeslot = movieTimeslot;
    }
    public void display(){
        Cinema cinemaShown = movieTimeslot.getCinema();

        if (!cinemaShown.getIsPlatinum()){
            System.out.println("                                              Screen                                             ");
            System.out.println("                                       --------------------                                      ");
            System.out.println("     1  |  2  |  3  |  4  |    |  5  |  6  |  7  |  8  |  9  | 10  |    | 11  | 12  | 13  | 14  |");
            System.out.println("---------------------------    -------------------------------------    -------------------------");

            for (int i = 0; i < cinemaShown.getNumOfRows(); i++) {
                if (i < cinemaShown.getNumOfRows()-2) {
                    System.out.println("  |     |     |     |     |    |     |     |     |     |     |     |    |     |     |     |     |");
                } else {
                    System.out.println("  |           |           |    |           |           |           |    |           |           |");
                }
                System.out.printf("%c |", (char)(i+65));

                for (int j = 0; j < cinemaShown.getNumOfCols(); j++) {
                    if (j == 4 || j == 10)
                        System.out.printf("    |");
                    if (i < cinemaShown.getNumOfRows()-2) {
                        if (cinemaShown.getCinemaLayout().get(i).get(j).getSeatState() == SeatState.AVAILABLE) {
                            if (j != cinemaShown.getNumOfCols()) {
                                System.out.printf("     |");
                            }
                        } else if (cinemaShown.getCinemaLayout().get(i).get(j).getSeatState() == SeatState.SELECTED) {
                            if (j != cinemaShown.getNumOfCols()) {
                                System.out.printf("  O  |");
                            }
                        } else {
                            if (j != cinemaShown.getNumOfCols()) {
                                System.out.printf("  X  |");
                            }
                        }
                    } else {
                        if (cinemaShown.getCinemaLayout().get(i).get(j/2).getSeatState() == SeatState.AVAILABLE) {
                            if (j <= cinemaShown.getNumOfCols()-1) {
                                System.out.printf("           |");
                                j++;
                            }
                        } else if (cinemaShown.getCinemaLayout().get(i).get(j/2).getSeatState() == SeatState.SELECTED) {
                            if (j <= cinemaShown.getNumOfCols()-1) {
                                System.out.printf("     O     |");
                                j++;
                            }
                        } else {
                            if (j <= cinemaShown.getNumOfCols()-1) {
                                System.out.printf("     X     |");
                                j++;
                            }
                        }
                    }
                }

                if (i < cinemaShown.getNumOfRows()-2) {
                    System.out.println("\n  |     |     |     |     |    |     |     |     |     |     |     |    |     |     |     |     |");
                } else {
                    System.out.println("\n  |           |           |    |           |           |           |    |           |           |");
                }

                if (i != cinemaShown.getNumOfRows()-1) {
                    System.out.println("---------------------------    -------------------------------------    -------------------------");
                }
            }
        } else {
            System.out.println("                               Screen                             ");
            System.out.println("                        --------------------                      ");
            System.out.println("     1  |  2  |    |  3  |  4  |    |  5  |  6  |    |  7  |  8  |");
            System.out.println("---------------    -------------    -------------    -------------");

            for (int i = 0; i < cinemaShown.getNumOfRows(); i++) {
                if (i < cinemaShown.getNumOfRows()-2) {
                    System.out.println("  |     |     |    |     |     |    |     |     |    |     |     |");
                } else {
                    System.out.println("  |           |    |           |    |           |    |           |");
                }
                System.out.printf("%c |", (char)(i+65));

                for (int j = 0; j < cinemaShown.getNumOfCols(); j++) {
                    if (j == 2 || j == 4 || j == 6)
                        System.out.printf("    |");
                    if (i < cinemaShown.getNumOfRows()-2) {
                        if (cinemaShown.getCinemaLayout().get(i).get(j).getSeatState() == SeatState.AVAILABLE) {
                            if (j != cinemaShown.getNumOfCols()) {
                                System.out.printf("     |");
                            }
                        } else if (cinemaShown.getCinemaLayout().get(i).get(j).getSeatState() == SeatState.SELECTED) {
                            if (j != cinemaShown.getNumOfCols()) {
                                System.out.printf("  O  |");
                            }
                        } else {
                            if (j != cinemaShown.getNumOfCols()) {
                                System.out.printf("  X  |");
                            }
                        }
                    } else {
                        if (cinemaShown.getCinemaLayout().get(i).get(j/2).getSeatState() == SeatState.AVAILABLE) {
                            if (j <= cinemaShown.getNumOfCols()-1) {
                                System.out.printf("           |");
                                j++;
                            }
                        } else if (cinemaShown.getCinemaLayout().get(i).get(j/2).getSeatState() == SeatState.SELECTED) {
                            if (j <= cinemaShown.getNumOfCols()-1) {
                                System.out.printf("     O     |");
                                j++;
                            }
                        } else {
                            if (j <= cinemaShown.getNumOfCols()-1) {
                                System.out.printf("     X     |");
                                j++;
                            }
                        }
                    }
                }

                if (i < cinemaShown.getNumOfRows()-2) {
                    System.out.println("\n  |     |     |    |     |     |    |     |     |    |     |     |");
                } else {
                    System.out.println("\n  |           |    |           |    |           |    |           |");
                }

                if (i != cinemaShown.getNumOfRows()-1) {
                    System.out.println("---------------    -------------    -------------    -------------");
                }
            }
        }

        System.out.println("\nX --------- Seat taken");
        System.out.println("O --------- Seat selected\n");
    }
}

