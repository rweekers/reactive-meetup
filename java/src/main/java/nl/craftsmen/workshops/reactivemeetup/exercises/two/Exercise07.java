package nl.craftsmen.workshops.reactivemeetup.exercises.two;

import static nl.craftsmen.workshops.reactivemeetup.util.Utils.waitForStreamToComplete;

import nl.craftsmen.workshops.reactivemeetup.domain.railway.TrainMetrics;
import nl.craftsmen.workshops.reactivemeetup.util.RailwayStreams;

import rx.Observable;

public class Exercise07 {

	public static void main(String[] args) {
		
		Observable<TrainMetrics> trainMetrics$ = RailwayStreams.trainMetrics$();
		
		Observable<Double> averageVelocity$ = trainMetrics$
			.buffer(10, 5)
			.filter((measurements) -> measurements.size() > 1)
			.map((measurements) -> {
				TrainMetrics first = measurements.get(0);
				TrainMetrics last = measurements.get(measurements.size() - 1);
				long elapsedTime = last.getTimestamp() - first.getTimestamp();
				double distance = last.getPosition().distanceTo(first.getPosition());
				double velocity = distance * 1000 / elapsedTime;
				return velocity;
			})
			.map((velocity) -> velocity * 3.6);
		
		averageVelocity$.subscribe(System.out::println);
		
		waitForStreamToComplete(averageVelocity$);
	}
	
}
