package nl.craftsmen.workshops.reactivemeetup.exercises.two;

import nl.craftsmen.workshops.reactivemeetup.domain.railway.Departure;
import nl.craftsmen.workshops.reactivemeetup.domain.railway.ERailwayStation;
import nl.craftsmen.workshops.reactivemeetup.util.RailwayStreams;
import rx.Observable;

import java.time.format.DateTimeFormatter;

import static nl.craftsmen.workshops.reactivemeetup.util.Utils.waitForStreamToComplete;

public class Exercise03 {

    public static void main(String[] args) {
        RailwayStreams.buildStations();

        Observable<Departure> departure$ = RailwayStreams.departure$();
        Observable<String> dulyDepartures$ = departure$
                .filter((departure) -> departure.getDestination().getId() == ERailwayStation.AMS)
                .filter((departure) -> departure.getDelay().isZero())
                .map((departure) -> "Train for " + departure.getDestination().getName() +
                        " will be leaving at " + departure.getDepartureTime().format(DateTimeFormatter.ofPattern("HH:mm")));

        Observable<String> delayedDepartures$ = departure$
                .filter((departure) -> departure.getDestination().getId() == ERailwayStation.AMS)
                .filter((departure) -> !departure.getDelay().isZero())
                .map((departure) -> "Train for " + departure.getDestination().getName() + " planned for " +
                        departure.getDepartureTime().format(DateTimeFormatter.ofPattern("HH:mm")) +
                        " leaving at " + departure.getDepartureTime().plus(departure.getDelay()).format(DateTimeFormatter.ofPattern("HH:mm"))
                        + " (delay " + departure.getDelay().toMinutes() + " minutes)");

        dulyDepartures$.mergeWith(delayedDepartures$)
                .subscribe(System.out::println);


        waitForStreamToComplete(departure$);
    }
}
