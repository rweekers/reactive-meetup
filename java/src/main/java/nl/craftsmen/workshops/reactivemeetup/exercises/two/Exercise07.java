package nl.craftsmen.workshops.reactivemeetup.exercises.two;

import static nl.craftsmen.workshops.reactivemeetup.util.Utils.waitForStreamToComplete;

import nl.craftsmen.workshops.reactivemeetup.domain.railway.TrainMetrics;
import nl.craftsmen.workshops.reactivemeetup.util.RailwayStreams;
import rx.Observable;

public class Exercise07 {

	public static void main(String[] args) {
		
		Observable<TrainMetrics> trainMetrics$ = RailwayStreams.trainMetrics$();
		
		trainMetrics$.subscribe(System.out::println);
		
		waitForStreamToComplete(trainMetrics$);
	}
	
}
