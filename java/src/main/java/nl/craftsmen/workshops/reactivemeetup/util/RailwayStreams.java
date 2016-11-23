package nl.craftsmen.workshops.reactivemeetup.util;

import nl.craftsmen.workshops.reactivemeetup.domain.railway.Departure;
import nl.craftsmen.workshops.reactivemeetup.domain.railway.RailwayStation;
import nl.craftsmen.workshops.reactivemeetup.domain.railway.GateCheckEvent;
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
		return Observable.from(Arrays.asList(new GateCheckEvent(isCheckin, System.currentTimeMillis() + delay, RailwayStation.AMR)))
			.delay(delay, TimeUnit.MILLISECONDS);
	}
	
	public static Observable<GateCheckEvent> personalCheckinsCheckouts$() {
		return sample(Observable.from(Arrays.asList(
			new GateCheckEvent(true,  parseDate("08:04:11.345 16-12-2016"), RailwayStation.UTR),
			new GateCheckEvent(false, parseDate("08:41:03.409 16-12-2016"), RailwayStation.AMS),
			new GateCheckEvent(true,  parseDate("17:44:56.122 16-12-2016"), RailwayStation.AMS),
			new GateCheckEvent(false, parseDate("18:49:04.123 16-12-2016"), RailwayStation.DH),
			new GateCheckEvent(true,  parseDate("22:15:44.616 16-12-2016"), RailwayStation.DH),
			new GateCheckEvent(true,  parseDate("08:03:54.883 17-12-2016"), RailwayStation.UTR),
			new GateCheckEvent(false, parseDate("08:39:21.512 17-12-2016"), RailwayStation.AMS)
		)), 500);
	}
	
	public static TravelCostMatrix travelCostMatrix() {
		return TravelCostMatrix.builder()
			.define(RailwayStation.UTR, RailwayStation.AMS,  7.50)
			.define(RailwayStation.UTR, RailwayStation.DH,  11.00)
			.define(RailwayStation.DH,  RailwayStation.AMS, 11.50)
			.build();
	}
	
	public static Observable<TrainMetrics> trainMetrics$() {
		
		RailwaySystemSimulation railwaySystem = new RailwaySystemSimulation(null);
		
		return railwaySystem.trainMetrics$();
	}
	
	private static long parseDate(String dateString) {
		try {
			return DATE_FORMAT.parse(dateString).getTime();
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
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
                        new Departure(Utils.getZonedDateTime(16, 23), RailwayStation.AMS),
                        new Departure(Utils.getZonedDateTime(16, 27), RailwayStation.DB),
                        new Departure(Utils.getZonedDateTime(16, 31), RailwayStation.DH, Duration.ofMinutes(3)),
                        new Departure(Utils.getZonedDateTime(16, 35), RailwayStation.AMR),
                        new Departure(Utils.getZonedDateTime(16, 38), RailwayStation.AMS, Duration.ofMinutes(12)),
                        new Departure(Utils.getZonedDateTime(16, 42), RailwayStation.DB),
                        new Departure(Utils.getZonedDateTime(16, 46), RailwayStation.DH),
                        new Departure(Utils.getZonedDateTime(16, 50), RailwayStation.AMR, Duration.ofMinutes(6)),
                        new Departure(Utils.getZonedDateTime(16, 53), RailwayStation.AMS, Duration.ofMinutes(25)),
                        new Departure(Utils.getZonedDateTime(16, 57), RailwayStation.DB, Duration.ofMinutes(25)),
                        new Departure(Utils.getZonedDateTime(17, 1), RailwayStation.DH),
                        new Departure(Utils.getZonedDateTime(17, 5), RailwayStation.AMR, Duration.ofMinutes(2)),
                        new Departure(Utils.getZonedDateTime(17, 8), RailwayStation.AMS),
                        new Departure(Utils.getZonedDateTime(17, 12), RailwayStation.DB, Duration.ofMinutes(15)),
                        new Departure(Utils.getZonedDateTime(17, 16), RailwayStation.DH),
                        new Departure(Utils.getZonedDateTime(17, 20), RailwayStation.AMR),
                        new Departure(Utils.getZonedDateTime(17, 23), RailwayStation.AMS, Duration.ofMinutes(3)),
                        new Departure(Utils.getZonedDateTime(17, 27), RailwayStation.DB, Duration.ofMinutes(30)),
                        new Departure(Utils.getZonedDateTime(17, 31), RailwayStation.DH),
                        new Departure(Utils.getZonedDateTime(17, 35), RailwayStation.AMR, Duration.ofMinutes(1)),
                        new Departure(Utils.getZonedDateTime(17, 38), RailwayStation.AMS),
                        new Departure(Utils.getZonedDateTime(17, 42), RailwayStation.DB),
                        new Departure(Utils.getZonedDateTime(17, 46), RailwayStation.DH, Duration.ofMinutes(3)),
                        new Departure(Utils.getZonedDateTime(17, 50), RailwayStation.AMR),
                        new Departure(Utils.getZonedDateTime(17, 53), RailwayStation.AMS),
                        new Departure(Utils.getZonedDateTime(17, 57), RailwayStation.DB),
                        new Departure(Utils.getZonedDateTime(18, 1), RailwayStation.DH, Duration.ofMinutes(10)),
                        new Departure(Utils.getZonedDateTime(18, 5), RailwayStation.AMR),
                        new Departure(Utils.getZonedDateTime(18, 8), RailwayStation.AMS),
                        new Departure(Utils.getZonedDateTime(18, 12), RailwayStation.DB, Duration.ofMinutes(5)),
                        new Departure(Utils.getZonedDateTime(18, 16), RailwayStation.DH),
                        new Departure(Utils.getZonedDateTime(18, 20), RailwayStation.AMR),
                        new Departure(Utils.getZonedDateTime(18, 23), RailwayStation.AMS, Duration.ofMinutes(10)),
                        new Departure(Utils.getZonedDateTime(18, 27), RailwayStation.DB, Duration.ofMinutes(2)),
                        new Departure(Utils.getZonedDateTime(18, 31), RailwayStation.DH),
                        new Departure(Utils.getZonedDateTime(18, 35), RailwayStation.AMR)
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
