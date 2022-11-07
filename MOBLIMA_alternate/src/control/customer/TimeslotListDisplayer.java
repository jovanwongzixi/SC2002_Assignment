package control.customer;

import entity.movie.Timeslot;
import interfaces.Displayer;

import java.util.List;

public class TimeslotListDisplayer implements Displayer {
    private final List<Timeslot> movieTimeslots;
    public TimeslotListDisplayer(List<Timeslot> movieTimeslots){
        this.movieTimeslots = movieTimeslots;
    }
    public void display(){
        if(movieTimeslots.isEmpty()){
            System.out.println("There are no timeslots for the movie!");
        }
        else {
            System.out.printf("------------------ %s Timeslots ------------------\n", movieTimeslots.get(0).getMovieTitle());
            int i=0;
            for (Timeslot ts : movieTimeslots) {
                System.out.printf("(%d) -------\t Cineplex %s at Cinema %d on %td %<tb %<tY "+ts.getShowDate().getDayOfWeek()+" at %tR", ++i, ts.getCineplex(),
                        ts.getCinema().getID(), ts.getShowDate(), ts.getShowTime());
            }
        }

    }
}
