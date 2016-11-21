package nl.craftsmen.workshops.reactivemeetup.exercises.two;

import static nl.craftsmen.workshops.reactivemeetup.util.Utils.waitForStreamToComplete;

import nl.craftsmen.workshops.reactivemeetup.domain.railway.GateCheckEvent;
import nl.craftsmen.workshops.reactivemeetup.util.RailwayStreams;
import rx.Observable;

public class Exercise06 {

	public static void main(String[] args) {
		
		// ASSIGNMENT:
		Observable<GateCheckEvent> gateCheckEvent$ = RailwayStreams.personalCheckinsCheckouts$();
		
		gateCheckEvent$.subscribe(System.out::println);
		
		waitForStreamToComplete(gateCheckEvent$);
		
	}
	
}
