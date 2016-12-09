package nl.craftsmen.workshops.reactivemeetup.exercises.two;

import static nl.craftsmen.workshops.reactivemeetup.util.Utils.*;

import java.util.Optional;

import nl.craftsmen.workshops.reactivemeetup.domain.railway.MotionType;
import nl.craftsmen.workshops.reactivemeetup.domain.railway.RailwayStation;
import nl.craftsmen.workshops.reactivemeetup.domain.railway.TrainMovementAction;
import nl.craftsmen.workshops.reactivemeetup.domain.railway.TrainMetrics;
import nl.craftsmen.workshops.reactivemeetup.util.RailwayStreams;
import rx.Observable;

public class Exercise04 {

	public static void main(String[] args) {
		
		// This is a bonus exercise that is bit more involved. Given are two streams. The first is trainMetrics$ stream, which is the same
		// stream you have already used in exercise 2.3. The second stream is the velocity$ stream that is derived from the trainMetrics$
		// stream (which is actually the solution of exercise 2.3). This exercise is split into three smaller exercises, which all need to
		// be solved to accomplish the final goal: showing from which railway station the train departs and at which station it arrives.
		
		Observable<TrainMetrics> trainMetrics$ = RailwayStreams.trainMetrics$();
		
		Observable<Double> velocity$ = RailwayStreams.velocity$(trainMetrics$);
		
		// ASSIGNMENT: Using the velocity$ stream, define a new motion$ stream that describes the motion of the train using one of the 
		// the following constants:
		//  - MotionType.ACCELERATING    The train is accelerating (its velocity is increasing).
		//  - MotionType.DECELERATING    The train is decelerating (its velocity is decreasing).
		//  - MotionType.CONSTANT_SPEED  The train is moving at constant speed.
		//  - MotionType.STATIONARY      The train is not moving at all.
		//
		// HINT: First try to get a stream that emits the acceleration for two subsequent emits of the velocity$ stream. 
		//
		// HINT: Use an epsilon value of 0.1 to determine if the train is accelerating or decelerating, i.e. the train is not accelerating /
		// decelerating if the value of the acceleration is greater than -0.1 and less than 0.1.
		//
		// HINT: You cannot use the stream of acceleration values alone to differentiate between a train that is stationary and a train
		// moving at constant speed, since the acceleration is 0 in both cases.
		//
		// HINT: Uncomment the "logAndWaitFor(motion$)" line below to test your stream.
		
		Observable<MotionType> motion$ = velocity$
			.buffer(2, 1)
			.filter((velocities) -> velocities.size() > 1)
			.map((velocities) -> velocities.get(1) - velocities.get(0))
			.zipWith(velocity$.skip(1), (acceleration, velocity) -> {
				if (Math.abs(acceleration) < 0.1) {
					return velocity < 0.1 ? MotionType.STATIONARY : MotionType.CONSTANT_SPEED;
				}
				return acceleration < 0 ? MotionType.DECELERATING : MotionType.ACCELERATING;
			})
			.distinctUntilChanged();
		
		//logAndWaitFor(motion$);
		
		// ASSIGNMENT: With the motion$ stream, defined above, it is possible to detect whether a train is departing or arriving. Use the
		// following constants and conditions to define a trainAction$ stream that indicates when a train is departing or arriving at a
		// railway station:
		//  - TrainMovementAction.DEPARTING  If the motion changes from STATIONARY to ACCELERATING.
		//  - TrainMovementAction.ARRIVING   If the motion changes from DECELERATING to STATIONARY.
		// For other motion transitions that do not match one the condition above the trainAction$ stream should not emit any value.
		//
		// HINT: We'll assume trains do not encounter any malfunctions or obstructions on the railway network and as such we can safely
		// assume that whenever a train starts decelerating it will always stop at a railway station.
		//
		// HINT: Use Optional.empty() or null in case irrelevant motion transitions are detected and filter them out in a second step.
		//
		// HINT: Uncomment the "logAndWaitFor(trainAction$)" line below to test your stream.
		
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
		
		//logAndWaitFor(trainAction$);
		
		// ASSIGNMENT: Using the trainAction$ and trainMetrics$ streams, define a new message$ stream that emits a message whenever a
		// train arrives or departs at a railway station including the name of that station. The output should be something like this:
		//  - Departing from stationA
		//  - Arriving at stationB
		//
		// HINT: For this exercise you have to use an operator that you (probably) have not used before in the other exercises.
		//
		// HINT: Use RailwayStation.closestTo(position) to find the railway station closest to the specified LatLong coordinate.
		//
		// HINT: Uncomment the "logAndWaitFor(messages$)" line below to test your stream.
		
		Observable<String> messages$ = trainAction$
			.withLatestFrom(trainMetrics$, (action, trainMetrics) -> {
				RailwayStation station = RailwayStation.closestTo(trainMetrics.getPosition());
				
				if (TrainMovementAction.DEPARTING.equals(action)) {
					return "Departing from " + station;
				} else {
					return "Arriving at " + station;
				}
			});
		

		logAndWaitFor(messages$);
	}
	
	private static void logAndWaitFor(Observable<?> stream$) {
		stream$.subscribe(System.out::println);		
		waitForStreamToComplete(stream$);		
	}

}
