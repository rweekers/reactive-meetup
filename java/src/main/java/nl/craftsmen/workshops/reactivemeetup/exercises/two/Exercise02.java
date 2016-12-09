package nl.craftsmen.workshops.reactivemeetup.exercises.two;

import static nl.craftsmen.workshops.reactivemeetup.util.Utils.*;

import nl.craftsmen.workshops.reactivemeetup.domain.railway.GateCheckEvent;
import nl.craftsmen.workshops.reactivemeetup.domain.railway.TravelCostMatrix;
import nl.craftsmen.workshops.reactivemeetup.util.RailwayStreams;
import rx.Observable;

public class Exercise02 {
	
	private static final double NO_CHECKOUT_COST = 20.0;

	public static void main(String[] args) {
		
		TravelCostMatrix costMatrix = RailwayStreams.travelCostMatrix();
		
		// Each check-in and check-out event is associated with an "OV chipkaart". The gateCheckEvent$ stream below represents the stream
		// of check-in and check-out events for a single "OV chipkaart". For every check-in/out the railway station is recorded. This
		// information can be used to compute the travel cost for a journey. The price for journey from railway station A to B can be
		// determined through the costMatrix object above. It might be possible that someone forgets to check in or check out. This
		// situation can be detected by two successive check-in events. In that case the travel cost is 20 euros.
		Observable<GateCheckEvent> gateCheckEvent$ = RailwayStreams.personalCheckinsCheckouts$();
		
		// ASSIGNMENT: Given the gateCheckEvent$ of check-in and check-out events compute the cumulative travel cost. The resulting stream
		// should emit the total travel cost for every new journey. Often it is possible to a use different set of operators to define 
		// streams. This is also the case for this assignment. For example you can solve this assignment using the buffer operator. However,
		// for this exercise we would like you to use another operator, so you are not allowed to use the buffer operator for this exercise.
		//
		// HINT: To solve this exercise you will first need to find a method to obtain a stream of two successive gate check-in/out events.
		//
		// HINT: For each pair (a, b) of GateCheckEvents use the following rules to determine the travel cost:
		//  - a.isCheckIn() && b.isCheckOut()  ->  costMatrix.getTravelCost(a.getRailwayStation(), b.getRailwayStation())
		//  - a.isCheckIn() && b.isCheckIn()   ->  NO_CHECKOUT_COST
		//  - otherwise                        ->  Nothing. You can represent this using one of the following: Optional.empty(), null or 0.0
		
		Observable<Double> travelCost$ = gateCheckEvent$
			.zipWith(gateCheckEvent$.skip(1), (a, b) -> {
				if (a.isCheckIn() && b.isCheckOut()) {
					return costMatrix.getTravelCost(a.getRailwayStation(), b.getRailwayStation());
				} else if (a.isCheckIn() && b.isCheckIn()) {
					return NO_CHECKOUT_COST;
				}
				return 0.0;
			})
			.filter((cost) -> cost > 0)
			.scan((a, b) -> a + b);
		
		// When implemented correctly you should see the following output:
		// 7.5, 19.0, 39.0, 46.5
		
		travelCost$.subscribe(System.out::println);
		
		waitForStreamToComplete(travelCost$);
		
	}
	
}
