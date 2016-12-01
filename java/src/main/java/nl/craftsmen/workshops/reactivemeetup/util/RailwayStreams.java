package nl.craftsmen.workshops.reactivemeetup.util;

import nl.craftsmen.workshops.reactivemeetup.domain.railway.RailwayStation;
import nl.craftsmen.workshops.reactivemeetup.domain.railway.StationaryTrainSimulation;
import nl.craftsmen.workshops.reactivemeetup.domain.railway.CompositeTrainSimulation;
import nl.craftsmen.workshops.reactivemeetup.domain.railway.GateCheckEvent;
import nl.craftsmen.workshops.reactivemeetup.domain.railway.TrainJourneySimulation;
import nl.craftsmen.workshops.reactivemeetup.domain.railway.TrainSimulationParameters;
import nl.craftsmen.workshops.reactivemeetup.domain.railway.TrainMetrics;
import nl.craftsmen.workshops.reactivemeetup.domain.railway.TrainSimulation;
import nl.craftsmen.workshops.reactivemeetup.domain.railway.TravelCostMatrix;

import rx.Observable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static nl.craftsmen.workshops.reactivemeetup.util.Utils.sample;

public class RailwayStreams {

	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm:ss.SSS dd-MM-YYYY");

	private static Observable<GateCheckEvent> gateCheckEvent$;

	public static Observable<GateCheckEvent> gateCheckEvent$() {

		if (gateCheckEvent$ == null) {
			gateCheckEvent$ = Observable.merge(Arrays.asList(
				singleGateCheckEvent$(true, 233),
				singleGateCheckEvent$(true, 978),
				singleGateCheckEvent$(false, 1313),
				singleGateCheckEvent$(true, 2105),
				singleGateCheckEvent$(false, 3643),
				singleGateCheckEvent$(false, 4411),
				singleGateCheckEvent$(true, 5556),
				singleGateCheckEvent$(false, 8123),
				singleGateCheckEvent$(false, 9722),
				singleGateCheckEvent$(true, 10880)
			));
		}

		return gateCheckEvent$;
	}

	private static Observable<GateCheckEvent> singleGateCheckEvent$(boolean isCheckin, long delay) {
		return Observable.from(Arrays.asList(
			new GateCheckEvent(isCheckin, System.currentTimeMillis() + delay, RailwayStation.AMR))
		).delay(delay, TimeUnit.MILLISECONDS);
	}

	public static Observable<GateCheckEvent> personalCheckinsCheckouts$() {
		return sample(Observable.from(Arrays.asList(
			new GateCheckEvent(true, parseDate("08:04:11.345 16-12-2016"), RailwayStation.UTR),
			new GateCheckEvent(false, parseDate("08:41:03.409 16-12-2016"), RailwayStation.AMS),
			new GateCheckEvent(true, parseDate("17:44:56.122 16-12-2016"), RailwayStation.AMS),
			new GateCheckEvent(false, parseDate("18:49:04.123 16-12-2016"), RailwayStation.DH),
			new GateCheckEvent(true, parseDate("22:15:44.616 16-12-2016"), RailwayStation.DH),
			new GateCheckEvent(true, parseDate("08:03:54.883 17-12-2016"), RailwayStation.UTR),
			new GateCheckEvent(false, parseDate("08:39:21.512 17-12-2016"), RailwayStation.AMS))),
		500);
	}

	public static TravelCostMatrix travelCostMatrix() {
		return TravelCostMatrix.builder()
			.define(RailwayStation.UTR, RailwayStation.AMS, 7.50)
			.define(RailwayStation.UTR, RailwayStation.DH, 11.00)
			.define(RailwayStation.DH, RailwayStation.AMS, 11.50)
			.build();
	}

	public static Observable<TrainMetrics> trainMetrics$() {

		TrainSimulationParameters simulationParameters = new TrainSimulationParameters()
			.setTickFrequency(100)
			.setMaxVelocity(140 / 3.6)
			.setAcceleration(2 / 3.6)
			.setTrainId("1042")
			.setTimeDilation(40.0);

		TrainSimulation simulation = new CompositeTrainSimulation(
			new StationaryTrainSimulation(RailwayStation.AMR.getLocation(), 60.0),
			new TrainJourneySimulation(RailwayStation.AMR.getLocation(), RailwayStation.UTR.getLocation()),
			new StationaryTrainSimulation(RailwayStation.UTR.getLocation(), 60.0)
		);
		
		return simulation.trainMetrics$(simulationParameters, System.currentTimeMillis());
	}

	private static long parseDate(String dateString) {
		try {
			return DATE_FORMAT.parse(dateString).getTime();
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

}
