package nl.craftsmen.workshops.reactivemeetup.exercises.two;

import static nl.craftsmen.workshops.reactivemeetup.util.Utils.*;

import java.util.concurrent.TimeUnit;

import nl.craftsmen.workshops.reactivemeetup.domain.railway.GateCheckEvent;
import nl.craftsmen.workshops.reactivemeetup.util.RailwayStreams;
import rx.Observable;

public class Exercise003 {
	
	private static final long GATE_OCCUPY_TIME = 1500;

	public static void main(String[] args) {
		// With the introduction of the "OV chipkaart" in the Netherlands, every person that wishes to travel by train needs to
		// check in and check out with their "OV chipkaart". Many railway stations have adopted gates which (in the future) can
		// only be opened by either checking in or checking out. The gateCheckEvent$ stream below represents the checkins and 
		// checkouts for such a gate at a railway station.
		Observable<GateCheckEvent> gateCheckEvent$ = RailwayStreams.gateCheckEvent$();
		
		// ASSIGNMENT: Define the gateIsFree$ stream based on the provided gateCheckEvent$. This stream should emit boolean
		// values, where true indicates that the gate is free and false indicates that is occupied (being used by someone).
		//
		// Note that the stream should emit alternating values, i.e. two successive values may not be the same. For example,
		// "true, true, false" is not a valid output, while "true, false, true" is okay.
		//
		// A gate is considered to be free if in the last 1,5 seconds nobody has checked in or out. Initially the gate is
		// considered to be free, so your output should always start with "true".
		//
		// HINT: To specify the 1,5 second interval use the GATE_OCCUPY_TIME constant in combination with TimeUnit.MILLISECONDS.
		//
		// HINT: Solve this assignment using divide and conquer. First try to define two streams, one that tells whenever the
		// gate is occupied and another that tells when the gate is free. Next find a way to combine those streams to get the
		// desired output.
		
		Observable<Boolean> gateIsFree$ = Observable.merge(
			gateCheckEvent$.map((x) -> false),
			gateCheckEvent$.debounce(GATE_OCCUPY_TIME, TimeUnit.MILLISECONDS).map((x) -> true)
		).distinctUntilChanged().startWith(true);
		
		// When implemented correctly you should see the following output:
		// free, occupied, free, occupied, free, occupied, free, occupied, free
		
		gateIsFree$.subscribe((free) -> System.out.println(free ? "free" : "occupied"));
		
		waitForStreamToComplete(gateIsFree$);
	}
	
}
