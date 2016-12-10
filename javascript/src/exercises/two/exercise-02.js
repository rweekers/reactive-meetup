const Rx = require('rxjs/Rx');

const NO_CHECKOUT_COST = 20.0;

// Each check-in and check-out event is associated with an "OV chipkaart". The gateCheckEvent$ stream below represents the stream
// of check-in and check-out events for a single "OV chipkaart". For every check-in/out the railway station is recorded. This
// information can be used to compute the travel cost for a journey. The price for journey from railway station A to B can be
// determined through the costMatrix object above. It might be possible that someone forgets to check in or check out. This
// situation can be detected by two successive check-in events. In that case the travel cost is 20 euros.

const personalCheckinsCheckouts$ = require('../../util/railway-streams.js').personalCheckinsCheckouts$;

const costMatrix = require('../../util/railway-streams.js').travelCostMatrix;

// ASSIGNMENT: Given the gateCheckEvent$ of check-in and check-out events compute the cumulative travel cost. The resulting stream
// should emit the total travel cost for every new journey. For this exercise you are not allowed to use the buffer operator.
//
// HINT: To solve this exercise you will first need to find a method to obtain a stream of two successive gate check-in/out events.
//
// HINT: For each pair (a, b) of GateCheckEvents use the following rules to determine the travel cost:
//  - a.isCheckIn() && b.isCheckOut()  ->  costMatrix.getTravelCost(a.getRailwayStation(), b.getRailwayStation())
//  - a.isCheckIn() && b.isCheckIn()   ->  NO_CHECKOUT_COST
//  - otherwise                        ->  Nothing. You can represent this using one of the following: undefined, null or 0

const travelCost$ = personalCheckinsCheckouts$.zip(personalCheckinsCheckouts$.skip(1), 
	(a, b) => {
		if (a.isCheckIn() && b.isCheckOut()) {
			return costMatrix.getTravelCost(a.getRailwayStation(), b.getRailwayStation());
		} else if (a.isCheckIn() && b.isCheckIn()) {
			return NO_CHECKOUT_COST;
		}
		return 0.0;
	})
	.filter((cost) => cost > 0)
	.scan((a, b) => a + b);

// When implemented correctly you should see the following output:
// 7.5, 19.0, 39.0, 46.5
		
travelCost$.subscribe(console.log);
