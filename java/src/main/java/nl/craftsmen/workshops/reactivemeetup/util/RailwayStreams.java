package nl.craftsmen.workshops.reactivemeetup.util;

import nl.craftsmen.workshops.reactivemeetup.domain.railway.Departure;
import nl.craftsmen.workshops.reactivemeetup.domain.railway.ERailwayStation;
import nl.craftsmen.workshops.reactivemeetup.domain.railway.RailwayStation;
import nl.craftsmen.workshops.reactivemeetup.domain.railway.Train;
import rx.Observable;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static nl.craftsmen.workshops.reactivemeetup.util.Utils.sample;

public class RailwayStreams {

    private static Map<ERailwayStation, RailwayStation> stations = new HashMap<>();

    public static void buildStations() {
        stations.put(ERailwayStation.AMS, new RailwayStation(ERailwayStation.AMS, "Amsterdam CS", 52.3791283, 4.8980833));
        stations.put(ERailwayStation.DB, new RailwayStation(ERailwayStation.DB, "'s Hertogenbosch CS", 51.6905476,5.2913696));
        stations.put(ERailwayStation.DH, new RailwayStation(ERailwayStation.DH, "Den Haag CS", 52.0809271, 4.3222312));
        stations.put(ERailwayStation.AMR, new RailwayStation(ERailwayStation.AMR, "Amersfoort CS", 52.1530195,5.3711025));
    }

    public static Observable<Departure> departure$() {
        return sample(Observable.from(getDepartures()), 200);
    }

    public static Observable<Train> train$() {
        return sample(Observable.from(new Train[] {}), 200);
    }

    private static Departure[] getDepartures() {
        return new Departure[]
                {
                        new Departure(Utils.getZonedDateTime(16, 23), stations.get(ERailwayStation.AMS)),
                        new Departure(Utils.getZonedDateTime(16, 27), stations.get(ERailwayStation.DB)),
                        new Departure(Utils.getZonedDateTime(16, 31), stations.get(ERailwayStation.DH), Duration.ofMinutes(3)),
                        new Departure(Utils.getZonedDateTime(16, 35), stations.get(ERailwayStation.AMR)),
                        new Departure(Utils.getZonedDateTime(16, 38), stations.get(ERailwayStation.AMS), Duration.ofMinutes(12)),
                        new Departure(Utils.getZonedDateTime(16, 42), stations.get(ERailwayStation.DB)),
                        new Departure(Utils.getZonedDateTime(16, 46), stations.get(ERailwayStation.DH)),
                        new Departure(Utils.getZonedDateTime(16, 50), stations.get(ERailwayStation.AMR), Duration.ofMinutes(6)),
                        new Departure(Utils.getZonedDateTime(16, 53), stations.get(ERailwayStation.AMS), Duration.ofMinutes(25)),
                        new Departure(Utils.getZonedDateTime(16, 57), stations.get(ERailwayStation.DB), Duration.ofMinutes(25)),
                        new Departure(Utils.getZonedDateTime(17, 1), stations.get(ERailwayStation.DH)),
                        new Departure(Utils.getZonedDateTime(17, 5), stations.get(ERailwayStation.AMR), Duration.ofMinutes(2)),
                        new Departure(Utils.getZonedDateTime(17, 8), stations.get(ERailwayStation.AMS)),
                        new Departure(Utils.getZonedDateTime(17, 12), stations.get(ERailwayStation.DB), Duration.ofMinutes(15)),
                        new Departure(Utils.getZonedDateTime(17, 16), stations.get(ERailwayStation.DH)),
                        new Departure(Utils.getZonedDateTime(17, 20), stations.get(ERailwayStation.AMR)),
                        new Departure(Utils.getZonedDateTime(17, 23), stations.get(ERailwayStation.AMS), Duration.ofMinutes(3)),
                        new Departure(Utils.getZonedDateTime(17, 27), stations.get(ERailwayStation.DB), Duration.ofMinutes(30)),
                        new Departure(Utils.getZonedDateTime(17, 31), stations.get(ERailwayStation.DH)),
                        new Departure(Utils.getZonedDateTime(17, 35), stations.get(ERailwayStation.AMR), Duration.ofMinutes(1)),
                        new Departure(Utils.getZonedDateTime(17, 38), stations.get(ERailwayStation.AMS)),
                        new Departure(Utils.getZonedDateTime(17, 42), stations.get(ERailwayStation.DB)),
                        new Departure(Utils.getZonedDateTime(17, 46), stations.get(ERailwayStation.DH), Duration.ofMinutes(3)),
                        new Departure(Utils.getZonedDateTime(17, 50), stations.get(ERailwayStation.AMR)),
                        new Departure(Utils.getZonedDateTime(17, 53), stations.get(ERailwayStation.AMS)),
                        new Departure(Utils.getZonedDateTime(17, 57), stations.get(ERailwayStation.DB)),
                        new Departure(Utils.getZonedDateTime(18, 1), stations.get(ERailwayStation.DH), Duration.ofMinutes(10)),
                        new Departure(Utils.getZonedDateTime(18, 5), stations.get(ERailwayStation.AMR)),
                        new Departure(Utils.getZonedDateTime(18, 8), stations.get(ERailwayStation.AMS)),
                        new Departure(Utils.getZonedDateTime(18, 12), stations.get(ERailwayStation.DB), Duration.ofMinutes(5)),
                        new Departure(Utils.getZonedDateTime(18, 16), stations.get(ERailwayStation.DH)),
                        new Departure(Utils.getZonedDateTime(18, 20), stations.get(ERailwayStation.AMR)),
                        new Departure(Utils.getZonedDateTime(18, 23), stations.get(ERailwayStation.AMS), Duration.ofMinutes(10)),
                        new Departure(Utils.getZonedDateTime(18, 27), stations.get(ERailwayStation.DB), Duration.ofMinutes(2)),
                        new Departure(Utils.getZonedDateTime(18, 31), stations.get(ERailwayStation.DH)),
                        new Departure(Utils.getZonedDateTime(18, 35), stations.get(ERailwayStation.AMR))
                };
    }

    private Train[] getTrains() {
        return new Train[] {
                new Train("Utrecht CS-Amsterdam CS", 52.248962,5.0036328),
                new Train("Utrecht CS-Den Haag CS", 52.0666685,4.8700748),
                new Train("Utrecht CS-'s Hertogenbosch CS", 51.7903443,5.2662574),
                new Train("Utrecht CS-Amersfoort CS", 52.1469975,5.3434571)
        };
    }
}
