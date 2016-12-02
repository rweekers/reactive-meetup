package nl.craftsmen.workshops.reactivemeetup.exercises.two;

import static nl.craftsmen.workshops.reactivemeetup.util.Utils.waitForStreamToComplete;

import java.util.Optional;

import nl.craftsmen.workshops.reactivemeetup.domain.railway.MotionType;
import nl.craftsmen.workshops.reactivemeetup.domain.railway.RailwayStation;
import nl.craftsmen.workshops.reactivemeetup.domain.railway.TrainMovementAction;
import nl.craftsmen.workshops.reactivemeetup.domain.railway.TrainMetrics;
import nl.craftsmen.workshops.reactivemeetup.util.RailwayStreams;
import rx.Observable;

public class Exercise04 {

	public static void main(String[] args) {
		
		Observable<TrainMetrics> trainMetrics$ = RailwayStreams.trainMetrics$();
		
		Observable<Double> velocity$ = RailwayStreams.velocity$(trainMetrics$);
		
		Observable<MotionType> motion$ = velocity$
			.buffer(2, 1)
			.filter((velocities) -> velocities.size() > 1)
			.map((velocities) -> velocities.get(1) - velocities.get(0))
			.zipWith(velocity$.skip(1), (acceleration, velocity) -> {
				if (Math.abs(acceleration) < 0.1) {
					return velocity < 0.1 ? MotionType.STATIONARY : MotionType.MOVING_STEADY;
				}
				return acceleration < 0 ? MotionType.DECELERATING : MotionType.ACCELERATING;
			})
			.distinctUntilChanged();
		
		Observable<TrainMovementAction> trainAction$ = motion$
			.buffer(2, 1)
			.filter((motions) -> motions.size() > 1)
			.map((motions) -> {
				MotionType a = motions.get(0);
				MotionType b = motions.get(1);
				
				Optional<TrainMovementAction> result;
				if (MotionType.STATIONARY.equals(a) && MotionType.ACCELERATING.equals(b)) {
					result = Optional.of(TrainMovementAction.DEPARTING);
				} else if (MotionType.DECELERATING.equals(a) && MotionType.STATIONARY.equals(b)) {
					result = Optional.of(TrainMovementAction.ARRIVING);
				} else {
					result = Optional.empty();
				}
				
				return result;
			})
			.filter(Optional::isPresent)
			.map(Optional::get);
		
		Observable<String> messages$ = trainAction$
			.withLatestFrom(trainMetrics$, (action, trainMetrics) -> {
				RailwayStation station = RailwayStation.closestTo(trainMetrics.getPosition());
				
				if (TrainMovementAction.DEPARTING.equals(action)) {
					return "Departing from " + station;
				} else {
					return "Arriving at " + station;
				}
			});
		
		messages$.subscribe(System.out::println);
		
		waitForStreamToComplete(messages$);
	}

}
