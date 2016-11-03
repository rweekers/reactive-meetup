package nl.craftsmen.workshops.reactivemeetup.util;

import nl.craftsmen.workshops.reactivemeetup.domain.railway.Departure;
import nl.craftsmen.workshops.reactivemeetup.domain.railway.ERailwayStation;
import nl.craftsmen.workshops.reactivemeetup.domain.railway.RailwayStation;
import rx.Observable;

import java.util.HashMap;
import java.util.Map;

import static nl.craftsmen.workshops.reactivemeetup.util.Utils.sample;

public class RailwayStreams {

    private static Map<ERailwayStation, RailwayStation> stations = new HashMap<>();

    public static void buildStations() {
        stations.put(ERailwayStation.AMS, new RailwayStation(ERailwayStation.AMS, "Amsterdam CS", 52.3791283, 4.8980833));
        stations.put(ERailwayStation.UTR, new RailwayStation(ERailwayStation.UTR, "Utrecht CS", 52.0893191, 5.1079804));
        stations.put(ERailwayStation.DH, new RailwayStation(ERailwayStation.DH, "Den Haag CS", 52.0809271, 4.3222312));
        stations.put(ERailwayStation.AMR, new RailwayStation(ERailwayStation.AMR, "Amersfoort CS", 52.1530195,5.3711025));
    }

    public static Observable<Departure> departure$() {
        return sample(Observable.from(getDepartures()), 200);
    }

    private static Departure[] getDepartures() {
        return new Departure[]
                {
                        new Departure(Utils.getZonedDateTime(16, 23), stations.get(ERailwayStation.AMS)),
                        new Departure(Utils.getZonedDateTime(16, 27), stations.get(ERailwayStation.UTR)),
                        new Departure(Utils.getZonedDateTime(16, 31), stations.get(ERailwayStation.DH)),
                        new Departure(Utils.getZonedDateTime(16, 35), stations.get(ERailwayStation.AMR)),
                        new Departure(Utils.getZonedDateTime(16, 38), stations.get(ERailwayStation.AMS)),
                        new Departure(Utils.getZonedDateTime(16, 42), stations.get(ERailwayStation.UTR)),
                        new Departure(Utils.getZonedDateTime(16, 46), stations.get(ERailwayStation.DH)),
                        new Departure(Utils.getZonedDateTime(16, 50), stations.get(ERailwayStation.AMR)),
                        new Departure(Utils.getZonedDateTime(16, 53), stations.get(ERailwayStation.AMS)),
                        new Departure(Utils.getZonedDateTime(16, 57), stations.get(ERailwayStation.UTR)),
                        new Departure(Utils.getZonedDateTime(17, 1), stations.get(ERailwayStation.DH)),
                        new Departure(Utils.getZonedDateTime(17, 5), stations.get(ERailwayStation.AMR)),
                        new Departure(Utils.getZonedDateTime(17, 8), stations.get(ERailwayStation.AMS)),
                        new Departure(Utils.getZonedDateTime(17, 12), stations.get(ERailwayStation.UTR)),
                        new Departure(Utils.getZonedDateTime(17, 16), stations.get(ERailwayStation.DH)),
                        new Departure(Utils.getZonedDateTime(17, 20), stations.get(ERailwayStation.AMR)),
                        new Departure(Utils.getZonedDateTime(17, 23), stations.get(ERailwayStation.AMS)),
                        new Departure(Utils.getZonedDateTime(17, 27), stations.get(ERailwayStation.UTR)),
                        new Departure(Utils.getZonedDateTime(17, 31), stations.get(ERailwayStation.DH)),
                        new Departure(Utils.getZonedDateTime(17, 35), stations.get(ERailwayStation.AMR)),
                        new Departure(Utils.getZonedDateTime(17, 38), stations.get(ERailwayStation.AMS)),
                        new Departure(Utils.getZonedDateTime(17, 42), stations.get(ERailwayStation.UTR)),
                        new Departure(Utils.getZonedDateTime(17, 46), stations.get(ERailwayStation.DH)),
                        new Departure(Utils.getZonedDateTime(17, 50), stations.get(ERailwayStation.AMR)),
                        new Departure(Utils.getZonedDateTime(17, 53), stations.get(ERailwayStation.AMS)),
                        new Departure(Utils.getZonedDateTime(17, 57), stations.get(ERailwayStation.UTR)),
                        new Departure(Utils.getZonedDateTime(18, 1), stations.get(ERailwayStation.DH)),
                        new Departure(Utils.getZonedDateTime(18, 5), stations.get(ERailwayStation.AMR)),
                        new Departure(Utils.getZonedDateTime(18, 8), stations.get(ERailwayStation.AMS)),
                        new Departure(Utils.getZonedDateTime(18, 12), stations.get(ERailwayStation.UTR)),
                        new Departure(Utils.getZonedDateTime(18, 16), stations.get(ERailwayStation.DH)),
                        new Departure(Utils.getZonedDateTime(18, 20), stations.get(ERailwayStation.AMR)),
                        new Departure(Utils.getZonedDateTime(18, 23), stations.get(ERailwayStation.AMS)),
                        new Departure(Utils.getZonedDateTime(18, 27), stations.get(ERailwayStation.UTR)),
                        new Departure(Utils.getZonedDateTime(18, 31), stations.get(ERailwayStation.DH)),
                        new Departure(Utils.getZonedDateTime(18, 35), stations.get(ERailwayStation.AMR))
                };
    }
}
