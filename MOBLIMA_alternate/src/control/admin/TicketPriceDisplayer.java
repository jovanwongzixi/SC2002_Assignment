package control.admin;

import entity.movie.TicketPrice;
import interfaces.Displayer;

import java.util.List;

public class TicketPriceDisplayer implements Displayer {
    List<TicketPrice> ticketPrices;
    public TicketPriceDisplayer(List<TicketPrice> ticketPrices){
        this.ticketPrices = ticketPrices;
    }
    public void display(){
        System.out.println("\n-------------- Edit Ticket Prices --------------");
        System.out.printf("(1) ----- Group A (Fri-Sun, PH for Platinum Members):\t\t%.2f\n", ticketPrices.get(0).getPrice());
        System.out.printf("(2) ----- Group B (Fri-Sun, PH for Regular Movies):\t\t%.2f\n", ticketPrices.get(1).getPrice());
        System.out.printf("(3) ----- Group C (Fri-Sun, PH for 3D Movies):\t\t\t%.2f\n", ticketPrices.get(2).getPrice());
        System.out.printf("(4) ----- Group D (Mon-Thu for Platinum Members):\t\t%.2f\n", ticketPrices.get(3).getPrice());
        System.out.printf("(5) ----- Group E (Mon-Thu for Child - Regular Movies):\t\t%.2f\n", ticketPrices.get(4).getPrice());
        System.out.printf("(6) ----- Group F (Mon-Thu for Child - 3D):\t\t\t%.2f\n", ticketPrices.get(5).getPrice());
        System.out.printf("(7) ----- Group G (Mon-Thu for Adult - Regular Movies):\t\t%.2f\n", ticketPrices.get(6).getPrice());
        System.out.printf("(8) ----- Group H (Mon-Thu for Adult - 3D):\t\t\t%.2f\n", ticketPrices.get(7).getPrice());
        System.out.printf("(9) ----- Group I (Mon-Thu for Senior Citizens):\t\t%.2f\n", ticketPrices.get(8).getPrice());
        System.out.printf("(10) ----- Blockbuster Quantum:\t\t\t\t\t%.2f\n", ticketPrices.get(9).getPrice());
        System.out.printf("(11) ----- Return to system config\n");
    }
}
