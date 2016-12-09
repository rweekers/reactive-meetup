package nl.craftsmen.workshops.reactivemeetup.exercises.one;

import static nl.craftsmen.workshops.reactivemeetup.util.Utils.*;

import nl.craftsmen.workshops.reactivemeetup.util.ExampleStreams;
import rx.Observable;

public class Exercise02 {

	public static void main(String[] args) throws InterruptedException {
		Observable<Integer> number$ = ExampleStreams.numbersWithError$();
		
		// ASSIGNMENT: Subscribe to the number$ stream and log each event (next, error, complete) to the console.
		
		number$.subscribe(
			(value) -> System.out.println("next: " + value),
			(error) -> System.out.println("error: " + error),
			()      -> System.out.println("completed")
		);
		
		// If you have completed the assignment successfully you should see the an output that is similar to:
		//
		// next: 1
		// next: 9
		// next: 4
		// next: 7
		// error: java.lang.RuntimeException: uh oh! an error!
		// 
		// This also illustrates that if a stream emits an error event, it will no longer emit a complete event, so a stream
		// either terminates with an error event or a complete event.
		
		waitForStreamToComplete(number$);
	}

}
