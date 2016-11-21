package nl.craftsmen.workshops.reactivemeetup.exercises.two;

import nl.craftsmen.workshops.reactivemeetup.domain.railway.ERailwayStation;
import nl.craftsmen.workshops.reactivemeetup.domain.railway.RailwayStation;
import nl.craftsmen.workshops.reactivemeetup.domain.railway.Train;
import nl.craftsmen.workshops.reactivemeetup.util.RailwayStreams;
import rx.Observable;

public class Exercise04 {

	public static void main(String[] args) {
		
		RailwayStation targetStation = RailwayStreams.getStation(ERailwayStation.AMS);
		
		Observable<Train> train$ = RailwayStreams.train$();
		
		Observable<Double> distanceToStation$ = train$
			.filter((train) -> train == null) // TODO filter based on train id.
			.map((train) -> 0.0)
			;
		
		

	}

}
