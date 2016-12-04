package nl.craftsmen.workshops.reactivemeetup.exercises.two;

import static nl.craftsmen.workshops.reactivemeetup.util.Utils.*;

import nl.craftsmen.workshops.reactivemeetup.domain.railway.TrainMetrics;
import nl.craftsmen.workshops.reactivemeetup.util.RailwayStreams;

import rx.Observable;

public class Exercise03 {

	public static void main(String[] args) {
		
		// In this exercise you will work with a stream of (simulated) train sensor data. This data is wrapped in a class called
		// TrainMetrics, which has the following properties: 
		//  - trainId   Identifier of train for which the sensor data was recorded.
		//  - timestamp Time at which the sensor data was recorded, stored as an epoch timestamp in milliseconds.
		//  - position  Geographical coordinate that represents the location of the train at the time of the measurements.
		//
		// The trainMetrics$ stream below represents the data for a train that travels from one railway station to another. Although the
		// data is simulated realistic values are emitted. To make this data practical for use with this exercise time has been
		// artificially accelerated at speed of 40x. Nonetheless you can still treat this data is if it was a real-time sensor data stream
		// from a real train.
		Observable<TrainMetrics> trainMetrics$ = RailwayStreams.trainMetrics$();
		
		// ASSIGNMENT: Compute the velocity (in Km/h) of the train using the data from the trainMetrics$ stream. The velocity should be 
		// updated in real-time so your output should again be a stream, only this time it should emit average velocity values. Compute the
		// "real-time" velocity every time over a set of 10 subsequent TrainMetrics values emitted from the trainMetrics$ stream. Do this
		// once out of every 5 times a value gets emitted from the trainMetrics$ stream. Finally, determine the maximum velocity of the
		// train using the output from the velocity$ stream.
		//
		// HINT: You can compute the average velocity for a set of subsequent TrainMetrics values by using only the first and last value
		// from that set. The velocity is then computed by taking the difference between the position of the first and last element
		// divided by the time difference between those to values.
		//
		// HINT: The distance (in meters) between two TrainMetrics values x and y can be computed as follows:
		//  - x.getPosition().distanceTo(y.getPosition())
		//
		// HINT: Time difference (in seconds) between two TrainMetrics values x and y is computed in the following way:
		//  - Math.abs(x.getTimestamp() - y.getTimestamp()) / 1000.0
		//
		// HINT: To convert a velocity in meters per second (m/s) to kilometers per hour (Km/h) multiple the velocity with 3.6
		
		Observable<Double> velocity$ = unknown(); // ???
		
		// When implemented correctly you should find a maximum velocity of ~ 140 Km/h.
		velocity$.subscribe(System.out::println);
		
		waitForStreamToComplete(velocity$);
	}
	
}
