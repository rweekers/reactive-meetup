package nl.craftsmen.workshops.reactivemeetup.util;

import nl.craftsmen.workshops.reactivemeetup.domain.railway.Departure;
import nl.craftsmen.workshops.reactivemeetup.domain.railway.ERailwayStation;
import nl.craftsmen.workshops.reactivemeetup.domain.railway.GateCheckEvent;
import nl.craftsmen.workshops.reactivemeetup.domain.railway.RailwayNetwork;
import nl.craftsmen.workshops.reactivemeetup.domain.railway.RailwayStation;
import nl.craftsmen.workshops.reactivemeetup.domain.railway.RailwaySystemSimulation;
import nl.craftsmen.workshops.reactivemeetup.domain.railway.Train;
import nl.craftsmen.workshops.reactivemeetup.domain.railway.TrainMetrics;
import nl.craftsmen.workshops.reactivemeetup.domain.railway.TravelCostMatrix;
import rx.Observable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static nl.craftsmen.workshops.reactivemeetup.util.Utils.sample;

public class RailwayStreams {
	
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm:ss.SSS dd-MM-YYYY");
	
	private static Observable<GateCheckEvent> gateCheckEvent$;
	
	public static Observable<GateCheckEvent> gateCheckEvent$() {
		
		if (gateCheckEvent$ == null) {
			gateCheckEvent$ = Observable.merge(Arrays.asList(
				singleGateCheckEvent$(true,    233),
				singleGateCheckEvent$(true,    978),
				singleGateCheckEvent$(false,  1313),
				singleGateCheckEvent$(true,   2105),
				singleGateCheckEvent$(false,  3643),
				singleGateCheckEvent$(false,  4411),
				singleGateCheckEvent$(true,   5556),
				singleGateCheckEvent$(false,  8123),
				singleGateCheckEvent$(false,  9722),
				singleGateCheckEvent$(true,  10880)
			));
		}
		
		return gateCheckEvent$;
	}
	
	private static Observable<GateCheckEvent> singleGateCheckEvent$(boolean isCheckin, long delay) {
		return Observable.from(Arrays.asList(new GateCheckEvent(isCheckin, System.currentTimeMillis() + delay, ERailwayStation.AMR)))
			.delay(delay, TimeUnit.MILLISECONDS);
	}
	
	public static Observable<GateCheckEvent> personalCheckinsCheckouts$() {
		return sample(Observable.from(Arrays.asList(
			new GateCheckEvent(true,  parseDate("08:04:11.345 16-12-2016"), ERailwayStation.UTR),
			new GateCheckEvent(false, parseDate("08:41:03.409 16-12-2016"), ERailwayStation.AMS),
			new GateCheckEvent(true,  parseDate("17:44:56.122 16-12-2016"), ERailwayStation.AMS),
			new GateCheckEvent(false, parseDate("18:49:04.123 16-12-2016"), ERailwayStation.DH),
			new GateCheckEvent(true,  parseDate("22:15:44.616 16-12-2016"), ERailwayStation.DH),
			new GateCheckEvent(true,  parseDate("08:03:54.883 17-12-2016"), ERailwayStation.UTR),
			new GateCheckEvent(false, parseDate("08:39:21.512 17-12-2016"), ERailwayStation.AMS)
		)), 500);
	}
	
	public static TravelCostMatrix travelCostMatrix() {
		return TravelCostMatrix.builder()
			.define(ERailwayStation.UTR, ERailwayStation.AMS,  7.50)
			.define(ERailwayStation.UTR, ERailwayStation.DH,  11.00)
			.define(ERailwayStation.DH,  ERailwayStation.AMS, 11.50)
			.build();
	}
	
	public static Observable<TrainMetrics> trainMetrics$() {
		
		RailwaySystemSimulation railwaySystem = new RailwaySystemSimulation();
		
		return railwaySystem.trainMetrics$();
	}
	
	private static long parseDate(String dateString) {
		try {
			return DATE_FORMAT.parse(dateString).getTime();
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

    private static Map<ERailwayStation, RailwayStation> stations = new MapBuilder<ERailwayStation, RailwayStation>()
        .set(ERailwayStation.AMS, new RailwayStation(ERailwayStation.AMS, "Amsterdam CS", 52.3791283, 4.8980833))
        .set(ERailwayStation.DB, new RailwayStation(ERailwayStation.DB, "'s Hertogenbosch CS", 51.6905476,5.2913696))
        .set(ERailwayStation.DH, new RailwayStation(ERailwayStation.DH, "Den Haag CS", 52.0809271, 4.3222312))
        .set(ERailwayStation.AMR, new RailwayStation(ERailwayStation.AMR, "Amersfoort CS", 52.1530195,5.3711025))
        .build();
    
    public static RailwayStation getStation(ERailwayStation stationId) {
    	return stations.get(stationId);
    }

    public static Observable<Departure> departure$() {
        return sample(Observable.from(getDepartures()), 200);
    }

    public static Observable<Train> train$() {
        return sample(Observable.from(getTrains()), 200);
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

    private static Train[] getTrains() {
        return new Train[] {
                new Train("Train A", 52.248962,5.0036328), // Utrecht - Amsterdam
                new Train("Train A", 52.0666685,4.8700748), // Utrecht - Den Haag
                new Train("Train A", 51.7903443,5.2662574), // Utrecht - 's Hertogenbosch
                new Train("Train A", 52.1469975,5.3434571) // Utrecht - Amersfoort
        };
    }
}
