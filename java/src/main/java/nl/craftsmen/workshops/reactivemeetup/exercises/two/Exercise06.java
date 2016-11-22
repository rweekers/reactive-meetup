package nl.craftsmen.workshops.reactivemeetup.exercises.two;

import static nl.craftsmen.workshops.reactivemeetup.util.Utils.waitForStreamToComplete;

import nl.craftsmen.workshops.reactivemeetup.domain.railway.GateCheckEvent;
import nl.craftsmen.workshops.reactivemeetup.domain.railway.TravelCostMatrix;
import nl.craftsmen.workshops.reactivemeetup.util.RailwayStreams;
import rx.Observable;

public class Exercise06 {
	
	private static final double NO_CHECKOUT_COST = 20.0;

	public static void main(String[] args) {
		
		TravelCostMatrix costMatrix = RailwayStreams.travelCostMatrix();
		
		// ASSIGNMENT:
		Observable<GateCheckEvent> gateCheckEvent$ = RailwayStreams.personalCheckinsCheckouts$();
		
		Observable<Double> travelCost$ = Observable
			.zip(gateCheckEvent$, gateCheckEvent$.skip(1), (a, b) -> {
				if (a.isCheckIn() && b.isCheckOut()) {
					return costMatrix.getTravelCost(a.getRailwayStation(), b.getRailwayStation());
				} else if (a.isCheckIn() && b.isCheckIn()) {
					return NO_CHECKOUT_COST;
				}
				return 0.0;
			})
			.filter((cost) -> cost > 0)
			.scan((a, b) -> a + b);
		
		travelCost$.subscribe(System.out::println);
		
		waitForStreamToComplete(travelCost$);
		
	}
	
}
