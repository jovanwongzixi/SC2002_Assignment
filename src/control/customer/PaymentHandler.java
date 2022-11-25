package control.customer;

import control.datahandler.BookingDataHandler;
import control.datahandler.CineplexDataHandler;
import control.datahandler.HolidayDataHandler;
import entity.Booking;
import entity.Customer;
import entity.Holiday;
import entity.cinema.Cineplex;
import entity.cinema.Seat;
import entity.movie.*;
import interfaces.DataHandler;
import interfaces.Displayer;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PaymentHandler {
    protected boolean ticketPayment(Movie movie, Timeslot ts, List<Seat> selectedSeats, Customer currentUser) {
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
                    //PaymentHandler handler = new PaymentHandler();
                    if (payCreditCard()) {
                        LocalDateTime timeOfPurchase = LocalDateTime.now();
                        String tID = cineplexCode + timeOfPurchase.format(formatter);
                        Booking b = new Booking(currentUser.getName(), currentUser.getEmailAddress(), currentUser.getMobileNumber(), ticketPrice,
                                ts.getMovieTitle(), ts.getCineplex(), ts.getCinema().getID(), ts.getShowDate(),
                                ts.getShowTime(), timeOfPurchase, tID);
                        bookingData.add(b);

                        bookingDataHandler.save(bookingData);

                        System.out.printf("The ticket has been successfully purchase. Your transaction ID is %s. Returning to previous menu...\n", tID);
                        return true;
                    }
                    else{
                        System.out.println("Credit card invalid! Please try again!");
			break;
                    }
                case 2:
                    System.out.println("Returning to previous menu...");
                    return false;
                default:
                    System.out.println("Option does not exist. Please key in a valid option!\n");
            }

        } while (true);
    }
    private boolean payCreditCard(){
        Scanner sc = new Scanner(System.in);
        boolean inputPass;
        String cardNumber;
        System.out.println("Only Mastercard, Visa and American Express card excepted!");
        do {
            System.out.println("Input credit card number:");
            cardNumber = sc.nextLine();
            inputPass = isNumeric(cardNumber);
        }while(!inputPass);
        return checkDigits(cardNumber) && checkSum(cardNumber);
    }
    private boolean checkSum(String cardNumber){
        int checkSum=0;
        for(int i=cardNumber.length()-1; i>=0; i--){
            int digit = cardNumber.charAt(i)-'0';
            if((cardNumber.length()-i)%2==0) digit *=2;
            checkSum += digit/10;
            checkSum += digit%10;
        }
        return checkSum%10==0;
    }
    private boolean isNumeric(String num){
        if (num==null) return false;
        try{
            Long.parseLong(num);

        } catch (NumberFormatException e){
            System.out.println("Input is not numeric!");
            return false;
        }
        return true;
    }
    private boolean checkDigits(String cardNumber){
        //Check American Express
        if((cardNumber.startsWith("34") || cardNumber.startsWith("37")) && cardNumber.length() == 15) return true;
        //Check Visa
        else if(cardNumber.startsWith("4") && (cardNumber.length() == 13 || cardNumber.length() == 16)) return true;
        //Check MasterCard
        else return (Integer.parseInt(cardNumber.substring(0,2))>=22 || Integer.parseInt(cardNumber.substring(0,2))<=27 || Integer.parseInt(cardNumber.substring(0,2))>=51 || Integer.parseInt(cardNumber.substring(0,2))<=55)&& cardNumber.length() == 16;
    }
}
