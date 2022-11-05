package control.customer;

import control.SerializeDB;
import entity.movie.AgeGroup;
import entity.movie.Ticket;
import entity.movie.TicketPrice;

import java.util.List;

public class TicketPriceCalculator {
    protected double use(Ticket ticket) {
        //List<Double> ticketPrices = SerializeDB.getTicketPrices();
        List<TicketPrice> ticketPrices = SerializeDB.getList("TicketPrice");
        double ticketPrice = 0;

        if (ticket.getIsSpecial()) {
            if(ticket.getIsPlatinum()) {
                ticketPrice += ticketPrices.get(0).getPrice();
            } else {
                if(!ticket.getIs3D()) {
                    ticketPrice += ticketPrices.get(1).getPrice();
                } else {
                    ticketPrice += ticketPrices.get(2).getPrice();
                }
            }
        } else {
            if(ticket.getIsPlatinum()) {
                ticketPrice += ticketPrices.get(3).getPrice();
            } else {
                if(ticket.getAgeGroup() == AgeGroup.SENIOR_CITIZEN) {
                    ticketPrice += ticketPrices.get(8).getPrice();
                } else if (ticket.getAgeGroup() == AgeGroup.CHILD) {
                    if(!ticket.getIs3D()) {
                        ticketPrice += ticketPrices.get(4).getPrice();
                    } else {
                        ticketPrice += ticketPrices.get(5).getPrice();
                    }
                } else {
                    if(!ticket.getIs3D()) {
                        ticketPrice += ticketPrices.get(6).getPrice();
                    } else {
                        ticketPrice += ticketPrices.get(7).getPrice();
                    }
                }
            }
        }

        if(ticket.getIsBlockbuster()) {
            ticketPrice += ticketPrices.get(9).getPrice();
        }

        if(ticket.getIsDouble()) {
            ticketPrice *= 2;
        }
        return ticketPrice;
    }
}
