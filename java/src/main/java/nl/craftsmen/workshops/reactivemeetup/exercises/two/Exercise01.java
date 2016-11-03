package nl.craftsmen.workshops.reactivemeetup.exercises.two;

import nl.craftsmen.workshops.reactivemeetup.domain.railway.Departure;
import nl.craftsmen.workshops.reactivemeetup.domain.railway.ERailwayStation;
import nl.craftsmen.workshops.reactivemeetup.util.RailwayStreams;
import rx.Observable;

import java.time.format.DateTimeFormatter;

import static nl.craftsmen.workshops.reactivemeetup.util.Utils.waitForStreamToComplete;

public class Exercise01 {

    public static void main(String[] args) {
        RailwayStreams.buildStations();

        Observable<Departure> departure$ = RailwayStreams.departure$();
        departure$
                .filter((i) -> i.getDestination().getId() == ERailwayStation.AMS)
                .map((r) -> "Train for: " + r.getDestination().getName() + " leaving at " +
                        r.getDepartureTime().format(DateTimeFormatter.ofPattern("HH:mm")))
                .subscribe(System.out::println);


        waitForStreamToComplete(departure$);
    }
}
